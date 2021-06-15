package com.shangcg.mode.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式： 实现过滤标准的实现类
 */
public class CriteriaMale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> malePersons = new ArrayList<>();
        for (Person person: personList){
            if (person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
