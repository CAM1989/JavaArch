package ru.gb.patterns.Bridge.pages;

import ru.gb.patterns.Bridge.thems.Theme;

public class Careers implements WebPage{
    private final Theme theme;

    public Careers(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void getContent() {
        System.out.println("Careers page in " + this.theme.getColor());
    }
}
