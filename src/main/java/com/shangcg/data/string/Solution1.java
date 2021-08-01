package com.shangcg.data.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符串的最长子串：给定一个字符串，找出其中不含有重复字符的最长子串 的长度
 * 自然语言：
 * 1 循环字符串，将每个不重复的元素存入map
 * 2 定义i、j指针，分别存储不重复元素的开始位置和结束位置，即字符下标 0-length-1
 * 3 i、j初始都指向下标0， j向后移动，如果j位置的元素在map中存在，则重复，i的值进行更新 i = j， j继续向后移动
 * 4 j-i就是不重复元素的长度
 */
public class Solution1 {

    int calc(String str){
        int i , j = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (; j < str.length(); j++){
            if (!map.containsKey(str.charAt(j))){
                map.put(str.charAt(j), j);
            }else {
                i = j;
            }
        }
        return j - 1;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        String a = "aabbcc";
        int calc = solution1.calc(a);
        System.out.println(calc);

    }
}


