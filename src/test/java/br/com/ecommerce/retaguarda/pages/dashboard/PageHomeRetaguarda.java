package br.com.ecommerce.retaguarda.pages.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageHomeRetaguarda extends PageObjectGeneric<PageHomeRetaguarda> {

	public PageHomeRetaguarda() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/span")
	WebElement user;
	
	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/b")
	WebElement btDropDownUser;
	
	@FindBy(id = "logout")
	WebElement btSair;
	
	public void verificaAutenticidadeUsuario(){
		aguardarElementoVisivel(user);
		Log.info("Verificando autenticidade do usuário...");
		Utils.assertEquals(user.getText(), Property.USR);
		Log.info("Login realizado com sucesso.");
	}
	
	public void sairDoRetaguarda() {
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		btDropDownUser.click();
		btSair.click();
		Utils.wait(2000);
		Log.info("Logout realizado com sucesso.");
	}
}
