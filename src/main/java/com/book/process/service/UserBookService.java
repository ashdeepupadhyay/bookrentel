package com.book.process.service;

import com.book.process.to.BookTO;
import com.book.process.to.SelectBookTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserBookService {

     ResponseEntity<String> getSelectedBookDetail(SelectBookTO selectBookDetails);

     ResponseEntity<String> updateBookReturn(SelectBookTO returnBook);

     List<BookTO> getClientBooks(Long clientId);
}
