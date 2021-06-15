package com.shangcg.mode.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式：过滤标准实现 过滤单身
 */
public class CriteriaSingle implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : personList) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
