package com.shangcg.data.struct;

/**
 * 冒泡排序
 * 自然语言描述：
 * 1 遍历数组a,从第一个元素，既下标为0的元素开始 ，直到数组最后一个元素，既下标为length -1的元素结束
 * 2 内层从第一个元素，既下标为0的元素开始，依次和相邻元素比较,直到下标为 length - 1 -i个元素，因为每外层循环一次，就会冒出一个最大元素到最后位置
 *   如果a[j] > a[j+1], 则交换a[j] 和 a[j+1]的位置。将a[j]向后冒
 */
public class BubbleSort {

    void bubbleSort(int[] a){
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length - 1 - i; j++){
                if (a[j] > a[j + 1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        int a[] = {2,4,5,3,6,12,54,353,9};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(a);
        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
