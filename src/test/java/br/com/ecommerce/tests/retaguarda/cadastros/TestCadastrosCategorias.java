package br.com.ecommerce.tests.retaguarda.cadastros;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import java.util.List;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.categorias.PageCategorias;
import br.com.ecommerce.pages.retaguarda.cadastros.categorias.PageEditarCategoria;
import br.com.ecommerce.pages.retaguarda.cadastros.categorias.PageIncluirCategoria;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Categorias
 * @author Jarbas
 * 
 * */
public class TestCadastrosCategorias extends BaseTest{

	PageMenu             pageMenu             = new PageMenu();
	PageCategorias       pageCategorias       = new PageCategorias();
	PageHomeLojaVirtual  pageHomeLojaVirtual  = new PageHomeLojaVirtual();
	PageEditarCategoria  pageEditarCategoria  = new PageEditarCategoria();
	PageIncluirCategoria pageIncluirCategoria = new PageIncluirCategoria();
	
	@Test
	public void cadastrarCategoriaComSucesso(){
		String categoria = "Teste " + Utils.geraCategoria();
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
	}

	@Test
	public void cadastrarSubCategoriaComSucesso(){
		String categoria    = "Teste " + Utils.geraCategoria();
		String subcategoria = "Teste " + Utils.geraCategoria();
		/*
		 * Incluir categoria teste caso não exista
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		if (!pageCategorias.existsCategoriaTeste()) {
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
			getDriver().navigate().to(Property.URL_RETAGUARDA);
			pageMenu.acessarMenuCadastrosCategorias();
			pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		}else{
			categoria = pageCategorias.getCategoriaTeste();
		}
		/*
		 * Incluir subcategoria
		 */
		pageCategorias.navegarParaPaginaInclusaoDeCategoria();
		pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
		pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
		pageCategorias.validarMsgFeedbackInclusaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoria);
	}
	
	@Test
	public void alterarNomeCategoriaComSucesso(){
		String categoria     = "Teste " + Utils.geraCategoria();
		String novaCategoria = "Teste " + Utils.geraCategoria();
		/*
		 * Incluir categoria teste caso não exista
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		if (!pageCategorias.existsCategoriaTeste()) {
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
			getDriver().navigate().to(Property.URL_RETAGUARDA);
			pageMenu.acessarMenuCadastrosCategorias();
			pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
			getDriver().navigate().to(Property.URL_RETAGUARDA);
			pageMenu.acessarMenuCadastrosCategorias();
			pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		}else{
			categoria = pageCategorias.getCategoriaTeste();
		}
		/*
		 * Alterar categoria
		 */
		pageCategorias.navegarParaPaginaEdicaoDeCategoria(categoria);
		pageEditarCategoria.validarOrtografiaDeCamposTelaEditarCategoria(categoria);
		pageEditarCategoria.editarNomeCategoria(categoria, novaCategoria);
		pageCategorias.validarMsgFeedbackEdicaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeCategoriaPrincipal(novaCategoria);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirCategoriaPrincipalNaLojaVirtual(novaCategoria);
	}
	
	@Test
	public void alterarNomeSubCategoriaComSucesso(){
		String categoria            = "Teste " + Utils.geraCategoria();
		String subcategoriaAtual    = "Teste " + Utils.geraCategoria();
		String subcategoriaAnterior = "Teste " + Utils.geraCategoria();
		/*
		 * Incluir categoria e subcategoria caso não existam
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		if (!pageCategorias.existsSubCategoriaTeste()) {
			if (!pageCategorias.existsCategoriaTeste()) {
				/*
				 * Incluir categoria
				 */
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
				getDriver().navigate().to(Property.URL_RETAGUARDA);
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
				/*
				 * Inclui subcategoria
				 */
				pageCategorias.navegarParaPaginaInclusaoDeCategoria();
				pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
				pageIncluirCategoria.incluirSubCategoria(categoria, subcategoriaAnterior);
				pageCategorias.validarMsgFeedbackInclusaoSucesso();
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
				pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAnterior);
				getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
				pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoriaAnterior);
				getDriver().navigate().to(Property.URL_RETAGUARDA);
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
			}else{
				/*
				 * Inclui subcategoria
				 */
				categoria = pageCategorias.getCategoriaTeste();
				pageCategorias.navegarParaPaginaInclusaoDeCategoria();
				pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
				pageIncluirCategoria.incluirSubCategoria(categoria, subcategoriaAnterior);
				pageCategorias.validarMsgFeedbackInclusaoSucesso();
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
				pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAnterior);
				getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
				pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoriaAnterior);
				getDriver().navigate().to(Property.URL_RETAGUARDA);
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
			}
		}else{
			categoria            = pageCategorias.getCategoriaTeste();
			subcategoriaAnterior = pageCategorias.getSubCategoriaTeste(categoria);
		}
		/*
		 * Alterar subcategoria
		 */
		pageCategorias.navegarParaPaginaEdicaoDeSubCategoria(subcategoriaAnterior);
		pageEditarCategoria.validarOrtografiaDeCamposTelaEditarCategoria(subcategoriaAnterior);
		pageEditarCategoria.editarNomeCategoria(subcategoriaAnterior, subcategoriaAtual);
		pageCategorias.validarMsgFeedbackEdicaoSucesso();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoriaAtual);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoriaAtual);
	}
	
	@Test
	public void excluirCategoriaComSucesso(){
		String categoria = "Teste " + Utils.geraCategoria();
		/*
		 * Incluir categoria teste caso não exista
		 */
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		List<String> categoriasSemSubcategorias = pageCategorias.categoriasTesteSemSubcategorias();
		/*
		 * Verifica se existe categorias sem subcategorias, caso exista, o teste cria uma
		 * nova para ser excluída posteriormente
		 */
		if (categoriasSemSubcategorias.size() == 0) {
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
			getDriver().navigate().to(Property.URL_RETAGUARDA);
			pageMenu.acessarMenuCadastrosCategorias();
			pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		}else{
			int indexCategoriaSemPai = Utils.geraNumeroEntreIntervalo(0, categoriasSemSubcategorias.size()-1);
			categoria = categoriasSemSubcategorias.get(indexCategoriaSemPai);
		}
		/*
		 * Excluir categoria
		 */
		pageCategorias.excluirCategoria(categoria);
		pageCategorias.validarMsgFeedbackExclusaoSucesso();
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirExclusaoCategoriaNaLojaVirtual(categoria);
	}
	
	@Test
	public void excluirSubCategoriaComSucesso(){
		String categoria    = "Teste " + Utils.geraCategoria();
		String subcategoria = "Teste " + Utils.geraCategoria();
		pageMenu.acessarMenuCadastrosCategorias();
		pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
		if (!pageCategorias.existsSubCategoriaTeste()) {
			if (!pageCategorias.existsCategoriaTeste()) {
				/*
				 * Incluir categoria
				 */
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
				getDriver().navigate().to(Property.URL_RETAGUARDA);
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
				/*
				 * Inclui subcategoria
				 */
				pageCategorias.navegarParaPaginaInclusaoDeCategoria();
				pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
				pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
				pageCategorias.validarMsgFeedbackInclusaoSucesso();
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
				pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
				getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
				pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoria);
				getDriver().navigate().to(Property.URL_RETAGUARDA);
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
			}else{
				/*
				 * Inclui subcategoria
				 */
				categoria = pageCategorias.getCategoriaTeste();
				pageCategorias.navegarParaPaginaInclusaoDeCategoria();
				pageIncluirCategoria.validarOrtografiaDeCamposTelaIncluirCategoria();
				pageIncluirCategoria.incluirSubCategoria(categoria, subcategoria);
				pageCategorias.validarMsgFeedbackInclusaoSucesso();
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
				pageCategorias.conferirInclusaoDeSubCategoria(categoria, subcategoria);
				getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
				pageHomeLojaVirtual.conferirSubcategoriaNaLojaVirtual(categoria, subcategoria);
				getDriver().navigate().to(Property.URL_RETAGUARDA);
				pageMenu.acessarMenuCadastrosCategorias();
				pageCategorias.validarOrtografiaDeCamposTelaCategoriaDeProdutos();
			}
		}else{
			categoria    = pageCategorias.getCategoriaTesteComFilho();
			subcategoria = pageCategorias.getSubCategoriaTeste(categoria);
			categoria    = categoria.substring(categoria.length()-10, categoria.length());
		}
		/*
		 * Excluir subcategoria
		 */
		pageCategorias.excluirCategoria(subcategoria);
		pageCategorias.validarMsgFeedbackExclusaoSucesso();
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirExclusaoSubategoriaNaLojaVirtual(categoria, subcategoria);
	}
}