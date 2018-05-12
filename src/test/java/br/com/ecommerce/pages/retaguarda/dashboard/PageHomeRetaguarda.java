package br.com.ecommerce.pages.retaguarda.dashboard;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;
public class PageHomeRetaguarda extends BasePage {

	public PageHomeRetaguarda() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/span")
	private WebElement user;
	
	@FindBy(xpath = "//*[@id='container']/header/div[2]/ul/li/a/b")
	private WebElement btDropDownUser;
	
	@FindBy(id = "logout")
	private WebElement btSair;
	
	public void verificaAutenticidadeUsuario(){
		aguardarElementoVisivel(user);
		Log.info("Verificando autenticidade do usuário...");
		Utils.assertEquals(getTextElement(user), Property.USR);
		Log.info("Login realizado com sucesso.");
	}
	
	public void sairDoRetaguarda() {
		Log.info("Realizando logout do retaguarda...");
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		btDropDownUser.click();
		btSair.click();
		Utils.wait(1500);
	}
}
