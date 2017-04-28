package org.deepinfo.proton.service.impl;

import org.deepinfo.proton.repository.ExampleRepotory;
import org.deepinfo.proton.model.ExampleDO;
import org.deepinfo.proton.service.ExampleService;
import org.deepinfo.proton.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lixudong on 2017/4/24.
 */
@Service
@Transactional
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleRepotory exampleRepotory;

    @Override
    public Page<ExampleDO> list(Pageable pageable) {
        return exampleRepotory.findAll(pageable);
    }

    @Override
    public ExampleDO save(ExampleDO entity) {
        entity.setUuid(UuidUtils.getUUID(ExampleDO.class));
        entity.setCreateTime(new Date());
        entity.setVersion(1);
        entity.setDeleted(false);
        return exampleRepotory.saveAndFlush(entity);
    }

    @Override
    public ExampleDO findById(Long id) {
        return exampleRepotory.getOne(id);

    }

    @Override
    public ExampleDO update(ExampleDO entity) {
        entity.setVersion(entity.getVersion()+1);
        entity.setModifyTime(new Date());
       return exampleRepotory.saveAndFlush(entity);
    }

    @Override
    @Cacheable(value = "examples",keyGenerator = "wiselyKeyGenerator")
    public ExampleDO findByUuid(String uuid) {
        System.out.print("无缓存的时候调这里\r\n");
        return exampleRepotory.findByUuid(uuid);
    }

    @Override
    @Cacheable(value = "examples",keyGenerator = "wiselyKeyGenerator")
    public List<ExampleDO> findByName(String name) {
        System.out.print("无缓存的时候调这里\r\n");
        return exampleRepotory.findByName(name);
    }

    @Override
    public List<ExampleDO> findByAge(int age) {
        return exampleRepotory.findByAge(age);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        return exampleRepotory.deleteByIds(ids);
    }
}