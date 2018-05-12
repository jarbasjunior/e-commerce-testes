package br.com.ecommerce.pages.retaguarda.dadosgerais;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarConfiguracoes extends BasePage {

	public PageEditarConfiguracoes() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/h1[text()='Editar Configurações']")
	private WebElement titleEditarConfiguracoes;
	
	@FindBy(id = "parameters_1_value")
	private WebElement fieldNome;
	
	@FindBy(id = "parameters_2_value")
	private WebElement fieldEmail;
	
	@FindBy(id = "parameters_3_value")
	private WebElement fieldTelefone;
	
	@FindBy(id = "parameters_4_value")
	private WebElement fieldEndereco;
	
	@FindBy(id = "parameters_5_value")
	private WebElement fieldCNPJ;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	
	public void alterarDadosDaCompanhia(String nome, String email, String telefone, String endereco, String cnpj) {
		
		aguardarElementoVisivel(titleEditarConfiguracoes);
		Utils.assertEquals(getTextElement(titleEditarConfiguracoes), "Editar Configurações");
		
		Log.info("Realizando alterações na tela de configurações...");
		preencherCampo(fieldNome    , nome);
		preencherCampo(fieldEmail   , email);
		preencherCampo(fieldTelefone, telefone);
		preencherCampo(fieldEndereco, endereco);
		preencherCampo(fieldCNPJ    , cnpj);
		tab(fieldCNPJ);
		click(btSalvar);
		Log.info("Salvando alterações...");
	}
}