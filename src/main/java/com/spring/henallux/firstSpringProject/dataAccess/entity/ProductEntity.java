package com.spring.henallux.firstSpringProject.dataAccess.entity;


import javax.persistence.*;


@Entity
@Table(name="Product")
public class ProductEntity {

        @Id
        @Column(name="productID")
        private int productID;

        @Column(name="labelPRODUCT")
        private String labelProduct;

        @Column(name = "imagePATH")
        private String imagePath;

        @Column(name="unitPRICEEXCLUDINGTAX")
        private double unitPriceExcludingTax;

        @Column(name="vATRATE")
        private int vATRate;

        @Column(name="quantityINSTOCK")
        private int quantityInStock;

        @Column(name="descriptionFR")
        private String descriptionFR;

        @Column(name="descriptionEN")
        private String descriptionEN;

        @Column(name="categoryID")
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
            return vATRate;
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
