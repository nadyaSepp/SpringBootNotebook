package com.seppna.springbootnotebook.controllers;

import com.seppna.springbootnotebook.dao.BookDao;
import com.seppna.springbootnotebook.modules.Book;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  //Для Model импоритируем
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books") //помечаем, что вся наша логика начинается с данного префикса, чтоб его не повторять везде
public class BooksController {
    @Autowired //искать во всех классах приложения
    private BookDao bookDao;

    //Отображение всех книг
    @GetMapping
    public String index(Model model) {
        List<Book> bookList = bookDao.allBooks();
        //После добавления списка в модель он будет виден в нашем view
        model.addAttribute("books", bookList);
        return "books/index";
    }

    //Будут обрабатываться ссылки вида:
    // /books/1
    // /books/2 и так далее....
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDao.getBook(id);
        model.addAttribute("book", book);
        return "books/show";
    }
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    //@ModelAttribute нам даёт готовый объект book из нашего представления (book/new.html)

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") int id, Model model) {
        Book book = bookDao.getBook(id);
        model.addAttribute("book", book);
        return "books/edit";
    }
    //Patch
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {

        if(bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDao.updateBook(id, book);
        return "redirect:/books";
    }
}
