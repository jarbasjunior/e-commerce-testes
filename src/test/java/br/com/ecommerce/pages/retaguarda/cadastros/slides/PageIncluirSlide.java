package br.com.ecommerce.pages.retaguarda.cadastros.slides;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirSlide extends BasePage {

	public PageIncluirSlide() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovoSlide;
	
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
	
	@FindBy(id = "slide_photo")
	private WebElement btEscolherImagem;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirSlide(String titulo, String descricao, String labelBotao, String urlBotao, String imagem) {
		Log.info("Inserindo dados do slide ["+titulo+"]...");
		aguardarElementoVisivel(inputTitulo);
		preencherCampo(inputTitulo      , titulo);
		preencherCampo(inputDescricao   , descricao);
		preencherCampo(inputLabelBotao  , labelBotao);
		preencherCampo(inputUrlBotao    , urlBotao);
		adicionarImagem(btEscolherImagem, imagem);
		click(btSalvar);
		Log.info("Dados do slide ["+titulo+"] inseridos");
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se funcionário foi incluído com dados inválidos..");
		Utils.assertFalse("Sistema permitiu criação de funcionário com dados inválidos", isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de funcionário com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Funcionário criado com sucesso.");
	}
	
	public void verificarOrtografiaPageIncluirFuncionarios(){
		Log.info("Verificando ortografia da página de cadastro de slides...");
		Utils.assertEquals(getTextElement(titleNovoSlide), "Novo(a) Slide");
		Utils.assertEquals(getTextElement(labelTitulo)   , "Título");
		Utils.assertEquals(getTextElement(labelDescricao), "Descrição");
		Utils.assertEquals(getTextElement(labelBotao)    , "Label do botão");
		Utils.assertEquals(getTextElement(labelUrlBotao) , "URL do botão");
		Utils.assertEquals(getTextElement(labelImagem)   , "Imagem");
		Utils.assertEquals(getTextElement(btCancelar)    , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar), "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
