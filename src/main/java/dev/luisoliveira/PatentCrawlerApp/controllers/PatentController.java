package dev.luisoliveira.PatentCrawlerApp.controllers;

import dev.luisoliveira.PatentCrawlerApp.entities.Patent;
import dev.luisoliveira.PatentCrawlerApp.services.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatentController {

    @Autowired
    PatentService patentService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @GetMapping("/fetch")
    @ResponseBody
    public Patent fetchPatentData(@RequestParam String applicationNumber) {
        return patentService.fetchPatentData(applicationNumber);
    }

    @PostMapping("/save")
    @ResponseBody
    public Patent savePatent(@RequestBody Patent patent) {
        return patentService.savePatent(patent);
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Patent> findPatents(@RequestParam(required = false) String publicationNumber,
                                    @RequestParam(required = false) String applicant) {
        return patentService.findPatents(publicationNumber, applicant);
    }
}