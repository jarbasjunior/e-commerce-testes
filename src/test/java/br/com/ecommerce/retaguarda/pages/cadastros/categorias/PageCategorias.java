package br.com.ecommerce.retaguarda.pages.cadastros.categorias;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageCategorias extends PageObjectGeneric<PageCategorias> {

	public PageCategorias() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']//./h1")
	WebElement titleCategoriasProdutos;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='ID']")
	WebElement titleId;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='NOME']")
	WebElement titleNome;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='ATIVA']")
	WebElement titleAtiva;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='MENU PRINCIPAL']")
	WebElement titleMenuPrincipal;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='CATEGORIA PAI']")
	WebElement titleCategoriaPai;
	
	@FindBy(xpath = "//*[@href='/admin/categories/new']")
	WebElement btNovaCategoria;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	WebElement msgSucesso;
	
	public void navegarParaPaginaInclusaoDeCategoria() {
		Log.info("Navegando para página de inclusão de categoria de produtos...");
		waitAndClick(btNovaCategoria);
	}
	
	public void navegarParaPaginaEdicaoDeCategoria(String categoria) {
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[1]");
		String idCategoria = Selenium.getDriver().findElement(xpath).getText();
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA+"/categories/"+idCategoria+"/edit");
		Log.info("Navegando para página de edição de categoria de produtos...");
	}
	
	public void excluirCategoria(String categoria) {
		Log.info("Removendo categoria ["+categoria+"]...");
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[1]");
		String idCategoria = Selenium.getDriver().findElement(xpath).getText();
		Utils.scrollDown(titleAtiva);
		waitAndClick(Selenium.getDriver().findElement(By.xpath("//*[@href='/admin/categories/"+idCategoria+"']")));
		Log.info("Confirmando exclusão da categoria ["+categoria+"]...");
		confirmarAlerta();
		Log.info("Exclusão confirmada");
		Utils.wait(1000);
	}
	
	public void conferirInclusaoDeCategoriaPrincipal(String novaCategoria){
		
		Log.info("Conferindo listagem da nova categoria ["+novaCategoria+"] na tela...");

		WebElement fillAtiva         = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[3]"));
		WebElement fillCategoria     = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]"));
		WebElement fillCategoriaPai  = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[5]"));
		WebElement fillMenuPrincipal = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[4]"));
		
		Utils.scrollDown(titleAtiva);
		Utils.assertEquals(fillCategoria.getText()    , novaCategoria);
		Utils.assertEquals(fillAtiva.getText()        , "Sim");
		Utils.assertEquals(fillMenuPrincipal.getText(), "Sim");
		Utils.assertEquals(fillCategoriaPai.getText() , "-");
		
		Log.info("Categoria listada com sucesso no retaguarda.");
	}
	
	public void conferirInclusaoDeSubCategoria(String categoria, String subcategoria){
		
		Log.info("Conferindo listagem da subcategoria ["+subcategoria+"] na tela...");
		
		WebElement fillAtiva          = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[3]"));
		WebElement fillCategoria      = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[2]"));
		WebElement fillCategoriaPai   = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[5]"));
		WebElement fillMenuPrincipal  = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[4]"));
		WebElement fillIdCategoriaPai = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[4][contains(.,'Sim')]//../td[1]"));
		
		Utils.assertEquals(fillCategoria.getText()    , subcategoria);
		Utils.assertEquals(fillAtiva.getText()        , "Sim");
		Utils.assertEquals(fillMenuPrincipal.getText(), "Não");
		Utils.assertEquals(fillCategoriaPai.getText() , fillIdCategoriaPai.getText()+" - "+categoria);
		
		Log.info("Subcategoria listada com sucesso no retaguarda.");
	}
	
	public void validarMsgFeedbackInclusaoSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(msgSucesso.getText().replace("×", "").trim().toString(), "Categoria criada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarMsgFeedbackEdicaoSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(msgSucesso.getText().replace("×", "").trim().toString(), "Categoria atualizada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarMsgFeedbackExclusaoSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(msgSucesso.getText().replace("×", "").trim().toString(), "Categoria removida com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarOrtografiaDeCamposTelaCategoriaDeProdutos() {
		
		Log.info("Conferindo ortografia na tela categoria de produtos...");
		Utils.assertEquals(titleCategoriasProdutos.getText(), "Categorias de Produtos");
		Utils.assertEquals(titleId.getText()                , "ID");
		Utils.assertEquals(titleNome.getText()              , "NOME");
		Utils.assertEquals(titleAtiva.getText()             , "ATIVA");
		Utils.assertEquals(titleMenuPrincipal.getText()     , "MENU PRINCIPAL");
		Utils.assertEquals(titleCategoriaPai.getText()      , "CATEGORIA PAI");
		Log.info("Ortografia validada com sucesso.");
	}
}
