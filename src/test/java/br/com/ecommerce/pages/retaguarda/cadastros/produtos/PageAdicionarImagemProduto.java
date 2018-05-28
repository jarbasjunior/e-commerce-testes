package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageAdicionarImagemProduto extends BasePage {

	public PageAdicionarImagemProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleAdicionarImagem;
	
	@FindBy(xpath = "//label[text()='Foto Principal']")
	private WebElement labelFotoPrincipal;
	
	@FindBy(xpath = "//label[text()='Imagem']")
	private WebElement labelImagem;
	
	@FindBy(id = "product_photo_main_photo")
	private WebElement checkFotoPrincipal;

	@FindBy(id = "product_photo_photo")
	private WebElement btEscolherImagem;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;

	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void adicionarImagemPrincipalAoProduto(String imagem){
		aguardarElementoVisivel(btSalvar);
		Log.info("Adicionando imagem principal ao produto...");
		marcarCheckbox(checkFotoPrincipal);
		adicionarImagem(btEscolherImagem, imagem);
		click(btSalvar);
		validaMsgInclusao();
	}
	
	public void validaMsgInclusao(){
		Log.info("Validando mensagem de feedback de inclusão...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro criado com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}

	public void verificarOrtografiaPageAdicionarImagemProduto(){
		Log.info("Verificando ortografia da página de edição de produtos...");
		Utils.assertEquals(getTextElement(titleAdicionarImagem), "Adicionar Imagem");
		Utils.assertEquals(getTextElement(labelFotoPrincipal)  , "Foto Principal");
		Utils.assertEquals(getTextElement(labelImagem)         , "Imagem");
		Utils.assertEquals(getTextElement(btCancelar)          , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)      , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
