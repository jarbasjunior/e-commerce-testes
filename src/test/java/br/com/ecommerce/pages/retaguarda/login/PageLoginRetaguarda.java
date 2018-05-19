package br.com.ecommerce.pages.retaguarda.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.config.DriverFactory;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.util.Log;

public class PageLoginRetaguarda extends BasePage {

	public PageLoginRetaguarda() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(id = "session_email")
	WebElement fieldEmail;
	
	@FindBy(id = "session_password")
	WebElement fieldSenha;
	
	@FindBy(name = "commit")
	WebElement btEntrar;
	
	public void realizarLoginRetaguarda(){
		openPageRetaguarda();
		Log.info("Informando email...");
		preencherCampo(fieldEmail, Property.EMAIL);
		Log.info("Informando senha...");
		preencherCampo(fieldSenha, Property.PASSWORD);
		click(btEntrar);
		Log.info("Direcionando para retaguarda...");
	}
	
	public void openPageRetaguarda(){
		Log.info("Navegando para retaguarda...");
		DriverFactory.getDriver().navigate().to(Property.URL_RETAGUARDA);
		aguardarElementoVisivel(btEntrar);
	}
	
	public void driveNaPaginaLogin() {
		aguardarElementoVisivel(btEntrar);
		Log.info("Página de login carregada.");
	}
}
