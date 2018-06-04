package br.com.ecommerce.pages.retaguarda.cadastros.unidades;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageUnidade extends BasePage {

	public PageUnidade() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleUnidades;
	
	@FindBy(xpath = "//*[@href='https://ecommerce-rails-matheus.herokuapp.com/admin/units/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//span[text()='Abbreviation']")
	private WebElement labelAbbreviation;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void navegarParaPaginaIncluirUnidade(){
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de unidade...");
	}
	
	public void navegarParaPaginaEdicaoUnidade(String unidade){
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+unidade+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição da ["+unidade+"]");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de inclusão...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Unidade criada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}

	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Unidade atualizada com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Unidade deletada com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isAtiva(String unidade){
		By by = By.xpath("//tbody//../td[contains(.,'"+unidade+"')]//../td[3]/a[1]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(by);
		if (getTextElement(fillBtnActive).equalsIgnoreCase("Desativar")) {
			return true;
		}else  
			return false;
	}

	public void validarUnidadeRemovida(String unidade) {
		Utils.assertFalse("["+unidade+"] ainda está sendo exibida na listagem de unidades", existsUnidade(unidade));
		Log.info("["+unidade+"] removida com sucesso.");
	}
	
	public void validarUnidadeInseridaDesativada(String unidade, String abreviacao) {
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+unidade+"')]//../td[1]"))), unidade);
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+unidade+"')]//../td[2]"))), abreviacao);
		Utils.assertFalse("["+unidade+"] NÃO está desativada", isAtiva(unidade));
		Log.info("["+unidade+"] cadastrada e status validado com sucesso");
	}
	
	public void validarUnidadeInseridaAtivada(String unidade, String abreviacao) {
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+unidade+"')]//../td[1]"))), unidade);
		Utils.assertEquals(getTextElement(getDriver().findElement(By.xpath("//tbody//../td[contains(.,'"+unidade+"')]//../td[2]"))), abreviacao);
		Utils.assertTrue("["+unidade+"] NÃO está ativada", isAtiva(unidade));
		Log.info("["+unidade+"] cadastrada e status validado com sucesso");
	}
	
	public void removerUnidade(String unidade) {
		Log.info("Removendo ["+unidade+"]...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+unidade+"')]//../td/a[contains(.,'Remover')]");
		WebElement removerUnidade = getDriver().findElement(by);
		click(removerUnidade);
		confirmarAlerta();
		validaMsgSucessoExclusao();
		Log.info("Tipo entrada ["+unidade+"] removido...");
	}
	
	public String getUnidadeTeste(){
		By by = By.xpath("//tbody//../td[contains(.,'Unidade Teste')]//../td[1]");
		exibeRegistroVisivel(by, btNovo);
		return getTextElement(getDriver().findElement(by)).trim();
	}
	
	public String getAbreviacaoTeste(String unidade) {
		By by = By.xpath("//tbody//../td[contains(.,'"+unidade+"')]//../td[2]");
		exibeRegistroVisivel(by, btNovo);
		return getTextElement(getDriver().findElement(by)).trim();
	}
	
	public boolean existsUnidadeTeste(){
		By contaTeste = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Unidade Teste')]//../td[1]");
		return isVisibility(contaTeste);
	}
	
	public boolean existsUnidade(String unidade){
		Log.info("Verificando se ["+unidade+"] está cadastrada...");
		By by = By.xpath("//tbody//../td[contains(.,'"+unidade+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void ativarUnidadeTeste(String unidade) {
		WebElement fillBtnActive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+unidade+"')]//../td[3]/a[1]");
		fillBtnActive = getDriver().findElement(by);
		Log.info("Ativando operadora ["+unidade+"]...");
		click(fillBtnActive);
		validaMsgAtivacao();
	}
	
	public void desativarUnidadeTeste(String unidade) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+unidade+"')]//../td[3]/a[1]");
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Desativando ["+unidade+"]...");
		click(fillBtnInactive);
		validaMsgDesativacao();
	}
	
	public void validaMsgAtivacao(){
		Log.info("Validando mensagem de feedback de ativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Unidade ativada com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgDesativacao(){
		Log.info("Validando mensagem de feedback de desativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Unidade desativada com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void verificarOrtografiaPageUnidade(){
		aguardarElementoVisivel(btNovo);
		Log.info("Verificando ortografia da página unidades...");
		Utils.assertEquals(getTextElement(titleUnidades) , "Unidades");
		Utils.assertEquals(getTextElement(labelDescricao), "Descrição");
		Utils.assertEquals(getTextElement(btNovo)        , "Novo(a)");
		Utils.assertEquals(getTextElement(btEditar)      , "Editar");
		Utils.assertEquals(getTextElement(btRemover)     , "Remover");
		Log.info("Ortografia validada com sucesso.");
	}
}
