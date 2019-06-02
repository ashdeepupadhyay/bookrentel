package com.book.process.dao.repository.extractor;

import com.book.process.bo.BookBO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper implements ResultSetExtractor<List<BookBO>> {


    @Override
    public List<BookBO> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<BookBO> list = new ArrayList<>();
        while(resultSet.next())
        {
            BookBO bookBO = new BookBO();
            bookBO.setBookId(resultSet.getLong("book_id"));
            bookBO.setAuthor(resultSet.getString("book_author"));
            bookBO.setName(resultSet.getString("book_name"));
            bookBO.setDays(resultSet.getInt("no_of_release_days"));
            bookBO.setInitialPrice(resultSet.getDouble("init_days_price"));
            bookBO.setFixedPrice(resultSet.getDouble("fixed_days_price"));
            bookBO.setRentDate(resultSet.getTimestamp("rent_date"));
            bookBO.setCategory(resultSet.getString("category"));
            list.add(bookBO);
        }
        return list;
    }
}
