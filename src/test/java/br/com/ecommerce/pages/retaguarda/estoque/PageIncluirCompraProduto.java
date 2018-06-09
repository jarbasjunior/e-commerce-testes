package br.com.ecommerce.pages.retaguarda.estoque;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirCompraProduto extends BasePage {

	public PageIncluirCompraProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovaCompra;
	
	@FindBy(xpath = "//label[text()='Nota Fiscal']")
	private WebElement labelNotaFiscal;
	
	@FindBy(xpath = "//label[text()='Fornecedor']")
	private WebElement labelFornecedor;
	
	@FindBy(xpath = "//label[text()='Data']")
	private WebElement labelData;
	
	@FindBy(xpath = "//label[text()='Parcelas']")
	private WebElement labelParcelas;
	
	@FindBy(xpath = "//label[text()='Data de Vencimento']")
	private WebElement labelDataVencimento;
	
	@FindBy(xpath = "//label[text()='Desconto']")
	private WebElement labelDesconto;
	
	@FindBy(xpath = "//label[text()='Outras Despesas']")
	private WebElement labelOutrasDespesas;
	
	@FindBy(xpath = "//label[text()='Compra paga?']")
	private WebElement labelCompraPaga;

	@FindBy(id = "product_purchase_invoice_number")
	private WebElement inputNotaFiscal;
	
	@FindBy(id = "product_purchase_date")
	private WebElement inputData;
	
	@FindBy(id = "product_purchase_supplier_id")
	private WebElement comboFornecedores;
	
	@FindBy(id = "product_purchase_installments")
	private WebElement inputParcelas;
	
	@FindBy(id = "product_purchase_due_date")
	private WebElement inputDataVencimento;

	@FindBy(id = "product_purchase_discount")
	private WebElement inputDesconto;
	
	@FindBy(id = "product_purchase_other_expenses")
	private WebElement inputOutrasDespesas;
	
	@FindBy(id = "product_purchase_is_paid")
	private WebElement checkCompraPaga;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirCompraProduto(String notaFiscal, String data, String parcelas, String dataVencimento, 
			                         String desconto,   String outrasDespesas, boolean compraPaga) {
		
		aguardarElementoVisivel(btSalvar);
		Log.info("Inserindo dados da compra de produto...");
		
		List<WebElement> listaDeFornecedores = getAllElementosCombo(comboFornecedores);
		String           indiceFornecedor    = Utils.geraNumeroEntre(1, listaDeFornecedores.size());
		String           fornecedor          = getTextElement(getDriver().findElement(By.xpath("//*[@id='product_purchase_supplier_id']/option["+indiceFornecedor+"]")));
		selecionarValorComboTexto(comboFornecedores, fornecedor);
		preencherCampo(inputNotaFiscal     , notaFiscal);
		inputData.sendKeys(data);
		preencherCampo(inputParcelas       , parcelas);
		inputDataVencimento.sendKeys(dataVencimento);
		preencherCampo(inputDesconto       , desconto);
		preencherCampo(inputOutrasDespesas , outrasDespesas);
		if (compraPaga) {
			marcarCheckbox(checkCompraPaga);
		}else
			desmarcarCheckbox(checkCompraPaga);
		
		click(btSalvar);
		Log.info("Dados da compra inseridos");
	}
	
	public void verificarOrtografiaPageIncluirCompraProduto(){
		Log.info("Verificando ortografia da página de cadastro de produto...");
		Utils.assertEquals(getTextElement(titleNovaCompra)    , "Nova Compra");
		Utils.assertEquals(getTextElement(labelFornecedor)    , "Fornecedor");
		Utils.assertEquals(getTextElement(labelNotaFiscal)	  , "Nota Fiscal");
		Utils.assertEquals(getTextElement(labelData)          , "Data");
		Utils.assertEquals(getTextElement(labelParcelas)      , "Parcelas");
		Utils.assertEquals(getTextElement(labelDataVencimento), "Data de Vencimento");
		Utils.assertEquals(getTextElement(labelDesconto)      , "Desconto");
		Utils.assertEquals(getTextElement(labelOutrasDespesas), "Outras Despesas");
		Utils.assertEquals(getTextElement(labelCompraPaga)    , "Compra paga?");
		Utils.assertEquals(getTextElement(btCancelar)     	  , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)     , "Criar Compra de Produtos");
		Log.info("Ortografia validada com sucesso.");
	}
}
