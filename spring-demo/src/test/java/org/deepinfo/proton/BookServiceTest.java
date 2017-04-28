package org.deepinfo.proton;

import org.deepinfo.proton.model.BookDO;
import org.deepinfo.proton.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
/**
 * Created by lixudong on 2017/4/28.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = ProtonApplication.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(BookDO.class);
        esTemplate.createIndex(BookDO.class);
        esTemplate.putMapping(BookDO.class);
        esTemplate.refresh(BookDO.class);
    }

    @Test
    public void testSave() {

        BookDO book = new BookDO("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        BookDO testBook = bookService.save(book);

        assertNotNull(testBook.getId());
        assertEquals(testBook.getTitle(), book.getTitle());
        assertEquals(testBook.getAuthor(), book.getAuthor());
        assertEquals(testBook.getReleaseDate(), book.getReleaseDate());

    }

    @Test
    public void testFindOne() {

        BookDO book = new BookDO("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);

        BookDO testBook = bookService.findOne(book.getId());

        assertNotNull(testBook.getId());
        assertEquals(testBook.getTitle(), book.getTitle());
        assertEquals(testBook.getAuthor(), book.getAuthor());
        assertEquals(testBook.getReleaseDate(), book.getReleaseDate());

    }

    @Test
    public void testFindByTitle() {

        BookDO book = new BookDO("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);

        List<BookDO> byTitle = bookService.findByTitle(book.getTitle());
        assertThat(byTitle.size(), is(1));
    }

    @Test
    public void testFindByAuthor() {

        List<BookDO> bookList = new ArrayList<>();

        bookList.add(new BookDO("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookList.add(new BookDO("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookList.add(new BookDO("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        bookList.add(new BookDO("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
        bookList.add(new BookDO("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));

        for (BookDO book : bookList) {
            bookService.save(book);
        }

        Page<BookDO> byAuthor = bookService.findByAuthor("Rambabu Posa", new PageRequest(0, 10));
        assertThat(byAuthor.getTotalElements(), is(4L));

        Page<BookDO> byAuthor2 = bookService.findByAuthor("Mkyong", new PageRequest(0, 10));
        assertThat(byAuthor2.getTotalElements(), is(1L));

    }

    @Test
    public void testDelete() {

        BookDO book = new BookDO("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);
        bookService.delete(book);
        BookDO testBook = bookService.findOne(book.getId());
        assertNull(testBook);
    }
}
