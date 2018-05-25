package br.com.ecommerce.pages.retaguarda.cadastros.fornecedores;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageFornecedor extends BasePage {

	public PageFornecedor() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleFornecedores;
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Razão Social']")
	private WebElement labelRazaoSocial;
	
	@FindBy(xpath = "//th[text()='CNPJ']")
	private WebElement labelCnpj;
	
	@FindBy(xpath = "//th[text()='Telefone']")
	private WebElement labelTelefone;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoFornecedores() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de fornecedor");
	}
	
	public void navegarParaPaginaEdicaoFornecedor(String fornecedor) {
		aguardarElementoVisivel(btEditar);
		By b = By.xpath("//tbody//../tr/td[text()='"+fornecedor+"']//..//td[contains(.,'Editar')]/a[1]");
		if (isVisibility(b)) {	
			if (!getDriver().findElement(b).isDisplayed()) {
				pageDown(btNovo);
			}
		}
		click(getDriver().findElement(b));
		Log.info("Redirecionando para página de edição do fornecedor ["+fornecedor+"]");
	}
	
	public void validarFornecedorInserido(String fornecedor) {
		Utils.assertTrue("Fornecedor ["+fornecedor+"] não está sendo exibido na listagem de fornecedors", existsFornecedor(fornecedor));
		Log.info("Fornecedor ["+fornecedor+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Fornecedor criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarFornecedorNaListagem(String razaoSocial, String cnpj, String telefone) {
		
		Log.info("Conferindo dados do fornecedor ["+razaoSocial+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+razaoSocial+"')]//../td[1]");
		if (isVisibility(by)) {	
			if (!getDriver().findElement(by).isDisplayed()) {
				pageDown(btNovo);
			}
		}

		WebElement fillNome     = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+razaoSocial+"')]//../td[1]"));
		WebElement fillCnpj     = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+razaoSocial+"')]//../td[2]"));
		WebElement fillTelefone = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+razaoSocial+"')]//../td[3]"));

		Utils.assertEquals(getTextElement(fillNome)    , razaoSocial);
		Utils.assertEquals(getTextElement(fillCnpj)    , cnpj);
		Utils.assertEquals(getTextElement(fillTelefone), telefone);

		Log.info("Dados do fornecedor ["+razaoSocial+"] conferidos com sucesso.");
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Fornecedor atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Fornecedor atualizado com sucesso.");
	}
	
	public void validarFornecedorRemovido(String fornecedor) {
		Utils.assertFalse("Fornecedor ["+fornecedor+"] ainda está sendo exibida na listagem de fornecedores", existsFornecedor(fornecedor));
		Log.info("Fornecedor ["+fornecedor+"] removido com sucesso");
	}
	
	public void removerFornecedor(String fornecedor) {
		Log.info("Removendo fornecedor ["+fornecedor+"]...");
		pageDown(btNovo);
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+fornecedor+"']//../td/a[@data-method='delete']");
		WebElement removerFornecedor = getDriver().findElement(by);
		click(removerFornecedor);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Fornecedor ["+fornecedor+"] removido...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Fornecedor removido com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsFornecedor(String fornecedor){
		pageDown(btNovo);
		Log.info("Verificando se o fornecedor ["+fornecedor+"] está cadastrado...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+fornecedor+"']");
		return isVisibility(by);
	}

	public void verificarOrtografiaPageFornecedores(){
		Log.info("Verificando ortografia da página fornecedores...");
		Utils.assertEquals(getTextElement(titleFornecedores), "Fornecedores");
		Utils.assertEquals(getTextElement(labelRazaoSocial) , "Razão Social");
		Utils.assertEquals(getTextElement(labelCnpj)        , "CNPJ");
		Utils.assertEquals(getTextElement(labelTelefone)    , "Telefone");
		Utils.assertEquals(getTextElement(btNovo)           , "Novo(a)");
		Log.info("Ortografia validada com sucesso.");
	}
}
