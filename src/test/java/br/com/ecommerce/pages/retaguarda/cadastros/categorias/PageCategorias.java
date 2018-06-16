package br.com.ecommerce.pages.retaguarda.cadastros.categorias;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageCategorias extends BasePage {

	public PageCategorias() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']//./h1")
	private WebElement titleCategoriasProdutos;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='ID']")
	private WebElement titleId;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='NOME']")
	private WebElement titleNome;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='ATIVA']")
	private WebElement titleAtiva;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='MENU PRINCIPAL']")
	private WebElement titleMenuPrincipal;
	
	@FindBy(xpath = "//*[@id='main-content']//./th[text()='CATEGORIA PAI']")
	private WebElement titleCategoriaPai;
	
	@FindBy(xpath = "//*[@href='/admin/categories/new']")
	private WebElement btNovaCategoria;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void navegarParaPaginaInclusaoDeCategoria() {
		Log.info("Navegando para página de inclusão de categoria de produtos...");
		click(btNovaCategoria);
	}
	
	public String getCategoriaTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td[2]"))).trim();
	}
	
	public String getCategoriaTesteComFilho() {
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[5][contains(.,'Teste')]"))).trim();
	}

	public String getSubCategoriaTeste(String categoriaPai){
		String siglaCategoria = categoriaPai.substring(categoriaPai.length()-4, categoriaPai.length());
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+siglaCategoria+"')]//../td[2]//../td[4][(contains(.,'Não'))]//../td[2]"))).trim();
	}
	
	public boolean existsCategoriaTeste(){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td[2]");
		exibeRegistroVisivel(by, btNovaCategoria);
		return isVisibility(by);
	}
	
	public boolean existsCategoriaTesteComFilho(){
		By by = By.xpath("//*[@id='main-content']//tr/td[5][contains(.,'Teste')]");
		exibeRegistroVisivel(by, btNovaCategoria);
		return isVisibility(by);
	}
	
	public List<String> categoriasTesteSemSubcategorias(){
		List<String> categoriasTeste = new ArrayList<String>();
		int linha = 1;
		boolean achouFilho = false;
		while (isVisibility(By.xpath("//tbody/tr["+linha+"]"))) {
			if (isVisibility(By.xpath("//tbody/tr["+linha+"]/td[contains(.,'Teste')]"))) {
				By by = By.xpath("//tbody/tr["+linha+"]");
				exibeRegistroVisivel(by, btNovaCategoria);
				WebElement element = getDriver().findElement(By.xpath("//tbody/tr["+linha+"]/td[4]"));
				if (getTextElement(element).equalsIgnoreCase("Sim")) {
					String categoriaPai = getTextElement(getDriver().findElement(By.xpath("//tbody/tr["+linha+"]/td[2]")));
					categoriasTeste.add(categoriaPai);
				}
			}
			linha++;
		}
		((JavascriptExecutor) getDriver()).executeScript(
	            "arguments[0].scrollIntoView();", btNovaCategoria);
		List<String> paisSemFilhos = new ArrayList<String>();
		linha = 1;
		if (categoriasTeste.size() > 0) {
			for (int i = 0; i < categoriasTeste.size(); i++) {
				while (isVisibility(By.xpath("//tbody/tr["+linha+"]"))) {
					By by = By.xpath("//tbody/tr["+linha+"]");
					exibeRegistroVisivel(by, btNovaCategoria);
					WebElement element = getDriver().findElement(By.xpath("//tbody/tr["+linha+"]/td[5]"));
					if (getTextElement(element).contains(categoriasTeste.get(i))) {
						achouFilho = true;
						break;
					}
					linha++;
				}
				if (!achouFilho) {
					paisSemFilhos.add(categoriasTeste.get(i));
				}
				linha = 1;
				achouFilho = false;
			}
		}
		return paisSemFilhos;
	}
	
	public boolean existsSubCategoriaTeste(){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste')]//../td[2]//../td[4][(contains(.,'Não'))]");
		exibeRegistroVisivel(by, btNovaCategoria);
		return isVisibility(by);
	}
	
	
	public void navegarParaPaginaEdicaoDeCategoria(String categoria) {
		String siglaCategoria = categoria.replace("Teste ", "");
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+siglaCategoria+"')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(xpath, btNovaCategoria);
		click(getDriver().findElement(xpath));
		Log.info("Navegando para página de edição de categoria de produtos...");
	}
	
	public void navegarParaPaginaEdicaoDeSubCategoria(String subcategoria) {
		String siglaCategoria = subcategoria.replace("Teste ", "");
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+siglaCategoria+"')]//../td[2]//../td[4][(contains(.,'Não'))]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(xpath, btNovaCategoria);
		click(getDriver().findElement(xpath));
		Log.info("Navegando para página de edição de categoria de produtos...");
	}
	
	public void excluirCategoria(String categoria) {
		Log.info("Removendo categoria ["+categoria+"]...");
		By xpath = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[1]");
		pageDown(btNovaCategoria);
		String idCategoria = getTextElement(getDriver().findElement(xpath));
		click(getDriver().findElement(By.xpath("//*[@href='/admin/categories/"+idCategoria+"']")));
		Log.info("Confirmando exclusão da categoria ["+categoria+"]...");
		confirmarAlerta();
		Log.info("Exclusão confirmada");
		Utils.wait(1000);
	}
	
	public void conferirInclusaoDeCategoriaPrincipal(String novaCategoria){
		
		Log.info("Conferindo listagem da nova categoria ["+novaCategoria+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]//../td[4][(contains(.,'Sim'))]");
		exibeRegistroVisivel(by, btNovaCategoria);
		
		WebElement fillAtiva         = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td[3]"));
		WebElement fillCategoria     = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td[2]"));
		WebElement fillCategoriaPai  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td[5]"));
		WebElement fillMenuPrincipal = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+novaCategoria+"')]//../td[2]//../td[4][(contains(.,'Sim'))]//../td[4]"));
		
		Utils.assertEquals(getTextElement(fillCategoria)    , novaCategoria);
		Utils.assertEquals(getTextElement(fillAtiva)        , "Sim");
		Utils.assertEquals(getTextElement(fillMenuPrincipal), "Sim");
		Utils.assertEquals(getTextElement(fillCategoriaPai) , "-");
		
		Log.info("Categoria listada com sucesso no retaguarda.");
	}
	
	public void conferirInclusaoDeSubCategoria(String categoria, String subcategoria){
		
		Log.info("Conferindo listagem da subcategoria ["+subcategoria+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[2]");
		exibeRegistroVisivel(by, btNovaCategoria);
		
		WebElement fillAtiva          = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[3]"));
		WebElement fillCategoria      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[2]"));
		WebElement fillCategoriaPai   = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[5]"));
		WebElement fillMenuPrincipal  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+subcategoria+"')]//../td[4]"));
		WebElement fillIdCategoriaPai = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+categoria+"')]//../td[4][contains(.,'Sim')]//../td[1]"));
		
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
