package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageCategoriaProduto extends BasePage {

	public PageCategoriaProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleCategoriaProduto;
	
	@FindBy(xpath = "//div[@class='col-md-12'][1]")
	private WebElement labelProduto;
	
	@FindBy(xpath = "//label[text()='Categoria:']")
	private WebElement labelCategoria;

	@FindBy(id = "product_category_category_id")
	private WebElement comboCategoria;
	
	@FindBy(name = "commit")
	private WebElement btAdicionar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void adicionaCategoriaMulheres(String categoria) {
		aguardarElementoVisivel(btAdicionar);
		selecionarValorComboTexto(comboCategoria, categoria);
		click(btAdicionar);
		validaMsgInclusao();
	}
	
	public void validaMsgInclusao(){
		Log.info("Validando mensagem de feedback de inclusão...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro criado com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaCategoriaAdicionada(String categoria) {
		Log.info("Validando categoria adicionada ao produto...");
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+categoria+"')]"))), categoria);
		Log.info("Categoria ["+categoria+"] adicionada com sucesso.");
	}

	public void verificarOrtografiaPageCategoriaProduto(String produto){
		Log.info("Verificando ortografia da página de edição de produtos...");
		Utils.assertEquals(getTextElement(titleCategoriaProduto), "Unidades de Produtos");
		Utils.assertEquals(getTextElement(labelProduto)         , "Produto: "+produto);
		Utils.assertEquals(getTextElement(labelCategoria)       , "Categoria:");
		Utils.assertEquals(getTextValueAtributo(btAdicionar)    , "Adicionar");
		Log.info("Ortografia validada com sucesso.");
	}
}
