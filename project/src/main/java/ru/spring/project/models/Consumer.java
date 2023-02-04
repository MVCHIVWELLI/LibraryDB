package ru.spring.project.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer {

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2,max=30, message = "Name should be between 2 and 30 characters")
    private String consumer_name;
    @Min(value = 1900, message = "Year born  should be big than 1900")
    private int consumer_bd;
    private int consumer_id;


    public Consumer(String consumer_name, int consumer_bd, int consumer_id) {
        this.consumer_name = consumer_name;
        this.consumer_bd = consumer_bd;
        this.consumer_id = consumer_id;
    }

    public int getConsumer_id() {
        return consumer_id;
    }
    public void setConsumer_id(int consumer_id) {
        this.consumer_id = consumer_id;
    }
    public String getConsumer_name () {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name=consumer_name;
    }

    public int getConsumer_bd() {
        return consumer_bd;
    }

    public void setConsumer_bd(int consumer_bd) {
        this.consumer_bd=consumer_bd;
    }
    public Consumer(){

    }
}
