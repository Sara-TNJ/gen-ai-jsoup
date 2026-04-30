package com.att.poc.crawler4j_demo.controller;

 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.att.poc.crawler4j_demo.services.CrawlService;

@RestController
@RequestMapping("/crawl")
public class CrawlController {

    private final CrawlService crawlService;

    public CrawlController(CrawlService crawlService) {
        this.crawlService = crawlService;
    }

    @PostMapping
    public String startCrawl(@RequestParam String url) {
        String jobId = crawlService.startCrawl(url);
        return "Crawl started. Job ID = " + jobId;
    }
}

