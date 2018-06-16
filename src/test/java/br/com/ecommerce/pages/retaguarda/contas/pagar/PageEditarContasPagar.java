package br.com.ecommerce.pages.retaguarda.contas.pagar;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarContasPagar extends BasePage {

	public PageEditarContasPagar() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarContasPagar;
	
	@FindBy(xpath = "//label[text()='Nota Fiscal:']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//label[text()='Fornecedor:']")
	private WebElement labelFornecedor;
	
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
	
	@FindBy(id = "bill_to_pay_bill_type_id")
	private WebElement comboTipoConta;
	
	@FindBy(id = "bill_to_pay_number")
	private WebElement inputQtdParcelas;

	@FindBy(id = "bill_to_pay_supplier_id")
	private WebElement comboFornecedores;
	
	@FindBy(id = "payment_day")
	private WebElement inputDataVencimento;

	@FindBy(id = "bill_to_pay_invoice_number")
	private WebElement inputNotaFiscal;
	
	@FindBy(id = "bill_to_pay_paid_at")
	private WebElement inputDataPagto;
	
	@FindBy(id = "bill_to_pay_value")
	private WebElement inputValorTotal;
	
	@FindBy(id = "bill_to_pay_notes")
	private WebElement inputObservacoes;
	
	@FindBy(id = "product_purchase_is_paid")
	private WebElement checkCompraPaga;
	
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
	
	public void verificarOrtografiaPageEditarContasPagar(){
		Log.info("Verificando ortografia da página de edição de contas à pagar...");
		Utils.assertEquals(getTextElement(titleEditarContasPagar) , "Editar Conta à Pagar");
		Utils.assertEquals(getTextElement(labelTipoConta)           , "Tipo de Conta:");
		Utils.assertEquals(getTextElement(labelFornecedor)    		, "Fornecedor:");
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
