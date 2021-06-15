package com.shangcg.data;

/**
 * 给定一个有序数组nums， 在原地删除重复出现的元素，使每个元素最多出现两次，返回删除后数组的新长度
 * 不要使用额外空间
 *
 * 自然语言描述
 * 1：定义元素重复出现的次数cnt = 0;  需要覆盖的元素下标cur
 * 2：遍历数组nums，从i=1开始，到i=nums.length-1为止，比较 nums[i] 和 nums[i-1]，如果相等， 则计数器cnt++， 不相等，则cnt =0重新开始
 * 3：在遍历内部，判断cnt<2 如果小于2，则重复次数小于2个，不需要特殊处理，只需将nums[cur]=nums[i] cur++;
 *    如果cnt<2不成立，则说明重复次数大于等于2了，说明该元素不能放入该数组
 * 4：直到遍历结束
 * 5: 返回数组长度
 */
public class SolutionTwo {

    public int removeDuplicates(int[] nums){
        int cnt = 0; int cur = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1]){
                cnt++;
            }else {
                cnt = 0;
            }
            //只有重复计数器cnt<2说明重复出现次数少于2次，元素个数最多2个，元素可以放到该数组里边
            if (cnt < 2){
                nums[cur] = nums[i];
                cur++;
            }
        }
        return cur;
    }
}
