package br.com.ecommerce.pages.retaguarda.cadastros.clientes;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageClientes extends BasePage {

	public PageClientes() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleCoresEstilos;
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Título']")
	private WebElement nomeTitutlo;
	
	@FindBy(xpath = "//th[text()='Cor']")
	private WebElement nomeCor;
	
	@FindBy(xpath = "//a[@class='btn btn-default'][contains(.,'Ativar')]")
	private WebElement btAtivar;
	
	@FindBy(xpath = "//a[@class='btn btn-warning'][contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@class='btn btn-danger'][contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoCores() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
	}
	
	public void validarCorInserida(String cor) {
		Utils.assertTrue("Cor ["+cor+"] não está sendo exibida na listagem de cores", existsColor(cor));
		Log.info("Cor ["+cor+"] inserida com sucesso");
	}
	
	public void validarCorRemovida(String cor) {
		Utils.assertTrue("Cor ["+cor+"] ainda está sendo exibida na listagem de cores", !existsColor(cor));
		Log.info("Cor ["+cor+"] removida com sucesso");
	}
	
	public boolean existsColor(String str){
		Utils.wait(1000);
		Log.info("Verificando se cor ["+str+"] está cadastrada...");
		By cor = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+str+"']");
		return isVisibility(cor);
	}

	public void removerCor(String str) {
		Log.info("Removendo cor ["+str+"]...");
		Utils.wait(1000);
		By cor = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+str+"']//../td/a[@data-method='delete']");
		WebElement removerCor = getDriver().findElement(cor);
		click(removerCor);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Cor ["+str+"] removida...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro foi apagado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void verificarOrtografiaPageCores(){
		Log.info("Verificando ortografia da página cores...");
		Utils.assertEquals(getTextElement(titleCoresEstilos), "Cores/Estilos");
		Utils.assertEquals(getTextElement(btNovo)           , "Novo");
		Utils.assertEquals(getTextElement(nomeTitutlo)      , "Título");
		Utils.assertEquals(getTextElement(nomeCor)          , "Cor");
		Utils.assertEquals(getTextElement(btAtivar)         , "Ativar");
		Utils.assertEquals(getTextElement(btEditar)         , "Editar");
		Utils.assertEquals(getTextElement(btRemover)         , "Remover");
		Log.info("Ortografia validada com sucesso.");
	}
}
