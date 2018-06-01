package br.com.ecommerce.pages.retaguarda.cadastros.tiposconta;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageTipoConta extends BasePage {

	public PageTipoConta() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleTipoContas;
	
	@FindBy(xpath = "//*[@href='/admin/bill_types/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//th[text()='Despesa?']")
	private WebElement labelDespesa;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void navegarParaPaginaIncluirTipoDeConta(){
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de contas...");
	}
	
	public void navegarParaPaginaEdicaoTipoDeConta(String tipoConta){
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+tipoConta+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição do produto ["+tipoConta+"]");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de inclusão...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Conta criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}

	public void validaMsgAtivacao(){
		Log.info("Validando mensagem de feedback de ativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Conta ativada com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgDesativacao(){
		Log.info("Validando mensagem de feedback de desativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Conta desativada com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Conta atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Conta removido com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isAtivo(String tipoConta){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoConta+"')]//../td[3]/a[1]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(by);
		if (getTextElement(fillBtnActive).equalsIgnoreCase("Desativar")) {
			return true;
		}else
			return false;
	}

	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Tipo conta atualizado com sucesso.");
	}
	
	public void validarTipoContaRemovido(String tipoConta) {
		Utils.assertFalse("Tipo conta ["+tipoConta+"] ainda está sendo exibida na listagem de tipos de conta", existsTipoConta(tipoConta));
		Log.info("Tipo conta ["+tipoConta+"] removido com sucesso");
	}
	
	public void validarTipoContaInserido(String tipoConta, boolean deveSerDespesa, boolean deveEstarAtiva) {
		Utils.assertTrue("Tipo conta ["+tipoConta+"] NÃO está sendo exibida na listagem de tipos de conta", existsTipoConta(tipoConta));
		/*
		 * Valida se a conta deve ser do tipo despesa de acordo com o parâmetro
		 */
		if (deveSerDespesa) {
			Log.info("Verificando se ["+tipoConta+"] está cadastrada como despesa...");
			Utils.assertTrue("["+tipoConta+"] NÃO está cadastrada como despesa", isDespesa(tipoConta));
		}else{
			Log.info("Verificando se ["+tipoConta+"] NÃO está cadastrada como despesa...");
			Utils.assertFalse("["+tipoConta+"] ESTÁ cadastrada como despesa", isDespesa(tipoConta));
		}
		/*
		 * Valida se conta deve estar ativa de acordo com o parâmetro
		 */
		if (deveEstarAtiva) {
			Log.info("Verificando se ["+tipoConta+"] está ativa...");
			Utils.assertTrue("["+tipoConta+"] NÃO está ativa", isAtivo(tipoConta));
		}else{
			Log.info("Verificando se ["+tipoConta+"] está desativada...");
			Utils.assertFalse("["+tipoConta+"] ESTÁ ativa", isAtivo(tipoConta));
		}	
		Log.info("["+tipoConta+"] cadastrada e status validado com sucesso");
	}
	
	public void removerTipoConta(String tipoConta) {
		Log.info("Removendo tipo conta ["+tipoConta+"]...");
		By by = By.xpath("//tbody//../tr/td[text()='"+tipoConta+"']//../td/a[contains(.,'Remover')]");
		WebElement removerTipoConta = getDriver().findElement(by);
		click(removerTipoConta);
		confirmarAlerta();
		validaMsgSucessoExclusao();
		Log.info("Tipo conta ["+tipoConta+"] removido...");
	}
	
	public String getTipoContaTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Conta teste')]//../td[1]"))).trim();
	}
	
	public boolean existsTipoContaTeste(){
		By contaTeste = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Conta teste')]//../td[1]");
		return isVisibility(contaTeste);
	}
	
	public boolean existsTipoConta(String tipoConta){
		Log.info("Verificando se o tipo conta ["+tipoConta+"] está cadastrada...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+tipoConta+"']");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void ativarTipoConta(String tipoConta) {
		WebElement fillBtnActive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoConta+"')]//../td[3]/a[1]");
		exibeRegistroVisivel(by, btNovo);
		fillBtnActive = getDriver().findElement(by);
		Log.info("Ativando tipo conta..");
		click(fillBtnActive);
		validaMsgAtivacao();
	}
	
	public void desativarTipoConta(String tipoConta) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoConta+"')]//../td[3]/a[1]");
		exibeRegistroVisivel(by, btNovo);
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Desativando tipo conta..");
		click(fillBtnInactive);
		validaMsgDesativacao();
	}
	
	public void validarAtivacaoDeConta(String tipoConta) {
		Log.info("Verificando ativação ["+tipoConta+"]...");
		By btDesativar = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoConta+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(btDesativar, btNovo);
		if (tipoConta.contains("à receber")) {
			Utils.assertFalse("Conta à receber está sendo exibida como despesa", isDespesa(tipoConta));
		}
		Utils.assertTrue("Botão ainda exibe que conta não foi ativada.", isAtivo(tipoConta));
		Log.info("Conta ativa...");
	}
	
	public boolean isDespesa(String tipoConta){
		By isDespesa = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoConta+"')]//../td[2]");
		exibeRegistroVisivel(isDespesa, btNovo);
		return getTextElement(getDriver().findElement(isDespesa)).equalsIgnoreCase("S");
	}
	
	public void validarDesativacaoDeConta(String tipoConta) {
		Log.info("Verificando desativação ["+tipoConta+"]...");
		By btAtivar = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoConta+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(btAtivar, btNovo);
		if (tipoConta.contains("à pagar")) {
			Utils.assertTrue("Conta à pagar não está sendo exibida como despesa", isDespesa(tipoConta));
		}
		Utils.assertFalse("Botão ainda exibe que conta está ativa.", isAtivo(tipoConta));
		Log.info("Conta desativada...");
	}

	public void verificarOrtografiaPageTiposDeConta(){
		aguardarElementoVisivel(btNovo);
		Log.info("Verificando ortografia da página tipos de conta...");
		Utils.assertEquals(getTextElement(titleTipoContas), "Tipos de Conta");
		Utils.assertEquals(getTextElement(labelDescricao) , "Descrição");
		Utils.assertEquals(getTextElement(labelDespesa)   , "Despesa?");
		Utils.assertEquals(getTextElement(btNovo)         , "Novo");
		Utils.assertEquals(getTextElement(btEditar)       , "Editar");
		Utils.assertEquals(getTextElement(btRemover)      , "Remover");
		Log.info("Ortografia validada com sucesso.");
	}

}
