package br.com.ecommerce.pages.lojavirtual;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageHomeLojaVirtual extends BasePage {

	public PageHomeLojaVirtual() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='header']/div[1]/div/div/div[1]/div/ul/li[1]/a")
	private WebElement telefoneCompanhia;
	
	@FindBy(xpath = "//*[@id='header']/div[1]/div/div/div[1]/div/ul/li[2]/a")
	private WebElement emailCompanhia;
	
	@FindBy(xpath = "//*[@id='footer']/div[1]/div/div/div[3]/div/p")
	private WebElement enderecoCompanhia;
	
	@FindBy(xpath = "//*[@id='header']/div[2]/div/div/div[1]/div/a/img")
	private WebElement logoCompanhia;
	
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
		
		WebElement categoriaPrincipal = getDriver().findElement(By.xpath("//*[@id='header']//a[text()='"+categoria+"']"));
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
		moverCursorPara(getDriver().findElement(categoriaPrincipal));
		Utils.wait(1000);
		Utils.assertTrue("Categoria ["+categoria+"] não foi exculída na home page da loja virtual", !isVisibility(categoriaFilho));
		Log.info("Exclusão de categoria validada com sucesso na loja virtual.");
	}
	
	public void conferirSubcategoriaNaLojaVirtual(String categoria, String subcategoria) {
		aguardarElementoVisivel(logoCompanhia);
		
		WebElement categoriaFilho     = getDriver().findElement(By.xpath("//*/a[text()='"+subcategoria+"']"));
		WebElement categoriaPrincipal = getDriver().findElement(By.xpath("//*[@id='header']//a[text()='"+categoria+"']"));
		Log.info("Conferindo inclusão de subcategoria na home page da loja virtual...");
		moverCursorPara(categoriaPrincipal);
		Utils.wait(2000);		
		Utils.assertEquals(getTextElement(categoriaFilho), subcategoria);
		Log.info("Inclusão de subcategoria validada com sucesso na loja virtual.");
	}
}
