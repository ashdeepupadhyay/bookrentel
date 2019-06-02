package com.book.process.to;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookListTO {

    private List<BookTO> bookList;
    private double totalCharges;

    public double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public List<BookTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookTO> bookList) {
        this.bookList = bookList;
    }
}
