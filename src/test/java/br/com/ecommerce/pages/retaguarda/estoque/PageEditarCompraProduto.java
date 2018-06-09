package br.com.ecommerce.pages.retaguarda.estoque;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarCompraProduto extends BasePage {

	public PageEditarCompraProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarCompra;
	
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

	public void alterarNotaFiscalCompraProduto(String notaFiscal, String notaFiscal2) {
		Log.info("Alterando nota fiscal ["+notaFiscal+"] para ["+notaFiscal2+"]...");
		aguardarElementoVisivel(inputNotaFiscal);
		preencherCampo(inputNotaFiscal, notaFiscal2);
		click(btSalvar);
		Log.info("Nota alterada para ["+notaFiscal2+"].");
	}
	
	public void alterarStatusCompraProduto(String notaFiscal, boolean compraPaga) {
		aguardarElementoVisivel(checkCompraPaga);
		if (compraPaga) {
			desmarcarCheckbox(checkCompraPaga);
			Log.info("Alterando status pagamento da nota fiscal ["+notaFiscal+"] de PAGA para PENDENTE DE PAGAMENTO...");
		}else{
			marcarCheckbox(checkCompraPaga);
			Log.info("Alterando status pagamento da nota fiscal ["+notaFiscal+"] de PENDENTE DE PAGAMENTO para PAGA...");
		}
		click(btSalvar);
		Log.info("Status do pagamento da compra alterado");
	}
	
	public void verificarOrtografiaPageEditarCompraProduto(){
		Log.info("Verificando ortografia da página de edição de compra de produtos...");
		Utils.assertEquals(getTextElement(titleEditarCompra)  , "Alterar Compra de Produto");
		Utils.assertEquals(getTextElement(labelFornecedor)    , "Fornecedor");
		Utils.assertEquals(getTextElement(labelNotaFiscal)	  , "Nota Fiscal");
		Utils.assertEquals(getTextElement(labelData)          , "Data");
		Utils.assertEquals(getTextElement(labelParcelas)      , "Parcelas");
		Utils.assertEquals(getTextElement(labelDataVencimento), "Data de Vencimento");
		Utils.assertEquals(getTextElement(labelDesconto)      , "Desconto");
		Utils.assertEquals(getTextElement(labelOutrasDespesas), "Outras Despesas");
		Utils.assertEquals(getTextElement(labelCompraPaga)    , "Compra paga?");
		Utils.assertEquals(getTextElement(btCancelar)     	  , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)     , "Atualizar Compra de Produtos");
		Log.info("Ortografia validada com sucesso.");
	}
}
