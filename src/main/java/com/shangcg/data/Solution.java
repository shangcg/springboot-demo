package com.shangcg.data;


/**
 * 删除排序数组中的重复项 不允许用额外空间 返回数组的长度
 * 自然语言描述
 * 0：因要返回去重后的数组长度，需要定义重复元素个数，定义变量cnt，初始重复个数0，处理后数组的长度就是n-cnt
 * 1：从数组第i个元素开始遍历，和第i-1个元素对比
 * 2：第i个元素等于第i-1个元素，则重复个数计数器cnt+1, 第i个元素与第i-1个元素不相等，虽然不相等，但是由于前边的位置可能有空缺，需要
 *    把第i个元素移动到 i-cnt个元素， 如：cnt=0则相当于第i个元素没有动 num[i]=num[i] ,
 *    cnt=1重复了一个，则第i个元素则向前移动了1位，
 *    cnt=2则第i个元素向前移动了2位
 * 3: 数组原始元素个数n - 重复元素个数cnt = 去重后的数组长度
 */
public class Solution {



    //删除一个元素
    public int removeDuplicates(int[] nums){
        int cnt = 0, n = nums.length;
        for (int i = 1; i < n; ++i){
            if (nums[i] == nums[i-1]){
                ++cnt;
            }else {
                nums[i - cnt] = nums[i];
            }
        }
        return n -cnt;
    }


    //一个元素最多出现两次，多余的删除
    public int removeDuplicates2(int[] nums){
        int cnt = 0, cur = 1;
        for (int i = 1; i < nums.length; ++cnt){
            if (nums[i] == nums[i -1]){
                ++cnt;
            }else {
                cnt = 0;
            }
            if (cnt < 2){
                nums [cur++] = nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int [] a = {1, 2, 3, 4, 4};
        Solution solution = new Solution();
        int ret = solution.removeDuplicates(a);
        System.out.println(ret);
    }
}
