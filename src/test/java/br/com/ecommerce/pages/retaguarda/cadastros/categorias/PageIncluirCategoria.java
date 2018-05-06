package br.com.ecommerce.pages.retaguarda.cadastros.categorias;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecommerce.config.basepage.BasePage;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;

public class PageIncluirCategoria extends BasePage<PageIncluirCategoria> {

	public PageIncluirCategoria() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
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
		click(btSalvar);
		Log.info("Salvando categoria: ["+categoria+"]");
	}
	
	public void incluirSubCategoria(String categoria, String subCategoria) {
		Log.info("Incluindo categoria subcategoria ["+subCategoria+"] com categoria pai ["+categoria+"]");
		preencherCampo(fieldNomeCategoria, subCategoria);
		selecionarValorComboValue(comboAtiva              , "true");
		selecionarValorComboValue(comboMenuPrincipal      , "false");
		selecionarValorComboTexto(comboCategoriaAgrupadora, categoria);
		click(btSalvar);
		Log.info("Salvando subcategoria: ["+subCategoria+"]");
	}
	
	public void validarOrtografiaDeCamposTelaIncluirCategoria() {
		
		Log.info("Conferindo ortografia na tela de inclusão de categoria...");
		Utils.assertEquals(getTextElement(titleNovaCategorias)    , "Novo(a) Categoria");
		Utils.assertEquals(getTextElement(nameNovaCategorias)     , "Nome da Categoria:");
		Utils.assertEquals(getTextElement(nameAtiva)              , "Ativa");
		Utils.assertEquals(getTextElement(nameMenuPrincipal)      , "Menu Principal");
		Utils.assertEquals(getTextElement(nameCategoriaAgrupadora), "Categoria agrupadora");
		Utils.assertEquals(getTextElement(btCancelar)             , "Cancelar");
		Utils.assertEquals(getTextValueAtributo(btSalvar)         , "Salvar");
		Log.info("Ortografia validada com sucesso.");
	}

}
