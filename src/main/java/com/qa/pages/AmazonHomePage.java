package com.qa.pages;
import com.microsoft.playwright.Page;

public class AmazonHomePage {
	Page page;
	
	private String search="//input[@id='twotabsearchtextbox']";
	private String searchBtn="//input[contains(@id,'submit-button')]";
	
	public AmazonHomePage(Page page) {
		this.page=page; 
	}	
	
	
	public void search(String searchTxt)  {
		page.locator(search).fill(searchTxt);
		page.locator(searchBtn).click();
	}

	
	
}
