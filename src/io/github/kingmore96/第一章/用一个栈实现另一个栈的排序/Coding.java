package io.github.kingmore96.第一章.用一个栈实现另一个栈的排序;

import java.util.Stack;

/**
 * 一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。
 * 除此之外，可以申请新的变量，但不能申请额外的数据结构。
 */
public class Coding {

    public static void sortByStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("栈为空，不需要排序");
        }

        if (stack.size() == 1) {
            return;
        }

        //初始化tmpStack
        Stack<Integer> helpStack = new Stack<>();

        while (!stack.isEmpty()) {
            findAndPush(stack.pop(), helpStack);
        }

        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }

    private static void findAndPush(Integer pop, Stack<Integer> tmpStack) {
        if (tmpStack.isEmpty()) {
            tmpStack.push(pop);
            return;
        }
        Integer tmpStackInt = tmpStack.pop();
        if (pop < tmpStackInt) {
            tmpStack.push(tmpStackInt);
            tmpStack.push(pop);
            return;
        } else {
            findAndPush(pop, tmpStack);
            tmpStack.push(tmpStackInt);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < 10; i++) {
//            stack.push(i);
//        }
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(9);
        stack.push(0);
        sortByStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
