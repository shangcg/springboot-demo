package com.shangcg.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 十进制数转二进制
 *
 * 1 需转整数 n % 2 = y 得到余数，
 * 2 n /2 记录商、 商为0则结束，否则一直循环
 * 3 余数倒着依次排序即该整数的二进制数
 *
 *  汉明重量也可以采取此方法
 *
 * @author shangchenguang
 * @date 2021/8/23 8:06 下午
 */
public class TenToTwo {

    List<Integer> yList = new ArrayList<>();

    public void tenTotwo(int n){

        int y = 0;
        while (n != 0){
            y = n % 2;
            yList.add(y);
            n = n / 2;
        }

        for (int i = 0; i < yList.size(); i++){
            System.out.println(yList.get(i));
        }
    }

    public static void main(String[] args) {
        TenToTwo tenToTwo = new TenToTwo();
        tenToTwo.tenTotwo(10);
        System.out.println(tenToTwo.tenTotwoWithInt(10));

        System.out.println(tenToTwo.higWeight(10));

    }


    /**
     * 利用math函数直接转int
     * 10进制转2进制
     * @param n
     * @return
     */
    public int tenTotwoWithInt(int n){

        int w = 0;
        int y = 0;
        int ret = 0;
        while (n != 0){
            y = n % 2;
            ret += y * Math.pow(10, w);
            w ++;
            n = n / 2;
        }

      return ret;
    }


    /**
     * 按位&的方式计算汉明重量
     *
     * 1 n&1 不为0， 则n肯定为奇数，在此处n只能为1， 个数+1
     * 2 mask一直左移，mask是一个标记位 初始值1 = 0001
     * 左移一位  1 * 2^1 = 2 = 0010
     * 左移两位 1 * 2^2  = 4 = 0100
     *
     * 注意：只有1与1才会得1，其他任何数与1进行与运算都得不到1
     *
     * @param n
     * @return
     */
    public int higWeight(int n){
        int ret = 0;
        int mask = 1;
        for(int i = 0; i < 32; i++){
            if ((n & mask) != 0){
                ret ++;
            }
            mask = mask << 1;
        }
        return ret;
    }

}
