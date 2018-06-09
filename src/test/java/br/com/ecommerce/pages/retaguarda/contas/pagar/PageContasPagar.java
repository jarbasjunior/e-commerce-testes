package br.com.ecommerce.pages.retaguarda.contas.pagar;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageContasPagar extends BasePage {

	public PageContasPagar() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleContasPagar;
	
	@FindBy(xpath = "//*[@href='/admin/bills_to_pay/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//input[@value='Atualizar']")
	private WebElement btAtualizar;
	
	@FindBy(xpath = "//th[text()='Tipo de Conta']")
	private WebElement labelTipoConta;
	
	@FindBy(xpath = "//th[text()='Paga']")
	private WebElement labelPaga;
	
	@FindBy(xpath = "//th[text()='Fornecedor']")
	private WebElement labelFornecedor;
	
	@FindBy(xpath = "//th[text()='Nota Fiscal']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//th[text()='Valor']")
	private WebElement labelValor;
	
	@FindBy(xpath = "//th[text()='Parcela']")
	private WebElement labelParcela;
	
	@FindBy(xpath = "//th[text()='Vencimento']")
	private WebElement labelVencimento;
	
	@FindBy(xpath = "//th[text()='Data Pago']")
	private WebElement labelDataPagto;
	
	@FindBy(xpath = "//th[text()='Obs.']")
	private WebElement labelObs;
	
	@FindBy(xpath = "//label[text()='Vencidas']")
	private WebElement labelVencidas;
	
	@FindBy(xpath = "//label[text()='Exibir: ']")
	private WebElement labelExibir;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remover')]")
	private WebElement btRemover;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoContasPagar() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de contas a pagar...");
	}
	
	public void navegarParaPaginaEdicaoContasPagar(String notaFiscal) {
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição de contas a pagar...");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Conta criada com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarContaPagarNaListagem(boolean contaPaga, String tipoConta, String fornecedor, String notaFiscal,  
			                                String valor, String parcela, String vencimento, String dataPagto) {
		aguardarElementoVisivel(btNovo);
		if (!valor.contains("R$")) {
			valor = "R$"+valor;
		}
		Log.info("Conferindo dados da conta à pagar...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]");
		exibeRegistroVisivel(by, btNovo);
		
		WebElement fillData 	  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[2]"));
		WebElement fillValor      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[6]"));
		WebElement fillParcela    = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[7]"));
		WebElement fillTipoConta  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[3]"));
		WebElement fillDataPagto  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[9]"));
		WebElement fillFornecedor = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[4]"));
		WebElement fillNotaFiscal = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[5]"));
		WebElement fillVencimento = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[8]"));
		
		if (contaPaga) {
			Utils.assertTrue("Compra está sendo exibida como NÃO paga", isContaPaga(notaFiscal));
		}else{
			Utils.assertFalse("Compra está sendo exibida como paga", isContaPaga(notaFiscal));
		}
		
		Utils.assertEquals(getTextElement(fillData).substring(0, 10), Utils.getDataAtual());
		Utils.assertEquals(getTextElement(fillTipoConta)      		, tipoConta);
		Utils.assertEquals(getTextElement(fillFornecedor)      		, fornecedor);
		Utils.assertEquals(getTextElement(fillNotaFiscal)      		, notaFiscal);
		Utils.assertEquals(getTextElement(fillValor)      			, valor);
		Utils.assertEquals(getTextElement(fillParcela)      		, parcela);
		Utils.assertEquals(getTextElement(fillVencimento)      		, vencimento);
		Utils.assertEquals(getTextElement(fillDataPagto).trim()		, dataPagto);

		Log.info("Dados da conta ["+tipoConta+"] com nota fiscal ["+notaFiscal+"] conferidos com sucesso.");
	}
	
	public boolean isContaPaga(String notaFiscal){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[1]/img");
		exibeRegistroVisivel(by, btNovo);
		if (isVisibility(by)) {
			WebElement checkPagamento = getDriver().findElement(by);
			String     statusCheck    = checkPagamento.getAttribute("alt");
			if (statusCheck.equals("Check 992fe03a2ec8c701d260d7dda2a4f635134c812cfdea8313567d7472898fea74")) {
				return true;
			}else{
				return false;
			}
		}else
			return false;
	}
	
	public boolean existsRegistroContaPagar(){
		return isVisibility(By.xpath("//tbody/tr/td[5]"));
	}
	
	public String getNotaFiscalCompra(){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[5]"))).trim();
	}
	
	public String getDataPagamento(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[9]"))).trim();
	}
	
	public String getVencimento(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[8]"))).trim();
	}
	
	public String getValor(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[6]"))).trim();
	}
	
	public String getValorQtdParcelas(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[7]"))).trim();
	}
	
	public String getTipoConta(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[3]"))).trim();
	}
	
	public String getFornecedor(String notaFiscal){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[4]"))).trim();
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Atualização realizada com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarContaPagarRemovida(String notaFiscal) {
		Utils.assertFalse("Conta de nota fiscal ["+notaFiscal+"] ainda está sendo exibida na listagem de contas a pagar", existsCompraProdutos(notaFiscal));
		Log.info("Conta de nota fiscal ["+notaFiscal+"] removida com sucesso");
	}
	
	public void removerContaPagar(String notaFiscal) {
		Log.info("Removendo conta pagar de nota ["+notaFiscal+"]...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]//../td[@class='last']/a[contains(.,'Remover')]");
		exibeRegistroVisivel(by, btNovo);
		WebElement removerConta = getDriver().findElement(by);
		click(removerConta);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Conta a pagar de nota fiscal ["+notaFiscal+"] removida...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro foi removido com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsCompraProdutos(String notaFiscal){
		Log.info("Verificando se conta de nota fiscal ["+notaFiscal+"] não está cadastrada...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void verificarOrtografiaPageContasPagar(){
		Log.info("Verificando ortografia da página de contas a pagar...");
		Utils.assertEquals(getTextElement(titleContasPagar) , "Contas à Pagar");
		Utils.assertEquals(getTextElement(labelPaga)        , "Paga");
		Utils.assertEquals(getTextElement(labelTipoConta)   , "Tipo de Conta");
		Utils.assertEquals(getTextElement(labelFornecedor)  , "Fornecedor");
		Utils.assertEquals(getTextElement(labelNotaFiscal)  , "Nota Fiscal");
		Utils.assertEquals(getTextElement(labelValor)       , "Valor");
		Utils.assertEquals(getTextElement(labelParcela)     , "Parcela");
		Utils.assertEquals(getTextElement(labelVencimento)  , "Vencimento");
		Utils.assertEquals(getTextElement(labelDataPagto)   , "Data Pago");
		Utils.assertEquals(getTextElement(labelObs)         , "Obs.");
		Utils.assertEquals(getTextElement(labelVencidas)    , "Vencidas");
		Utils.assertEquals(getTextElement(labelExibir)      , "Exibir:");
		Utils.assertEquals(getTextElement(btNovo)           , "Novo(a)");
		Utils.assertEquals(getTextValueAtributo(btAtualizar), "Atualizar");
		Log.info("Ortografia validada com sucesso.");
	}
}
