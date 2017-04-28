package org.deepinfo.proton.repository;

import org.deepinfo.proton.model.BookDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by lixudong on 2017/4/28.
 */
public interface BookRepository extends ElasticsearchRepository<BookDO, String> {

    Page<BookDO> findByAuthor(String author, Pageable pageable);

    List<BookDO> findByTitle(String title);
}
