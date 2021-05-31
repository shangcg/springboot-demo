package data;

import java.util.Arrays;

/**
 * 移除元素
 * 给定一个数组nums，给定一个元素val, 原地移除数值等于val的元素，并返回数组长度
 * 不要使用额外空间, 不需要考虑超出新长度后面的元素
 *
 * 自然语言描述
 * 1：定义数组下个待插入元素的下标cur=0
 * 2：遍历数组nums， 从i=0到i=nums.length-1， 在遍历内部判断 nums[i] = val是否成立 如果成立，则该元素不能放入数组，i++ 不做其他处理
 *     如果nums[i] = val成立，则该元素可以放入数组， 即nums[cur] = nums[i], cur++ i++;
 *
 * 3：如果需要考虑超出新数组长度后面的元素该怎么处理呢？
 */
public class SolutionThree {

    public int removeVal(int[] nums, int val){
        int cur = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != val){
                nums[cur++] = nums[i];
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,7,7,8,7};
        SolutionThree solutionThree = new SolutionThree();
//        int length = solutionThree.removeVal(nums, 1);
        int length = solutionThree.removeElement(nums, 1);
        System.out.println(length);

        for (int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }


    /**
     * 解法二： 自然语言描述
     * 1：定义计数器计数相等元素的个数
     * 2：遍历数组nums 从i = 0 到 i = nums[i].length
     */
    //标准的 和上边自己写的结果一样
    public int removeElement(int[] nums, int val) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == val) ++cnt;
            else nums[i - cnt] = nums[i];
        }
        return n - cnt;
    }
}


