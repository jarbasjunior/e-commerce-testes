package br.com.ecommerce.lojavirtual.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageHomeLojaVirtual extends BasePage<PageHomeLojaVirtual> {

	public PageHomeLojaVirtual() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
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
		Utils.assertEquals(getTextElement(telefoneCompanhia), telefone);
		Utils.assertEquals(getTextElement(emailCompanhia)   , email);
		pageDown(telefoneCompanhia);
		Utils.assertEquals(getTextElement(enderecoCompanhia), endereco);
		Log.info("Alterações validadas com sucesso na loja virtual.");
	}
	
	public void conferirCategoriaPrincipalNaLojaVirtual(String categoria) {
		aguardarElementoVisivel(logoCompanhia);
		
		WebElement categoriaPrincipal = DriverFactory.getDriver().findElement(By.xpath("//*[@id='header']//a[text()='"+categoria+"']"));
		Log.info("Conferindo inclusão de categoria na home page da loja virtual...");
		Utils.assertEquals(getTextElement(categoriaPrincipal), categoria);
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
		moverCursorPara(DriverFactory.getDriver().findElement(categoriaPrincipal));
		Utils.wait(1000);
		Utils.assertTrue("Categoria ["+categoria+"] não foi exculída na home page da loja virtual", !isVisibility(categoriaFilho));
		Log.info("Exclusão de categoria validada com sucesso na loja virtual.");
	}
	
	public void conferirSubcategoriaNaLojaVirtual(String categoria, String subcategoria) {
		aguardarElementoVisivel(logoCompanhia);
		
		WebElement categoriaFilho     = DriverFactory.getDriver().findElement(By.xpath("//*/a[text()='"+subcategoria+"']"));
		WebElement categoriaPrincipal = DriverFactory.getDriver().findElement(By.xpath("//*[@id='header']//a[text()='"+categoria+"']"));
		Log.info("Conferindo inclusão de subcategoria na home page da loja virtual...");
		moverCursorPara(categoriaPrincipal);
		Utils.wait(1000);		
		Utils.assertEquals(getTextElement(categoriaFilho), subcategoria);
		Log.info("Inclusão de subcategoria validada com sucesso na loja virtual.");
	}
}
