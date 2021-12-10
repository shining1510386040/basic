package com.demo.springboot.redissionwithspringboot.utils;

import com.demo.springboot.redissionwithspringboot.entity.Person;
import com.demo.springboot.redissionwithspringboot.entity.PersonCompator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试
 * @date 2021/12/6 17:11
 * @see
 */
public class Test {

    public static void main(String[] args) {

        List<Person> personlist = new ArrayList<>();
        Person person = new Person();
        for (int i = 0; i < 10; i++) {
            person.setId(i);
            person.setName("你好==》" + i);
            personlist.add(person);
        }
        // 1.
        Long firstSum = personlist.stream()
                .collect(Collectors.summarizingLong(Person::getId)).getSum();
        // 2.
        Long secondSum = personlist.stream()
                .collect(Collectors.summingLong(Person::getId));
        // 3. 先转化为int 集合在求和
        int thirdSum = personlist.stream()
                .mapToInt(Person::getId).sum();
        // map reduce
        Integer forthSum = personlist.stream()
                .collect(Collectors.reducing(0, Person::getId, (i, j) -> i + j));
        // 5.统计分析
        IntSummaryStatistics stats = personlist.stream().mapToInt(Person::getId).summaryStatistics();
        Long fiveSum = stats.getSum();
        System.out.println(firstSum + ":" + secondSum + ":" + thirdSum + ":" + forthSum + ":" + fiveSum);


        new Test().testCompare();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 测试比较
     * @date 2021/12/6 18:35
     */
    public void testCompare() {

        List<Person> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person(i * 2, "姓名" + i);
            data.add(person);
        }

        // 对象实现Comparable接口的
//        Collections.sort(data);

        // 比较器的
        Collections.sort(data, new PersonCompator());

        System.out.println(Arrays.toString(data.toArray()));
    }

}
