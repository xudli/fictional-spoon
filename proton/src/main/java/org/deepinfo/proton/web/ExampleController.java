package com.andlinks.proton.web;

import com.andlinks.proton.domain.ExampleDO;
import com.andlinks.proton.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lixudong on 2017/4/24.
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @RequestMapping(value="/findById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ExampleDO findById(@PathVariable long id) {
        return exampleService.findById(id);
    }

    @RequestMapping(value="/findByUuid/{uuid}",method = RequestMethod.GET)
    @ResponseBody
    public ExampleDO findByUuid(@PathVariable String uuid) {
        return exampleService.findByUuid(uuid);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ExampleDO save(@RequestBody ExampleDO entity) {
        return exampleService.save(entity);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ExampleDO update(@RequestBody ExampleDO entity){
        return exampleService.update(entity);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Page<ExampleDO> list(@PageableDefault(value = 5, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        return exampleService.list(pageable);
    }

    @RequestMapping(value="/findByName/{name}",method = RequestMethod.GET)
    @ResponseBody
    public List<ExampleDO> findByName(@PathVariable String name){
        return exampleService.findByName(name);
    }

    @RequestMapping(value="/findByAge/{age}",method = RequestMethod.GET)
    @ResponseBody
    public List<ExampleDO> findByAge(@PathVariable int age){
        return exampleService.findByAge(age);
    }

    @RequestMapping(value="/deleteByIds",method = RequestMethod.POST)
    public int deleteByIds(@RequestBody List<Long> ids){
        return exampleService.deleteByIds(ids);
    }
}
