package br.com.ecommerce.pages.retaguarda.cadastros.operadorascartaocredito;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageOpCartoesCredito extends BasePage {

	public PageOpCartoesCredito() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleOpCartoes;
	
	@FindBy(xpath = "//th[text()='Nome']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//th[text()='Ativo?']")
	private WebElement labelAtivo;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Grupo Fiscal criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgAtivacao(){
		Log.info("Validando mensagem de feedback de ativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Operadora de cartão de crédito ativada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgDesativacao(){
		Log.info("Validando mensagem de feedback de desativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Operadora de cartão de crédito desativada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isAtiva(String grupoFiscal){
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[2]"));
		if (getTextElement(fillBtnActive).equalsIgnoreCase("Sim")) {
			return true;
		}else
			return false;
	}
	
	public void ativarOperadora(String operadora) {
		WebElement fillBtnActive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+operadora+"')]//../td[3]/a[2]");
		fillBtnActive = getDriver().findElement(by);
		Log.info("Ativando operadora ["+operadora+"]...");
		click(fillBtnActive);
		validaMsgAtivacao();
	}
	
	public void desativarOperadora(String operadora) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+operadora+"')]//../td[3]/a[2]");
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Desativando operadora ["+operadora+"]...");
		click(fillBtnInactive);
		validaMsgDesativacao();
	}
	
	public void verificarOperadoraCartaoAtivada(String operadora) {
		Log.info("Verificando ativação de operadora...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+operadora+"')]//../td[3]/a[2]");
		WebElement fillBtnActive = getDriver().findElement(by);
		Utils.assertTrue("Operadora ["+operadora+"] não foi ativada", isAtiva(operadora));
		Utils.assertEquals(getTextElement(fillBtnActive), "Desativar");
		Log.info("Operadora ["+operadora+"] ativada");
	}
	
	public void verificarOperadoraCartaoDesativada(String operadora) {
		Log.info("Verificando desativação da operadora ["+operadora+"]...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+operadora+"')]//../td[3]/a[2]");
		WebElement fillBtnActive = getDriver().findElement(by);
		Utils.assertFalse("Operadora ["+operadora+"] NÃO foi desativada", isAtiva(operadora));
		Utils.assertEquals(getTextElement(fillBtnActive), "Ativar");
		Log.info("Operadora desativada");
	}

	public void verificarOrtografiaPageOpCartoesCredito(){
		Log.info("Verificando ortografia da página operadoras de cartões de crédito...");
		Utils.assertEquals(getTextElement(titleOpCartoes), "Operadoras de Cartão de Crédito");
		Utils.assertEquals(getTextElement(labelNome)     , "Nome");
		Utils.assertEquals(getTextElement(labelAtivo)    , "Ativo?");
		Utils.assertEquals(getTextElement(btEditar)      , "Editar");
		Log.info("Ortografia validada com sucesso.");
	}

}
