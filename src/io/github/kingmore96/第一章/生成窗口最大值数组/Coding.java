package io.github.kingmore96.第一章.生成窗口最大值数组;

import java.util.LinkedList;

/**
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 例如，数组为[4，3，5，4，3，3，6，7]，窗口大小为3时：
 * [4  3  5] 4  3  3  6  7              窗口中最大值为5
 * 4 [3  5  4] 3  3  6  7              窗口中最大值为5
 * 4  3 [5  4  3] 3  6  7              窗口中最大值为5
 * 4  3  5 [4  3  3] 6  7              窗口中最大值为4
 * 4  3  5  4 [3  3  6] 7              窗口中最大值为6
 * 4  3  5  4  3 [3  6  7]              窗口中最大值为7
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数。
 * •输入：整型数组arr，窗口大小为w。
 * •输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值。
 * 以本题为例，结果应该返回{5，5，5，4，6，7}。
 */
public class Coding {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //队列里面保存的是数组的位置而不是值
            //双端队列的头部到尾部，依次保存着小值和较小值，如果新的数据大于qmax中的所有，依次弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            //处理过期头部
            // * 4  3 [5  4  3] 3  6  7              窗口中最大值为5
            // * 4  3  5 [4  3  3] 6  7              窗口中最大值为4
            //这个时候应该把5弹出去了，失效了
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            //只要大于等于2，每一步都应该生成一个最大值（双端队列的头部）
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    public static int[] getMinWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        LinkedList<Integer> qmin = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmin.isEmpty() && arr[qmin.peekLast()] > arr[i]) {
                qmin.pollLast();
            }
            qmin.addLast(i);

            //判断是否过期
            if (qmin.peekFirst() == i - w) {
                qmin.pollFirst();
            }
            //判断是否可以生成窗口最小值
            if (i >= w - 1) {
                res[index++] = arr[qmin.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{4, 3, 5, 4, 2, 3, 6, 0};
//        int[] maxInts = getMaxWindow(ints, 3);
        int[] minWindow = getMinWindow(ints, 3);
        for (int minInt : minWindow) {
            System.out.println(minInt);
        }
    }

}
