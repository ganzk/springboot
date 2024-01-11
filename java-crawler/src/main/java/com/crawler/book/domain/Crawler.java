package com.crawler.book.domain;

import com.crawler.until.ConfigPrefix;

import java.util.List;

@ConfigPrefix(value = "crawler")
public class Crawler {

    private String name;

    private String url;

    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Crawler{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", books=" + books +
                '}';
    }
}
