package com.spring.henallux.firstSpringProject.model;

public class Product {
    private Integer productID;
    private String labelProduct;
    private String imagePath;
    private double unitPriceExcludingTax;
    private Integer vATRate;
    private Integer loyaltyPointsValue;
    private Integer quantityInStock;
    private String descriptionFR;
    private String descriptionEN;
    private Category category;


    public Product() {
    }

    public Product(Integer productID, String labelProduct, String imagePath, double unitPriceExcludingTax, Integer vATRate, Integer loyaltyPointsValue, Integer quantityInStock, String descriptionFR, String descriptionEN, Category category) {
        this.productID = productID;
        this.labelProduct = labelProduct;
        this.imagePath = imagePath;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vATRate = vATRate;
        this.loyaltyPointsValue = loyaltyPointsValue;
        this.quantityInStock = quantityInStock;
        this.descriptionFR = descriptionFR;
        this.descriptionEN = descriptionEN;
        this.category = category;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getLabelProduct() {
        return labelProduct;
    }

    public void setLabelProduct(String labelProduct) {
        this.labelProduct = labelProduct;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getUnitPriceExcludingTax() {
        return unitPriceExcludingTax;
    }

    public void setUnitPriceExcludingTax(double unitPriceExcludingTax) {
        this.unitPriceExcludingTax = unitPriceExcludingTax;
    }

    public Integer getVATRate() {
        return vATRate;
    }

    public void setVATRate(Integer vATRate) {
        this.vATRate = vATRate;
    }

    public Integer getLoyaltyPointsValue() {
        return loyaltyPointsValue;
    }

    public void setLoyaltyPointsValue(Integer loyaltyPointsValue) {
        this.loyaltyPointsValue = loyaltyPointsValue;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDescriptionFR() {
        return descriptionFR;
    }

    public void setDescriptionFR(String descriptionFR) {
        this.descriptionFR = descriptionFR;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
