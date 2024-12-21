package com.crawler;

import com.crawler.book.BookCrawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        BookCrawler.bookCrawlerMain();

        // https://b.biquim.cc/15/15277/9456656.html

        // https://www.biqukan.co/book/219793/76837009.html
        // https://www.biqukan.co/book/182463/64785554.html
        // https://www.biqukan.co/book/130291/55247668.html
        // https://www.biqukan.co/book/130028/55190124.html
        // https://www.biqukan.co/book/129920/55165241.html
        // https://www.biqukan.co/book/189361/66813801.html
        // https://www.biqukan.co/book/130838/55394133.html

        // https://www.biqukan.co/book/218271/76241183.html
        // https://www.biqukan.co/book/218354/76282750.html
        // https://www.biqukan.co/book/130532/55312508.html
        // https://www.biqukan.co/book/130543/55314975.html
        // https://www.biqukan.co/book/130084/55198853.html
        // https://www.biqukan.co/book/130287/55247471.html
        // https://www.biqukan.co/book/130484/55304622.html
//        BookCrawler.testBQG1("https://www.biqukan.co/book/218271/",  "76241183.html");
//        BookCrawler.testBQG1("https://www.biqukan.co/book/218354/",  "76282750.html");
//        BookCrawler.testBQG1("https://www.biqukan.co/book/130532/",  "55312508.html");
//        BookCrawler.testBQG1("https://www.biqukan.co/book/130543/",  "55314975.html");
//        BookCrawler.testBQG1("https://www.biqukan.co/book/130084/",  "55198853.html");
//        BookCrawler.testBQG1("https://www.biqukan.co/book/130287/",  "55247471.html");
//        BookCrawler.testBQG1("https://www.biqukan.co/book/130484/",  "55304622.html");

        // http://www.ychd.net/yxgsk_152171/75859160.html
        BookCrawler.testBQG2("http://www.ychd.net",  "/yxgsk_152171/75859160.html", "cf");

//        Map<String, String> map = new HashMap();
//        List<Map<String, String>> mapList = new ArrayList<>();
//        for (int i = 0; i < mapList.size(); i++) {
//            Map<String, String> stringStringMap = mapList.get(i);
//
//            BookCrawler.testBQG1("https://www.biqukan.co/book/218271/",  "76241183.html");
//        }



    }
}