package com.andlinks.proton.dao;

import com.andlinks.proton.domain.ExampleDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Created by lixudong on 2017/4/24.
 */
public interface ExampleRepotory extends JpaRepository<ExampleDO,Long>{

    ExampleDO findByUuid(String Uuid);

    List<ExampleDO> findByName(String name);

    @Query("select t from ExampleDO t where t.age = :age and t.deleted=false")
    List<ExampleDO> findByAge(@Param("age") int age);

    Page<ExampleDO> findAll(Pageable pageable);

    @Modifying
    @Query("update ExampleDO t set t.deleted=true where t.id in (:ids)")
    int deleteByIds(@Param("ids") List<Long> ids);
}
