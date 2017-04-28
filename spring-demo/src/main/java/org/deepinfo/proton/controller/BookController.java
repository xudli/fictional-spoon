package org.deepinfo.proton.controller;

import org.deepinfo.proton.model.BookDO;
import org.deepinfo.proton.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lixudong on 2017/4/28.
 */
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public BookDO save(@RequestBody BookDO book){
        return bookService.save(book);
    }

    @RequestMapping(value = "/findOne/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BookDO findOne(@PathVariable String id){
        return bookService.findOne(id);
    }
}
