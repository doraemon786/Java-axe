package com.qa.pages;

import com.microsoft.playwright.Page;

public class CheckOutPage {

    Page page;

    private String item="//span[contains(text(),  'Atmos and Dolby Vision')]";


    public CheckOutPage(Page page) {
        this.page=page;
    }

    public void addtocart(){

    }



}
