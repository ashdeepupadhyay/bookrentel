package com.book.process.dao.repository.extractor;

import com.book.process.bo.BookBO;
import com.book.process.to.BookListTO;
import com.book.process.to.BookTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotRentBookMapper implements ResultSetExtractor<BookListTO> {
    @Override
    public BookListTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<BookTO> list = new ArrayList<>();
        while(resultSet.next())
        {
            BookTO bookBO = new BookTO();
            bookBO.setBookId(resultSet.getLong("book_id"));
            bookBO.setAuthor(resultSet.getString("book_author"));
            bookBO.setName(resultSet.getString("book_name"));
            bookBO.setCategory(resultSet.getString("category"));
            list.add(bookBO);
        }
        BookListTO bookListTO = new BookListTO();
        bookListTO.setBookList(list);
        return bookListTO;
    }
}
