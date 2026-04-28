package com.att.poc.jsoup.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.att.poc.jsoup.RateLimiter;
import com.att.poc.jsoup.dao.CrawlResult;
import com.att.poc.jsoup.dao.InputField;

@Service
public class CrawlService {
    
  
public CrawlResult crawl(String url) throws Exception {
    RateLimiter.acquire();

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10_000)
                .get();

        CrawlResult result = new CrawlResult();
        result.setUrl(url);
        result.setTitle(doc.title());

        // ✅ Extract links
        List<String> links = new ArrayList<>();
        for (Element link : doc.select("a[href]")) {
            links.add(link.absUrl("href"));
        }
        result.setLinks(links);

        // ✅ Extract inputs
        List<InputField> inputs = new ArrayList<>();
        for (Element input : doc.select("input")) {
            InputField field = new InputField();
            field.setType(input.attr("type"));
            field.setName(input.attr("name"));
            field.setPlaceholder(input.attr("placeholder"));
            inputs.add(field);
        }
        result.setInputs(inputs);

        // ✅ Extract buttons
        List<String> buttons = new ArrayList<>();
        for (Element btn : doc.select("button, input[type=submit]")) {
            buttons.add(btn.text());
        }
        result.setButtons(buttons);

        // ✅ Detect login page
        boolean hasLogin =
                !doc.select("input[type=password]").isEmpty();
        result.setHasLoginForm(hasLogin);

        return result;
    }


}
