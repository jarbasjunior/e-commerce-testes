package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarProduto extends BasePage {

	public PageEditarProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarProduto;
	
	@FindBy(xpath = "//label[text()='Nome']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//label[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//label[text()='Marca']")
	private WebElement labelMarca;
	
	@FindBy(xpath = "//label[text()='Unidade']")
	private WebElement labelUnidade;
	
	@FindBy(xpath = "//label[text()='Grupo Fiscal']")
	private WebElement labelGrupoFical;
	
	@FindBy(xpath = "//label[text()='Preço de compra']")
	private WebElement labelPrecoCompra;
	
	@FindBy(xpath = "//label[text()='Preço de venda']")
	private WebElement labelPrecoVenda;
	
	@FindBy(xpath = "//label[text()='Preço promocional']")
	private WebElement labelPrecoPromocional;

	@FindBy(xpath = "//label[text()='Quantidade minima']")
	private WebElement labelQtdMinima;
	
	@FindBy(xpath = "//label[text()='Código de barras']")
	private WebElement labelCodBarras;
	
	@FindBy(id = "product_name")
	private WebElement inputNome;
	
	@FindBy(id = "product_description")
	private WebElement inputDescricao;
	
	@FindBy(id = "product_tax_group_id")
	private WebElement comboGrupoFiscal;
	
	@FindBy(id = "product_brand_id")
	private WebElement comboMarca;
	
	@FindBy(id = "product_cost_price")
	private WebElement inputPrecoCompra;
	
	@FindBy(id = "product_selling_price")
	private WebElement inputPrecoVenda;

	@FindBy(id = "product_minimum_quantity")
	private WebElement inputQtdMinima;
	
	@FindBy(id = "product_barcode")
	private WebElement inputCodBarras;

	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void alterarProduto(String produto, String descricao) {
		Log.info("Alterando dados do produto para ["+produto+"]...");
		aguardarElementoVisivel(inputNome);
		preencherCampo(inputNome     , produto);
		preencherCampo(inputDescricao, descricao);
		click(btSalvar);
		Log.info("Dados produto ["+produto+"] alterados.");
	}
	
	public void verificarOrtografiaPageEditarProduto(){
		Log.info("Verificando ortografia da página de edição de produtos...");
		Utils.assertEquals(getTextElement(titleEditarProduto)   , "Editar Produto");
		Utils.assertEquals(getTextElement(labelNome)		    , "Nome");
		Utils.assertEquals(getTextElement(labelDescricao)	    , "Descrição");
		Utils.assertEquals(getTextElement(labelMarca)           , "Marca");
		Utils.assertEquals(getTextElement(labelUnidade)         , "Unidade");
		Utils.assertEquals(getTextElement(labelGrupoFical)      , "Grupo Fiscal");
		Utils.assertEquals(getTextElement(labelPrecoCompra)     , "Preço de compra");
		Utils.assertEquals(getTextElement(labelPrecoVenda)      , "Preço de venda");
		Utils.assertEquals(getTextElement(labelPrecoPromocional), "Preço promocional");
		Utils.assertEquals(getTextElement(labelQtdMinima)       , "Quantidade minima");
		Utils.assertEquals(getTextElement(labelCodBarras)       , "Código de barras");
		tab(inputCodBarras);
		Utils.assertEquals(getTextElement(btCancelar)     	   	, "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)       , "Salvar");
		pageDown(btCancelar);
		Log.info("Ortografia validada com sucesso.");
	}
}
