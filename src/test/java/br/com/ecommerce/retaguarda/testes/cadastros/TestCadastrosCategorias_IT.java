package br.com.ecommerce.retaguarda.testes.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.testbase.BaseTestCase;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;
import br.com.ecommerce.lojavirtual.pages.PageHomeLojaVirtual;
import br.com.ecommerce.retaguarda.pages.cadastros.categorias.PageCategorias;
import br.com.ecommerce.retaguarda.pages.cadastros.categorias.PageEditarCategoria;
import br.com.ecommerce.retaguarda.pages.cadastros.categorias.PageIncluirCategoria;
import br.com.ecommerce.retaguarda.pages.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.retaguarda.pages.dashboard.PageMenu;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosCategorias_IT extends BaseTestCase {

	PageMenu             pageMenu             = new PageMenu();
	PageCategorias       pageCategorias       = new PageCategorias();
	PageHomeRetaguarda   pageHomeRetaguarda   = new PageHomeRetaguarda();
	PageHomeLojaVirtual  pageHomeLojaVirtual  = new PageHomeLojaVirtual();
	PageEditarCategoria  pageEditarCategoria  = new PageEditarCategoria();
	PageIncluirCategoria pageIncluirCategoria = new PageIncluirCategoria();
	
	@Test
	public void cadastrarCategoriaComSucesso(){
		String categoria = Utils.geraCategoria();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirCategoriaPrincipal(categoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(categoria);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		pageHomeRetaguarda.sairDoRetaguarda();
	}

	@Test
	public void cadastrarSubCategoriaComSucesso(){
		String categoria    = Utils.geraCategoria();
		String subcategoria = Utils.geraCategoria();
		/*
		 * Incluir categoria
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirCategoriaPrincipal(categoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(categoria);
		Log.info("Navegando para home page da loja virtual...");
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Incluir subcategoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoria);
		pageHomeRetaguarda.sairDoRetaguarda();
	}
	
	@Test
	public void alterarNomeCategoriaComSucesso(){
		String categoria     = Utils.geraCategoria();
		String novaCategoria = Utils.geraCategoria();
		/*
		 * Incluir categoria
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirCategoriaPrincipal(categoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(categoria);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Alterar categoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaEdicaoDeCategoria(categoria);
		pageEditarCategoria.validarOrtografiaDeCamposTelaEditarCategoria(categoria);
		pageEditarCategoria.editarNomeCategoria(categoria, novaCategoria);
		pageCategorias.validarMsgFeedbackEdicaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(novaCategoria);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(novaCategoria);
		pageHomeRetaguarda.sairDoRetaguarda();
		
	}
	
	@Test
	public void alterarNomeSubCategoriaComSucesso(){
		
		String categoria            = Utils.geraCategoria();
		String subcategoriaAtual    = Utils.geraCategoria();
		String subcategoriaAnterior = Utils.geraCategoria();
		/*
		 * Incluir categoria
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirCategoriaPrincipal(categoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(categoria);
		Log.info("Navegando para home page da loja virtual...");
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Incluir subcategoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoriaAnterior);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAnterior);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoriaAnterior);
		/*
		 * Alterar subcategoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaEdicaoDeCategoria(subcategoriaAnterior);
		pageEditarCategoria.validarOrtografiaDeCamposTelaEditarCategoria(subcategoriaAnterior);
		pageEditarCategoria.editarNomeCategoria(subcategoriaAnterior, subcategoriaAtual);
		pageCategorias.validarMsgFeedbackEdicaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAtual);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoriaAtual);
		pageHomeRetaguarda.sairDoRetaguarda();
	}
	
	@Test
	public void excluirCategoriaComSucesso(){
		String categoria = Utils.geraCategoria();
		/*
		 * Incluir categoria
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirCategoriaPrincipal(categoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(categoria);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Excluir categoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.excluirCategoria(categoria);
		pageCategorias.validarMsgFeedbackExclusaoSucesso();
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirExclusaoCategoriaNaLojaVirtual(categoria);
		pageHomeRetaguarda.sairDoRetaguarda();
	}
	
	@Test
	public void excluirSubCategoriaComSucesso(){
		String categoria    = Utils.geraCategoria();
		String subcategoria = Utils.geraCategoria();
		/*
		 * Incluir categoria
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirCategoriaPrincipal(categoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(categoria);
		Log.info("Navegando para home page da loja virtual...");
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Incluir subcategoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoria);
		/*
		 * Excluir subcategoria
		 */
		Selenium.getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.excluirCategoria(subcategoria);
		pageCategorias.validarMsgFeedbackExclusaoSucesso();
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirExclusaoSubategoriaNaLojaVirtual(categoria, subcategoria);
		pageHomeRetaguarda.sairDoRetaguarda();
	}
}