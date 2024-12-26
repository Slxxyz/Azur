package com.spring.henallux.firstSpringProject.dataAccess.entity;


import javax.persistence.*;


@Entity
@Table(name="Product")
public class ProductEntity {

        @Id
        @Column(name="product_id")
        private int productID;

        @Column(name="label_product")
        private String labelProduct;

        @Column(name = "image_path")
        private String imagePath;

        @Column(name="unit_price_excluding_tax")
        private double unitPriceExcludingTax;

        @Column(name="vat_rate")
        private int vatRate;

        @Column(name="quantity_in_stock")
        private int quantityInStock;

        @Column(name="description_fr")
        private String descriptionFR;

        @Column(name="description_en")
        private String descriptionEN;

        @Column(name="category_id")
        private int categoryID;

        public ProductEntity() {
        }

        public int getProductID() {
            return productID;
        }

        public String getLabelProduct() {
            return labelProduct;
        }

        public String getImagePath() {
            return imagePath;
        }

        public double getUnitPriceExcludingTax() {
            return unitPriceExcludingTax;
        }

        public int getVATRate() {
            return vatRate;
        }

        public int getQuantityInStock() {
            return quantityInStock;
        }

        public String getDescriptionFR() {
            return descriptionFR;
        }

        public String getDescriptionEN() {
            return descriptionEN;
        }

        public int getCategoryID() {
            return categoryID;
        }

}
