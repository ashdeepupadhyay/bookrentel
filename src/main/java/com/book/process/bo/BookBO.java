package com.book.process.bo;

import java.sql.Timestamp;
import java.util.Date;

public class BookBO {

    private Long bookId;
    private String name;
    private String author;
    private String category;
    private Timestamp rentDate;
    private Double initialPrice;
    private Double fixedPrice;
    private Integer days;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getRentDate() {
        return rentDate;
    }

    public void setRentDate(Timestamp rentDate) {
        this.rentDate = rentDate;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Double getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
