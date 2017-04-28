package org.deepinfo.proton.service.impl;

import org.deepinfo.proton.model.BookDO;
import org.deepinfo.proton.repository.BookRepository;
import org.deepinfo.proton.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixudong on 2017/4/28.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDO save(BookDO book) {
        return bookRepository.save(book);
    }

    public void delete(BookDO book) {
        bookRepository.delete(book);
    }

    public BookDO findOne(String id) {
        return bookRepository.findOne(id);
    }

    public Iterable<BookDO> findAll() {
        return bookRepository.findAll();
    }

    public Page<BookDO> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    public List<BookDO> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
