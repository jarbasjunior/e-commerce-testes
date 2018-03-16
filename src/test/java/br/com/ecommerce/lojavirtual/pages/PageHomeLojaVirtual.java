package br.com.ecommerce.lojavirtual.pages;

import org.openqa.selenium.By;
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
	
	public void conferirDadosCompanhiaNaLojaVirtual(String telefone, String endereco, String email) {
		aguardarElementoVisivel(logoCompanhia);
		
		Log.info("Conferindo alterações de configurações na home page da loja virtual...");
		Utils.assertEquals(telefoneCompanhia.getText(), telefone);
		Utils.assertEquals(emailCompanhia.getText()   , email);
		Utils.assertEquals(enderecoCompanhia.getText(), endereco);
		Log.info("Alterações validadas com sucesso na loja virtual.");
	}
	
	public void conferirCategoriaPrincipalNaLojaVirtual(String categoria) {
		aguardarElementoVisivel(logoCompanhia);
		
		WebElement categoriaPrincipal = Selenium.getDriver().findElement(By.xpath("//*[@id='header']//a[text()='"+categoria+"']"));
		Log.info("Conferindo inclusão de categoria na home page da loja virtual...");
		Utils.assertEquals(categoriaPrincipal.getText(), categoria);
		Log.info("Inclusão de categoria principal validada com sucesso na loja virtual.");
	}
	
	public void conferirExclusaoCategoriaNaLojaVirtual(String categoria) {
		aguardarElementoVisivel(logoCompanhia);
		Log.info("Conferindo exclusão de categoria na home page da loja virtual...");
		By by = By.xpath("//*[@id='header']//a[text()='"+categoria+"']");
		Utils.assertTrue("Categoria ["+categoria+"] não foi exculída na home page da loja virtual", !isVisibility(by));
		Log.info("Exclusão de categoria validada com sucesso na loja virtual.");
	}
	
	public void conferirExclusaoSubategoriaNaLojaVirtual(String categoria, String subcategoria) {
		aguardarElementoVisivel(logoCompanhia);
		Log.info("Conferindo exclusão de categoria na home page da loja virtual...");
		By categoriaFilho     = By.xpath("//*/a[text()='"+subcategoria+"']");
		By categoriaPrincipal = By.xpath("//*[@id='header']//a[text()='"+categoria+"']");
		moverCursorPara(Selenium.getDriver().findElement(categoriaPrincipal));
		Utils.wait(1000);
		Utils.assertTrue("Categoria ["+categoria+"] não foi exculída na home page da loja virtual", !isVisibility(categoriaFilho));
		Log.info("Exclusão de categoria validada com sucesso na loja virtual.");
	}
	
	public void conferirSubcategoriaNaLojaVirtual(String categoria, String subcategoria) {
		aguardarElementoVisivel(logoCompanhia);
		
		WebElement categoriaFilho     = Selenium.getDriver().findElement(By.xpath("//*/a[text()='"+subcategoria+"']"));
		WebElement categoriaPrincipal = Selenium.getDriver().findElement(By.xpath("//*[@id='header']//a[text()='"+categoria+"']"));
		Log.info("Conferindo inclusão de subcategoria na home page da loja virtual...");
		moverCursorPara(categoriaPrincipal);
		Utils.wait(1000);		
		Utils.assertEquals(categoriaFilho.getText(), subcategoria);
		Log.info("Inclusão de subcategoria validada com sucesso na loja virtual.");
	}
}
