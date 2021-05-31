package data;


import java.util.HashMap;
import java.util.Map;

public class SolutionAB {

    /**
     * 数组a=[1,2,2,2,1,2,7,8,5,3], b = [1,3,4]
     * 求 a - b
     */

    /**
     * 自然语言描述
     * 1 遍历数组b,存放到map
     * 2 遍历数组a,从i=0到 i < nums.length 定义下标下一个要插入的元素下标cur，如果map不包含 nums[cur]，则不做处理， cur++
     *      如果map包含nums[cur]， 则把 nums[cur++]=nums[i]
     * 3 删除后导致数组变短可根据题意处理，全部置为0或者null
     */
    public void solution(int[] a, int[] b){

        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < b.length; j++){
            if (!map.containsKey(b[j])){
                map.put(b[j], j);
            }
        }

        int cur = 0;
        for (int i = 0; i < a.length; i++){
            if (!map.containsKey(a[i])){
                a[cur++] = a[i];
            }
        }

        for (int i = a.length -1 ; i >= cur; i--){
            a[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,2,2,1,2,7,8,5,3};
        int[] b = {1,3,4};
        SolutionAB solutionAB = new SolutionAB();
        solutionAB.solution(a, b);

        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
