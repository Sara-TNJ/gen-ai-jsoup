package com.att.poc.jsoup.dao;

import java.util.List;

import lombok.Data;
 
 @Data
 public class CrawlResult {

    private String url;
    private String title;
    private List<String> links;
    private List<InputField> inputs;
    private List<String> buttons;
    private boolean hasLoginForm;

    // getters & setters
}