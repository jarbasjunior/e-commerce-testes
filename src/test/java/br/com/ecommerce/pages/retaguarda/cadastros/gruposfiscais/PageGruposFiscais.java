package br.com.ecommerce.pages.retaguarda.cadastros.gruposfiscais;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageGruposFiscais extends BasePage {

	public PageGruposFiscais() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleGruposFiscais;
	
	@FindBy(xpath = "//*[@href='/admin/tax_groups/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//th[text()='Ativo?']")
	private WebElement labelAtivo;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoGrupoFiscal() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de funcionário");
	}
	
	public void navegarParaPaginaEdicaoFuncionario(String funcionario) {
		aguardarElementoVisivel(btEditar);
		By b = By.xpath("//tbody//../tr/td[text()='"+funcionario+"']//..//td[contains(.,'Editar')]/a[1]");
		exibeRegistroVisivel(b, btNovo);
		click(getDriver().findElement(b));
		Log.info("Redirecionando para página de edição do funcionario ["+funcionario+"]");
	}
	
	public void validarGrupoFiscalInserido(String grupoFiscal) {
		Utils.assertTrue("Grupo fiscal ["+grupoFiscal+"] não está sendo exibido na listagem de grupos fiscais", existsGrupoFiscal(grupoFiscal));
		Log.info("Grupo fiscal ["+grupoFiscal+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Grupo Fiscal criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgAtivacao(){
		Log.info("Validando mensagem de feedback de ativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Grupo Fiscal ativado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgInativacao(){
		Log.info("Validando mensagem de feedback de inativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Grupo Fiscal desativado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarGrupoFiscalAtivoNaListagem(String grupoFiscal) {
		Log.info("Conferindo dados do grupo fiscal ["+grupoFiscal+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[1]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[3]/a[2]"));
		WebElement fillDescricao = getDriver().findElement(by);
		
		Utils.wait(1000);
		Utils.assertEquals(getTextElement(fillDescricao), grupoFiscal);
		Utils.assertTrue("Listagem exibe grupo fiscal INATIVO", isAtivo(grupoFiscal));
		Utils.wait(1000);
		Utils.assertEquals(getTextElement(fillBtnActive), "Desativar");
		
		Log.info("Dados do funcionário ["+grupoFiscal+"] conferidos com sucesso.");
	}
	
	public void validarGrupoFiscalInativoNaListagem(String grupoFiscal) {
		Log.info("Conferindo dados do grupo fiscal ["+grupoFiscal+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[1]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[3]/a[2]"));
		WebElement fillDescricao = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[1]"));
		Utils.assertEquals(getTextElement(fillDescricao), grupoFiscal);
		Utils.assertFalse("Listagem exibe grupo fiscal ATIVO", isAtivo(grupoFiscal));
		Utils.wait(1000);
		Utils.assertEquals(getTextElement(fillBtnActive), "Ativar");
		
		Log.info("Dados do funcionário ["+grupoFiscal+"] conferidos com sucesso.");
	}
	
	public boolean isAtivo(String grupoFiscal){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[2]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillBtnActive = getDriver().findElement(by);
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
	
	public void validarGrupoFiscalRemovido(String grupoFiscal) {
		Utils.assertFalse("Grupo fiscal ["+grupoFiscal+"] ainda está sendo exibido na listagem de grupos fiscais", existsGrupoFiscal(grupoFiscal));
		Log.info("Grupo fiscal ["+grupoFiscal+"] removido com sucesso");
	}
	
	public void removerGrupoFiscal(String grupoFiscal) {
		Log.info("Removendo grupo fiscal ["+grupoFiscal+"]...");
		By by = By.xpath("//tbody//../tr/td[text()='"+grupoFiscal+"']//../td/a[contains(.,'Remover')]");
		WebElement removerGrupoFiscal = getDriver().findElement(by);
		click(removerGrupoFiscal);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Grupo fiscal ["+grupoFiscal+"] removido...");
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
	
	public boolean existsGrupoFiscal(String grupoFiscal){
		Log.info("Verificando se o grupo fiscal ["+grupoFiscal+"] está cadastrado...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+grupoFiscal+"']");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void ativarGrupoFiscal(String grupoFiscal) {
		WebElement fillBtnActive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(by, btNovo);
		Utils.wait(1000);
		fillBtnActive = getDriver().findElement(by);
		Log.info("Ativando grupo fiscal...");
		click(fillBtnActive);
		Utils.wait(1000);
		confirmarAlerta();
		validaMsgAtivacao();
	}
	
	public void desativarGrupoFiscal(String grupoFiscal) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[3]/a[2]");
		exibeRegistroVisivel(by, btNovo);
		Utils.wait(1000);
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Desativando grupo fiscal...");
		click(fillBtnInactive);
		Utils.wait(1000);
		confirmarAlerta();
		validaMsgInativacao();
	}
	
	public void verificarGrupoFiscalAtivo(String grupoFiscal) {
		Log.info("Verificando ativação de grupo fiscal...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[3]/a[2]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		WebElement fillBtnActive = getDriver().findElement(by);
		Utils.assertTrue("Grupo não foi ativado", isAtivo(grupoFiscal));
		Utils.assertEquals(getTextElement(fillBtnActive), "Desativar");
		Log.info("Grupo fiscal ativo...");
	}
	
	public void verificarGrupoFiscalInativo(String grupoFiscal) {
		Log.info("Verificando inativação de grupo fiscal");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+grupoFiscal+"')]//../td[3]/a[2]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		WebElement fillBtnActive = getDriver().findElement(by);
		Utils.assertFalse("Grupo não foi inativado", isAtivo(grupoFiscal));
		Utils.assertEquals(getTextElement(fillBtnActive), "Ativar");
		Log.info("Grupo fiscal desativado...");
	}

	public void verificarOrtografiaPageGruposFiscais(){
		Log.info("Verificando ortografia da página grupos fiscais...");
		aguardarElementoVisivel(btNovo);
		Utils.assertEquals(getTextElement(titleGruposFiscais), "Grupos Fiscais");
		Utils.assertEquals(getTextElement(labelDescricao)    , "Descrição");
		Utils.assertEquals(getTextElement(labelAtivo)        , "Ativo?");
		Utils.assertEquals(getTextElement(btNovo)            , "Novo(a)");
		Log.info("Ortografia validada com sucesso.");
	}

}
