package br.com.ecommerce.pages.retaguarda.cadastros.gruposfiscais;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirGruposFiscais extends BasePage {

	public PageIncluirGruposFiscais() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovoGrupoFiscal;
	
	@FindBy(xpath = "//label[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(xpath = "//label[text()='CST']")
	private WebElement labelCst;
	
	@FindBy(xpath = "//label[text()='CFOP']")
	private WebElement labelCfop;
	
	@FindBy(xpath = "//label[text()='Alíquota']")
	private WebElement labelAliquota;
	
	@FindBy(xpath = "//label[text()='Ativo?']")
	private WebElement labelAtivo;
	
	@FindBy(id = "tax_group_description")
	private WebElement inputDescricao;
	
	@FindBy(id = "tax_group_is_active")
	private WebElement checkAtivo;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;

	public void incluirGrupoFiscalAtivo(String grupoFiscal) {
		Log.info("Inserindo dados do grupo fiscal ["+grupoFiscal+"]...");
		aguardarElementoVisivel(inputDescricao);
		preencherCampo(inputDescricao, grupoFiscal);
		marcarCheckbox(checkAtivo);
		click(btSalvar);
		Utils.wait(1000);
		Log.info("Dados do grupo fiscal ["+grupoFiscal+"] inseridos");
	}
	
	public void incluirGrupoFiscalInativo(String grupoFiscal) {
		Log.info("Inserindo dados do grupo fiscal ["+grupoFiscal+"]...");
		aguardarElementoVisivel(inputDescricao);
		preencherCampo(inputDescricao, grupoFiscal);
		desmarcarCheckbox(checkAtivo);
		click(btSalvar);
		Log.info("Dados do grupo fiscal ["+grupoFiscal+"] inseridos");
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se funcionário foi incluído com dados inválidos..");
		Utils.assertFalse("Sistema permitiu criação de funcionário com dados inválidos", isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de funcionário com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Funcionário criado com sucesso.");
	}
	
	public void verificarOrtografiaPageIncluirGrupoFiscal(){
		Log.info("Verificando ortografia da página de cadastro de grupo fiscal...");
		Utils.assertEquals(getTextElement(titleNovoGrupoFiscal)    , "Novo(a) Grupo Fiscal");
		Utils.assertEquals(getTextElement(labelDescricao)          , "Descrição");
		Utils.assertEquals(getTextElement(labelCst)	               , "CST");
		Utils.assertEquals(getTextElement(labelCfop)               , "CFOP");
		Utils.assertEquals(getTextElement(labelAliquota)           , "Alíquota");
		Utils.assertEquals(getTextElement(labelAtivo)              , "Ativo?");
		Utils.assertEquals(getTextElement(btCancelar)     	   	   , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)          , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
