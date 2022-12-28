package com.qa.pages;

import com.microsoft.playwright.Page;

public class ProductListPage {

    Page page;

    private String item="//span[contains(text(),  'Atmos and Dolby Vision')]";

    public ProductListPage(Page page) {
        this.page=page;
    }

    public void selectItemfromList(){
        page.locator(item).click();
    }

}
