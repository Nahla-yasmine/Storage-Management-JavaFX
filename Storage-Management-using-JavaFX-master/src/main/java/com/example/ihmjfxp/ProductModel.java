package com.example.ihmjfxp;
/* --  Storage management Project  --
 * This program was written by @Latif & @NahlaMih
 * for the IHM TP project (L3-ISIL)
 */
public class ProductModel {

        private int product_id ;
        private int discount;
        private int price ;
        private String category ;
        private String product_name ;
        private int product_quantity ;
        private String exp_date ;

    public ProductModel ( int product_id ,String product_name ,int discount  , int price ,String category , int product_quantity  , String exp_date ) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.discount = discount;
        this.price = price;
        this.category = category;
        this.product_quantity = product_quantity;
        this.exp_date = exp_date;
    }
    public ProductModel (){ }
//-------------------------------------------------------
        public int getProduct_id() {
            return this.product_id;
        }

        public void setProduct_id(final int product_id) {
            this.product_id = product_id;
        }
//-------------------------------------------------------

        public int getDiscount() {
            return this.discount;
        }

        public void setDiscount(final int discount) {
            this.discount = discount;
        }
    //-------------------------------------------------------
        public int getPrice() {
            return this.price;
        }

        public void setPrice(final int price) {
            this.price = price;
        }
    //-------------------------------------------------------
        public String getCategory() {
            return this.category;
        }

        public void setCategory(final String category) {
            this.category = category;
        }
    //-------------------------------------------------------
        public String getProduct_name() {
            return this.product_name;
        }

        public void setProduct_name(final String product_name) {
            this.product_name = product_name;
        }
    //-------------------------------------------------------
    public int getProduct_quantity() {
        return this.product_quantity;
    }

    public void setProduct_quantity(final int product_quantity) {
        this.product_quantity = product_quantity;
    }
    //-------------------------------------------------------
    public String getExp_date() {
        return this.exp_date;
    }

    public void setExp_date(final String exp_date) {
        this.exp_date = exp_date;
    }
    //-------------------------------------------------------
    }