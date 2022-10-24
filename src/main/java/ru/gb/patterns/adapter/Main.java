package ru.gb.patterns.adapter;

public class Main {
    public static void main(String[] args) {
        WildDog dog = new WildDog();
        Lion wildDogAdapter = new WildDogAdapter(dog);
        Hunter hunter = new Hunter();
        hunter.hunt(wildDogAdapter);
    }
}
