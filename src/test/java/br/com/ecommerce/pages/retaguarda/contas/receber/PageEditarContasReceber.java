package br.com.ecommerce.pages.retaguarda.contas.receber;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarContasReceber extends BasePage {

	public PageEditarContasReceber() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarContasReceber;
	
	@FindBy(xpath = "//label[text()='Nota Fiscal:']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//label[text()='Devedor:']")
	private WebElement labelDevedor;
	
	@FindBy(xpath = "//label[text()='Data Pago:']")
	private WebElement labelDataPagamento;
	
	@FindBy(xpath = "//label[text()='Vencimento:']")
	private WebElement labelDataVencimento;
	
	@FindBy(xpath = "//label[text()='Tipo de Conta:']")
	private WebElement labelTipoConta;
	
	@FindBy(xpath = "//label[text()='Valor Total:']")
	private WebElement labelValorTotal;
	
	@FindBy(xpath = "//label[text()='Observações:']")
	private WebElement labelObservacoes;
	
	@FindBy(id = "bill_to_receive_bill_type_id")
	private WebElement comboTipoConta;
	
	@FindBy(id = "bill_to_receive_number")
	private WebElement inputQtdParcelas;
	
	@FindBy(id = "bill_to_receive_debtor")
	private WebElement inputDevedor;

	@FindBy(id = "bill_to_receive_date_to_pay")
	private WebElement inputDataVencimento;

	@FindBy(id = "bill_to_receive_invoice_number")
	private WebElement inputNotaFiscal;
	
	@FindBy(id = "bill_to_receive_date")
	private WebElement inputDataPagto;
	
	@FindBy(id = "bill_to_receive_value")
	private WebElement inputValorTotal;
	
	@FindBy(id = "bill_to_receive_notes")
	private WebElement inputObservacoes;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void alterarNotaFiscalCompraProduto(String notaFiscal, String notaFiscal2) {
		Log.info("Alterando nota fiscal ["+notaFiscal+"] para ["+notaFiscal2+"]...");
		aguardarElementoVisivel(inputNotaFiscal);
		preencherCampo(inputNotaFiscal, notaFiscal2);
		click(btSalvar);
		Log.info("Nota alterada para ["+notaFiscal2+"].");
	}
	
	public void verificarOrtografiaPageEditarContasReceber(){
		Log.info("Verificando ortografia da página de edição de contas à receber...");
		Utils.assertEquals(getTextElement(titleEditarContasReceber) , "Editar Conta à Pagar");
		Utils.assertEquals(getTextElement(labelTipoConta)           , "Tipo de Conta:");
		Utils.assertEquals(getTextElement(labelDevedor)    		    , "Devedor:");
		Utils.assertEquals(getTextElement(labelDataVencimento)		, "Vencimento:");
		Utils.assertEquals(getTextElement(labelNotaFiscal)	  		, "Nota Fiscal:");
		Utils.assertEquals(getTextElement(labelDataPagamento)       , "Data Pago:");
		Utils.assertEquals(getTextElement(labelValorTotal)			, "Valor Total:");
		tab(inputValorTotal);
		Utils.assertEquals(getTextElement(labelObservacoes)    		, "Observações:");
		tab(inputObservacoes);
		Utils.assertEquals(getTextElement(btCancelar)     	  		, "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)     		, "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
