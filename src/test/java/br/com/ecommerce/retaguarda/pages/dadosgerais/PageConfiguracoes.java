package br.com.ecommerce.retaguarda.pages.dadosgerais;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageConfiguracoes extends PageObjectGeneric<PageConfiguracoes> {

	public PageConfiguracoes() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/h1[text()='Configurações']")
	WebElement titleConfiguracoes;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Nome da empresa: ']/..")
	WebElement nomeEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Email da empresa: ']/..")
	WebElement emailEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Telefone da empresa: ']/..")
	WebElement telefoneEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Endereço da empresa: ']/..")
	WebElement enderecoEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='CNPJ da empresa: ']/..")
	WebElement cnpjEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/a[@href='/admin/settings/edit']")
	WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	WebElement msgSucesso;
	
	
	public void navegarParaEdicaoDeConfiguracoes(){
		Log.info("Navegando para página de editar configurações...");
		Utils.assertEquals(titleConfiguracoes.getText(), "Configurações");
		waitAndClick(btEditar);
	}
	
	public void conferirAlteracaoDaCompanhia(String nome, String email, String telefone, String endereco, String cnpj) {
		
		Log.info("Capturando dados da tela de configurações...");
		Log.info("Conferindo alterações realizadas na tela configurações...");
		Utils.assertEquals(titleConfiguracoes.getText(), "Configurações");
		Utils.assertEquals(nomeEmpresa.getText()       , "Nome da empresa: "+nome);
		Utils.assertEquals(emailEmpresa.getText()      , "Email da empresa: "+email);
		Utils.assertEquals(telefoneEmpresa.getText()   , "Telefone da empresa: "+telefone);
		Utils.assertEquals(enderecoEmpresa.getText()   , "Endereço da empresa: "+endereco);
		Utils.assertEquals(cnpjEmpresa.getText()       , "CNPJ da empresa: "+cnpj);
		Log.info("Alterações realizadas com sucesso no retaguarda.");
	}
	
	public void conferirOrtografiaDeCamposTelaConfigurcoes() {
		
		Log.info("Capturando dados da tela de configurações...");
		WebElement nomeEmpresa     = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Nome da empresa: ']"));
		WebElement emailEmpresa    = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Email da empresa: ']"));
		WebElement telefoneEmpresa = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Telefone da empresa: ']"));
		WebElement enderecoEmpresa = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Endereço da empresa: ']"));
		WebElement cnpjEmpresa     = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='CNPJ da empresa: ']"));
		
		Log.info("Conferindo ortografia na tela configurações...");
		Utils.assertEquals(titleConfiguracoes.getText(), "Configurações");
		Utils.assertEquals(nomeEmpresa.getText()       , "Nome da empresa:");
		Utils.assertEquals(emailEmpresa.getText()      , "Email da empresa:");
		Utils.assertEquals(telefoneEmpresa.getText()   , "Telefone da empresa:");
		Utils.assertEquals(enderecoEmpresa.getText()   , "Endereço da empresa:");
		Utils.assertEquals(cnpjEmpresa.getText()       , "CNPJ da empresa:");
		Log.info("Ortografia validada com sucesso.");
	}

	public void validarMsgFeedbackSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(msgSucesso.getText().replace("×", "").trim().toString(), "Parâmetros de configuraçāo salvos com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
}

