package br.com.ecommerce.retaguarda.pages.cadastros.categorias;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageCategorias extends BasePage<PageCategorias> {

	public PageCategorias() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
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
		click(btNovaCategoria);
	}
	
	public void navegarParaPaginaEdicaoDeCategoria(String categoria) {
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[1]");
		pageDown(btNovaCategoria);
		String idCategoria = getTextElement(DriverFactory.getDriver().findElement(xpath));
		DriverFactory.getDriver().navigate().to(Property.URL_RETAGUARDA+"/categories/"+idCategoria+"/edit");
		Log.info("Navegando para página de edição de categoria de produtos...");
	}
	
	public void excluirCategoria(String categoria) {
		Log.info("Removendo categoria ["+categoria+"]...");
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[1]");
		pageDown(btNovaCategoria);
		String idCategoria = getTextElement(DriverFactory.getDriver().findElement(xpath));
		click(DriverFactory.getDriver().findElement(By.xpath("//*[@href='/admin/categories/"+idCategoria+"']")));
		Log.info("Confirmando exclusão da categoria ["+categoria+"]...");
		confirmarAlerta();
		Log.info("Exclusão confirmada");
		Utils.wait(1000);
	}
	
	public void conferirInclusaoDeCategoriaPrincipal(String novaCategoria){
		
		Log.info("Conferindo listagem da nova categoria ["+novaCategoria+"] na tela...");
		pageDown(btNovaCategoria);
		
		WebElement fillAtiva         = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[3]"));
		WebElement fillCategoria     = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]"));
		WebElement fillCategoriaPai  = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[5]"));
		WebElement fillMenuPrincipal = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[4]"));
		
		Utils.assertEquals(getTextElement(fillCategoria)    , novaCategoria);
		Utils.assertEquals(getTextElement(fillAtiva)        , "Sim");
		Utils.assertEquals(getTextElement(fillMenuPrincipal), "Sim");
		Utils.assertEquals(getTextElement(fillCategoriaPai) , "-");
		
		Log.info("Categoria listada com sucesso no retaguarda.");
	}
	
	public void conferirInclusaoDeSubCategoria(String categoria, String subcategoria){
		
		Log.info("Conferindo listagem da subcategoria ["+subcategoria+"] na tela...");
		pageDown(btNovaCategoria);
		
		WebElement fillAtiva          = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[3]"));
		WebElement fillCategoria      = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[2]"));
		WebElement fillCategoriaPai   = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[5]"));
		WebElement fillMenuPrincipal  = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[4]"));
		WebElement fillIdCategoriaPai = DriverFactory.getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[4][contains(.,'Sim')]//../td[1]"));
		
		Utils.assertEquals(getTextElement(fillCategoria)    , subcategoria);
		Utils.assertEquals(getTextElement(fillAtiva)        , "Sim");
		Utils.assertEquals(getTextElement(fillMenuPrincipal), "Não");
		Utils.assertEquals(getTextElement(fillCategoriaPai) , getTextElement(fillIdCategoriaPai)+" - "+categoria);
		
		Log.info("Subcategoria listada com sucesso no retaguarda.");
	}
	
	public void validarMsgFeedbackInclusaoSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim().toString(), "Categoria criada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarMsgFeedbackEdicaoSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim().toString(), "Categoria atualizada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarMsgFeedbackExclusaoSucesso() {
		Log.info("Validando mensagem de feedback de sucesso...");
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim().toString(), "Categoria removida com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarOrtografiaDeCamposTelaCategoriaDeProdutos() {
		
		Log.info("Conferindo ortografia na tela categoria de produtos...");
		Utils.assertEquals(getTextElement(titleCategoriasProdutos), "Categorias de Produtos");
		Utils.assertEquals(getTextElement(titleId)                , "ID");
		Utils.assertEquals(getTextElement(titleNome)              , "NOME");
		Utils.assertEquals(getTextElement(titleAtiva)             , "ATIVA");
		Utils.assertEquals(getTextElement(titleMenuPrincipal)     , "MENU PRINCIPAL");
		Utils.assertEquals(getTextElement(titleCategoriaPai)      , "CATEGORIA PAI");
		Log.info("Ortografia validada com sucesso.");
	}
}
