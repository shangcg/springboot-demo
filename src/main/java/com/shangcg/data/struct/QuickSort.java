package com.shangcg.data.struct;

/**
 * 快速排序
 * 自然语言描述：
 * 1递归临界条件 if left > right 直接返回
 * 2定义两个指针变量，分别赋值i = left; j = right; 保留left和right 定义快排比较基准x  x=a[left]
 * 3 while(i < j) 则一直遍历，在遍历内部分别移动i指针向后移动，j指针向前移动，直到 i不再小于j则退出
 *   在遍历内部一直比较 ai是否小于基准x 既a[i] < x，则i一直后向移动，直到找到ai比x大的那个值，ai既是需要暂时保留等待交换的值
 *   在遍历内部一直比较 aj是否大于基准x 既a[j] > x，则j一直向前移动，直到找到aj比x小的那个值，aj即是需要暂时保留等待交换的值
 * 4 if(i < j) 依然成立，则交换a[i]和a[j]的值
 */
public class QuickSort {



    public  void quickSort(int[] nums, int left, int high) {
        if (left >= high) {
            return;
        }
        int i = left - 1, j = high + 1;
        int x = nums[left];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        quickSort(nums, left, i);
        quickSort(nums, i + 1, high);
    }


    public static void main(String[] args) {
        int a[] = {6,5,4,3,2,1};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(a, 0, a.length-1);

        for (int i = 0; i< a.length; i++){
            System.out.println(a[i]);
        }

    }


}
