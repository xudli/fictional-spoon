package org.deepinfo.proton.controller;

import org.deepinfo.proton.model.ExampleDO;
import org.deepinfo.proton.params.HbaseParam;
import org.deepinfo.proton.service.ExampleService;
import org.deepinfo.proton.util.HbaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lixudong on 2017/4/24.
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;
    @Autowired
    private HbaseUtils hbaseUtils;

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ExampleDO findById(@PathVariable long id) {
        return exampleService.findById(id);
    }

    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public ExampleDO findByUuid(@PathVariable String uuid) {
        return exampleService.findByUuid(uuid);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ExampleDO save(@RequestBody ExampleDO entity) {
        return exampleService.save(entity);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ExampleDO update(@RequestBody ExampleDO entity) {
        return exampleService.update(entity);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Page<ExampleDO> list(@PageableDefault(value = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return exampleService.list(pageable);
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<ExampleDO> findByName(@PathVariable String name) {
        return exampleService.findByName(name);
    }

    @RequestMapping(value = "/findByAge/{age}", method = RequestMethod.GET)
    @ResponseBody
    public List<ExampleDO> findByAge(@PathVariable int age) {
        return exampleService.findByAge(age);
    }

    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    public int deleteByIds(@RequestBody List<Long> ids) {
        return exampleService.deleteByIds(ids);
    }

    @RequestMapping(value = "/hbase", method = RequestMethod.GET)
    public String testHbase() {
        hbaseUtils.testHbase();
        return "1";
    }

    @RequestMapping(value = "/hbase/get", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getHbase(@RequestBody HbaseParam param) {
        List<Map<String, Object>> map = hbaseUtils.find(param.getTableName(), param.getStartRow(), param.getStopRow());
        return map;
    }


}
