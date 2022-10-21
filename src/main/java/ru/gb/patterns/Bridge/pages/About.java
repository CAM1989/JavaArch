package ru.gb.patterns.Bridge.pages;

import ru.gb.patterns.Bridge.thems.Theme;

public class About implements WebPage{
    private final Theme theme;

    public About(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void getContent() {
        System.out.println("About page in " + this.theme.getColor());
    }
}
