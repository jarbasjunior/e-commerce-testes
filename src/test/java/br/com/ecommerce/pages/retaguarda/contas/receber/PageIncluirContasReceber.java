package br.com.ecommerce.pages.retaguarda.contas.receber;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirContasReceber extends BasePage {

	public PageIncluirContasReceber() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleAdicionarContaReceber;
	
	@FindBy(xpath = "//label[text()='Nota Fiscal:']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//label[text()='Devedor:']")
	private WebElement labelDevedor;
	
	@FindBy(xpath = "//label[text()='Data Recebimento:']")
	private WebElement labelDataRecebimento;
	
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

	@FindBy(id = "bill_to_receive_bill_type_id")
	private WebElement comboTipoConta;
	
	@FindBy(id = "bill_to_receive_number")
	private WebElement inputQtdParcelas;

	@FindBy(id = "bill_to_receive_debtor")
	private WebElement inputDevedor;
	
	@FindBy(id = "payment_day")
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


	public String incluirContasReceber(String qtdParcelas, String vencimento, String devedor,
											 String notaFiscal, String dataRecebimento, String valorTotal) {
		
		aguardarElementoVisivel(btSalvar);
		Log.info("Inserindo dados da conta à receber...");
		
		List<WebElement> listaDeContasReceber  = getAllElementosCombo(comboTipoConta);
		String           indiceContasReceber   = Utils.geraNumeroEntre(1, listaDeContasReceber.size());
		String 			 tipoConta             = getTextElement(getDriver().findElement(By.xpath("//*[@id='bill_to_receive_bill_type_id']/option["+indiceContasReceber+"]")));;
		selecionarValorComboTexto(comboTipoConta, tipoConta);
		preencherCampo(inputQtdParcelas   , qtdParcelas);
		preencherCampo(inputDevedor       , devedor);
		preencherCampo(inputDataVencimento, vencimento);
		preencherCampo(inputNotaFiscal    , notaFiscal);
		preencherCampo(inputDataPagto     , dataRecebimento);
		tab(inputDataPagto);
		preencherCampo(inputValorTotal    , valorTotal);
		click(btSalvar);
		Log.info("Dados da conta à receber inseridos");
		return tipoConta;
	}
	
	public void verificarOrtografiaPageIncluirContasReceber(){
		Log.info("Verificando ortografia da página de cadastro de contas à receber...");
		Utils.assertEquals(getTextElement(titleAdicionarContaReceber) , "Adicionar Conta à Receber");
		Utils.assertEquals(getTextElement(labelTipoConta)             , "Tipo de Conta:");
		Utils.assertEquals(getTextElement(labelQtdParcelas)           , "Quantidade de Parcelas:");
		Utils.assertEquals(getTextElement(labelDevedor)    		 	  , "Devedor:");
		Utils.assertEquals(getTextElement(labelDataVencimento)		  , "Vencimento (dia):");
		Utils.assertEquals(getTextElement(labelNotaFiscal)	  		  , "Nota Fiscal:");
		Utils.assertEquals(getTextElement(labelDataRecebimento)       , "Data Recebimento:");
		Utils.assertEquals(getTextElement(labelValorTotal)			  , "Valor Total:");
		tab(inputValorTotal);
		Utils.assertEquals(getTextElement(labelObservacoes)    		  , "Observações:");
		tab(inputObservacoes);
		Utils.assertEquals(getTextElement(btCancelar)     	  		  , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)     		  , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
