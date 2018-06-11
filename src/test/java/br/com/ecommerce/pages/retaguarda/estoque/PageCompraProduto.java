package br.com.ecommerce.pages.retaguarda.estoque;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageCompraProduto extends BasePage {

	public PageCompraProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleCompraProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/product_purchases/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='NOTA FISCAL']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//th[text()='ID']")
	private WebElement labelId;
	
	@FindBy(xpath = "//th[text()='DATA']")
	private WebElement labelData;
	
	@FindBy(xpath = "//th[text()='VALOR']")
	private WebElement labelValor;
	
	@FindBy(xpath = "//th[text()='QUANT. PRODUTOS']")
	private WebElement labelQtdProdutos;
	
	@FindBy(xpath = "//th[text()='PAGO']")
	private WebElement labelPago;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoCompraProdutos() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de compras de produto...");
	}
	
	public void navegarParaPaginaEdicaoCompraProduto(String notaFiscal) {
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição de compra do produto...");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Compra cadastrada com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarCompraProdutoNaListagem(String notaFiscal, String dataCompra, boolean compraPaga) {
		aguardarElementoVisivel(btNovo);
		if (!dataCompra.contains("/")) {
			dataCompra = Utils.formataData(dataCompra);
		}
		Log.info("Conferindo dados da compra do produto...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[3]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		
		WebElement fillData 	  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[3]"));
		WebElement fillNotaFiscal = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[2]"));

		Utils.assertEquals(getTextElement(fillNotaFiscal), notaFiscal);
		Utils.assertEquals(getTextElement(fillData)      , dataCompra);
		if (compraPaga) {
			Utils.assertTrue("Compra está sendo exibida como NÃO paga", isCompraPaga(notaFiscal));
		}else
			Utils.assertFalse("Compra está sendo exibida como paga", isCompraPaga(notaFiscal));

		Log.info("Dados do produto com nota fiscal ["+notaFiscal+"] conferidos com sucesso.");
	}
	
	public boolean isCompraPaga(String notaFiscal){
		WebElement checkPagamento = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[6]/i"));
		String     statusCheck    = checkPagamento.getAttribute("style");
		if (statusCheck.equals("color: green;")) {
			return true;
		}else
			return false;
	}
	
	public boolean existsCompraProduto(){
		return isVisibility(By.xpath("//tbody/tr"));
	}
	
	
	public String getNotaFiscalCompra(){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[2]"))).trim();
	}
	
	public String getDataNotaFiscalCompra(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[3]"))).trim();
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Compra atualizada com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarCompraRemovida(String notaFiscal) {
		Utils.assertFalse("Compra de nota fiscal ["+notaFiscal+"] ainda está sendo exibida na listagem de compra de produtos", existsCompraProdutos(notaFiscal));
		Log.info("Compra da nota fiscal ["+notaFiscal+"] removida com sucesso");
	}
	
	public void removerCompraProduto(String notaFiscal) {
		Log.info("Removendo compra de nota ["+notaFiscal+"]...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]//../td[@class='last']/a[contains(.,'Remover')]");
		exibeRegistroVisivel(by, btNovo);
		WebElement removerCompra = getDriver().findElement(by);
		click(removerCompra);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Compra de nota fiscal ["+notaFiscal+"] removida...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Compra removida com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsCompraProdutos(String notaFiscal){
		Log.info("Verificando se compra de nota fiscal ["+notaFiscal+"] está cadastradas...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void verificarOrtografiaPageCompraProdutos(){
		Log.info("Verificando ortografia da página de compra de produtos...");
		Utils.assertEquals(getTextElement(titleCompraProdutos) , "Compra de Produtos");
		Utils.assertEquals(getTextElement(labelId)             , "ID");
		Utils.assertEquals(getTextElement(labelNotaFiscal)     , "NOTA FISCAL");
		Utils.assertEquals(getTextElement(labelData)           , "DATA");
		Utils.assertEquals(getTextElement(labelValor)          , "VALOR");
		Utils.assertEquals(getTextElement(labelQtdProdutos)    , "QUANT. PRODUTOS");
		Utils.assertEquals(getTextElement(labelPago)           , "PAGO");
		Utils.assertEquals(getTextElement(btNovo)              , "Novo");
		Log.info("Ortografia validada com sucesso.");
	}
}
