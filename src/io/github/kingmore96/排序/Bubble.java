package io.github.kingmore96.排序;

import java.util.Random;

/**
 * 冒泡排序
 */
public class Bubble {

    /**
     * 冒泡排序，升序
     *
     * @param ori
     * @return
     */
    public static int[] bubbleSortAsc(int[] ori) {
        return bubbleSortInner(ori, true);
    }


    /**
     * 冒泡排序，降序
     *
     * @param ori
     * @return
     */
    public static int[] bubbleSortDesc(int[] ori) {
        return bubbleSortInner(ori, false);
    }

    private static int[] bubbleSortInner(int[] ori, boolean isAsc) {
        int length = ori.length;
        boolean needContinue = true;
        for (int i = 0; i <= length - 2 && needContinue; i++) {
            needContinue = false;
            for (int j = length - 1; j > i; j--) {
                boolean needSwap;
                if (isAsc) {
                    needSwap = ori[j] < ori[j - 1] ? true : false;
                } else {
                    needSwap = ori[j] > ori[j - 1] ? true : false;
                }
                if (needSwap) {
                    swap(ori, j, j - 1);
                    needContinue = true;
                }
            }
        }
        return ori;
    }

    /**
     * 交换函数
     *
     * @param ori
     * @param j
     * @param i
     */
    private static void swap(int[] ori, int j, int i) {
        int tmp = ori[j];
        ori[j] = ori[i];
        ori[i] = tmp;
    }

    /**
     * 测试函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int[] ori = generateIntArray(size);
        int[] sorts = bubbleSortAsc(ori);
        //int[] sorts = bubbleSortDesc(ori);
        iteratorIntArray(sorts);
    }

    public static int[] generateIntArray(int size) {
        int[] ori = new int[size];
        for (int i = 0; i < size; i++) {
            ori[i] = new Random().nextInt(size);
        }
        return ori;
    }

    public static void iteratorIntArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
