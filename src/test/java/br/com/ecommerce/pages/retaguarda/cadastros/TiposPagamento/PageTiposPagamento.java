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
	
	public void validarGrupoFiscalAtivoNaListagem(String tipoPagamento) {
		Log.info("Conferindo dados do tipo pagamento ["+tipoPagamento+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[1]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[3]/a[2]"));
		WebElement fillDescricao = getDriver().findElement(by);
		
		Utils.assertEquals(getTextElement(fillDescricao), tipoPagamento);
		Utils.assertTrue("Listagem exibe tipo pagamento INATIVO", isAtivo(tipoPagamento));
		Utils.assertEquals(getTextElement(fillBtnActive), "Desativar");
		
		Log.info("Dados do funcionário ["+tipoPagamento+"] conferidos com sucesso.");
	}
	
	public void validarGrupoFiscalInativoNaListagem(String tipoPagamento) {
		Log.info("Conferindo dados do tipo pagamento ["+tipoPagamento+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[1]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[3]/a[2]"));
		WebElement fillDescricao = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[1]"));
		Utils.assertEquals(getTextElement(fillDescricao), tipoPagamento);
		Utils.assertFalse("Listagem exibe tipo pagamento ATIVO", isAtivo(tipoPagamento));
		Utils.assertEquals(getTextElement(fillBtnActive), "Ativar");
		
		Log.info("Dados do funcionário ["+tipoPagamento+"] conferidos com sucesso.");
	}
	
	public boolean isAtivo(String tipoPagamento){
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+tipoPagamento+"')]//../td[2]"));
		if (getTextElement(fillBtnActive).equalsIgnoreCase("Sim")) {
			return true;
		}else
			return false;
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Funcionário atualizado com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Grupo fiscal atualizado com sucesso.");
	}
	
	public void validarGrupoFiscalRemovido(String tipoPagamento) {
		Utils.assertFalse("Grupo fiscal ["+tipoPagamento+"] ainda está sendo exibido na listagem de tipos pagamentos", existsGrupoFiscal(tipoPagamento));
		Log.info("Grupo fiscal ["+tipoPagamento+"] removido com sucesso");
	}
	
	public void removerGrupoFiscal(String tipoPagamento) {
		Log.info("Removendo tipo pagamento ["+tipoPagamento+"]...");
		By by = By.xpath("//tbody//../tr/td[text()='"+tipoPagamento+"']//../td/a[contains(.,'Remover')]");
		WebElement removerGrupoFiscal = getDriver().findElement(by);
		click(removerGrupoFiscal);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Grupo fiscal ["+tipoPagamento+"] removido...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Grupo Fiscal removido com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsTerceiroGrupoFiscal(){
		By terceiraLinha = By.xpath("//tbody/tr[3]");
		return isVisibility(terceiraLinha);
	}
	
	public String getGrupoFiscalTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Fiscal Teste')]//../td[1]"))).trim();
	}
	
	public boolean existsGrupoFiscal(String tipoPagamento){
		Log.info("Verificando se o tipo pagamento ["+tipoPagamento+"] está cadastrado...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+tipoPagamento+"']");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
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
	
	public void verificarGrupoFiscalAtivo(String tipoPagamento) {
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
