package com.demo.springboot.redissionwithspringboot.entity;

import java.util.Comparator;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 人比较器
 * @date 2021/12/6 18:33
 * @see
 */
public class PersonCompator implements Comparator<Person> {
    @Override
    public int compare(Person person, Person other) {

        return Integer.compare(person.getId(), other.getId());
    }
}
