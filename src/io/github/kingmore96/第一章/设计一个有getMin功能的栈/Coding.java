package io.github.kingmore96.第一章.设计一个有getMin功能的栈;

import java.util.Stack;

/**
 * 1.pop、push、getMin操作的时间复杂度都是O（1）。
 * 2.设计的栈类型可以使用现成的栈结构。
 */
public class Coding {

    private Stack<Integer> integerStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public Integer pop() {
        minStack.pop();
        return integerStack.pop();
    }

    public Integer push(Integer integer) {
        //如果栈为空，直接压入
        if (minStack.empty()) {
            minStack.push(integer);
        } else {
            Integer min = minStack.peek();
            if (integer > min) {
                minStack.push(min);
            } else {
                minStack.push(integer);
            }
        }
        return integerStack.push(integer);
    }

    public Integer getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2, 3, 5, 2, 1, 4};
        Coding coding = new Coding();
        for (int anInt : ints) {
            coding.push(anInt);
            System.out.println("min:" + coding.getMin());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("pop:" + coding.pop());
            System.out.println("min:" + coding.getMin());
        }
    }
}
