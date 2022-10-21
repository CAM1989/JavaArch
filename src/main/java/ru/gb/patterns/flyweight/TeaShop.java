package ru.gb.patterns.flyweight;

import java.util.ArrayList;
import java.util.List;

public class TeaShop {
    private final List<Order> orders;
    private final TeaMaker teaMaker;

    public TeaShop(TeaMaker teaMaker) {
        this.teaMaker = teaMaker;
        this.orders = new ArrayList<>();
    }

    public void takeOrder(KarakTea tea, int table) {
        Order newOrder = new Order(teaMaker.make(tea), table);
        orders.add(newOrder);
    }

    public void serve() {
        for (Order order : orders) {
            System.out.println("Serving tea to table# " + order.getTable());
        }
    }
}