package br.com.ecommerce.pages.retaguarda.cadastros.clientes;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirClientes extends BasePage {

	public PageIncluirClientes() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleAdicionarCoresEstilos;
	
	@FindBy(xpath = "//label[text()='Título:']")
	private WebElement nomeTitutlo;
	
	@FindBy(id = "color_title")
	private WebElement inputTitutlo;
	
	@FindBy(xpath = "//label[text()='Cor (Hexadecimal):']")
	private WebElement nomeCor;
	
	@FindBy(id = "color_value")
	private WebElement inputCor;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirCor(String cor, String corHexa) {
		Log.info("Inserindo cor ["+cor+"]...");
		aguardarElementoVisivel(inputCor);
		preencherCampo(inputTitutlo, cor);
		preencherCampo(inputCor, corHexa);
		click(btSalvar);
		Log.info("Cor azul inserida com sucesso");
	}
	
	public void verificarOrtografiaPageIncluirCores() {
		aguardarElementoVisivel(titleAdicionarCoresEstilos);
		Log.info("Verificando ortografia da página de cadastro de cores...");
		Utils.assertEquals(getTextElement(titleAdicionarCoresEstilos), "Adicionar Cor/Estilo");
		Utils.assertEquals(getTextElement(nomeTitutlo)               , "Título:");
		Utils.assertEquals(getTextElement(nomeCor)                   , "Cor (Hexadecimal):");
		Utils.assertEquals(getTextElement(btCancelar)                , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)            , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro criado com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Registro criado com Sucesso!");
	}
}
