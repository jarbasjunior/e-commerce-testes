package br.com.ecommerce.pages.retaguarda.contas.pagar;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirContasPagar extends BasePage {

	public PageIncluirContasPagar() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleAdicionarContaPagar;
	
	@FindBy(xpath = "//label[text()='Nota Fiscal:']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//label[text()='Fornecedor:']")
	private WebElement labelFornecedor;
	
	@FindBy(xpath = "//label[text()='Data Pago:']")
	private WebElement labelDataPagamento;
	
	@FindBy(xpath = "//label[text()='Quantidade de Parcelas:']")
	private WebElement labelQtdParcelas;
	
	@FindBy(xpath = "//label[text()='Vencimento (dia):']")
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
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public List<String> incluirContasPagar(String qtdParcelas, String vencimento, String notaFiscal,  
								           String dataPagto,   String valorTotal) {
		
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		aguardarElementoVisivel(btSalvar);
		Log.info("Inserindo dados da conta à pagar...");
		
		List<WebElement> listaDeContasPagar  = getAllElementosCombo(comboTipoConta);
		String           indiceContasPagar   = Utils.geraNumeroEntre(1, listaDeContasPagar.size());
		String           contaPagar          = getTextElement(getDriver().findElement(By.xpath("//*[@id='bill_to_pay_bill_type_id']/option["+indiceContasPagar+"]")));
		tipoConta_E_Fornecedor.add(contaPagar);
		List<WebElement> listaDeFornecedores = getAllElementosCombo(comboFornecedores);
		String           indiceFornecedor    = Utils.geraNumeroEntre(1, listaDeFornecedores.size());
		String           fornecedor          = getTextElement(getDriver().findElement(By.xpath("//*[@id='bill_to_pay_supplier_id']/option["+indiceFornecedor+"]")));
		tipoConta_E_Fornecedor.add(fornecedor);
		selecionarValorComboTexto(comboTipoConta, contaPagar);
		selecionarValorComboTexto(comboFornecedores, fornecedor);
		preencherCampo(inputQtdParcelas   , qtdParcelas);
		preencherCampo(inputDataVencimento, vencimento);
		preencherCampo(inputNotaFiscal    , notaFiscal);
		preencherCampo(inputDataPagto     , dataPagto);
		tab(inputDataPagto);
		preencherCampo(inputValorTotal    , valorTotal);
		click(btSalvar);
		Log.info("Dados da conta à pagar inseridos");
		return tipoConta_E_Fornecedor;
	}
	
	public void verificarOrtografiaPageIncluirContasPagar(){
		Log.info("Verificando ortografia da página de cadastro de contas à pagar...");
		Utils.assertEquals(getTextElement(titleAdicionarContaPagar) , "Adicionar Conta à Pagar");
		Utils.assertEquals(getTextElement(labelTipoConta)           , "Tipo de Conta:");
		Utils.assertEquals(getTextElement(labelQtdParcelas)         , "Quantidade de Parcelas:");
		Utils.assertEquals(getTextElement(labelFornecedor)    		, "Fornecedor:");
		Utils.assertEquals(getTextElement(labelDataVencimento)		, "Vencimento (dia):");
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
