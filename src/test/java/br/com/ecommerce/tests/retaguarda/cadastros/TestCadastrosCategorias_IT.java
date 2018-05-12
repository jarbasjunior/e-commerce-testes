package br.com.ecommerce.tests.retaguarda.cadastros;

import static br.com.ecommerce.config.setup.DriverFactory.getDriver;

import org.junit.Test;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.testbase.BaseTest;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.categorias.PageCategorias;
import br.com.ecommerce.pages.retaguarda.cadastros.categorias.PageEditarCategoria;
import br.com.ecommerce.pages.retaguarda.cadastros.categorias.PageIncluirCategoria;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosCategorias_IT extends BaseTest{

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
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
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
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Incluir subcategoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
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
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Alterar categoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaEdicaoDeCategoria(categoria);
		pageEditarCategoria.validarOrtografiaDeCamposTelaEditarCategoria(categoria);
		pageEditarCategoria.editarNomeCategoria(categoria, novaCategoria);
		pageCategorias.validarMsgFeedbackEdicaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(novaCategoria);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
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
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Incluir subcategoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoriaAnterior);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAnterior);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoriaAnterior);
		/*
		 * Alterar subcategoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaEdicaoDeCategoria(subcategoriaAnterior);
		pageEditarCategoria.validarOrtografiaDeCamposTelaEditarCategoria(subcategoriaAnterior);
		pageEditarCategoria.editarNomeCategoria(subcategoriaAnterior, subcategoriaAtual);
		pageCategorias.validarMsgFeedbackEdicaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAtual);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
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
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Excluir categoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.excluirCategoria(categoria);
		pageCategorias.validarMsgFeedbackExclusaoSucesso();
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
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
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(categoria);
		/*
		 * Incluir subcategoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoria);
		/*
		 * Excluir subcategoria
		 */
		getDriver().navigate().to(Property.URL_RETAGUARDA);
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.excluirCategoria(subcategoria);
		pageCategorias.validarMsgFeedbackExclusaoSucesso();
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirExclusaoSubategoriaNaLojaVirtual(categoria, subcategoria);
		pageHomeRetaguarda.sairDoRetaguarda();
	}
}