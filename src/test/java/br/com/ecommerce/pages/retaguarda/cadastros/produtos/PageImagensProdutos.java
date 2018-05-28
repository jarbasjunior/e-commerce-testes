package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageImagensProdutos extends BasePage {

	public PageImagensProdutos() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleImagensProduto;
	
	@FindBy(xpath = "//th[text()='Imagem Principal']")
	private WebElement labelImagemPrincipal;
	
	@FindBy(xpath = "//div[@class='col-md-12']")
	private WebElement labelProduto;

	@FindBy(xpath = "//th[text()='Imagem']")
	private WebElement labelImagem;
	
	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement btNova;
	
	public void irParaPageAdicionarImagensProduto(){
		aguardarElementoVisivel(btNova);
		click(btNova);
		Log.info("Redirecionando para página de adição de imagem do produto...");
	}
	
	public void validaImagemAdicionada(String imagem) {
		String atributoImagem = getDriver().findElement(By.xpath("//tbody//../td[2]/img")).getAttribute("alt");
		Utils.assertTrue("Imagem não foi anexada", atributoImagem.equalsIgnoreCase(imagem.substring(0, imagem.length()-4)));
		Log.info("Imagem ["+imagem+"] adicionada com sucesso.");
	}

	public void verificarOrtografiaPageEditarImagemProduto(String produto){
		Log.info("Verificando ortografia da página de edição de produtos...");
		Utils.assertEquals(getTextElement(titleImagensProduto)  , "Imagens do Produto");
		Utils.assertEquals(getTextElement(labelProduto)         , "Produto: "+produto);
		Utils.assertEquals(getTextElement(labelImagemPrincipal) , "Imagem Principal");
		Utils.assertEquals(getTextElement(labelImagem)          , "Imagem");
		Utils.assertEquals(getTextElement(btNova)     	   	    , "Nova");
		Log.info("Ortografia validada com sucesso.");
	}
}
