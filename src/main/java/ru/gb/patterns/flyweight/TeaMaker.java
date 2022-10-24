package ru.gb.patterns.flyweight;

import java.util.ArrayList;
import java.util.List;

public class TeaMaker {
    private final List<KarakTea> availableTea;

    public TeaMaker() {
        availableTea = new ArrayList<>();
    }

    public KarakTea make(KarakTea tea) {
        if (availableTea.contains(tea)) {
            int index = availableTea.indexOf(tea);
            availableTea.set(index, new KarakTea());
            return availableTea.get(index);
        } else return tea;
    }
}