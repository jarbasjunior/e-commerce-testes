package br.com.ecommerce.pages.retaguarda.cadastros.fornecedores;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirFornecedor extends BasePage {

	public PageIncluirFornecedor() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovoFornecedor;
	
	@FindBy(xpath = "//label[text()='Razão Social']")
	private WebElement labelRazaoSocial;
	
	@FindBy(xpath = "//label[text()='CNPJ']")
	private WebElement labelCnpj;
	
	@FindBy(xpath = "//label[text()='Telefone']")
	private WebElement labelTelefone;
	
	@FindBy(id = "supplier_name")
	private WebElement inputRazaoSocial;
	
	@FindBy(id = "supplier_cnpj")
	private WebElement inputCnpj;
	
	@FindBy(id = "supplier_phone")
	private WebElement inputTelefone;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirFornecedor(String razaoSocial, String cnpj, String telefone) {
		Log.info("Inserindo dados do fornecedor ["+razaoSocial+"]...");
		aguardarElementoVisivel(inputRazaoSocial);
		preencherCampo(inputRazaoSocial, razaoSocial);
		preencherCampo(inputCnpj       , cnpj);
		preencherCampo(inputTelefone   , telefone);
		click(btSalvar);
		Log.info("Dados do fornecedor ["+razaoSocial+"] inseridos");
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se fornecedor foi incluído com dados inválidos..");
		Utils.assertFalse("Sistema permitiu criação de fornecedor com dados inválidos", isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de fornecedor com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Fornecedor criado com sucesso.");
	}
	
	public void verificarOrtografiaPageIncluirFornecedores(){
		Log.info("Verificando ortografia da página de cadastro de fornecedores...");
		Utils.assertEquals(getTextElement(titleNovoFornecedor), "Adicionar Fornecedor");
		Utils.assertEquals(getTextElement(labelRazaoSocial)   , "Razão Social");
		Utils.assertEquals(getTextElement(labelCnpj)		  , "CNPJ");
		Utils.assertEquals(getTextElement(labelTelefone) 	  , "Telefone");
		Utils.assertEquals(getTextElement(btCancelar)     	  , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)  	  , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
