package br.com.ecommerce.pages.retaguarda.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageMenu extends BasePage<PageMenu> {

	public PageMenu() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	//==========================================================
	// Botao principal retaguarda 
	//==========================================================
	@FindBy(xpath = "//*[@href='/admin/dashboard']")
	WebElement btDashboard;
	
	//==========================================================
	// Menu Dados Gerais
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Dados Gerais']")
	public static WebElement menuDadosGerais;

	@FindBy(xpath = "//*[@href='/admin/settings']")
	public static WebElement configuracoes;
	
	//==========================================================
	// Menu Cadastros
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']")
	public static WebElement menuCadastros;

	@FindBy(xpath = "//*[@href='/admin/categories']")
	public static WebElement cadastroCategorias;
	
	@FindBy(xpath = "//*[@href='/admin/colors']")
	public static WebElement cadastroCores;
	
	@FindBy(xpath = "//*[@href='/admin/clients']")
	public static WebElement cadastroClientes;
	
	@FindBy(xpath = "//*[@href='/admin/shipping_methods']")
	public static WebElement cadastroMetodosDeEnvio;
	
	@FindBy(xpath = "//*[@href='/admin/suppliers']")
	public static WebElement cadastroFornecedores;

	@FindBy(xpath = "//*[@href='/admin/employees']")
	public static WebElement cadastroFuncionarios;
	
	@FindBy(xpath = "//*[@href='/admin/brands']")
	public static WebElement cadastroMarcas;
	
	@FindBy(xpath = "//*[@href='/admin/tax_groups']")
	public static WebElement cadastroGruposFiscais;
	
	@FindBy(xpath = "//*[@href='/admin/credit_card_companies']")
	public static WebElement cadastroOpCartaoCredito;
	
	@FindBy(xpath = "//*[@href='/admin/products']")
	public static WebElement cadastroProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/payment_types']")
	public static WebElement cadastroTiposPagamento;
	
	@FindBy(xpath = "//*[@href='/admin/sizes']")
	public static WebElement cadastroTamanhos;
	
	@FindBy(xpath = "//*[@href='/admin/bill_types']")
	public static WebElement cadastroTiposDeContas;
	
	@FindBy(xpath = "//*[@href='/admin/product_in_types']")
	public static WebElement cadastroTiposDeEntrada;
	
	@FindBy(xpath = "//*[@href='/admin/product_out_types']")
	public static WebElement cadastroTiposDeSaidas;
	
	@FindBy(xpath = "//*[@href='/admin/slides']")
	public static WebElement cadastroSlides;
	
	@FindBy(xpath = "//*[@href='/admin/units']")
	public static WebElement cadastroUnidades;
	
	
	//==========================================================
	// Menu Estoque 
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']")
	public static WebElement menuEstoque;
	
	@FindBy(xpath = "//*[@href='/admin/dashboard' and text()='Entrada de Produtos']")
	public static WebElement entradaDeProdutos;

	@FindBy(xpath = "//*[@href='/admin/products_outs']")
	public static WebElement saidaDeProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/product_purchases']")
	public static WebElement compraDeProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/date_inventories']")
	public static WebElement balanco;
	
	
	//==========================================================
	// Menu Contas
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Contas']")
	public static WebElement menuContas;
		
	@FindBy(xpath = "//*[@href='/admin/bills_to_pay']")
	public static WebElement contasPagar;
	
	@FindBy(xpath = "//*[@href='/admin/bills_to_receive']")
	public static WebElement contasReceber;
	
	
	//==========================================================
	// Menu Relatórios
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']")
	public static WebElement menuRelatorios;
	
	@FindBy(xpath = "//*[@href='/admin/reports/inventaries']")
	public static WebElement relatorioEstqoueDeProduto;
	
	@FindBy(xpath = "//*[@href='/admin/reports/sales']")
	public static WebElement relatorioVendas;
	
	@FindBy(xpath = "//*[@href='/admin/reports/products']")
	public static WebElement relatorioProduto;
	
	@FindBy(xpath = "//*[@href='/admin/reports/bills_to_pay']")
	public static WebElement relatorioContasPagar;
	
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
		acessarMenu(menuCadastros, cadastroTiposDeContas);
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
