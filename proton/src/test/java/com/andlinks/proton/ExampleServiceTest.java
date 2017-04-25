package com.andlinks.proton;

import com.andlinks.proton.domain.ExampleDO;
import com.andlinks.proton.service.ExampleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by lixudong on 2017/4/24.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = ProtonApplication.class)
//@SpringBootConfiguration
public class ExampleServiceTest {

    @Autowired
    private ExampleService exampleService;

    ExampleDO entity = new ExampleDO();

    @Before
    public void setUp(){
        entity.setAge(11);
        entity.setName("wanng");
    }

    @Test
    public void save(){
        entity = exampleService.save(entity);
        System.out.print(exampleService.findById(entity.getId()));
        System.out.print(entity);
        assertEquals(exampleService.findById(entity.getId()),entity);
    }
}
