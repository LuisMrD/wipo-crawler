package dev.luisoliveira.PatentCrawlerApp;

import dev.luisoliveira.PatentCrawlerApp.entities.Patent;
import dev.luisoliveira.PatentCrawlerApp.services.PatentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatentServiceTest {

    @InjectMocks
    private PatentService patentService;

    @Test
    void shouldFetchPatentDataCorrectly() throws IOException {
        String processNumber = "WO2002008676";

        String urlString = "https://patentscope.wipo.int/search/pt/detail.jsf?docId=" + processNumber + "&redirectedID=true";

        String mockHtmlResponse = "<html>" +
                "<body>" +
                "<div class='ps-biblio-field--label'>Número da publicação</div>" +
                "<div class='ps-field--value ps-biblio-field--value'>WO2002008676</div>" +
                "<div class='ps-biblio-field--label'>Data de publicação</div>" +
                "<div class='ps-field--value ps-biblio-field--value'>2002.02.14</div>" +
                "<div class='ps-biblio-field--label'>Título</div>" +
                "<div class='ps-field--value ps-biblio-field--value'>" +
                "<div class='patent-title'>" +
                "<span class='PCTtitle'>" +
                "<div>" +
                "<b class='notranslate'>(DE)</b> <span class='notranslate'>KÄLTEGERÄT</span><br>" +
                "</div>" +
                "<div>" +
                "<b class='notranslate'>(EN)</b> <span class='needTranslation-title'>REFRIGERATION DEVICE</span><br>" +
                "</div>" +
                "<div>" +
                "<b class='notranslate'>(FR)</b> <span class='notranslate'>APPAREIL REFRIGERANT</span><br>" +
                "</div>" +
                "</span>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
        ;

        Document doc = Jsoup.parse(mockHtmlResponse);

        Patent patent = patentService.extractPatentFromDoc(doc);

        assertNotNull(patent);
        assertEquals("WO2002008676", patent.getPublicationNumber(), "Número da publicação não corresponde");
        assertEquals("REFRIGERATION DEVICE", patent.getTitle(), "Título não corresponde");
    }
}
