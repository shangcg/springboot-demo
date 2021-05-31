package data;

/**
 * 旋转数组： 给定一个数组，将数组中的元素向右移动K个位置，其中k是非负数。
 * 如：{1,2,3,4,5,6,7} k=3 则移动后的数组为{5,6,7,1,2,3,4}
 */
public class SolutionFive {

    /**
     * 解法一：自然语言描述
     * 1 将数组全部倒置
     * 2 将0-k-1个元素倒置
     * 3 将k-nums.length-1个元素倒置
     */
    public void rotate(int[] nums, int k){
        if (nums == null){
            return;
        }
        int n = nums.length;
        k %= n;
        if(k == 0){
            return;
        }
        revolve(nums, 0, nums.length-1);
        revolve(nums, 0, k-1);
        revolve(nums, k, nums.length-1);

    }

    //旋转数组元素，即头部和尾部一直交换，交换完毕后就相当于旋转了数组
    public void revolve(int[] nums, int i, int j){
        while (i < j){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        SolutionFive solutionFive = new SolutionFive();
        solutionFive.rotate(a, 2);

        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
