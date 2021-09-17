package com.shangcg.data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目：如何得到一个数据流的中位数，如果数据流中读出奇数个值，则中位数是中间的数值
 *      如果数据流中读出偶数个数，那么中位数
 *
 * @author shangchenguang
 * @date 2021/8/24 1:47 下午
 */
public class SolutionHeap {

    //记录数据流中的个数
    private int count = 0;

    //优先队列实现了堆、默认实现最小堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    //最大堆
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });



    public void insert(Integer num){
        //肯定是奇数
        if ((count & 1) == 0){
            //放入大根堆
            maxHeap.offer(num);

            //
            Integer filterMaxNum = maxHeap.poll();


            minHeap.offer(filterMaxNum);

        }
    }

}
