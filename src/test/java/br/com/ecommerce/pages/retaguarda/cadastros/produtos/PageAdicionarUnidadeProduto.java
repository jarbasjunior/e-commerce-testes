package br.com.ecommerce.pages.retaguarda.cadastros.produtos;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageAdicionarUnidadeProduto extends BasePage {

	public PageAdicionarUnidadeProduto() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleAdicionarUnidade;
	
	@FindBy(xpath = "//label[text()='Código:']")
	private WebElement labelCodigo;
	
	@FindBy(xpath = "//label[text()='Cor:']")
	private WebElement labelCor;
	
	@FindBy(xpath = "//label[text()='Tamanho:']")
	private WebElement labelTamanho;
	
	@FindBy(id = "product_unit_color_id")
	private WebElement comboCor;
	
	@FindBy(id = "product_unit_size_id")
	private WebElement comboTamanho;

	@FindBy(id = "product_unit_code")
	private WebElement inputCodigo;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;

	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;
	
	public void adicionarUnidadeAoProduto(String codigo, String cor, String tamanho){
		aguardarElementoVisivel(btSalvar);
		Log.info("Adicionando unidade ao produto...");
		preencherCampo(inputCodigo, codigo);
		selecionarValorComboTexto(comboCor, cor);
		selecionarValorComboTexto(comboTamanho, tamanho);
		click(btSalvar);
		validaMsgInclusao();
	}
	
	public String getCorDisponivel(){
		List<WebElement> listaDeCores = getAllElementosCombo(comboCor);
		String indiceCor = Utils.geraNumeroEntre(1, listaDeCores.size());
		return getTextElement(getDriver().findElement(By.xpath(" //*[@id='product_unit_color_id']/option["+indiceCor+"]")));
	}
	
	public String getTamanhoDisponivel(){
		List<WebElement> listaDeTamanhos = getAllElementosCombo(comboTamanho);
		String indiceTamanho = Utils.geraNumeroEntre(1, listaDeTamanhos.size());
		return getTextElement(getDriver().findElement(By.xpath(" //*[@id='product_unit_size_id']/option["+indiceTamanho+"]")));
	}
	
	public void validaMsgInclusao(){
		Log.info("Validando mensagem de feedback de inclusão...");
		aguardarElementoVisivel(msgSucesso);
		Utils.assertEquals(getTextElement(msgSucesso).replace("×", "").trim(), "Registro criado com Sucesso!");
		Log.info("Mensagem de feedback validada.");
	}

	public void verificarOrtografiaPageAdicionarUnidadeProduto(){
		Log.info("Verificando ortografia da página de unidade de produtos...");
		Utils.assertEquals(getTextElement(titleAdicionarUnidade), "Adicionar Unidade de Produto");
		Utils.assertEquals(getTextElement(labelCodigo)          , "Código:");
		Utils.assertEquals(getTextElement(labelCor)             , "Cor:");
		Utils.assertEquals(getTextElement(labelTamanho)         , "Tamanho:");
		Utils.assertEquals(getTextElement(btCancelar)           , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)       , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
