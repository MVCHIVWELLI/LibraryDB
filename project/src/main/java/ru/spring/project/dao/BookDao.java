package ru.spring.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spring.project.models.Book;
import ru.spring.project.models.Consumer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
@Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> show(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void  save(Book book){
        jdbcTemplate.update("INSERT INTO Book (book_name, book_creator, book_year ) VALUES (?,?,?)", book.getBook_name() , book.getBook_creator(), book.getBook_year());
    }
public Book index (int book_id){
return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",new Object[]{book_id},
        new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
}
    public void delete(int book_id) throws SQLException {
        jdbcTemplate.update("DELETE FROM  book WHERE  book_id=?", book_id);
    }
    public void update(int book_id, Book bookUp) throws SQLException {
        jdbcTemplate.update("UPDATE book SET book_name=?, book_creator=?, book_year=?  WHERE  book_id=?",
               bookUp.getBook_name(), bookUp.getBook_creator(), bookUp.getBook_year(), book_id);
    }

    public void add (int book_id, Consumer consumerSel){
    jdbcTemplate.update("UPDATE Book SET consumer_id=? WHERE  book_id=?", consumerSel.getConsumer_id(), book_id);
    }

    public void free (int book_id){
    jdbcTemplate.update("UPDATE Book SET  consumer_id=NULL WHERE book_id=?", book_id);
    }

   /* public Optional<Consumer> getBookOwner (int book_id){
    return jdbcTemplate.query("SELECT Consumer.* FROM Book JOIN Consumer ON Book.consumer_id=Consumer.consumer_id WHERE book.book_id=?",
            new Object[]{book_id}, new BeanPropertyRowMapper<>(Consumer.class)).stream().findAny();
    }*/
    public List<Book> showBooksByPerson(int consumer_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE consumer_id = ?",
                new BeanPropertyRowMapper<>(Book.class), consumer_id);
    }
}
