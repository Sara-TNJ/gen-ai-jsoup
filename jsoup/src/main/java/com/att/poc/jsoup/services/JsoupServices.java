package com.att.poc.jsoup.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.att.poc.jsoup.dao.InputField;


@Service
public class JsoupServices {
    
public String fetchTitle(String url) throws IOException {

        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10_000)
                .get();

        return document.title();
    }

    public List<String> fetchButtons(String url) throws IOException {
        List<String> buttonInfo = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10_000)
                .get();
        
            // <button> tags
            Elements buttons = document.select("button");
         
            for (Element button : buttons) {
                String info = "Button text: " + button.text() + ", Button type: " + button.attr("type");
                buttonInfo.add(info);
            }

        return buttonInfo;
    }

    public List<InputField> fetchInputs(String url) throws IOException {
        List<InputField> inputInfo = new ArrayList<>();
        Elements inputs = fetchDocument(url).select("input, select, textarea");
        for (Element input : inputs) {
            String tag = input.tagName();
            String type = input.attr("type");
            String name = input.attr("name");
            String id = input.attr("id");
            String placeholder = input.attr("placeholder");
            InputField inputData = new InputField(id, name, type, placeholder, tag);
            inputInfo.add(inputData);
        }

        return inputInfo;
    }

     private Document fetchDocument(String url) throws IOException {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10_000)
                    .get();
        
     }
}
