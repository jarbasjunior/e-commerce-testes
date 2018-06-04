package br.com.ecommerce.pages.retaguarda.cadastros.slides;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarSlide extends BasePage {

	public PageEditarSlide() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarSlide;
	
	@FindBy(xpath = "//label[text()='Título']")
	private WebElement labelTitulo;
	
	@FindBy(xpath = "//label[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//label[text()='Label do botão']")
	private WebElement labelBotao;
	
	@FindBy(xpath = "//label[text()='URL do botão']")
	private WebElement labelUrlBotao;
	
	@FindBy(xpath = "//label[text()='Imagem']")
	private WebElement labelImagem;
	
	@FindBy(id = "slide_title")
	private WebElement inputTitulo;
	
	@FindBy(id = "slide_description")
	private WebElement inputDescricao;
	
	@FindBy(id = "slide_button_value")
	private WebElement inputLabelBotao;
	
	@FindBy(id = "slide_button_url")
	private WebElement inputUrlBotao;

	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void alterarSlide(String titulo) {
		Log.info("Alterando titulo do slide para ["+titulo+"]...");
		aguardarElementoVisivel(inputTitulo);
		preencherCampo(inputTitulo, titulo);
		click(btSalvar);
		Log.info("Dados do slide ["+titulo+"] alterados");
	}
	
	public void verificarOrtografiaPageEditarSlide(){
		Log.info("Verificando ortografia da página de edição de slide...");
		Utils.assertEquals(getTextElement(titleEditarSlide), "Editar Slide");
		Utils.assertEquals(getTextElement(labelTitulo)     , "Título");
		Utils.assertEquals(getTextElement(labelDescricao)  , "Descrição");
		Utils.assertEquals(getTextElement(labelBotao)      , "Label do botão");
		Utils.assertEquals(getTextElement(labelUrlBotao)   , "URL do botão");
		Utils.assertEquals(getTextElement(labelImagem)     , "Imagem");
		Utils.assertEquals(getTextValueAtributo(btSalvar)  , "Salvar");
		Utils.assertEquals(getTextElement(btCancelar)      , "Cancelar");
		Log.info("Ortografia validada com sucesso.");
	}
}
