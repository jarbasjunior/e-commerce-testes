package br.com.ecommerce.config.pagebase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;

public class PageLoginRetaguarda extends PageObjectGeneric<PageLoginRetaguarda> {

	public PageLoginRetaguarda() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(id = "session_email")
	WebElement fieldEmail;
	
	@FindBy(id = "session_password")
	WebElement fieldSenha;
	
	@FindBy(name = "commit")
	WebElement btEntrar;
	
	public void realizarLoginRetaguarda(){
		Log.info("Informando email...");
		preencherCampo(fieldEmail, Property.EMAIL);
		Log.info("Informando senha...");
		preencherCampo(fieldSenha, Property.PASSWORD);
		waitAndClick(btEntrar);
		Log.info("Direcionando para retaguarda...");
	}
	
	public void driveNaPaginaLogin() {
		Log.info("Retornando para página de login...");
		aguardarElementoVisivel(fieldEmail);
		Log.info("Página de login carregada.");
	}
}
