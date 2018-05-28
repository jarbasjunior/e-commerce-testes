package br.com.ecommerce.pages.retaguarda.cadastros.cores;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageCores extends BasePage {

	public PageCores() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleCoresEstilos;
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Título']")
	private WebElement labelTitutlo;
	
	@FindBy(xpath = "//th[text()='Cor']")
	private WebElement labelCor;
	
	@FindBy(xpath = "//a[@class='btn btn-default'][contains(.,'Ativar')]")
	private WebElement btAtivar;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoCores() {
		getDriver().navigate().to(getDriver().getCurrentUrl()+"/new");
	}
	
	public void validarCorInserida(String cor) {
		Utils.assertTrue("Cor ["+cor+"] não está sendo exibida na listagem de cores", existsColor(cor));
		Log.info("Cor ["+cor+"] inserida com sucesso");
	}
	
	public void validarCorRemovida(String cor) {
		Utils.assertFalse("Cor ["+cor+"] ainda está sendo exibida na listagem de cores", existsColor(cor));
		Log.info("Cor ["+cor+"] removida com sucesso");
	}
	
	public boolean existsColor(String str){
		Log.info("Verificando se cor ["+str+"] está cadastrada...");
		By cor = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+str+"']");
		exibeRegistroVisivel(cor, btNovo);
		return isVisibility(cor);
	}

	public void removerCor(String cor) {
		Log.info("Removendo cor ["+cor+"]...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+cor+"']//../td/a[@data-method='delete']");
		exibeRegistroVisivel(by, btNovo);
		WebElement removerCor = getDriver().findElement(by);
		click(removerCor);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Cor ["+cor+"] removida...");
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
		Utils.assertEquals(getTextElement(labelTitutlo)      , "Título");
		Utils.assertEquals(getTextElement(labelCor)          , "Cor");
		Utils.assertEquals(getTextElement(btAtivar)         , "Ativar");
		Log.info("Ortografia validada com sucesso.");
	}
}
