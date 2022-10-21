package ru.gb.patterns.proxy;

public class SecuredDoor{
    private Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    public void open(String password) {
        if(authenticate(password)){
            this.door.open();
        } else{
            System.out.println("Big no! It ain't possible.");
        }

    }

    private boolean authenticate(String password)
    {
        return password.equals("$ecr@t");
    }
    public void close() {
        door.close();
    }
}