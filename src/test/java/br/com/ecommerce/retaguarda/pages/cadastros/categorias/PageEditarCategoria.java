package br.com.ecommerce.retaguarda.pages.cadastros.categorias;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageEditarCategoria extends PageObjectGeneric<PageEditarCategoria> {

	public PageEditarCategoria() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@for='category_name']")
	WebElement nameCategorias;
	
	@FindBy(xpath = "//*[@for='category_is_active']")
	WebElement nameAtiva;
	
	@FindBy(xpath = "//*[@for='category_main_menu']")
	WebElement nameMenuPrincipal;
	
	@FindBy(xpath = "//*[@for='category_category_id']")
	WebElement nameCategoriaAgrupadora;
	
	@FindBy(xpath = "//*[@href='/admin/categories' and @class='btn btn-default']")
	WebElement btCancelar;
	
	@FindBy(id = "save_button")
	WebElement btSalvar;
	
	@FindBy(id = "category_name")
	WebElement fieldNomeCategoria;
	
	@FindBy(id = "category_is_active")
	WebElement comboAtiva;
	
	@FindBy(id = "category_main_menu")
	WebElement comboMenuPrincipal;
	
	@FindBy(id = "category_category_id")
	WebElement comboCategoriaAgrupadora;
	
	
	public void editarNomeCategoria(String categoriaAnterior, String categoriaAtual) {
		Log.info("Alterando nome da categoria ["+categoriaAnterior+"] para ["+categoriaAtual+"]...");
		aguardarElementoVisivel(btSalvar);
		preencherCampo(fieldNomeCategoria, categoriaAtual);
		waitAndClick(btSalvar);
		Log.info("Salvando alteração da categoria ["+categoriaAnterior+"] para ["+categoriaAtual+"]...");
	}
	
	public void validarOrtografiaDeCamposTelaEditarCategoria(String categoria) {
		
		WebElement titleEditarCategorias = Selenium.getDriver().findElement(By.xpath("//*[@id='main-content']//span[text()='Editar "+categoria+"']"));
		Log.info("Conferindo ortografia na tela de inclusão de categoria...");
		Utils.assertEquals(titleEditarCategorias.getText()  , "Editar "+categoria);
		Utils.assertEquals(nameCategorias.getText()         , "Nome da Categoria:");
		Utils.assertEquals(nameAtiva.getText()              , "Ativa");
		Utils.assertEquals(nameMenuPrincipal.getText()      , "Menu Principal");
		Utils.assertEquals(nameCategoriaAgrupadora.getText(), "Categoria agrupadora");
		Utils.assertEquals(btCancelar.getText()             , "Cancelar");
		Utils.assertEquals(btSalvar.getAttribute("value")   , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}

}
