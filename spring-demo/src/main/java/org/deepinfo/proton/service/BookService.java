package org.deepinfo.proton.service;

import org.deepinfo.proton.model.BookDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by lixudong on 2017/4/28.
 */
public interface BookService {

    BookDO save(BookDO book);

    void delete(BookDO book);

    BookDO findOne(String id);

    Iterable<BookDO> findAll();

    Page<BookDO> findByAuthor(String author, PageRequest pageRequest);

    List<BookDO> findByTitle(String title);
}
