package ru.gb.mvn;

public class Main {
    public static void main(String[] args) {
        ProductModel model = new ProductModel("Milk", 68.99);
        ProductView view = new ProductView();
        ProductController controller = new ProductController(model, view);
        controller.updateView();
        controller.setPrice(controller.getPrice() + 250);
        controller.setTitle("Coffee");
        controller.updateView();
    }
}