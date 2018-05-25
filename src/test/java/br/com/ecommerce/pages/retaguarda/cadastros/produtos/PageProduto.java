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
	
	@FindBy(xpath = "//*[@class='btn btn-default'][contains(.,'Novo')]")
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
	
	public void navegarParaPaginaEdicaoProduto(String produto) {
		aguardarElementoVisivel(btEditar);
		pageDown(btNovo);
		By b = By.xpath("//tbody//../tr/td[text()='"+produto+"']//..//td[contains(.,'Editar')]/a[1]");
		click(getDriver().findElement(b));
		Log.info("Redirecionando para página de edição do produto ["+produto+"]");
	}
	
	public void validarFuncionarioInserido(String produto) {
		Utils.assertTrue("Funcionario ["+produto+"] não está sendo exibido na listagem de produtos", existsProdutos(produto));
		Log.info("Funcionario ["+produto+"] inserido com sucesso");
	}
	
	public void validaMsgSucessoInclusao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto criado com sucesso.");
		Log.info("Mensagem de feedback validada.");
	}
	
	public void validarProdutoNaListagem(String nome, String descricao) {
		
		Log.info("Conferindo dados do produto ["+nome+"] na tela...");
		pageDown(btNovo);

		WebElement fillNome      = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[1]"));
		WebElement fillDescricao = getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'"+nome+"')]//../td[2]"));

		Utils.assertEquals(getTextElement(fillNome)     , nome);
		Utils.assertEquals(getTextElement(fillDescricao), descricao);

		Log.info("Dados do produto ["+nome+"] conferidos com sucesso.");
	}
	
	public boolean isProdutoTeste(){
		return isVisibility(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td[2]"));
	}
	
	public String getProdutoTeste(){
		return getTextElement(getDriver().findElement(By.xpath("//*[@id='main-content']//tr/td[contains(.,'Teste Produto')]//../td[1]"))).trim();
	}
	
	public void validaMsgSucessoAlteracao(){
		Log.info("Validando mensagem de feedback de sucesso...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Produto atualizado com sucesso");
		Log.info("Mensagem de feedback validada.");
	}
	
	public boolean isMensagemSucessoAlteracao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Produto atualizado com sucesso.");
	}
	
	public void validarFuncionarioRemovido(String produto) {
		Utils.assertFalse("Produto ["+produto+"] ainda está sendo exibida na listagem de produtos", existsProdutos(produto));
		Log.info("Produto ["+produto+"] removido com sucesso");
	}
	
	public void removerProduto(String produto) {
		Log.info("Removendo produto ["+produto+"]...");
		pageDown(btNovo);
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]//../td[@class='last']/a[6]");
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
		pageDown(btNovo);
		Log.info("Verificando se o produto ["+produto+"] está cadastrado...");
		By by = By.xpath("//tbody//../tr/td[contains(.,'"+produto+"')]");
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
