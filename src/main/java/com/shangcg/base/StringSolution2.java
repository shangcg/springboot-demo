package com.shangcg.base;


import java.util.HashSet;

/**
 * 给定一个长度为n的字符串、需要去除所有之前曾经出现过的字符，只保留第一次出现的字符
 *
 * 思路：
 * 1 字符串转数组或者直接遍历，循环数组
 * 2 遍历内部，hashSet保存每一个字符，在保存之前判断字符是否存在，存在则是重复的，hashSet中的元素就是字符串(或者把不重复的拼接到一个stringBuilder)
 */
public class StringSolution2 {

    void checkStr(String str){
        StringBuilder s = new StringBuilder();
        HashSet<Character> targetSet = new HashSet();
        for (int i = 0; i < str.length(); i++){
            if (!targetSet.contains(str.charAt(i))){
                targetSet.add(str.charAt(i));
                s.append(str.charAt(i));
            }
        }
        System.out.println(s.toString());

    }

    public static void main(String[] args) {
        String str = "qwerertyr";
        StringSolution2 solution2 = new StringSolution2();
        solution2.checkStr(str);

    }
}
