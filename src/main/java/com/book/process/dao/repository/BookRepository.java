package com.book.process.dao.repository;

import com.book.process.bo.BookBO;
import com.book.process.dao.repository.extractor.BookMapper;
import com.book.process.dao.repository.extractor.NotRentBookMapper;
import com.book.process.to.BookListTO;
import com.book.process.to.SelectBookTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    NotRentBookMapper notRentBookMapper;

    private static final String UPDATE_REGISTRATION_DATE_QUERY = "update public.book set rent_date=now(),client_id=:clientId where book_id=:bookId";
    private static final String RETURN_BOOK_QUERY = "update public.book set rent_date=null,client_id=null where book_id=:bookId";
    private static final String BOOK_LIST_FOR_CLIENT = "select book_id,book_name,book_author,rent_date,no_of_release_days,init_days_price,fixed_days_price,category_name as category from public.book b\n" +
            "inner join public.category cat on cat.category_id=b.category_id where client_id=:clientId";
    private static final String BOOK_LIST_FOR_RENTING = "select book_id,book_name,book_author,category_name as category from public.book b" +
            " inner join public.category cat on cat.category_id=b.category_id where client_id is null and rent_date is null";

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void updateRentDateForClient(SelectBookTO selectBookTO){
        namedParameterJdbcTemplate.update(UPDATE_REGISTRATION_DATE_QUERY,new MapSqlParameterSource("clientId",selectBookTO.getClientId())
                                                                            .addValue("bookId",selectBookTO.getBookId()));
    }

    public void returnBook(SelectBookTO returnBook) {
        namedParameterJdbcTemplate.update(RETURN_BOOK_QUERY,new MapSqlParameterSource("bookId",returnBook.getBookId()));
    }

    public List<BookBO> getBooksForClient(Long clientId) {
       return namedParameterJdbcTemplate.query(BOOK_LIST_FOR_CLIENT,new MapSqlParameterSource("clientId",clientId),bookMapper);
    }

    public BookListTO getBookList() {
        return namedParameterJdbcTemplate.query(BOOK_LIST_FOR_RENTING,notRentBookMapper);
    }
}
