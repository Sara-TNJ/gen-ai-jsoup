package com.att.poc.jsoup.controller;

import com.att.poc.jsoup.dao.InputField;
//import com.att.poc.jsoup.service.JsoupService;
import com.att.poc.jsoup.services.JsoupServices;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class JsoupController {
    
    private static final Logger logger =
            LoggerFactory.getLogger(JsoupController.class);


    private final JsoupServices jsoupService;

    public JsoupController(JsoupServices jsoupService) {
        this.jsoupService = jsoupService;
    }

    /**
     * Example:
     * GET http://localhost:8080/jsoup/title?url=https://example.com
     */
    @GetMapping("/jsoup/title")
    public ResponseEntity<?> getPageTitle(@RequestParam String url) {
    try {
        return ResponseEntity.ok(jsoupService.fetchTitle(url));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Unable to fetch URL");
    }
     }

     @GetMapping("/jsoup/buttons")
     public ResponseEntity<List<String>> getAvailableButtons(@RequestParam String url) throws IOException {
        logger.info("Received request to fetch buttons from URL: {}");
        return ResponseEntity.ok(jsoupService.fetchButtons(url));
      }
      @GetMapping("/jsoup/inputs")
     public ResponseEntity<List<InputField>> getAvailableInputs(@RequestParam String url) throws IOException {
        logger.info("Received request to fetch inputs from URL: {}", url);
        return ResponseEntity.ok(jsoupService.fetchInputs(url));
      }
    }
