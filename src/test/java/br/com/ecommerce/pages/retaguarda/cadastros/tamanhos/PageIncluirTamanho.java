package br.com.ecommerce.pages.retaguarda.cadastros.tamanhos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirTamanho extends BasePage {

	public PageIncluirTamanho() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovoTamanho;
	
	@FindBy(xpath = "//label[text()='Tamanho']")
	private WebElement labelTamanho;
	
	@FindBy(id = "size_description")
	private WebElement comboTamanho;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirTamanho(String tamanho) {
		Log.info("Inserindo dados do tamanho ["+tamanho+"]...");
		aguardarElementoVisivel(btSalvar);
		selecionarValorComboTexto(comboTamanho, tamanho);
		click(btSalvar);
		Log.info("Tamanho ["+tamanho+"] inserido");
	}
	
	public void verificarOrtografiaPageIncluirFuncionarios(){
		Log.info("Verificando ortografia da página de cadastro de tamanho...");
		Utils.assertEquals(getTextElement(titleNovoTamanho), "Novo(a) Tamanho");
		Utils.assertEquals(getTextElement(labelTamanho)    , "Tamanho");
		Utils.assertEquals(getTextElement(btCancelar)      , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)  , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
