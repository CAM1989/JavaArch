package ru.gb.patterns.flyweight;

public class Order {
    private final KarakTea tea;
    private final Integer table;

    public Order(KarakTea tea, Integer table) {
        this.tea = tea;
        this.table = table;
    }

    public KarakTea getTea() {
        return tea;
    }

    public Integer getTable() {
        return table;
    }
}