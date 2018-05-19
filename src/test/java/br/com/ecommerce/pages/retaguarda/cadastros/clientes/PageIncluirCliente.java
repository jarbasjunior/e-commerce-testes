package br.com.ecommerce.pages.retaguarda.cadastros.clientes;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirCliente extends BasePage {

	public PageIncluirCliente() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovoCliente;
	
	@FindBy(xpath = "//label[text()='Nome']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//label[text()='Sobrenome']")
	private WebElement labelSobrenome;
	
	@FindBy(xpath = "//label[text()='CPF']")
	private WebElement labelCPF;
	
	@FindBy(xpath = "//label[text()='Email']")
	private WebElement labelEmail;
	
	@FindBy(xpath = "//label[text()='Senha']")
	private WebElement labelSenha;
	
	@FindBy(xpath = "//label[text()='Confirmação da senha']")
	private WebElement labelConfirmacaoSenha;
		
	@FindBy(xpath = "//label[text()='Telefone']")
	private WebElement labelTelefone;
	
	@FindBy(id = "color_title")
	private WebElement inputTitutlo;
	
	@FindBy(id = "client_first_name")
	private WebElement inputNome;
	
	@FindBy(id = "client_last_name")
	private WebElement inputSobrenome;
	
	@FindBy(id = "client_cpf")
	private WebElement inputCPF;
	
	@FindBy(id = "client_email")
	private WebElement inputEmail;
	
	@FindBy(id = "client_password")
	private WebElement inputSenha;
	
	@FindBy(id = "client_password_confirmation")
	private WebElement inputConfirmaSenha;
	
	@FindBy(id = "client_phone")
	private WebElement inputTelefone;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirCliente(String nome, String sobrenome, String cpf, String email, 
			                   String senha, String confirmaSenha, String telefone) {
		
		Log.info("Inserindo dados do cliente ["+nome+"]...");
		aguardarElementoVisivel(inputNome);
		preencherCampo(inputNome     	 , nome);
		preencherCampo(inputSobrenome	 , sobrenome);
		preencherCampo(inputCPF      	 , cpf);
		preencherCampo(inputEmail    	 , email);
		preencherCampo(inputSenha    	 , senha);
		preencherCampo(inputConfirmaSenha, confirmaSenha);
		tab(inputConfirmaSenha);
		preencherCampo(inputTelefone     , telefone);
		click(btSalvar);
		Log.info("Dados do cliente ["+nome+"] inseridos");
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se cliente foi incluído com dados inválidos..");
		Utils.assertTrue("Sistema permitiu criação de cliente com dados inválidos", !isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de cliente com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Cliente criado com sucesso.");
	}
	
	public void verificarOrtografiaPageIncluirClientes(){
		Log.info("Verificando ortografia da página de cadastro de clientes...");
		Utils.assertEquals(getTextElement(titleNovoCliente)  	, "Novo(a) Cliente");
		Utils.assertEquals(getTextElement(labelNome)     		, "Nome");
		Utils.assertEquals(getTextElement(labelSobrenome)		, "Sobrenome");
		Utils.assertEquals(getTextElement(labelCPF)      		, "CPF");
		Utils.assertEquals(getTextElement(labelEmail)    		, "Email");
		Utils.assertEquals(getTextElement(labelSenha)    		, "Senha");
		Utils.assertEquals(getTextElement(labelConfirmacaoSenha), "Confirmação da senha");
		Utils.assertEquals(getTextElement(labelTelefone) 		, "Telefone");
		Utils.assertEquals(getTextValueAtributo(btSalvar)  		, "Salvar");
		tab(inputConfirmaSenha);
		tab(inputTelefone);
		Utils.assertEquals(getTextElement(btCancelar)     		, "Cancelar");
		pageUp(btCancelar);
		Log.info("Ortografia validada com sucesso.");
	}
}
