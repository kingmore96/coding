package io.github.kingmore96.第一章.用栈来求解汉诺塔问题;

/**
 * 汉诺塔问题比较经典
 * 这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。
 * 求当塔有N层的时候，打印最优移动过程和最优移动总步数。
 * 例如，当塔数为两层时，最上层的塔记为1，最下层的塔记为2，则打印：
 * Move 1 from left to mid
 * Move 1 from mid to right
 * Move 2 from left to mid
 * <p>
 * Move 1 from right to mid
 * Move 1 from mid to left
 * Move 2 from mid to right
 * Move 1 from left to mid
 * Move 1 from mid to right
 * It will move 8 steps.
 * <p>
 * 用以下两种方法解决。
 * •方法一：递归的方法；
 * •方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。
 */
public class Coding {

    /**
     * 汉诺塔解法
     * @param num 汉诺塔层数
     * @param left left字符串
     * @param mid mid字符串
     * @param right right字符串
     * @return 总最优步数
     */
    public static int hanoiProblem1(int num, String left, String mid,
                             String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
        //return process(num, left, mid, right, left, mid);
    }


    /**
     * 真正的递归方法，包含了从左（右）移动到中、从中移动到左（右）和 从左（右）移动到右（左） 这两类汉诺塔问题
     * @param num
     * @param left
     * @param mid
     * @param right
     * @param from
     * @param to
     * @return
     */
    public static int process(int num, String left, String mid, String right,
                       String from, String to) {
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    public static void main(String[] args) {
        hanoiProblem1(6,"left","mid","right");
    }

}
