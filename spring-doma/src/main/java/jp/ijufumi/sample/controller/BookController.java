package jp.ijufumi.sample.controller;

import jp.ijufumi.sample.dao.BookDao;
import jp.ijufumi.sample.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookDao bookDao;

    @RequestMapping
    public List<Book> findAll() {
        return bookDao.selectAll();
    }
}
