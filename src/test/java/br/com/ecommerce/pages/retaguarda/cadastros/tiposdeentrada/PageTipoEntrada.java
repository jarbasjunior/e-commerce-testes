package br.com.ecommerce.pages.retaguarda.cadastros.tiposdeentrada;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageTipoEntrada extends BasePage {

	public PageTipoEntrada() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleTipoEntrada;
	
	@FindBy(xpath = "//*[@href='/admin/product_in_types/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='DESCRIÇÃO']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//th[text()='ATIVO']")
	private WebElement labelAtivo;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void navegarParaPaginaIncluirTipoDeEntrada(){
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de entradas...");
	}
	
	public void navegarParaPaginaEdicaoTipoDeEntrada(String tipoEntrada){
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+tipoEntrada+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição do tipo de entrada ["+tipoEntrada+"]");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de inclusão...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Entrada de Produto criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}

	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Entrada de Produto atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Conta removido com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isAtiva(String tipoEntrada){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoEntrada+"')]//../td[3]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(by);
		if (getTextElement(fillBtnActive).equalsIgnoreCase("SIM")) {
			return true;
		}else
			return false;
	}

	public void validarTipoEntradaRemovido(String tipoEntrada) {
		Utils.assertFalse("Tipo conta ["+tipoEntrada+"] ainda está sendo exibida na listagem de tipos de entrada", existsTipoEntrada(tipoEntrada));
		Log.info("["+tipoEntrada+"] removida com sucesso");
	}
	
	public void validarTipoEntradaInserido(String tipoEntrada) {
		Utils.assertTrue("Tipo conta ["+tipoEntrada+"] NÃO está sendo exibida na listagem de tipos de entrada", existsTipoEntrada(tipoEntrada));
		Utils.assertTrue("Tipo conta ["+tipoEntrada+"] NÃO está ativa", isAtiva(tipoEntrada));
		Log.info("["+tipoEntrada+"] cadastrada e status validado com sucesso");
	}
	
	public void removerTipoConta(String tipoEntrada) {
		Log.info("Removendo tipo entrada ["+tipoEntrada+"]...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+tipoEntrada+"')]//../td/a[contains(.,'Remover')]");
		WebElement removerTipoConta = getDriver().findElement(by);
		click(removerTipoConta);
		confirmarAlerta();
		validaMsgSucessoExclusao();
		Log.info("Tipo entrada ["+tipoEntrada+"] removido...");
	}
	
	public String getTipoEntradaTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'Entrada Teste')]"))).trim();
	}
	
	public boolean existsTipoEntradaTeste(){
		By contaTeste = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Entrada Teste')]//../td[1]");
		return isVisibility(contaTeste);
	}
	
	public boolean existsTipoEntrada(String tipoEntrada){
		Log.info("Verificando se o tipo entrada ["+tipoEntrada+"] está cadastrada...");
		By by = By.xpath("//tbody//../td[contains(.,'"+tipoEntrada+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void verificarOrtografiaPageTiposDeEntrada(){
		aguardarElementoVisivel(btNovo);
		Log.info("Verificando ortografia da página tipos de entrada...");
		Utils.assertEquals(getTextElement(titleTipoEntrada), "Tipos de Entrada");
		Utils.assertEquals(getTextElement(labelDescricao)  , "DESCRIÇÃO");
		Utils.assertEquals(getTextElement(labelAtivo)      , "ATIVO");
		Utils.assertEquals(getTextElement(btNovo)          , "Novo(a)");
		Utils.assertEquals(getTextElement(btEditar)        , "Editar");
		Utils.assertEquals(getTextElement(btRemover)       , "Remover");
		Log.info("Ortografia validada com sucesso.");
	}

}
