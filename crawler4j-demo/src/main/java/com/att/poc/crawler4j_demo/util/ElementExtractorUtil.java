package com.att.poc.crawler4j_demo.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ElementExtractorUtil {

    // ---------- DTO ----------
    public static class UiElement {
        public String tag;
        public String text;
        public String id;
        public String name;
        public String type;
        public String cssSelector;
        public String href;
        public String placeholder;

        @Override
        public String toString() {
            return "UiElement{" +
                    "tag='" + tag + '\'' +
                    ", text='" + text + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", cssSelector='" + cssSelector + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }

    // ---------- MAIN ENTRY METHOD ----------
    public static List<UiElement> extractAllUiElements(Document doc) {
        List<UiElement> results = new ArrayList<>();

        results.addAll(extractButtons(doc));
        results.addAll(extractLinks(doc));
        results.addAll(extractInputs(doc));

        return results;
    }

    // ---------- BUTTONS ----------
    private static List<UiElement> extractButtons(Document doc) {
        List<UiElement> list = new ArrayList<>();

        // <button>
        Elements buttons = doc.select("button");
        for (Element btn : buttons) {
            list.add(buildElement(btn));
        }

        // <input type="button|submit|reset">
        Elements inputButtons =
                doc.select("input[type=button], input[type=submit], input[type=reset]");
        for (Element btn : inputButtons) {
            list.add(buildElement(btn));
        }

        return list;
    }

    // ---------- LINKS ----------
    private static List<UiElement> extractLinks(Document doc) {
        List<UiElement> list = new ArrayList<>();

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            UiElement el = buildElement(link);
            el.href = link.absUrl("href");
            list.add(el);
        }

        return list;
    }

    // ---------- INPUT FIELDS ----------
    private static List<UiElement> extractInputs(Document doc) {
        List<UiElement> list = new ArrayList<>();

        Elements inputs = doc.select("input, textarea, select");
        for (Element input : inputs) {
            uiElementEnhancer(input, list);
        }

        return list;
    }

    private static void uiElementEnhancer(Element el, List<UiElement> list) {
        UiElement ui = buildElement(el);
        ui.placeholder = el.attr("placeholder");
        list.add(ui);
    }

    // ---------- COMMON BUILDER ----------
    private static UiElement buildElement(Element el) {
        UiElement ui = new UiElement();
        ui.tag = el.tagName();
        ui.text = el.text();
        ui.id = el.id();
        ui.name = el.attr("name");
        ui.type = el.attr("type");
        ui.cssSelector = el.cssSelector();
        return ui;
    }
}