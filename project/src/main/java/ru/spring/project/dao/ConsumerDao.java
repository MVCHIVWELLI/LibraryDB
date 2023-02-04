package ru.spring.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.spring.project.models.Book;
import ru.spring.project.models.Consumer;


import java.sql.SQLException;
import java.util.List;

@Component
public class ConsumerDao {

private final JdbcTemplate jdbcTemplate;
@Autowired
    public ConsumerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    public List<Consumer> show(){
    return jdbcTemplate.query("SELECT * FROM Consumer", new BeanPropertyRowMapper<>(Consumer.class));
    }

public Consumer index(int consumer_id){
   return jdbcTemplate.query("SELECT * FROM Consumer WHERE consumer_id=?",
            new Object[]{consumer_id}, new BeanPropertyRowMapper<>(Consumer.class)).stream().findAny().orElse(null);
}

public void  save(Consumer consumer){
jdbcTemplate.update("INSERT INTO Consumer (consumer_name, consumer_bd) VALUES (?,?)", consumer.getConsumer_name(), consumer.getConsumer_bd());
}
    public void delete(int consumer_id) throws SQLException {
        jdbcTemplate.update("DELETE FROM  consumer WHERE  consumer_id=?", consumer_id);
    }
    public void update(int consumer_id, Consumer consumerUp) throws SQLException {
        jdbcTemplate.update("UPDATE consumer SET consumer_name=?,consumer_bd=?  WHERE  consumer_id=?",
               consumerUp.getConsumer_name(), consumerUp.getConsumer_bd(), consumer_id);
    }
public List<Book> getBooksByConsumerId(int consumer_id){
    return jdbcTemplate.query("SELECT * FROM Book WHERE consumer_id=?", new Object[]{consumer_id},
            new BeanPropertyRowMapper<>(Book.class));

}

}
