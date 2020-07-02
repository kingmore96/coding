package io.github.kingmore96.第一章.猫狗队列.ori;

public abstract class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }
}