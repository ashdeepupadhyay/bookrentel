package com.book.process.service;

import com.book.process.dao.repository.BookRepository;
import com.book.process.to.BookListTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public BookListTO getBookList() {
        return bookRepository.getBookList();
    }
}
