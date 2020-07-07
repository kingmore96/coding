package io.github.kingmore96.第一章.用栈来求解汉诺塔问题;

import java.util.Stack;

/**
 * 方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。
 */
public class CodingUseStack {

    public enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        //record[0]记录的是上一步成功的行为
        Action[] record = {Action.No};
        int step = 0;
        //循环终止条件num+1是因为，之前压入了一个max_value
        while (rS.size() != num + 1) {
//            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS,
//                    left, mid);
//            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS,
//                    mid, left);
//            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS,
//                    mid, right);
//            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS,
//                    right, mid);
            //上面的解法不是很直观，可以这么写
            //初始步，一定走L-M
            if (Action.No.equals(record[0])) {
                mS.push(lS.pop());
                System.out.println("Move " + mS.peek() + " from " + left + " to " + mid);
                step += 1;
                record[0] = Action.LToM;
            } else {
                //上一步是L-M或者M-L
                if (Action.LToM.equals(record[0]) || Action.MToL.equals(record[0])) {
                    //可以走M-R
                    if (mS.peek() < rS.peek()) {
                        rS.push(mS.pop());
                        System.out.println("Move " + rS.peek() + " from " + mid + " to " + right);
                        step += 1;
                        record[0] = Action.MToR;
                    } else {
                        //可以走R-M
                        mS.push(rS.pop());
                        System.out.println("Move " + mS.peek() + " from " + right + " to " + mid);
                        step += 1;
                        record[0] = Action.RToM;
                    }
                    //上一步是R-M或者M-R
                } else if (Action.MToR.equals(record[0]) || Action.RToM.equals(record[0])) {
                    //可以走M-L
                    if (mS.peek() < lS.peek()) {
                        lS.push(mS.pop());
                        System.out.println("Move " + lS.peek() + " from " + mid + " to " + left);
                        step += 1;
                        record[0] = Action.MToL;
                    } else {
                        //可以走L-M
                        mS.push(lS.pop());
                        System.out.println("Move " + mS.peek() + " from " + left + " to " + mid);
                        step += 1;
                        record[0] = Action.LToM;
                    }
                }
            }
        }
        return step;
    }

    public static int fStackTotStack(Action[] record, Action preNoAct,
                                     Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        hanoiProblem2(3, "left", "mid", "right");
    }

}
