package com.shangcg.mode.filter;

import java.util.List;

/**
 * 过滤器模式：过滤标准实现：根据两个条件过滤出结果
 */
public class AndCriteria implements Criteria{
    /**
     * 第一个过滤标准
     */
    private Criteria criteria;

    /**
     * 第二个过滤标准
     */
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria){
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> people = criteria.meetCriteria(personList);
        return otherCriteria.meetCriteria(people);
    }
}
