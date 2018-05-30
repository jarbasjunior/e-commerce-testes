package br.com.ecommerce.pages.retaguarda.cadastros.tamanhos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageTamanhos extends BasePage {

	public PageTamanhos() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleTamanhos;
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
	private WebElement btNovo;
	
	@FindBy(xpath = "//span[text()='Tamanho']")
	private WebElement labelTamanho;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remove')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoTamanho() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de tamanho");
	}
	
	public void validarTamanhoFiscalInserido(String tamanho) {
		Utils.assertTrue("Tamanho ["+tamanho+"] não está sendo exibido na listagem de tamanhos", existsTamanho(tamanho));
		Log.info("Tamanho ["+tamanho+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Novo tamanho cadastrado.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarTamanhoRemovido(String tamanho) {
		Utils.assertFalse("Tamanho ["+tamanho+"] ainda está sendo exibido na listagem de tamanhos", existsTamanho(tamanho));
		Log.info("Tamanho ["+tamanho+"] removido com sucesso!");
	}
	
	public void removerTamanho(String tamanho) {
		Log.info("Removendo tamanho ["+tamanho+"]...");
		By by = By.xpath("//tbody//../td[contains(.,'"+tamanho+"')]//../td/a[contains(.,'Remove')]");
		WebElement removerTamanho = getDriver().findElement(by);
		click(removerTamanho);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Tamanho ["+tamanho+"] removido...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tamanho removido.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsTamanho(String tamanho){
		Log.info("Verificando se o tamanho ["+tamanho+"] está cadastrado...");
		By by = By.xpath("//tbody//../td[contains(.,'"+tamanho+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se tamanho foi incluído com dados inválidos..");
		Utils.assertFalse("Sistema permitiu criação de tamanho com dados inválidos", isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de tamanho com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Novo tamanho cadastrado.");
	}

	public void verificarOrtografiaPageTamanhos(){
		Log.info("Verificando ortografia da página tamanhos...");
		aguardarElementoVisivel(btNovo);
		Utils.assertEquals(getTextElement(titleTamanhos), "Tamanhos");
		Utils.assertEquals(getTextElement(labelTamanho) , "Tamanho");
		Utils.assertEquals(getTextElement(btNovo)       , "Novo(a)");
		Utils.assertEquals(getTextElement(btEditar)     , "Editar");
		Utils.assertEquals(getTextElement(btRemover)    , "Remove");
		Log.info("Ortografia validada com sucesso.");
	}

}
