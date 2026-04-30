package com.att.poc.crawler4j_demo.util
;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BasicCrawler extends WebCrawler {

    // Ignore non‑HTML files
    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|mp3|mp4|zip|gz|pdf))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {

        String href = url.getURL().toLowerCase();

        // Skip unwanted files
        if (FILTERS.matcher(href).matches()) {
            return false;
        }

        // Only crawl within same domain
        return href.startsWith("https://example.com");
    }

    @Override
    public void visit(Page page) {

        String url = page.getWebURL().getURL(); // receive   url for  Crawling 
        System.out.println("Visited: " + url);
        String content = new String(page.getContentData()); 
        //to get Raw HTML content of the page
        System.out.println("Html: " + content.substring(0, Math.min(content.length(), 200)) + "..."); // Print first 200 chars of HTML

        if (page.getParseData() instanceof HtmlParseData) {

            HtmlParseData data = (HtmlParseData) page.getParseData();

            String title = data.getTitle();
            System.out.println("Title: " + title);
            String text = data.getText();
              System.out.println("Text: " + text);
            Set<WebURL> links = data.getOutgoingUrls();
             String html = data.getHtml();

            links.forEach(link -> System.out.println("Outgoing URL: " + link.getURL() +"   "
        + link.getPath()));

        Document doc = Jsoup.parse(html, page.getWebURL().getURL());
        Elements buttons = doc.select("button");

        for (Element btn : buttons) {
            String buttontext = btn.text();
            String id = btn.id();
            String classes = btn.className();

            System.out.println("Button text: " + text);
            System.out.println("Button id: " + id);
            System.out.println("Button class: " + classes);
        }

           
            System.out.println("Outgoing links: " + links.size());
        }
    }
}