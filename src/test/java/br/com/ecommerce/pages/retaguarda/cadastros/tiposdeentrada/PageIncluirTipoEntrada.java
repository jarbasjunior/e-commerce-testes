package br.com.ecommerce.pages.retaguarda.cadastros.tiposdeentrada;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirTipoEntrada extends BasePage {

	public PageIncluirTipoEntrada() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovaEntrada;
	
	@FindBy(xpath = "//label[text()='Título']")
	private WebElement labelTitulo;
	
	@FindBy(id = "product_in_type_description")
	private WebElement inputTitulo;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	public void incluirTipoEntrada(String tipoEntrada) {
		Log.info("Inserindo dados da ["+tipoEntrada+"]...");
		aguardarElementoVisivel(btSalvar);
		preencherCampo(inputTitulo, tipoEntrada);
		click(btSalvar);
		Log.info("["+tipoEntrada+"] inserida");
	}
	
	public void verificarOrtografiaPageIncluirTipoDeEntrada(){
		Log.info("Verificando ortografia da página de cadastro de tipo de entrada...");
		Utils.assertEquals(getTextElement(titleNovaEntrada), "Novo(a) Tipo de Entrada");
		Utils.assertEquals(getTextElement(labelTitulo)     , "Título");
		Utils.assertEquals(getTextElement(btCancelar)      , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)  , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
