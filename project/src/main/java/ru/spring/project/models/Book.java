package ru.spring.project.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;
    private Integer consumer_id;
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2,max=30, message = "Name should be between 2 and 30 characters")
    private String book_name;
    private String book_creator;
    @Min(value = 100, message = "Year born  should be big than 100")
    private int book_year;

    public Book(int book_id, String book_name, String book_creator, int book_year, Integer consumer_id) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_creator = book_creator;
        this.book_year = book_year;
        this.consumer_id=consumer_id;
    }

    public Integer getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_creator() {
        return book_creator;
    }

    public void setBook_creator(String book_creator) {
        this.book_creator = book_creator;
    }

    public int getBook_year() {
        return book_year;
    }

    public void setBook_year(int book_year) {
        this.book_year = book_year;
    }
    public Book(){}
}
