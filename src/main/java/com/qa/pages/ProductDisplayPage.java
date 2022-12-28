package com.qa.pages;

import com.microsoft.playwright.Page;

public class ProductDisplayPage {


    Page page;

    private String addToCart="//input[contains(@id,'cart-button')]";
    private String buyNow="//input[contains(@id,'buy-now-button')]";
    private String buyNowText="Buy Now";



    public ProductDisplayPage(Page page) {
        this.page=page;
    }

    public void setAddToCart(){

        page.waitForSelector(addToCart);
        page.locator(addToCart).click();
    }
    public void clickBuyNow(){
//        page.waitForSelector(buyNow);
        page.evaluate("document.getElementById(\"buy-now-button\").scrollIntoView();");
        page.locator(buyNow).click();
    }

}
