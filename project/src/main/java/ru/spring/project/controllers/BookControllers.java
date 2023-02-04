package ru.spring.project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.project.dao.BookDao;
import ru.spring.project.dao.ConsumerDao;
import ru.spring.project.models.Book;
import ru.spring.project.models.Consumer;

import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookControllers {
    private BookDao bookDao;
    private ConsumerDao consumerDao;
@Autowired
    public BookControllers(BookDao bookDao, ConsumerDao consumerDao) {
        this.bookDao = bookDao;
        this.consumerDao = consumerDao;
    }

    @GetMapping()
    public String show(Model model){
     model.addAttribute("books", bookDao.show());
    return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
    return "books/new";
    }


    @PostMapping()
    public String create(@ModelAttribute ("book") Book book){
    bookDao.save(book);
    return "redirect:/books";
    }
@GetMapping("/{book_id}")
public String index(@PathVariable("book_id") int book_id, Model model,
                    @ModelAttribute("consumer") Consumer consumer){
    Book book = bookDao.index(book_id);
    model.addAttribute("book", book);
    if(book.getConsumer_id() != null)
        model.addAttribute("reader",consumerDao.index(book.getConsumer_id()));
 else {
        model.addAttribute("consumers", consumerDao.show());
    }

return "books/index";
}
    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id")int book_id) throws SQLException {
       bookDao.delete(book_id);
        return "redirect:/books";

    }

    @GetMapping("/{book_id}/edit")
    public String edit(Model model,@PathVariable("book_id") int book_id) throws SQLException {
        model.addAttribute("book", bookDao.index(book_id));
        return "books/edit";
    }
    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book")@Valid Book book,
                         @PathVariable("book_id") int book_id) throws SQLException {

       bookDao.update(book_id,book);
        return "redirect:/books";
    }
    @PatchMapping("/{book_id}/free")
    public String free (@PathVariable("book_id") int book_id){
    bookDao.free(book_id);
    return "redirect:/books/"+book_id;
    }
    @PatchMapping("/{book_id}/add")
    public String add(@PathVariable("book_id") int book_id, @ModelAttribute("consumer") Consumer consumerSel){
    bookDao.add(book_id,consumerSel);
    return "redirect:/books/"+book_id;
    }
}
