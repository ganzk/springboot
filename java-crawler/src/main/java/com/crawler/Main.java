package com.crawler;

import com.crawler.book.BookCrawler;

public class Main {
    public static void main(String[] args) {
//        BookCrawler.bookCrawlerMain();

        // https://b.biquim.cc/15/15277/9456656.html
        BookCrawler.testBQG("https://b.biquim.cc",  "/15/15277/9456656.html");
    }
}