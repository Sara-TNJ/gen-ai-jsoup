package com.att.poc.crawler4j_demo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service
public class CrawlService {

    public String startCrawl(String url) {

        // start crawler in a new thread
        new Thread(() -> {
            try {
                


        String crawlStorageFolder = "data/crawl";
        int numberOfCrawlers = 5;

        // Crawl configuration
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(2);
        config.setPolitenessDelay(1000); // 1 sec delay
        config.setIncludeHttpsPages(true);
        config.setIncludeBinaryContentInCrawling(false);

        // Setup fetcher and robots
        PageFetcher fetcher = new PageFetcher(config);
        RobotstxtConfig robotsConfig = new RobotstxtConfig();
        RobotstxtServer robotsServer =
                new RobotstxtServer(robotsConfig, fetcher);

                CrawlController controller =
                        new CrawlController(config, fetcher, robotsServer);

                // Seed URL
                controller.addSeed(url);

                // Start crawl
                controller.start(com.att.poc.crawler4j_demo.util.BasicCrawler.class, numberOfCrawlers);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return UUID.randomUUID().toString();
    }
}
