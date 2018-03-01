package br.com.ecommerce.lojavirtual.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageHomeLojaVirtual extends PageObjectGeneric<PageHomeLojaVirtual> {

	public PageHomeLojaVirtual() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='header']/div[1]/div/div/div[1]/div/ul/li[1]/a")
	WebElement telefoneCompanhia;
	
	@FindBy(xpath = "//*[@id='header']/div[1]/div/div/div[1]/div/ul/li[2]/a")
	WebElement emailCompanhia;
	
	@FindBy(xpath = "//*[@id='footer']/div[1]/div/div/div[3]/div/p")
	WebElement enderecoCompanhia;
	
	@FindBy(xpath = "//*[@id='header']/div[2]/div/div/div[1]/div/a/img")
	WebElement logoCompanhia;
	
	public void conferirDadosLojaVirtual(String telefone, String endereco, String email) {
		aguardarElementoVisivel(logoCompanhia);
		
		Log.info("Conferindo alterações de configurações na home page da loja virtual...");
		Utils.assertEquals(telefoneCompanhia.getText(), telefone);
		Utils.assertEquals(emailCompanhia.getText()   , email);
		Utils.assertEquals(enderecoCompanhia.getText(), endereco);
		Log.info("Alterações validadas com sucesso na loja virtual.");
	}
}
