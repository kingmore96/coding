package io.github.kingmore96.第一章.由两个栈组成的队列;

import java.util.Stack;

/**
 * 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。
 */
public class Coding {

    private Stack<Integer> stackForPut = new Stack<>();
    private Stack<Integer> stackForGet = new Stack<>();

    public Integer add(Integer item) {
        return stackForPut.push(item);
    }

    public Integer poll() {
        syncData();
        return stackForGet.pop();
    }

    public Integer peek() {
        syncData();
        return stackForGet.peek();
    }

    private void syncData() {
        if (stackForGet.empty()) {
            //同步put中的数据到get
            if (stackForPut.empty()) {
                throw new RuntimeException("队列为空");
            } else {
                //TODO 2如果直接使用iterator，会从栈底往上拿数据，不正确
//                for (Integer integer : stackForPut) {
//                    System.out.println("iterator:"+integer);
//                }

                //TODO 3还有一种方式，直接使用while
//                while(!stackForPut.empty()){
//                    stackForGet.push(stackForPut.pop());
//                }

                //TODO 1易错点，每次弹出的时候，size就变了，注意要使用原始的size
                int oriSize = stackForPut.size();
                for (int i = 0; i < oriSize; i++) {
                    stackForGet.push(stackForPut.pop());
                }
            }
        }
    }


    public static void main(String[] args) {
        Coding coding = new Coding();
        int[] ints = new int[]{1, 2, 3, 4, 5};
        for (int anInt : ints) {
            coding.add(anInt);
        }
        System.out.println(coding.poll());
        ints = new int[]{6, 7, 8, 9, 10};
        for (int anInt : ints) {
            coding.add(anInt);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(coding.poll());
        }
    }
}
