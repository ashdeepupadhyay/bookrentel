package com.book.process.controller;

import com.book.process.service.UserBookService;
import com.book.process.to.BookListTO;
import com.book.process.to.BookTO;
import com.book.process.to.SelectBookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserBookController {

    @Autowired
    UserBookService userBookService;

    @RequestMapping(value = "/selectBook",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSelectedBookDetail(@RequestBody SelectBookTO selectBookDetails)
    {
        return userBookService.getSelectedBookDetail(selectBookDetails);
    }

    @RequestMapping(value = "/returnBook",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBookReturn(@RequestBody SelectBookTO selectBookDetails)
    {
        return userBookService.updateBookReturn(selectBookDetails);
    }

    @RequestMapping(value = "/bookDetails",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BookListTO getClientBooks(@RequestHeader("clientId")Long clientId)
    {
        return userBookService.getClientBooks(clientId);
    }
}
