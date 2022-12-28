package com.qa.testcases;

import com.deque.html.axecore.playwright.AxeBuilder;
import com.qa.pages.AmazonHomePage;
import com.qa.pages.ProductDisplayPage;
import com.qa.pages.ProductListPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProductDisplayTest extends BaseTest {

    private AmazonHomePage amazonHomePage;

    private ProductListPage productListPage;

    private ProductDisplayPage productDisplayPage;


    private AxeBuilder axeBuilder ;

    @BeforeClass
    public void init(){
        amazonHomePage=new AmazonHomePage(page);
        productListPage=new ProductListPage(page);
        productDisplayPage=new ProductDisplayPage(page);
        amazonHomePage.search("TV");
        productListPage.selectItemfromList();
    }
    @Test
    public void testCheckoutPage(){
    }

}
