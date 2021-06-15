package com.shangcg.mode.filter;

import java.util.List;

/**
 * 过滤器模式 过滤标准的接口
 *
 * 过滤器模式思考：如果过滤条件很多，一种过滤条件需要一个类，造成的类爆炸问题怎么办？
 * 雪山中： 客户查询、订单查询、合同查询都有众多过滤条件，是否可以采用过滤器模式呢？尝试写一下
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> personList);
}
