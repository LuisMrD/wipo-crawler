package dev.luisoliveira.PatentCrawlerApp.services;

import dev.luisoliveira.PatentCrawlerApp.entities.Patent;
import dev.luisoliveira.PatentCrawlerApp.repositories.PatentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatentService {

    @Autowired
    PatentRepository patentRepository;

    public Patent fetchPatentData(String processNumber) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        try {
            String url = "https://patentscope.wipo.int/search/pt/detail.jsf?docId=" + processNumber + "&redirectedID=true";
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".ps-biblio-field--label")
            ));

            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);

            return extractPatentFromDoc(doc);

        } finally {
            driver.quit();
        }
    }

    public Patent extractPatentFromDoc(Document doc) {
        Patent patent = new Patent();

        Element[] elements = new Element[5];
        elements[0] = doc.selectFirst(".ps-biblio-field--label:contains(Número da publicação)");
        elements[1] = doc.selectFirst(".ps-biblio-field--label:contains(№ do pedido internacional)");
        elements[2] = doc.selectFirst(".ps-biblio-field--label:contains(Data de publicação)");
        elements[3] = doc.selectFirst(".ps-biblio-field--label:contains(Requerentes)");
        elements[4] = doc.selectFirst(".ps-biblio-field--label:contains(Título)");

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) continue; // Garante que não vai dar NullPointer

            Element divParent = elements[i].parent();
            Element valueSpan = divParent.selectFirst(".ps-field--value.ps-biblio-field--value");

            if (valueSpan == null) continue;

            switch (i) {
                case 0 -> patent.setPublicationNumber(valueSpan.text().trim());
                case 1 -> patent.setApplicationNumber(valueSpan.text().trim());
                case 2 -> patent.setPublicationDate(valueSpan.text().trim());
                case 3 -> patent.setApplicant(valueSpan.text().trim());
                case 4 -> {
                    Element valueContainer = elements[4].nextElementSibling();
                    if (valueContainer != null) {
                        Element englishTitle = valueContainer.selectFirst("div.patent-title div:has(b:contains(EN)) span.needTranslation-title");
                        if (englishTitle != null) {
                            patent.setTitle(englishTitle.text().trim());
                        }
                    }
                }
            }
        }

        return patent;
    }

    @Transactional
    public Patent savePatent(Patent patent) {
        return patentRepository.save(patent);
    }

    public List<Patent> findPatents(String publicationNumber, String applicant) {
        if ((publicationNumber == null || publicationNumber.isEmpty()) &&
                (applicant == null || applicant.isEmpty())) {
            return patentRepository.findAll();
        }

        if (publicationNumber == null || publicationNumber.isEmpty()) {
            return patentRepository.findByApplicantContaining(applicant);
        }

        if (applicant == null || applicant.isEmpty()) {
            return patentRepository.findAllByPublicationNumber(publicationNumber);
        }

        return patentRepository.findByPublicationNumberContainingAndApplicantContaining(publicationNumber, applicant);
    }

}
