package com.att.poc.jsoup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.att.poc.jsoup.dao.CrawlResult;
import com.att.poc.jsoup.services.CrawlService;
@RestController
public class CrawlController {

@Autowired
CrawlService vCrawlService;
@GetMapping("/crawl")
public CrawlResult startCrawl(@RequestParam String url) throws Exception {
    CrawlResult result=vCrawlService.crawl(url);
    return result;
}

    
}
