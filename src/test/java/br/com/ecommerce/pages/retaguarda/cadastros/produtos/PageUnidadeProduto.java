package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageUnidadeProduto extends BasePage {

	public PageUnidadeProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleUnidadeProduto;
	
	@FindBy(xpath = "//div[@class='col-md-12'][1]")
	private WebElement labelProduto;
	
	@FindBy(xpath = "//th[text()='Código']")
	private WebElement labelCodigo;

	@FindBy(xpath = "//th[text()='Cor']")
	private WebElement labelCor;
	
	@FindBy(xpath = "//th[text()='Tamanho']")
	private WebElement labelTamanho;

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement btNovo;
	
	public void navegarParaPageIncluirUnidadeProduto() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de unidade de produto");
	}
	
	public void validaUnidadeAdicionada(String codigo, String cor, String tamanho) {
		By by = By.xpath("//tbody//../td[contains(.,'"+codigo+"')]//../td[1]");
		exibeRegistroVisivel(by, btNovo);
		Log.info("Validando unidade adicionada ao produto...");
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+codigo+"')]//../td[1]"))), codigo);
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+codigo+"')]//../td[2]"))), cor);
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+codigo+"')]//../td[3]"))), tamanho);
		Log.info("Unidade adicionada com sucesso.");
	}

	public void verificarOrtografiaPageUnidadeProduto(String produto){
		Log.info("Verificando ortografia da página de edição de produtos...");
		Utils.assertEquals(getTextElement(titleUnidadeProduto), "Unidades de Produtos");
		Utils.assertEquals(getTextElement(labelProduto)       , "Produto: "+produto);
		Utils.assertEquals(getTextElement(labelCodigo)        , "Código");
		Utils.assertEquals(getTextElement(labelCor)           , "Cor");
		Utils.assertEquals(getTextElement(labelTamanho)       , "Tamanho");
		Utils.assertEquals(getTextElement(btNovo)             , "Novo");
		Log.info("Ortografia validada com sucesso.");
	}
}
