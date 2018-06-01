package br.com.ecommerce.pages.retaguarda.cadastros.funcionarios;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageFuncionario extends BasePage {

	public PageFuncionario() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleFuncionarios;
	
	@FindBy(xpath = "//*[@href='/admin/employees/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='ID']")
	private WebElement labelId;
	
	@FindBy(xpath = "//th[text()='NOME']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//th[text()='CPF']")
	private WebElement labelCpf;
	
	@FindBy(xpath = "//th[text()='EMAIL']")
	private WebElement labelEmail;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoFuncionarios() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de funcionário");
	}
	
	public void navegarParaPaginaEdicaoFuncionario(String funcionario) {
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[text()='"+funcionario+"']//..//td[contains(.,'Editar')]/a[1]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição do funcionario ["+funcionario+"]");
	}
	
	public String getPrimeiroNomeLista(){
		return getTextElement(getDriver().findElement(By.xpath("//tbody//../tr[1]/td[2]")));
	}
	
	public void validarFuncionarioInserido(String funcionario) {
		Utils.assertTrue("Funcionario ["+funcionario+"] não está sendo exibido na listagem de funcionarios", existsFuncionarios(funcionario));
		Log.info("Funcionario ["+funcionario+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Funcionário cadastrado com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarFuncionarioNaListagem(String nome, String cpf, String email) {
		
		Log.info("Conferindo dados do funcionario ["+nome+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[3]");
		if (isVisibility(by)) {	
			if (!getDriver().findElement(by).isDisplayed()) {
				pageDown(btNovo);
			}
		}

		WebElement fillCpf      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[3]"));
		WebElement fillNome     = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[2]"));
		WebElement fillEmail    = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[4]"));

		Utils.assertEquals(getTextElement(fillNome)    , nome);
		Utils.assertEquals(getTextElement(fillEmail)   , email);
		Utils.assertEquals(getTextElement(fillCpf)     , cpf);

		Log.info("Dados do funcionário ["+nome+"] conferidos com sucesso.");
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Funcionário atualizado com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Funcionário atualizado com sucesso.");
	}
	
	public void validarFuncionarioRemovido(String funcionario) {
		Utils.assertFalse("Funcionário ["+funcionario+"] ainda está sendo exibida na listagem de funcionários", existsFuncionarios(funcionario));
		Log.info("Funcionário ["+funcionario+"] removido com sucesso");
	}
	
	public void removerFuncionario(String funcionario) {
		Log.info("Removendo funcionário ["+funcionario+"]...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+funcionario+"']//../td/a[@data-method='delete']");
		if (isVisibility(by)) {	
			if (!getDriver().findElement(by).isDisplayed()) {
				pageDown(btNovo);
			}
		}
		WebElement removerFuncionario = getDriver().findElement(by);
		click(removerFuncionario);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Funcionario ["+funcionario+"] removido...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Funcionário removido com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsFuncionarios(String funcionario){
		pageDown(btNovo);
		Log.info("Verificando se o funcionario ["+funcionario+"] está cadastrado...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+funcionario+"']");
		return isVisibility(by);
	}

	public void verificarOrtografiaPageFuncionarios(){
		Log.info("Verificando ortografia da página funcionarios...");
		Utils.assertEquals(getTextElement(titleFuncionarios), "Funcionários");
		Utils.assertEquals(getTextElement(labelId)          , "ID");
		Utils.assertEquals(getTextElement(labelNome)        , "NOME");
		Utils.assertEquals(getTextElement(labelCpf)         , "CPF");
		Utils.assertEquals(getTextElement(btNovo)           , "Novo");
		Log.info("Ortografia validada com sucesso.");
	}
}
