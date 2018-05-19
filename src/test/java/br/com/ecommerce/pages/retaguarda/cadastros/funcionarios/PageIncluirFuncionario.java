package br.com.ecommerce.pages.retaguarda.cadastros.funcionarios;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirFuncionario extends BasePage {

	public PageIncluirFuncionario() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovoFuncionario;
	
	@FindBy(xpath = "//label[text()='Nome']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//label[text()='CPF']")
	private WebElement labelCpf;
	
	@FindBy(xpath = "//label[text()='Email']")
	private WebElement labelEmail;
	
	@FindBy(xpath = "//label[text()='Telefone']")
	private WebElement labelTelefone;
	
	@FindBy(id = "employee_name")
	private WebElement inputNome;
	
	@FindBy(id = "employee_cpf")
	private WebElement inputCpf;
	
	@FindBy(id = "employee_email")
	private WebElement inputEmail;
	
	@FindBy(id = "employee_phone")
	private WebElement inputTelefone;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btCriarFuncionario;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirFuncionario(String nome, String cpf, String email, String telefone) {
		Log.info("Inserindo dados do funcionário ["+nome+"]...");
		aguardarElementoVisivel(inputNome);
		preencherCampo(inputNome    , nome);
		preencherCampo(inputCpf     , cpf);
		preencherCampo(inputEmail   , email);
		preencherCampo(inputTelefone, telefone);
		click(btCriarFuncionario);
		Log.info("Dados do funcionário ["+nome+"] inseridos");
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se funcionário foi incluído com dados inválidos..");
		Utils.assertFalse("Sistema permitiu criação de funcionário com dados inválidos", isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de funcionário com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Funcionário criado com sucesso.");
	}
	
	public void verificarOrtografiaPageIncluirFuncionarios(){
		Log.info("Verificando ortografia da página de cadastro de funcionário...");
		Utils.assertEquals(getTextElement(titleNovoFuncionario)    , "Novo Funcionário");
		Utils.assertEquals(getTextElement(labelNome)               , "Nome");
		Utils.assertEquals(getTextElement(labelCpf)	               , "CPF");
		Utils.assertEquals(getTextElement(labelEmail)              , "Email");
		Utils.assertEquals(getTextElement(labelTelefone)           , "Telefone");
		Utils.assertEquals(getTextElement(btCancelar)     	   	   , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btCriarFuncionario), "Criar Funcionário");
		Log.info("Ortografia validada com sucesso.");
	}
}
