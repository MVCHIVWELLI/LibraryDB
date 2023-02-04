package ru.spring.project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.project.dao.ConsumerDao;
import ru.spring.project.models.Consumer;

import java.sql.SQLException;


@Controller
@RequestMapping("/consumers")
public class ConsumerControllers {
private ConsumerDao consumerDao;
@Autowired
    public ConsumerControllers(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }


@GetMapping()
    public String show (Model model){
model.addAttribute("consumers",consumerDao.show());
return "consumers/show";
}

@GetMapping ("/{consumer_id}")
public String index(@PathVariable("consumer_id") int consumer_id, Model model){
    model.addAttribute("books", consumerDao.getBooksByConsumerId(consumer_id));
model.addAttribute("consumer", consumerDao.index(consumer_id));
return "consumers/index";

}
    @PostMapping()
    public String create(@ModelAttribute("consumer")  Consumer consumer){
        consumerDao.save(consumer);
        return "redirect:/consumers";

    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("consumer")Consumer consumer){
        return "consumers/new";
    }
    @DeleteMapping("/{consumer_id}")
    public String delete(@PathVariable("consumer_id")int consumer_id) throws SQLException {
   consumerDao.delete(consumer_id);
        return "redirect:/consumers";

    }
    @GetMapping("/{consumer_id}/edit")
    public String edit(@PathVariable("consumer_id") int consumer_id, Model model) throws SQLException {
        model.addAttribute("consumer", consumerDao.index(consumer_id));
        return "consumers/edit";
    }
    @PatchMapping("/{consumer_id}")
    public String update(@ModelAttribute("consumer")@Valid Consumer consumer,
                         @PathVariable("consumer_id") int consumer_id) throws SQLException {

        consumerDao.update(consumer_id,consumer);
        return "redirect:/consumers";
    }

}
