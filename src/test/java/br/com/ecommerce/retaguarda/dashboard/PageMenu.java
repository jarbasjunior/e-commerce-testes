package br.com.ecommerce.retaguarda.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageMenu extends PageObjectGeneric<PageMenu> {

	public PageMenu() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}
	
	//==========================================================
	// Botao principal retaguarda 
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Dashboard']")
	WebElement btDashboard;
	
	//==========================================================
	// Menu Dados Gerais
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Dados Gerais']")
	public static WebElement menuDadosGerais;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Dados Gerais']//..//..//ul/li/a[text()='Configurações']")
	public static WebElement configuracoes;
	
	//==========================================================
	// Menu Cadastros
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']")
	public static WebElement menuCadastros;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Categorias']")
	public static WebElement cadastroCategorias;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Cores']")
	public static WebElement cadastroCores;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Clientes']")
	public static WebElement cadastroClientes;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Métodos de Envio']")
	public static WebElement cadastroMetodosDeEnvio;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Fornecedores']")
	public static WebElement cadastroFornecedores;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Funcionários']")
	public static WebElement cadastroFuncionarios;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Marcas']")
	public static WebElement cadastroMarcas;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Grupos Fiscais']")
	public static WebElement cadastroGruposFiscais;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Op. Cartão Crédito']")
	public static WebElement cadastroOpCartaoCredito;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Produtos']")
	public static WebElement cadastroProdutos;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Tipos de Pagamento']")
	public static WebElement cadastroTiposPagamento;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Tamanhos']")
	public static WebElement cadastroTamanhos;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Tipos de Contas']")
	public static WebElement cadastroTiposDeContas;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Tipos de Entrada']")
	public static WebElement cadastroTiposDeEntrada;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Tipos de Saídas']")
	public static WebElement cadastroTiposDeSaidas;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Slides']")
	public static WebElement cadastroSlides;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Cadastros']//..//..//ul/li/a[text()='Unidades']")
	public static WebElement cadastroUnidades;
	
	
	//==========================================================
	// Menu Estoque 
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']")
	public static WebElement menuEstoque;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']//..//..//ul/li/a[text()='Entrada de Produtos']")
	public static WebElement entradaDeProdutos;

	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']//..//..//ul/li/a[text()='Saída de Produtos']")
	public static WebElement saidaDeProdutos;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']//..//..//ul/li/a[text()='Compra de Produtos']")
	public static WebElement compraDeProdutos;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Estoque']//..//..//ul/li/a[text()='Balanço']")
	public static WebElement balanco;
	
	
	//==========================================================
	// Menu Contas
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Contas']")
	public static WebElement menuContas;
		
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Contas']//..//..//ul/li/a[text()='À Pagar']")
	public static WebElement contasPagar;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Contas']//..//..//ul/li/a[text()='À Receber']")
	public static WebElement contasReceber;
	
	
	//==========================================================
	// Menu Relatórios
	//==========================================================
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']")
	public static WebElement menuRelatorios;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']//..//..//ul/li/a[text()='Estoque de Produto']")
	public static WebElement relatorioEstqoueDeProduto;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']//..//..//ul/li/a[text()='Vendas']")
	public static WebElement relatorioVendas;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']//..//..//ul/li/a[text()='Produtos']")
	public static WebElement relatorioProduto;
	
	@FindBy(xpath = "//*[@id='sidebar']/ul/li/a/span[text()='Relatórios']//..//..//ul/li/a[text()='Contas a pagar']")
	public static WebElement relatorioContasPagar;
	
	//==========================================================
	// ******************* FIM MAPEAMENTO *********************
    //==========================================================
	
	
	//==========================================================
	// ******************* ACESSO AOS MENUS ********************
	//==========================================================
	
	public void acessarMenu(WebElement... elements){
		try {
			waitAndClick(btDashboard);
		} catch (Exception e) {
			Utils.takeScreenshot(elements.clone().toString());
			Utils.assertFail("Erro ao acessar menu. Favor verificar em: ["+Property.EVIDENCIAS_TESTE_PATH+"]");
		}
		for(WebElement element: elements){
			aguardarElementoVisivel(element);
			waitAndClick(element);
			Log.info("Acessando menu : " +element.getText());
		}
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU DADOS GERAIS
	 * -------------------------------------------------
	 */
	public void acessarMenuDadosGeraisConfiguracoes(){
		acessarMenu(menuDadosGerais,configuracoes);
	}
	
	
	/* -------------------------------------------------
	 *  ACESSO AO MENU CADASTROS
	 * -------------------------------------------------
	 */
	public void acessarMenuCadastrosCategorias(){
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
	public void acessarMenuContas_A_Pagar(){
		acessarMenu(menuContas, contasPagar);
	}
	
	public void acessarMenuContas_A_Receber(){
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


	/*public void acessarMenu(WebElement element){
		waitAndClick(btDashboard);//incluido para sempre voltar aos ids default
		waitAndClick(element);
		Log.info("Acessando menu : " +element.getText());
	}
	public void acessarSubMenu(WebElement element){
		aguardarElementoVisivel(element);
		waitAndClick(element);
		Log.info("Acessando menu : " +element.getText());
	}
	public void acessarSubItemMenu(WebElement element){
		aguardarElementoVisivel(element);
		waitAndClick(element);
		Log.info("Acessando menu : " +element.getText());
	}*/
