package com.shangcg.base;

import java.util.Stack;

/**
 * 给定一个字符串str、判断是不是整体有效的括号字符串（整体有效：存在一种括号匹配方案，使得每个括号均能找到对应返括号）
 *
 * 思路：用栈解决
 * 1 每输入一个字符，判断是不是括号，不是括号直接退出
 * 2 判断是否是左括号( 或者右括号 ) 左括号压栈，右括号出栈
 * 3 判断栈是否是空栈，是空栈则str是整体有效的括号字符串
 */
public class StringSolution1 {

    boolean solution(String str){
        Stack stack = new Stack();
        for (int i = 0; i < str.length(); i++){
            String index = str.substring(i, i + 1);
            if (!index.equals("(") && !index.equals(")")){
                return false;
            }
            if (index.equals("(")){
                stack.push(index);
            }

            if (index.equals(")")){
                if (!stack.empty()){
                    stack.pop();
                }else {
                    return false;
                }
            }

            if (stack.empty()){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String s = "((()))";
        String s1 = ")(()";
        StringSolution1 solution1 = new StringSolution1();
        System.out.println(solution1.solution(s));
        System.out.println(solution1.solution(s1));

    }
}
