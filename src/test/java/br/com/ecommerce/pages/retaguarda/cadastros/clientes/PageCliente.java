package br.com.ecommerce.pages.retaguarda.cadastros.clientes;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageCliente extends BasePage {

	public PageCliente() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleClientes;
	
	@FindBy(xpath = "//*[@href='https://ecommerce-rails-matheus.herokuapp.com/admin/clients/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//span[text()='Id']")
	private WebElement labelId;
	
	@FindBy(xpath = "//th[text()='Nome']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//span[text()='Email']")
	private WebElement labelEmail;
	
	@FindBy(xpath = "//th[text()='CPF']")
	private WebElement labelCPF;
	
	@FindBy(xpath = "//th[text()='Telefone']")
	private WebElement labelTelefone;
	
	@FindBy(xpath = "//a[@class='btn btn-default'][contains(.,'Ativar')]")
	private WebElement btAtivar;
	
	@FindBy(xpath = "//a[@class='btn btn-warning'][contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoClientes() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de cliente");
	}
	
	public void navegarParaPaginaEdicaoCliente(String cliente) {
		aguardarElementoVisivel(btEditar);
		By b = By.xpath("//tbody//../tr/td[text()='"+cliente+"']//..//td[contains(.,'Editar')]/a[1]");
		exibeRegistroVisivel(b, btNovo);
		click(getDriver().findElement(b));
		Log.info("Redirecionando para página de edição do cliente ["+cliente+"]");
	}
	
	public void validarClienteInserido(String cliente) {
		Utils.assertTrue("Cliente ["+cliente+"] não está sendo exibido na listagem de clientes", existsCliente(cliente));
		Log.info("Cliente ["+cliente+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Cliente criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarClienteNaListagem(String nome, String cpf, String email, String telefone) {
		String cpfFormatado = Utils.formataCPF(cpf);
		String telFormatado = Utils.formataTelefone(telefone);
		
		Log.info("Conferindo dados do cliente ["+nome+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[2]");
		exibeRegistroVisivel(by, btNovo);

		WebElement fillCPF      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[2]"));
		WebElement fillNome     = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[3]"));
		WebElement fillEmail    = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[4]"));
		WebElement fillTelefone = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[5]"));

		Utils.assertEquals(getTextElement(fillCPF)     , cpfFormatado);
		Utils.assertEquals(getTextElement(fillNome)    , nome);
		Utils.assertEquals(getTextElement(fillEmail)   , email);
		Utils.assertEquals(getTextElement(fillTelefone), telFormatado);

		Log.info("Dados do cliente ["+nome+"] conferidos com sucesso.");
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Cliente atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Cliente atualizado com sucesso.");
	}
	
	public boolean existsCliente(String cliente){
		Utils.wait(1000);
		Log.info("Verificando se o cliente ["+cliente+"] está cadastrado...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+cliente+"']");
		return isVisibility(by);
	}

	public void verificarOrtografiaPageClientes(){
		Log.info("Verificando ortografia da página clientes...");
		Utils.assertEquals(getTextElement(titleClientes), "Clientes");
		Utils.assertEquals(getTextElement(labelId)       , "Id");
		Utils.assertEquals(getTextElement(labelCPF)      , "CPF");
		Utils.assertEquals(getTextElement(labelNome)     , "Nome");
		Utils.assertEquals(getTextElement(labelEmail)    , "Email");
		Utils.assertEquals(getTextElement(labelTelefone) , "Telefone");
		Log.info("Ortografia validada com sucesso.");
	}
}
