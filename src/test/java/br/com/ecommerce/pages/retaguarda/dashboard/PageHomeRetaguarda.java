package br.com.ecommerce.pages.retaguarda.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageHomeRetaguarda extends BasePage<PageHomeRetaguarda> {

	public PageHomeRetaguarda() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
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
		Utils.assertEquals(getTextElement(user), Property.USR);
		Log.info("Login realizado com sucesso.");
	}
	
	public void sairDoRetaguarda() {
		Log.info("Realizando logout do retaguarda...");
		DriverFactory.getDriver().navigate().to(Property.URL_RETAGUARDA);
		btDropDownUser.click();
		btSair.click();
		Utils.wait(1500);
	}
}
