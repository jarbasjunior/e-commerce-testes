package br.com.ecommerce.pages.retaguarda.cadastros.marcas;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageMarca extends BasePage {

	public PageMarca() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleMarcas;
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Código']")
	private WebElement labelCodigo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoMarcas() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de marcas");
	}
	
	public void navegarParaPaginaEdicaoMarca(String marca) {
		aguardarElementoVisivel(btEditar);
		pageDown(btNovo);
		By b = By.xpath("//tbody//../tr/td[text()='"+marca+"']//..//td[contains(.,'Editar')]/a[1]");
		click(getDriver().findElement(b));
		Log.info("Redirecionando para página de edição da marca ["+marca+"]");
	}
	
	public void validarMarcaInserido(String marca) {
		Utils.assertTrue("Marca ["+marca+"] não está sendo exibida na listagem de marcas", existsMarca(marca));
		Log.info("Marca ["+marca+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Marca criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarMarcaNaListagem(String marca) {
		Log.info("Conferindo dados da marca ["+marca+"] na tela...");
		By by = By.xpath("//tbody//tr/td[contains(.,'"+marca+"')]//../td[2]");
		if (isVisibility(by)) {	
			if (!getDriver().findElement(by).isDisplayed()) {
				pageDown(btNovo);
			}
		}
		Utils.wait(1500);
		WebElement fillMarca = getDriver().findElement(by);
		Utils.assertEquals(getTextElement(fillMarca), marca);
		Log.info("Dados da marca ["+marca+"] conferida com sucesso.");
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Marca atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Tipo de Marca atualizado com sucesso.");
	}
	
	public void validarMarcaRemovido(String marca) {
		Utils.assertFalse("Marca ["+marca+"] ainda está sendo exibida na listagem de marcas", existsMarca(marca));
		Log.info("Marcas ["+marca+"] removido com sucesso");
	}
	
	public void removerMarca(String marca) {
		Log.info("Removendo marca ["+marca+"]...");
		pageDown(btNovo);
		By by = By.xpath("//*[@class='table']//../tr/td[text()='"+marca+"']//../td/a[@class='btn btn-danger']");
		WebElement removerMarca = getDriver().findElement(by);
		click(removerMarca);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Marca ["+marca+"] removida.");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Tipo de Marca removido com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsMarca(String marca){
		pageDown(btNovo);
		Log.info("Verificando se o marca ["+marca+"] está cadastrada...");
		By by = By.xpath("//*[@class='table table-striped']//../tr/td[text()='"+marca+"']");
		return isVisibility(by);
	}

	public void verificarOrtografiaPageMarcas(){
		Log.info("Verificando ortografia da página marcas...");
		Utils.assertEquals(getTextElement(titleMarcas)   , "Marcas");
		Utils.assertEquals(getTextElement(labelCodigo)   , "Código");
		Utils.assertEquals(getTextElement(labelDescricao), "Descrição");
		Utils.assertEquals(getTextElement(btNovo)        , "Novo(a)");
		Log.info("Ortografia validada com sucesso.");
	}
}
