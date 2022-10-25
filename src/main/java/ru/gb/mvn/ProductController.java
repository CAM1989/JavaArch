package ru.gb.mvn;

public class ProductController {
    private final ProductModel model;
    private final ProductView view;

    public ProductController(ProductModel model, ProductView view) {
        this.model = model;
        this.view = view;
    }
    public String getTitle() {
        return model.getTitle();
    }

    public void setTitle(String title) {
        model.setTitle(title);
    }

    public Double getPrice() {
        return model.getPrice();
    }

    public void setPrice(Double price) {
        model.setPrice(price);
    }

    public void updateView(){
        view.displayModel(model);
    }
}