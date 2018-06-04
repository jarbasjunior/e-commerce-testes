package br.com.ecommerce.pages.retaguarda.cadastros.unidades;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarUnidade extends BasePage {

	public PageEditarUnidade() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarUnidade;
	
	@FindBy(xpath = "//label[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//label[text()='Abreviação']")
	private WebElement labelAbreviacao;
	
	@FindBy(id = "unit_description")
	private WebElement inputDescricao;
	
	@FindBy(id = "unit_abbreviation")
	private WebElement inputAbreviacao;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	public void alterarUnidade(String unidade, String abreviacao) {
		Log.info("Alterando dados da ["+unidade+"]...");
		aguardarElementoVisivel(btSalvar);
		preencherCampo(inputDescricao , unidade);
		preencherCampo(inputAbreviacao, abreviacao);
		click(btSalvar);
		Log.info("["+unidade+"] inserida");
	}
	
	public void verificarOrtografiaPageEditarUnidade(){
		Log.info("Verificando ortografia da página de edição de unidade...");
		Utils.assertEquals(getTextElement(titleEditarUnidade), "Editar Unidade");
		Utils.assertEquals(getTextElement(labelDescricao)    , "Descrição");
		Utils.assertEquals(getTextElement(labelAbreviacao)   , "Abreviação");
		Utils.assertEquals(getTextElement(btCancelar)        , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)    , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
