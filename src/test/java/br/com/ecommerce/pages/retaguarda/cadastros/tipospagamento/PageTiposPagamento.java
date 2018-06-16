package br.com.ecommerce.pages.retaguarda.cadastros.tipospagamento;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageTiposPagamento extends BasePage {

	public PageTiposPagamento() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleTipoPagamentos;
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//th[text()='Ativo?']")
	private WebElement labelAtivo;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void validaMsgAtivacao(){
		Log.info("Validando mensagem de feedback de ativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de pagamento ativado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgDesativacao(){
		Log.info("Validando mensagem de feedback de desativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de pagamento desativado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isAtivo(String tipoPagamento){
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[2]"));
		if (getTextElement(fillBtnActive).equalsIgnoreCase("Sim")) {
			return true;
		}else
			return false;
	}
	
	public void ativarTipoPagamento(String tipoPagamento) {
		WebElement fillBtnActive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(by, btNovo);
		fillBtnActive = getDriver().findElement(by);
		Log.info("Ativando tipo pagamento..");
		click(fillBtnActive);
		validaMsgAtivacao();
	}
	
	public void desativarTipoPagamento(String tipoPagamento) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(by, btNovo);
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Desativando tipo pagamento..");
		click(fillBtnInactive);
		validaMsgDesativacao();
	}
	
	public void verificarTipoPagamentoAtivo(String tipoPagamento) {
		Log.info("Verificando ativação de tipo pagamento...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(by);
		Utils.assertTrue("Tipo de pagamento não foi ativado", isAtivo(tipoPagamento));
		Utils.assertEquals(getTextElement(fillBtnActive), "Desativar");
		Log.info("Grupo fiscal ativo...");
	}
	
	public void verificarTipoPagamentoInativo(String tipoPagamento) {
		Log.info("Verificando desativação de tipo pagamento");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(by);
		Utils.assertFalse("Tipo de pagamento não foi inativado", isAtivo(tipoPagamento));
		Utils.assertEquals(getTextElement(fillBtnActive), "Ativar");
		Log.info("Grupo fiscal desativado...");
	}

	public void verificarOrtografiaPageTiposPagamento(){
		Log.info("Verificando ortografia da página tipos pagamentos...");
		Utils.assertEquals(getTextElement(titleTipoPagamentos), "Tipos de Pagamento");
		Utils.assertEquals(getTextElement(labelDescricao)     , "Descrição");
		Utils.assertEquals(getTextElement(labelAtivo)         , "Ativo?");
		Utils.assertEquals(getTextElement(btEditar)           , "Editar");
		Log.info("Ortografia validada com sucesso.");
	}

}
