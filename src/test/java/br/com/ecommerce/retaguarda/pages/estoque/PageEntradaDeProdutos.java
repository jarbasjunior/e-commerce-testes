package br.com.ecommerce.retaguarda.pages.estoque;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;

public class PageEntradaDeProdutos extends BasePage<PageEntradaDeProdutos> {

	public PageEntradaDeProdutos() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/span")
	WebElement user;
	
	public void sairDoSistema() {
		Log.info("Saindo do retaguarda...");
		click(user);
	}
}
