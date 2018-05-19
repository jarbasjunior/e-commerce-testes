package br.com.ecommerce.pages.retaguarda.cadastros.marcas;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.BasePage;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

public class PageEditarMarca extends BasePage {

	public PageEditarMarca() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//h1")
	private WebElement titleEditarMarca;
	
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

	public void alterarMarca(String marca) {
		Log.info("Alterando dados da marca para ["+marca+"]...");
		aguardarElementoVisivel(inputMarca);
		preencherCampo(inputMarca, marca);
		click(btSalvar);
		Log.info("Dados da marca ["+marca+"] alterada");
	}
	
	public void verificarOrtografiaPageEditarMarcas(){
		Log.info("Verificando ortografia da página de cadastro de funcionários...");
		Utils.assertEquals(getTextElement(titleEditarMarca), "Editar Marca");
		Utils.assertEquals(getTextElement(labelDescricao)  , "Descrição");
		Utils.assertEquals(getTextValueAtributo(btSalvar)  , "Salvar");
		Utils.assertEquals(getTextElement(btCancelar)      , "Cancelar");
		Log.info("Ortografia validada com sucesso.");
	}
}
