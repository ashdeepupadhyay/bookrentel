package com.book.process.service;

import com.book.process.bo.BookBO;
import com.book.process.dao.repository.BookRepository;
import com.book.process.to.BookListTO;
import com.book.process.to.BookTO;
import com.book.process.to.SelectBookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserBookServiceImpl implements UserBookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public ResponseEntity<String> getSelectedBookDetail(SelectBookTO selectBookDetails) {
        validateSelectBook(selectBookDetails);
        bookRepository.updateRentDateForClient(selectBookDetails);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    private void validateSelectBook(SelectBookTO selectBookDetails) {
        //todo exception
        if(selectBookDetails==null)
            System.out.println("Empty payload");
        if(selectBookDetails.getBookId()==null)
            System.out.println("Book ID is empty");
        if(selectBookDetails.getClientId()==null)
            System.out.println("Empty Client Id");

    }

    @Override
    public ResponseEntity<String> updateBookReturn(SelectBookTO returnBook) {
        validateSelectBook(returnBook);
        bookRepository.returnBook(returnBook);
        return new ResponseEntity<>("Succesfull",HttpStatus.OK);
    }

    @Override
    public BookListTO getClientBooks(Long clientId) {
        if(clientId==null)
            System.out.println("clientId missing");
        List<BookBO> books = bookRepository.getBooksForClient(clientId);
        List<BookTO> bookList = new ArrayList<>();

        BookListTO bookListTO = new BookListTO();

        bookListTO.setTotalCharges(0D);

        books.stream().forEach(book->{
            Long exceedDay = Duration.between(book.getRentDate().toLocalDateTime(), LocalDateTime.now()).toDays();
            Double price = book.getInitialPrice();
            if(exceedDay-book.getDays()>0)
                price=price+(exceedDay-book.getDays())*book.getFixedPrice();
            Double sum=bookListTO.getTotalCharges()+price;
            bookListTO.setTotalCharges(sum);
            BookTO bookTO = new BookTO(book.getBookId(),book.getName(),book.getAuthor(),book.getCategory(),book.getRentDate().toString(),price);
            bookList.add(bookTO);

        });
        bookListTO.setBookList(bookList);
        return bookListTO;
    }
}
