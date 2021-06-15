package com.shangcg.mode.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤模式， 过滤标准实现类
 */
public class CriteriaFemale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> femalePersons = new ArrayList<>();
        for (Person person: personList){
            if (person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
