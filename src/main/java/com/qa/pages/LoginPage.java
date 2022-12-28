package com.qa.pages;

import com.microsoft.playwright.Page;

public class LoginPage {


    private String username="//input[@type='email']";
    private String continueBtn="//input[@id='continue']";

    private String password="//input[@type='password']";
    private String signIn="//input[@id='signInSubmit']";

    Page page;



    public LoginPage(Page page) {
        this.page=page;
    }

    public void fillLoginDetailsAndLogin(){
        page.waitForSelector(username);
        page.locator(username).fill("7985024753");
        page.locator(continueBtn).click();
        page.locator(password).fill("@Hello123");
        page.locator(signIn).click();

    }


}
