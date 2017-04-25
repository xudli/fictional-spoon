package org.deepinfo.proton.service;

import org.deepinfo.proton.domain.ExampleDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lixudong on 2017/4/24.
 */
public interface ExampleService {
    Page<ExampleDO> list(Pageable pageable);

    ExampleDO save(ExampleDO entity);

    ExampleDO findById(Long id);

    ExampleDO update(ExampleDO entity);

    ExampleDO findByUuid(String uuid);

    List<ExampleDO> findByName(String name);

    List<ExampleDO> findByAge(int age);

    int deleteByIds(List<Long> ids);
}
