package com.shangcg.mode.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式使用demo
 */
public class CriteriaPatternDemo {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Robert","Male", "Single"));
        personList.add(new Person("John","Male", "Married"));
        personList.add(new Person("Laura","Female", "Married"));
        personList.add(new Person("Diana","Female", "Single"));
        personList.add(new Person("Mike","Male", "Single"));
        personList.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();

        //组合过滤器 参数为两个过滤器
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        //过滤出males
        System.out.println("Males:");
        printPersons(male.meetCriteria(personList));


        //过滤出females
        System.out.println("females");
        printPersons(female.meetCriteria(personList));

        //过滤出 single males
        System.out.println("single females");
        printPersons(singleMale.meetCriteria(personList));
    }

    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }
    }
}
