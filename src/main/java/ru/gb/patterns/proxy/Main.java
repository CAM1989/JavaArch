package ru.gb.patterns.proxy;

public class Main {
    public static void main(String[] args) {
        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("password");
        door.open("$ecr@t");
        door.close();
    }
}