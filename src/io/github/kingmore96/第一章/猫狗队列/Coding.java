package io.github.kingmore96.第一章.猫狗队列;

import io.github.kingmore96.第一章.猫狗队列.ori.Cat;
import io.github.kingmore96.第一章.猫狗队列.ori.Dog;
import io.github.kingmore96.第一章.猫狗队列.ori.Pet;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 题目描述：
 * •用户可以调用add方法将cat类或dog类的实例放入队列中；
 * •用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
 * •用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出；
 * •用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出；
 * •用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
 * •用户可以调用isDogEmpty方法，检查队列中是否有dog类的实例；
 * •用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
 */
public class Coding {

    private class NewPet {
        private Pet pet;
        private Long inQueueTime;

        public NewPet(Pet pet, Long inQueueTime) {
            this.pet = pet;
            this.inQueueTime = inQueueTime;
        }

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        public Long getInQueueTime() {
            return inQueueTime;
        }

        public void setInQueueTime(Long inQueueTime) {
            this.inQueueTime = inQueueTime;
        }
    }

    private Queue<NewPet> dogQueue = new LinkedBlockingQueue<>();

    private Queue<NewPet> catQueue = new LinkedBlockingQueue<>();

    public void add(Pet pet) {
        NewPet newPet = new NewPet(pet, System.currentTimeMillis());
        if (pet instanceof Cat) {
            catQueue.add(newPet);
        } else if (pet instanceof Dog) {
            dogQueue.add(newPet);
        } else {
            throw new RuntimeException("add error，不是猫也不是狗，不能传入");
        }
    }

    public Pet pollAll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法弹出数据！");
        }

        if (isCatEmpty()) {
            return dogQueue.poll().getPet();
        }

        if (isDogEmpty()) {
            return catQueue.poll().getPet();
        }

        NewPet catPeek = catQueue.peek();
        NewPet dogPeek = dogQueue.peek();
        if (catPeek.inQueueTime < dogPeek.inQueueTime) {
            return catQueue.poll().getPet();
        } else {
            return dogQueue.poll().getPet();
        }
    }

    public Pet pollDog() {
        if (isDogEmpty()) {
            throw new RuntimeException("队列为空，无法弹出数据！");
        }
        return dogQueue.poll().getPet();
    }

    public Pet pollCat() {
        if (isCatEmpty()) {
            throw new RuntimeException("队列为空，无法弹出数据！");
        }
        return catQueue.poll().getPet();
    }

    public boolean isEmpty() {
        return catQueue.isEmpty() && dogQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public static void main(String[] args) {
        Coding coding = new Coding();
        coding.add(new Cat());
        coding.add(new Dog());
        coding.add(new Cat());

        coding.pollAll();
    }

}
