package br.com.ecommerce.pages.retaguarda.contas.receber;

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

public class PageContasReceber extends BasePage {

	public PageContasReceber() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleContasReceber;
	
	@FindBy(xpath = "//*[@href='/admin/bills_to_receive/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//input[@value='Atualizar']")
	private WebElement btAtualizar;
	
	@FindBy(name = "exibicao")
	private WebElement comboExibir;
	
	@FindBy(xpath = "//th[text()='Tipo']")
	private WebElement labelTipo;
	
	@FindBy(xpath = "//th[text()='Paga']")
	private WebElement labelPaga;
	
	@FindBy(xpath = "//th[text()='Cliente']")
	private WebElement labelCliente;
	
	@FindBy(xpath = "//th[text()='Método de Pagamento']")
	private WebElement labelMetodoPagto;
	
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

	public void navegarParaPageInclusaoContasReceber() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de contas a receber...");
	}
	
	public void navegarParaPaginaEdicaoContasReceber(String notaFiscal) {
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição de contas à receber...");
	}
	
	public void verificaFiltroContasAreceber(List<String> notasFiscais){
		aguardarElementoVisivel(btNovo);
		for (int i = 0; i < notasFiscais.size(); i++) {
			int linha = 1;
			while(isVisibility(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[10]"))){
				By by = By.xpath("//tbody/tr[contains(.,'"+notasFiscais.get(i)+"')]/td[10]");
				Utils.assertTrue("Conta está exibindo data pagamento da nota fiscal ["+notasFiscais.get(i)+"]", isVisibility(by));
				Utils.assertFalse("Check está marcando a nota fiscal ["+notasFiscais.get(i)+"] como paga]", isContaPaga(notasFiscais.get(i)));
				linha++;
			}
		}
		Log.info("Filtro contas à pagar verificado com sucesso.");
	}
	
	public void verificaFiltroContasRecebidas(List<String> notasFiscais){
		aguardarElementoVisivel(btNovo);
		for (int i = 0; i < notasFiscais.size(); i++) {
			int linha = 1;
			while(isVisibility(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[10]"))){
				By by = By.xpath("//tbody/tr[contains(.,'"+notasFiscais.get(i)+"')]/td[10]");
				Utils.assertFalse("Conta NÃO está exibindo data pagamento da nota fiscal ["+notasFiscais.get(i)+"]", isCreditoAreceber(by));
				Utils.assertTrue("Check NÃO está marcando a nota fiscal ["+notasFiscais.get(i)+"] como paga]", isContaPaga(notasFiscais.get(i)));
				linha++;
			}
		}
		Log.info("Filtro contas à pagar verificado com sucesso.");
	}
	
	public List<String> getNotasContasCreditoAreceber(){
		Log.info("Buscando notas fiscais de contas à receber...");
		List<String> notasFiscais = new ArrayList<>();
		int linha = 1;
		while(isVisibility(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]"))){
			By by = By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[10]");
			if (isCreditoAreceber(by)) {
				WebElement e = getDriver().findElement(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[6]"));
				notasFiscais.add(getTextElement(e));
			}
			linha++;
		}
		Log.info("Notas capturadas.");
		return notasFiscais;
	}
	
	public List<String> getNotasContasRecebidas(){
		Log.info("Buscando notas fiscais de contas recebidas...");
		List<String> notasFiscais = new ArrayList<>();
		int linha = 1;
		while(isVisibility(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]"))){
			By by = By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[10]");
			if (!isCreditoAreceber(by)) {
				WebElement e = getDriver().findElement(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[6]"));
				notasFiscais.add(getTextElement(e));
			}
			linha++;
		}
		Log.info("Notas capturadas.");
		return notasFiscais;
	}
	
	public boolean existsContaCreditoAreceber(){
		Log.info("Verificando se há receitas pagas na listagem...");
		int linha = 1;
		boolean existeContaCreditoPaga = false;
		while(isVisibility(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]"))){
			By by = By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[10]");
			if (isCreditoAreceber(by)) {
				existeContaCreditoPaga = true;
				break;
			}
			linha++;
		}
		return existeContaCreditoPaga;
	}
	
	public boolean existsContaRecebida(){
		Log.info("Verificando se há contas pagas na listagem...");
		int linha = 1;
		boolean existeContaPaga = false;
		while(isVisibility(By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]"))){
			By by = By.xpath("//tbody/tr["+linha+"][not(contains(.,'Total'))]//../td[10]");
			if (!isCreditoAreceber(by)) {
				existeContaPaga = true;
				break;
			}
			linha++;
		}
		return existeContaPaga;
	}
	
	public boolean isCreditoAreceber(By by){
		WebElement e = getDriver().findElement(by);
		if (getTextElement(e).equals("--")) {
			return true;
		}else
			return false;
	}
	
	public void filtrarContasAreceber(){
		aguardarElementoVisivel(comboExibir);
		selecionarValorComboTexto(comboExibir, "À Receber");
		click(btAtualizar);
		Log.info("Filtrando busca por contas À RECEBER...");
	}
	
	public void filtrarContasRecebidas(){
		aguardarElementoVisivel(comboExibir);
		selecionarValorComboTexto(comboExibir, "Recebidas");
		click(btAtualizar);
		Log.info("Filtrando busca por contas RECEBIDAS...");
	}
	
	public void filtrarContasVencidas(){
		aguardarElementoVisivel(comboExibir);
		selecionarValorComboTexto(comboExibir, "Pagas");
		click(btAtualizar);
		Log.info("Filtrando busca por contas PAGAS...");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro criado com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarContaReceberNaListagem(boolean contaPaga, String data, String tipoConta, String fornecedor,   
												String notaFiscal, String valor, String parcela, String vencimento, String dataPagto) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", btNovo);
		if (!valor.contains("R$")) {
			valor = "R$"+valor;
		}
		Log.info("Conferindo dados da conta à pagar...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		
		WebElement fillData 	  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[2]"));
		WebElement fillValor      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[7]"));
		WebElement fillDevedor 	  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[5]"));
		WebElement fillParcela    = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[8]"));
		WebElement fillTipoConta  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[3]"));
		WebElement fillDataPagto  = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[10]"));
		WebElement fillNotaFiscal = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[6]"));
		WebElement fillVencimento = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+notaFiscal+"')]//../td[9]"));
		
		if (contaPaga) {
			Utils.assertTrue("Compra está sendo exibida como NÃO paga", isContaPaga(notaFiscal));
		}else{
			Utils.assertFalse("Compra está sendo exibida como paga", isContaPaga(notaFiscal));
		}
		
		Utils.assertEquals(getTextElement(fillData).substring(0, 10), data);
		Utils.assertEquals(getTextElement(fillTipoConta)      		, tipoConta);
		Utils.assertEquals(getTextElement(fillDevedor)      		, fornecedor);
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
	
	public boolean existsRegistroContaReceber(){
		return isVisibility(By.xpath("//tbody/tr/td[6]"));
	}
	
	public String getNotaFiscalConta(){
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[6]"))).trim();
	}
	
	public String getDataPagamento(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[10]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[10]"))).trim();
	}
	
	public String getData(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[2]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(by)).substring(0, 10);
	}
	
	public String getVencimento(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[9]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[9]"))).trim();
	}
	
	public String getValor(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[7]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[7]"))).trim();
	}
	
	public String getValorQtdParcelas(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[8]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[8]"))).trim();
	}
	
	public String getTipoConta(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[3]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[3]"))).trim();
	}
	
	public String getDevedor(String notaFiscal){
		By by = By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[5]");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", getDriver().findElement(by));
		return getTextElement(getDriver().findElement(By.xpath("//tbody/tr/td[contains(.,'"+notaFiscal+"')]//../td[5]"))).trim();
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Atualização realizada com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarContaReceberRemovida(String notaFiscal) {
		Utils.assertFalse("Conta de nota fiscal ["+notaFiscal+"] ainda está sendo exibida na listagem de contas a pagar", existsContaReceber(notaFiscal));
		Log.info("Conta de nota fiscal ["+notaFiscal+"] removida com sucesso");
	}
	
	public void removerContaReceber(String notaFiscal) {
		Log.info("Removendo conta receber de nota ["+notaFiscal+"]...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]//../td[@class='last']/a[contains(.,'Remover')]");
		WebElement removerConta = getDriver().findElement(by);
		click(removerConta);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Conta à receber de nota fiscal ["+notaFiscal+"] removida...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro foi removido com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsContaReceber(String notaFiscal){
		Log.info("Verificando se conta de nota fiscal ["+notaFiscal+"] não está cadastrada...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+notaFiscal+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void verificarOrtografiaPageContasReceber(){
		Log.info("Verificando ortografia da página de contas a pagar...");
		Utils.assertEquals(getTextElement(titleContasReceber) , "Contas à Receber");
		Utils.assertEquals(getTextElement(labelPaga)          , "Paga");
		Utils.assertEquals(getTextElement(labelTipo)          , "Tipo");
		Utils.assertEquals(getTextElement(labelCliente)       , "Cliente");
		Utils.assertEquals(getTextElement(labelMetodoPagto)   , "Método de Pagamento");
		Utils.assertEquals(getTextElement(labelNotaFiscal)    , "Nota Fiscal");
		Utils.assertEquals(getTextElement(labelValor)         , "Valor");
		Utils.assertEquals(getTextElement(labelParcela)       , "Parcela");
		Utils.assertEquals(getTextElement(labelVencimento)    , "Vencimento");
		Utils.assertEquals(getTextElement(labelDataPagto)     , "Data Pago");
		Utils.assertEquals(getTextElement(labelObs)           , "Obs.");
		Utils.assertEquals(getTextElement(labelVencidas)      , "Vencidas");
		Utils.assertEquals(getTextElement(labelExibir)        , "Exibir:");
		Utils.assertEquals(getTextElement(btNovo)             , "Novo");
		Utils.assertEquals(getTextValueAtributo(btAtualizar)  , "Atualizar");
		Log.info("Ortografia validada com sucesso.");
	}
}
