package data;

/**
 * 在一个长度为n的数组nums里的所有数字都在0 - n-1范围内。数组中某些数字是重复的，但是不知道有几个数字重复，也不知道重复几次，找出数组中任意一个重读的数字
 * 输入【2，3，1，0，2，5，3】 输出 2或者3
 */
public class SolutionFour {

    /**
     * 解法一：自然语言描述
     * 1：遍历数组，i从0到nums.length 在遍历内部将数据放到对应的位置上，如数字2放到下标为2的位置， 数字3放到下标为3的位置
     *   2 在遍历内部当nums[i] == i 时， 则i++继续向后处理
     *   3 在遍历内部如果nums[i] != i时，
     *   4 则判断nums[i] != nums[nums[i]]是否成立，如果不成立，则需要将下标为nums[i]的值放到下标i中
     *   5 如果成立nums[i]的值在该位置已经存在了，重复则直接返回
     *   解释下关键逻辑： 则判断nums[i]的值X应该放置在nums[x]中(如：nums[i] = 5 则该nums[i] = 5应该放置在nums[5]中，即 nums[nums[i]] ,也就是需要将nums下标i 和 nums下标 nums[i]交换)
     *
     */
    public int findRepeatNumber(int[] nums){

        //1 遍历数组
        for (int i = 0; i < nums.length; ++i){
            //2判断数组nums[i]中元素是否在位置i上，是则退出 i++ 否则继续
            while (nums[i] != i){
                //数组nums[i]未在i的位置上，则nums[i]的值应该放在nums[nums[i]]位置上，
                //   判断nums[nums[i]]的值是否与nums[i]相等，相等则说明该位置已放置正确元素，重复直接返回
                //   如果nums[nums[i]]的值是否与nums[i] 不相等，则需把nums[i]的值放到i中，即nums[nums[i]] 和 nums[i]进行交换，即下标为nums[i]的元素和下标为i的元素交换
                if (nums[i] == nums[nums[i]]) return nums[i];
                swap(nums, i, nums[i]);
            }
        }
        return 0;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int [] a = {2, 3, 1, 0, 2, 5, 3};
        SolutionFour solutionFour = new SolutionFour();
        int repeatNumber = solutionFour.findRepeatNumber(a);
        System.out.println(repeatNumber);
    }
}


