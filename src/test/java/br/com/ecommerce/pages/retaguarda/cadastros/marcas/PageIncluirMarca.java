package br.com.ecommerce.pages.retaguarda.cadastros.marcas;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageIncluirMarca extends BasePage {

	public PageIncluirMarca() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleNovaMarca;
	
	@FindBy(xpath = "//label[text()='Descrição']")
	private WebElement labelDescricao;
	
	@FindBy(id = "brand_name")
	private WebElement inputMarca;
	
	@FindBy(xpath = "//a[text()='Cancelar']")
	private WebElement btCancelar;
	
	@FindBy(name = "commit")
	private WebElement btSalvar;
	
	@FindBy(xpath = "//*[@id='main-content']/section/div[2]['×']")
	private WebElement msgSucesso;


	public void incluirMarca(String marca) {
		Log.info("Inserindo dados do funcionário ["+marca+"]...");
		aguardarElementoVisivel(inputMarca);
		preencherCampo(inputMarca, marca);
		click(btSalvar);
		Log.info("Dados do funcionário ["+marca+"] inseridos");
	}
	
	public void validaMsgInclusaoSemSucesso() {
		Log.info("Validando se funcionário foi incluído com dados inválidos..");
		Utils.assertFalse("Sistema permitiu criação de funcionário com dados inválidos", isMsgSucessoInclusao());
		Log.info("Sistema não permitiu criação de funcionário com dados inválidos \\o/");
	}
	
	public boolean isMsgSucessoInclusao(){
		return getTextElement(msgSucesso).replace("×", "").trim().equals("Funcionário criado com sucesso.");
	}
	
	public void verificarOrtografiaPageIncluirMarcas(){
		Log.info("Verificando ortografia da página de cadastro de marcas...");
		Utils.assertEquals(getTextElement(titleNovaMarca), "Novo(a) Marca");
		Utils.assertEquals(getTextElement(labelDescricao), "Descrição");
		Utils.assertEquals(getTextElement(btCancelar)    , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar), "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}
}
