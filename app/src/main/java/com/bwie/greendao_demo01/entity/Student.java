package com.bwie.greendao_demo01.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 武医宁 on 2018/5/15.
 */
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private String age;
    private String num;
    @Generated(hash = 620369879)
    public Student(Long id, String name, String age, String num) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.num = num;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }

}
