package br.com.ecommerce.pages.retaguarda.cadastros.tiposconta;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirTipoConta extends BasePage {

	public PageIncluirTipoConta() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleAdicionarConta;
	
	@FindBy(xpath = "//label[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//label[text()='Despesa?']")
	private WebElement labelDespesa;
	
	@FindBy(id = "bill_type_description")
	private WebElement inputDescricao;
	
	@FindBy(id = "bill_type_is_expense")
	private WebElement checkDespesa;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	public void incluirTipoConta(String tipoConta) {
		Log.info("Inserindo dados da ["+tipoConta+"]...");
		aguardarElementoVisivel(btSalvar);
		preencherCampo(inputDescricao, tipoConta);
		if (isContaDespesa(tipoConta)) {
			marcarCheckbox(checkDespesa);
		}else
			desmarcarCheckbox(checkDespesa);
		click(btSalvar);
		Log.info("["+tipoConta+"] inserida");
	}
	
	public boolean isContaDespesa(String tipoConta){
		if (tipoConta.contains("à pagar")) {
			return true;
		}else
			return false;
	}
	
	public void verificarOrtografiaPageIncluirTipoConta(){
		Log.info("Verificando ortografia da página de cadastro de tipo de contas...");
		Utils.assertEquals(getTextElement(titleAdicionarConta), "Adicionar Tipo de Conta");
		Utils.assertEquals(getTextElement(labelDescricao)     , "Descrição");
		Utils.assertEquals(getTextElement(labelDespesa)       , "Despesa?");
		Utils.assertEquals(getTextElement(btCancelar)         , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)     , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
