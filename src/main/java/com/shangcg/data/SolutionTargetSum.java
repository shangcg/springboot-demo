package com.shangcg.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和：给定一个整数数组nums和一个目标值target，请你在该数组中找出和为target的两个整数，并返回他们的数组下标
 * 假定每种输入只会对应一个答案
 */
public class SolutionTargetSum {

    /**
     * 解法一
     * 自然语言描述
     * 1：定义start和end两个变量存储元素下标
     * 2：外层遍历数组nums, 从i=0到 i=nums.length-1
     * 3：内层遍历数组nums，从j=i到 j=nums.length-1
     * 4: 当nums[i] + nums[j] = target时， 返回i和j
     */
    public void solution(int[] nums, int target){

        int start, end = 0;
        for (int i = 0; i < nums.length; i++){
            start = i;
            for (int j = i; j < nums.length; j++){
                end = j;
                if (nums[i] + nums[j] == target){
                    System.out.println(start);
                    System.out.println(end);
                    break;
                }
            }
        }
    }

    /**
     * 解法2
     * 自然语言描述
     * 思路：求和目标target-nums[i] 如果该值在nums中， 则说明该nums 与 nums[i] 即为寻找目标
     * 1 定义map存储 nums中值nums[i]和其下标i
     * 2 遍历nums从i = 0 到 i< nums.length 如果map不包含target-nums[i]，则把nums[i]和i存储到map
     *          如果map包含target-nums[i]，则从map中取出该值的下标返回 map.get(target-nums[i]) 和 i 就是寻找的两个目标数字
     */
    public void solution2(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int num = target - nums[i];
            if (map.containsKey(num)){
                System.out.println("one:" + map.get(num));
                System.out.println("two:" + i);
            }
            map.put(nums[i], i);
        }
    }

    public static void main(String[] args) {
        int[] a = {11,7,2,15};
        int target = 9;
        SolutionTargetSum solutionTargetSum = new SolutionTargetSum();
//        solutionTargetSum.solution(a, target);

        solutionTargetSum.solution2(a, target);
    }

}
