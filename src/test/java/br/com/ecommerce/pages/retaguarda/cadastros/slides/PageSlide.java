package br.com.ecommerce.pages.retaguarda.cadastros.slides;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageSlide extends BasePage {

	public PageSlide() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleSlides;
	
	@FindBy(xpath = "//*[@href='/admin/slides/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Título']")
	private WebElement labelTitulo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//th[text()='Label do botão']")
	private WebElement labelBotao;
	
	@FindBy(xpath = "//th[text()='URL do botão']")
	private WebElement labelUrlBotao;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoSlides() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de slides");
	}
	
	public void navegarParaPaginaEdicaoSlides(String slide) {
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[text()='"+slide+"']//..//td[contains(.,'Editar')]/a[1]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição do slide ["+slide+"]");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Slide criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarSlideNaListagem(String titulo, String descricao, String labelBotao, String urlBotao) {
		
		Log.info("Conferindo dados do slide ["+titulo+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+titulo+"')]//../td[3]");
		exibeRegistroVisivel(by, btNovo);

		WebElement fillTitulo     = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+titulo+"')]//../td[1]"));
		WebElement fillUrlBotao   = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+titulo+"')]//../td[4]"));
		WebElement fillDescricao  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+titulo+"')]//../td[2]"));
		WebElement fillLabelBotao = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+titulo+"')]//../td[3]"));

		Utils.assertEquals(getTextElement(fillTitulo)    , titulo);
		Utils.assertEquals(getTextElement(fillDescricao) , descricao);
		Utils.assertEquals(getTextElement(fillLabelBotao), labelBotao);
		Utils.assertEquals(getTextElement(fillUrlBotao)  , urlBotao);

		Log.info("Dados do slide ["+titulo+"] conferidos com sucesso no retagurda.");
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Slide atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarSlideRemovidoRetaguarda(String funcionario) {
		Utils.assertFalse("Slide ["+funcionario+"] ainda está sendo exibida na listagem de funcionários", existsSlide(funcionario));
		Log.info("Slide ["+funcionario+"] removido com sucesso do retaguarda.");
	}
	
	public boolean existsSlide(String slide){
		Log.info("Verificando se o slide ["+slide+"] está cadastrado...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+slide+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void removerSlide(String slide) {
		Log.info("Removendo slide ["+slide+"]...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+slide+"')]//../td[5]/a[contains(.,'Remover')]");
		exibeRegistroVisivel(by, btNovo);
		WebElement removerSlide = getDriver().findElement(by);
		click(removerSlide);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Slide ["+slide+"] removido...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Slide removido com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public String getSlideOstentacao(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'OSTENTAÇÃO')]//../td[1]"))).trim();
	}
	
	public boolean existsSlideOstentacao(){
		By slide = By.xpath("//*[@id='main-content']//tr/td[contains(.,'OSTENTAÇÃO')]//../td[1]");
		return isVisibility(slide);
	}

	public void verificarOrtografiaPageSlides(){
		Log.info("Verificando ortografia da página slides...");
		Utils.assertEquals(getTextElement(titleSlides)   , "Slides");
		Utils.assertEquals(getTextElement(labelTitulo)   , "Título");
		Utils.assertEquals(getTextElement(labelDescricao), "Descrição");
		Utils.assertEquals(getTextElement(labelBotao)    , "Label do botão");
		Utils.assertEquals(getTextElement(labelUrlBotao) , "URL do botão");
		Utils.assertEquals(getTextElement(btNovo)        , "Novo(a)");
		Log.info("Ortografia validada com sucesso.");
	}
}
