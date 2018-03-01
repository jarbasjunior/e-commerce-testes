package br.com.ecommerce.retaguarda.pages.dadosgerais;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageEditarConfiguracoes extends PageObjectGeneric<PageEditarConfiguracoes> {

	public PageEditarConfiguracoes() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/h1[text()='Editar Configurações']")
	WebElement titleEditarConfiguracoes;
	
	@FindBy(id = "parameters_1_value")
	WebElement fieldNome;
	
	@FindBy(id = "parameters_2_value")
	WebElement fieldEmail;
	
	@FindBy(id = "parameters_3_value")
	WebElement fieldTelefone;
	
	@FindBy(id = "parameters_4_value")
	WebElement fieldEndereco;
	
	@FindBy(id = "parameters_5_value")
	WebElement fieldCNPJ;
	
	@FindBy(name = "commit")
	WebElement btSalvar;
	
	
	public void alterarDadosDaCompanhia(String nome, String email, String telefone, String endereco, String cnpj) {
		
		aguardarElementoVisivel(titleEditarConfiguracoes);
		Utils.assertEquals(titleEditarConfiguracoes.getText(), "Editar Configurações");
		
		Log.info("Realizando alterações na tela de configurações...");
		preencherCampo(fieldNome    , nome);
		preencherCampo(fieldEmail   , email);
		preencherCampo(fieldTelefone, telefone);
		preencherCampo(fieldEndereco, endereco);
		preencherCampo(fieldCNPJ    , cnpj);
		waitAndClick(btSalvar);
		Log.info("Salvando alterações...");
	}
}
