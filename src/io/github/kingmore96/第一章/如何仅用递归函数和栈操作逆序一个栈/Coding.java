package io.github.kingmore96.第一章.如何仅用递归函数和栈操作逆序一个栈;

import java.util.Stack;

/**
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序
 * 但是只能用递归函数来实现，不能用其他数据结构。
 */
public class Coding {

    /**
     * 把栈底的数据冒泡冒出来
     * @param stack
     * @return
     */
    public Integer getAndRemoveLastElement(Stack<Integer> stack) {
        Integer pop = stack.pop();
        //只有到最后一层的1的时候，才走这块的逻辑
        if (stack.isEmpty()) {
            return pop;
        } else {
            //其他层都走这块，用一个变量保存得到的栈底元素，然后把自己重新压入栈，返回栈底元素，这样栈底元素就通过一层层函数调用返回了
            int lastOne = getAndRemoveLastElement(stack);
            stack.push(pop);
            return lastOne;
        }
    }

    /**
     * 递归函数到最后一层时，栈已经空了，这个时候把3，2，1再依次压入就可以形成逆序
     * @param stack
     */
    public void reverse(Stack<Integer> stack) {
        int last = getAndRemoveLastElement(stack);
        if (!stack.empty()) {
            reverse(stack);
        }
        stack.push(last);
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        Stack<Integer> stack = new Stack<>();
        for (int anInt : ints) {
            stack.push(anInt);
        }

        Coding coding = new Coding();
        coding.reverse(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }
}
