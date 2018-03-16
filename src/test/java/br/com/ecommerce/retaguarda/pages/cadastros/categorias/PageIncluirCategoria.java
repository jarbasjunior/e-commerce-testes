package br.com.ecommerce.retaguarda.pages.cadastros.categorias;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.pagebase.PageObjectGeneric;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageIncluirCategoria extends PageObjectGeneric<PageIncluirCategoria> {

	public PageIncluirCategoria() {
		PageFactory.initElements(Selenium.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id='main-content']//span[text()='Novo(a) Categoria']")
	WebElement titleNovaCategorias;
	
	@FindBy(xpath = "//*[@id='new_category']//label[@for='category_name']")
	WebElement nameNovaCategorias;
	
	@FindBy(xpath = "//*[@id='new_category']//label[@for='category_is_active']")
	WebElement nameAtiva;
	
	@FindBy(xpath = "//*[@id='new_category']//label[@for='category_main_menu']")
	WebElement nameMenuPrincipal;
	
	@FindBy(xpath = "//*[@id='new_category']//label[@for='category_category_id']")
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
	
	
	public void incluirCategoriaPrincipal(String categoria) {
		Log.info("Incluindo categoria ["+categoria+"]...");
		preencherCampo(fieldNomeCategoria, categoria);
		selecionarValorComboValue(comboAtiva              , "true");
		selecionarValorComboValue(comboMenuPrincipal      , "true");
		selecionarValorComboTexto(comboCategoriaAgrupadora, "Nenhuma");
		waitAndClick(btSalvar);
		Log.info("Salvando categoria: ["+categoria+"]");
	}
	
	public void incluirSubCategoria(String categoria, String subCategoria) {
		Log.info("Incluindo categoria subcategoria ["+subCategoria+"] com categoria pai ["+categoria+"]");
		preencherCampo(fieldNomeCategoria, subCategoria);
		selecionarValorComboValue(comboAtiva              , "true");
		selecionarValorComboValue(comboMenuPrincipal      , "false");
		selecionarValorComboTexto(comboCategoriaAgrupadora, categoria);
		waitAndClick(btSalvar);
		Log.info("Salvando subcategoria: ["+subCategoria+"]");
	}
	
	public void validarOrtografiaDeCamposTelaIncluirCategoria() {
		
		Log.info("Conferindo ortografia na tela de inclusão de categoria...");
		Utils.assertEquals(titleNovaCategorias.getText()    , "Novo(a) Categoria");
		Utils.assertEquals(nameNovaCategorias.getText()     , "Nome da Categoria:");
		Utils.assertEquals(nameAtiva.getText()              , "Ativa");
		Utils.assertEquals(nameMenuPrincipal.getText()      , "Menu Principal");
		Utils.assertEquals(nameCategoriaAgrupadora.getText(), "Categoria agrupadora");
		Utils.assertEquals(btCancelar.getText()             , "Cancelar");
		Utils.assertEquals(btSalvar.getAttribute("value")   , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}

}
