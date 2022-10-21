package ru.gb.patterns.Bridge;

import ru.gb.patterns.Bridge.pages.About;
import ru.gb.patterns.Bridge.pages.Careers;
import ru.gb.patterns.Bridge.pages.WebPage;
import ru.gb.patterns.Bridge.thems.DarkTheme;
import ru.gb.patterns.Bridge.thems.Theme;

public class Main {
    public static void main(String[] args) {
        Theme darkThem = new DarkTheme();
        WebPage about = new About(darkThem);
        WebPage careers = new Careers(darkThem);
        about.getContent();
        careers.getContent();
    }
}