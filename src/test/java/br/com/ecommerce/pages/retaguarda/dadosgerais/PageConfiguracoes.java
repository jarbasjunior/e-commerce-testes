package br.com.ecommerce.pages.retaguarda.dadosgerais;

import static br.com.ecommerce.config.setup.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageConfiguracoes extends BasePage {

	public PageConfiguracoes() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/h1[text()='Configurações']")
	private WebElement titleConfiguracoes;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Nome da empresa: ']/..")
	private WebElement nomeEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Email da empresa: ']/..")
	private WebElement emailEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Telefone da empresa: ']/..")
	private WebElement telefoneEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='Endereço da empresa: ']/..")
	private WebElement enderecoEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/p/strong[text()='CNPJ da empresa: ']/..")
	private WebElement cnpjEmpresa;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]/div/a[@href='/admin/settings/edit']")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	
	public void navegarParaEdicaoDeConfiguracoes(){
		Log.info("Navegando para página de editar configurações...");
		Utils.assertEquals(getTextElement(titleConfiguracoes), "Configurações");
		click(btEditar);
	}
	
	public void conferirAlteracaoDaCompanhia(String nome, String email, String telefone, String endereco, String cnpj) {
		
		Log.info("Capturando dados da tela de configurações...");
		Log.info("Conferindo alterações realizadas na tela configurações...");
		Utils.assertEquals(getTextElement(titleConfiguracoes), "Configurações");
		Utils.assertEquals(getTextElement(nomeEmpresa)       , "Nome da empresa: "+nome);
		Utils.assertEquals(getTextElement(emailEmpresa)      , "Email da empresa: "+email);
		Utils.assertEquals(getTextElement(telefoneEmpresa)   , "Telefone da empresa: "+telefone);
		Utils.assertEquals(getTextElement(enderecoEmpresa)   , "Endereço da empresa: "+endereco);
		Utils.assertEquals(getTextElement(cnpjEmpresa)       , "CNPJ da empresa: "+cnpj);
		Log.info("Alterações realizadas com sucesso no retaguarda.");
	}
	
	public void conferirOrtografiaDeCamposTelaConfigurcoes() {
		
		Log.info("Capturando dados da tela de configurações...");
		WebElement nomeEmpresa     = getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Nome da empresa: ']"));
		WebElement emailEmpresa    = getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Email da empresa: ']"));
		WebElement telefoneEmpresa = getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Telefone da empresa: ']"));
		WebElement enderecoEmpresa = getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='Endereço da empresa: ']"));
		WebElement cnpjEmpresa     = getDriver().findElement(By.xpath("//*[@id='main-content']/section/div[2]/div/p/strong[text()='CNPJ da empresa: ']"));
		
		Log.info("Conferindo ortografia na tela configurações...");
		Utils.assertEquals(getTextElement(titleConfiguracoes), "Configurações");
		Utils.assertEquals(getTextElement(nomeEmpresa)       , "Nome da empresa:");
		Utils.assertEquals(getTextElement(emailEmpresa)      , "Email da empresa:");
		Utils.assertEquals(getTextElement(telefoneEmpresa)   , "Telefone da empresa:");
		Utils.assertEquals(getTextElement(enderecoEmpresa)   , "Endereço da empresa:");
		Utils.assertEquals(getTextElement(cnpjEmpresa)       , "CNPJ da empresa:");
		Log.info("Ortografia validada com sucesso.");
	}

	public void validarMsgFeedbackSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim().toString(), "Parâmetros de configuraçāo salvos com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
}
