package com.shangcg.base;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 递归算法常见面试题
 *
 * @author shangchenguang
 * @date 2021/7/24 11:44 上午
 */
public class Recursion {

    /**
     *
     * 递归的没太透彻
     * 给定一个数组，把数组中元素的全部组合方案列出，比如{1,2}列出为 1，2，12，21
     *
     * 递归实现：
     * 自然语言描述：
     * 1
     *
     * 非递归实现：
     */

    public static void main(String[] args) {
        String[] array = new String[]{
                "1", "2", "3","4"
        };

        listAll(Arrays.asList(array),"");
    }

    public static void listAll(List candidate, String prefix){
//        if (candidate.isEmpty()){
            System.out.println(prefix);
//        }
        for (int i = 0; i < candidate.size(); i++){
            List temp = new LinkedList(candidate);
            listAll(temp, prefix + temp.remove(i));
        }
    }


    //循环与条件
    //for循环没有花括号、则只能声明执行语句，不能声明变量
    public void foreachTest(){
//        for (int i = 0;  i < 10; i++)
//            Integer k = new Integer(i);

    }
}
