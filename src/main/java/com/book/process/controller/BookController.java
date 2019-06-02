package com.book.process.controller;

import com.book.process.service.BookService;
import com.book.process.to.BookListTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/notRented",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BookListTO getBookList()
    {
        return bookService.getBookList();
    }
}
