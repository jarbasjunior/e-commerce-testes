package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageProduto extends BasePage {

	public PageProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleProdutos;
	
	@FindBy(xpath = "//*[@href='/admin/products/new']")
	private WebElement btNovo;
	
	@FindBy(xpath = "//th[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//th[text()='Nome']")
	private WebElement labelNome;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Imagens')]")
	private WebElement btImagens;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Categorias')]")
	private WebElement btCategorias;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Unidades')]")
	private WebElement btUnidades;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Remove')]")
	private WebElement btRemove;
	
	@FindBy(xpath = "//tbody//../a[contains(.,'Editar')]")
	private WebElement btEditar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void navegarParaPageInclusaoProdutos() {
		aguardarElementoVisivel(btNovo);
		click(btNovo);
		Log.info("Redirecionando para página de inclusão de produto");
	}
	
	public void navegarParaPageCategoriaProduto(String produto) {
		aguardarElementoVisivel(btNovo);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Categorias')]");
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de categorias de produto");
	}
	
	public void navegarParaPageUnidadeProduto(String produto) {
		aguardarElementoVisivel(btNovo);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Unidades')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de unidade de produto");
	}
	
	public void navegarParaPaginaEdicaoProduto(String produto) {
		aguardarElementoVisivel(btEditar);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Editar')]");
		exibeRegistroVisivel(by, btNovo);
		click(getDriver().findElement(by));
		Log.info("Redirecionando para página de edição do produto ["+produto+"]");
	}
	
	public void ativarProduto(String produto) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Ativar')]");
		exibeRegistroVisivel(by, btNovo);
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Ativando produto..");
		click(fillBtnInactive);
		validaMsgAtivacao();
	}
	
	public void desativarProduto(String produto) {
		WebElement fillBtnInactive = null;
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Desativar')]");
		exibeRegistroVisivel(by, btNovo);
		fillBtnInactive = getDriver().findElement(by);
		Log.info("Desativando produto..");
		click(fillBtnInactive);
		validaMsgInativacao();
	}
	
	public void irParaPageImagensProduto(String produto) {
		WebElement btImagem = null;
		By by = By.xpath("//tbody//../td[contains(.,'"+produto+"')]//../td/a[contains(.,'Imagens')]");
		btImagem = getDriver().findElement(by);
		click(btImagem);
		Log.info("Redirecionando para página de edição de imagens do produto produto..");
	}
	
	public void validaMsgAtivacao(){
		Log.info("Validando mensagem de feedback de ativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto ativado com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validaMsgInativacao(){
		Log.info("Validando mensagem de feedback de inativação...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto desativado com sucesso!");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isAtivo(String produto){
		return isVisibility(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Desativar')]"));
	}
	
	public boolean isInativo(String produto){
		return isVisibility(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+produto+"')]//../td/a[contains(.,'Ativar')]"));
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarProdutoNaListagem(String nome, String descricao) {
		
		Log.info("Conferindo dados do produto ["+nome+"] na tela...");
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]");
		exibeRegistroVisivel(by, btNovo);
		WebElement fillNome      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[1]"));
		WebElement fillDescricao = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[2]"));

		Utils.assertEquals(getTextElement(fillNome)     , nome);
		Utils.assertEquals(getTextElement(fillDescricao), descricao);

		Log.info("Dados do produto ["+nome+"] conferidos com sucesso.");
	}
	
	public boolean isProdutoTesteAtivo(){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td/a[contains(.,'Desativar')]");
		if (isProdutoTeste()) {
			if (isAtivo("Teste Produto")) {
				if (!getDriver().findElement(by).isDisplayed()) {
					pageDown(btNovo);
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean isProdutoTesteInativo(){
		By by = By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td/a[contains(.,'Ativar')]");
		if (isProdutoTeste()) {
			if (isInativo("Teste Produto")) {
				if (!getDriver().findElement(by).isDisplayed()) {
					pageDown(btNovo);
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean isProdutoTeste(){
		return isVisibility(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td[2]"));
	}
	
	public String getProdutoTesteAtivo(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td[1]//../td/a[contains(.,'Desativar')]//../../td[1]"))).trim();
	}
	
	public String getProdutoTesteDesativado(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td[1]//../td/a[contains(.,'Ativar')]//../../td[1]"))).trim();
	}
	
	public String getProdutoTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td[1]"))).trim();
	}
	
	public String getDescricaoProdutoTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+getProdutoTeste()+"')]//../td[2]"))).trim();
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto atualizado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarProdutoRemovido(String produto) {
		Utils.assertFalse("Produto ["+produto+"] ainda está sendo exibida na listagem de produtos", existsProdutos(produto));
		Log.info("Produto ["+produto+"] removido com sucesso");
	}
	
	public void validarProdutoDesativado(String produto) {
		Utils.assertFalse("Produto ["+produto+"] não foi desativado", isAtivo(produto));
		Log.info("Produto ["+produto+"] desativado com sucesso");
	}
	
	public void validarProdutoAtivado(String produto) {
		Utils.assertTrue("Produto ["+produto+"] não foi ativado", isAtivo(produto));
		Log.info("Produto ["+produto+"] ativado com sucesso");
	}
	
	public void removerProduto(String produto) {
		Log.info("Removendo produto ["+produto+"]...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]//../td[@class='last']/a[6]");
		exibeRegistroVisivel(by, btNovo);
		WebElement removerFuncionario = getDriver().findElement(by);
		click(removerFuncionario);
		confirmarAlerta();
		validarMsgSucessoExclusao();
		Log.info("Funcionario ["+produto+"] removido...");
	}
	
	public void validarMsgSucessoExclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto removido com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean existsProdutos(String produto){
		Log.info("Verificando se o produto ["+produto+"] está cadastrado...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]");
		exibeRegistroVisivel(by, btNovo);
		return isVisibility(by);
	}
	
	public void verificarOrtografiaPageProdutos(){
		Log.info("Verificando ortografia da página produtos...");
		Utils.assertEquals(getTextElement(titleProdutos) , "Produtos");
		Utils.assertEquals(getTextElement(labelNome)     , "Nome");
		Utils.assertEquals(getTextElement(labelDescricao), "Descrição");
		Utils.assertEquals(getTextElement(btNovo)        , "Novo(a)");
		Utils.assertEquals(getTextElement(btImagens)     , "Imagens");
		Utils.assertEquals(getTextElement(btCategorias)  , "Categorias");
		Utils.assertEquals(getTextElement(btUnidades)    , "Unidades");
		Utils.assertEquals(getTextElement(btEditar)      , "Editar");
		Utils.assertEquals(getTextElement(btRemove)      , "Remove");
		Log.info("Ortografia validada com sucesso.");
	}
}
