package org.deepinfo.proton.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lixudong on 2017/4/24.
 */
@Entity
@Table(name = "T_EXAMPLE")
public class ExampleDO extends BaseDO {

    private static final long serialVersionUID = -521329088369108022L;

    @Column(nullable = false)
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ExampleDO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
