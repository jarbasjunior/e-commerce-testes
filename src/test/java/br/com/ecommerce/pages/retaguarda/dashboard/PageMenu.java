package br.com.ecommerce.pages.retaguarda.dashboard;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageMenu extends BasePage {

	public PageMenu() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//==========================================================
	// Botao principal retaguarda 
	//==========================================================
	@FindBy(xpath = "//*[@href='/admin/dashboard']")
	private WebElement btDashboard;
	
	//==========================================================
	// Menu Dados Gerais
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Dados Gerais']")
	private WebElement menuDadosGerais;

	@FindBy(xpath = "//*[@href='/admin/settings']")
	private WebElement configuracoes;
	
	//==========================================================
	// Menu Cadastros
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']")
	private WebElement menuCadastros;

	@FindBy(xpath = "//*[@href='/admin/categories']")
	private WebElement cadastroCategorias;
	
	@FindBy(xpath = "//*[@href='/admin/colors']")
	private WebElement cadastroCores;
	
	@FindBy(xpath = "//*[@href='/admin/clients']")
	private WebElement cadastroClientes;
	
	@FindBy(xpath = "//*[@href='/admin/shipping_methods']")
	private WebElement cadastroMetodosDeEnvio;
	
	@FindBy(xpath = "//*[@href='/admin/suppliers']")
	private WebElement cadastroFornecedores;

	@FindBy(xpath = "//*[@href='/admin/employees']")
	private WebElement cadastroFuncionarios;
	
	@FindBy(xpath = "//*[@href='/admin/brands']")
	private WebElement cadastroMarcas;
	
	@FindBy(xpath = "//*[@href='/admin/tax_groups']")
	private WebElement cadastroGruposFiscais;
	
	@FindBy(xpath = "//*[@href='/admin/credit_card_companies']")
	private WebElement cadastroOpCartaoCredito;
	
	@FindBy(xpath = "//*[@href='/admin/products']")
	private WebElement cadastroProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/payment_types']")
	private WebElement cadastroTiposPagamento;
	
	@FindBy(xpath = "//*[@href='/admin/sizes']")
	private WebElement cadastroTamanhos;
	
	@FindBy(xpath = "//*[@href='/admin/bill_types']")
	private WebElement cadastroTiposDeContas;
	
	@FindBy(xpath = "//*[@href='/admin/product_in_types']")
	private WebElement cadastroTiposDeEntrada;
	
	@FindBy(xpath = "//*[@href='/admin/product_out_types']")
	private WebElement cadastroTiposDeSaidas;
	
	@FindBy(xpath = "//*[@href='/admin/slides']")
	private WebElement cadastroSlides;
	
	@FindBy(xpath = "//*[@href='/admin/units']")
	private WebElement cadastroUnidades;
	
	
	//==========================================================
	// Menu Estoque 
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']")
	private WebElement menuEstoque;
	
	@FindBy(xpath = "//*[@href='/admin/dashboard' and text()='Entrada de Produtos']")
	private WebElement entradaDeProdutos;

	@FindBy(xpath = "//*[@href='/admin/products_outs']")
	private WebElement saidaDeProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/product_purchases']")
	private WebElement compraDeProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/date_inventories']")
	private WebElement balanco;
	
	
	//==========================================================
	// Menu Contas
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Contas']")
	private WebElement menuContas;
		
	@FindBy(xpath = "//*[@href='/admin/bills_to_pay']")
	private WebElement contasPagar;
	
	@FindBy(xpath = "//*[@href='/admin/bills_to_receive']")
	private WebElement contasReceber;
	
	
	//==========================================================
	// Menu Relatórios
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']")
	private WebElement menuRelatorios;
	
	@FindBy(xpath = "//*[@href='/admin/reports/inventaries']")
	private WebElement relatorioEstqoueDeProduto;
	
	@FindBy(xpath = "//*[@href='/admin/reports/sales']")
	private WebElement relatorioVendas;
	
	@FindBy(xpath = "//*[@href='/admin/reports/products']")
	private WebElement relatorioProduto;
	
	@FindBy(xpath = "//*[@href='/admin/reports/bills_to_pay']")
	private WebElement relatorioContasPagar;
	
	//==========================================================
	// ******************* FIM MAPEAMENTO *********************
    //==========================================================
	
	
	//==========================================================
	// ******************* ACESSO AOS MENUS ********************
	//==========================================================
	
	public void acessarMenu(WebElement... elements){
		try {
			click(btDashboard);
		} catch (Exception e) {
			Utils.takeScreenshot(elements.clone().toString());
			Utils.assertFail("Erro ao acessar menu. Favor verificar em: ["+Property.EVIDENCIAS_TESTE_PATH+"]");
		}
		for(WebElement element: elements){
			Utils.wait(500);
			click(element);
		}
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU DADOS GERAIS
	 * -------------------------------------------------
	 */
	public void acessarMenuDadosGeraisConfiguracoes(){
		Log.info("Navegando para tela de configurações...");
		acessarMenu(menuDadosGerais,configuracoes);
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU CADASTROS
	 * -------------------------------------------------
	 */
	public void acessarMenuCadastrosCategorias(){
		Log.info("Navegando para tela de cadastro de categorias...");
		acessarMenu(menuCadastros, cadastroCategorias);
	}
	
	public void acessarMenuCadastrosCores(){
		acessarMenu(menuCadastros, cadastroCores);
	}
	
	public void acessarMenuCadastrosClientes(){
		acessarMenu(menuCadastros, cadastroClientes);
	}
	
	public void acessarMenuCadastrosMetodosDeEnvio(){
		acessarMenu(menuCadastros, cadastroMetodosDeEnvio);
	}
	
	public void acessarMenuCadastrosFornecedores(){
		acessarMenu(menuCadastros, cadastroFornecedores);
	}
	
	public void acessarMenuCadastrosFuncionarios(){
		acessarMenu(menuCadastros, cadastroFuncionarios);
	}
	
	public void acessarMenuCadastrosMarcas(){
		acessarMenu(menuCadastros, cadastroMarcas);
	}
	
	public void acessarMenuCadastrosGruposFiscais(){
		acessarMenu(menuCadastros, cadastroGruposFiscais);
	}
	
	public void acessarMenuCadastrosOpCartaoCredito(){
		acessarMenu(menuCadastros, cadastroOpCartaoCredito);
	}
	
	public void acessarMenuCadastrosProdutos(){
		acessarMenu(menuCadastros, cadastroProdutos);
	}
	
	public void acessarMenuCadastrosTiposDePagamentos(){
		acessarMenu(menuCadastros, cadastroTiposPagamento);
	}
	
	public void acessarMenuCadastrosTamanhos(){
		acessarMenu(menuCadastros, cadastroTamanhos);
	}
	
	public void acessarMenuCadastrosTiposDeConta(){
		click(menuCadastros);
		Utils.wait(1000);
		((JavascriptExecutor) getDriver()).executeScript(
	            "arguments[0].scrollIntoView();", cadastroTiposDeContas);
		click(cadastroTiposDeContas);
	}
	
	public void acessarMenuCadastrosTiposDeEntrada(){
		acessarMenu(menuCadastros, cadastroTiposDeEntrada);
	}
	
	public void acessarMenuCadastrosTiposDeSaidas(){
		acessarMenu(menuCadastros, cadastroTiposDeSaidas);
	}
	
	public void acessarMenuCadastrosSlides(){
		acessarMenu(menuCadastros, cadastroSlides);
	}
	
	public void acessarMenuCadastrosUnidades(){
		acessarMenu(menuCadastros, cadastroUnidades);
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU ESTOQUE
	 * -------------------------------------------------
	 */
	public void acessarMenuEstoqueEntradaDeProdutos(){
		acessarMenu(menuEstoque, entradaDeProdutos);
	}
	
	public void acessarMenuEstoqueSaidaDeProdutos(){
		acessarMenu(menuEstoque, saidaDeProdutos);
	}
	
	public void acessarMenuEstoqueCompraDeProdutos(){
		acessarMenu(menuEstoque, compraDeProdutos);
	}
	
	public void acessarMenuEstoqueBalanco(){
		acessarMenu(menuEstoque, balanco);
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU CONTAS
	 * -------------------------------------------------
	 */
	public void acessarMenuContasApagar(){
		acessarMenu(menuContas, contasPagar);
	}
	
	public void acessarMenuContasAreceber(){
		acessarMenu(menuContas, contasReceber);
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU RELATÓRIOS
	 * -------------------------------------------------
	 */
	public void acessarMenuRelatoriosEstoqueDeProduto(){
		acessarMenu(menuRelatorios, relatorioEstqoueDeProduto);
	}
	
	public void acessarMenuRelatoriosVendas(){
		acessarMenu(menuRelatorios, relatorioVendas);
	}
	
	public void acessarMenuRelatoriosProdutos(){
		acessarMenu(menuRelatorios, relatorioProduto);
	}
	
	public void acessarMenuRelatoriosContas_A_Pagar(){
		acessarMenu(menuRelatorios, relatorioContasPagar);
	}
	
	//==========================================================
	// ****************** FIM ACESSO MENUS ********************
	//==========================================================
}
