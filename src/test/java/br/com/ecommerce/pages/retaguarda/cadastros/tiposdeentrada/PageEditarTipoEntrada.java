package br.com.ecommerce.pages.retaguarda.cadastros.tiposdeentrada;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarTipoEntrada extends BasePage {

	public PageEditarTipoEntrada() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarTipoEntrada;
	
	@FindBy(xpath = "//label[text()='Título']")
	private WebElement labelTitulo;
	
	@FindBy(id = "product_in_type_description")
	private WebElement inputTitulo;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	public void alterarTipoEntrada(String tipoEntrada) {
		Log.info("Alterando dados da ["+tipoEntrada+"]...");
		aguardarElementoVisivel(btSalvar);
		preencherCampo(inputTitulo, tipoEntrada);
		click(btSalvar);
		Log.info("["+tipoEntrada+"] inserida");
	}
	
	public boolean isContaDespesa(String tipoConta){
		if (tipoConta.contains("à pagar")) {
			return true;
		}else
			return false;
	}
	
	public void verificarOrtografiaPageEditarTipoEntrada(){
		Log.info("Verificando ortografia da página de edição de tipo de entrada...");
		Utils.assertEquals(getTextElement(titleEditarTipoEntrada), "Editar Tipo de Entrada");
		Utils.assertEquals(getTextElement(labelTitulo)           , "Título");
		Utils.assertEquals(getTextElement(btCancelar)            , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)        , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
