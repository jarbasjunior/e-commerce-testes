package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.marcas.PageEditarMarca;
import br.com.ecommerce.pages.retaguarda.cadastros.marcas.PageIncluirMarca;
import br.com.ecommerce.pages.retaguarda.cadastros.marcas.PageMarca;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosMarcas extends BaseTest{

	PageMenu            pageMenu            = new PageMenu();
	PageMarca 	        pageMarca		    = new PageMarca();
	PageEditarMarca     pageEditarMarca     = new PageEditarMarca();
	PageIncluirMarca    pageIncluirMarca    = new PageIncluirMarca();
	PageHomeRetaguarda  pageHomeRetaguarda  = new PageHomeRetaguarda();
	PageHomeLojaVirtual pageHomeLojaVirtual = new PageHomeLojaVirtual();
	
	@Test
	public void cadastrarMarcaComSucesso(){
		String marca = Utils.geraMarca();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.navegarParaPageInclusaoMarcas();
		pageIncluirMarca.verificarOrtografiaPageIncluirMarcas();
		pageIncluirMarca.incluirMarca(marca);
		pageMarca.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.validarMarcaNaListagem(marca);
	}
	
	@Test
	public void alterarDadosMarcaComSucesso(){
		/*
		 * Incluir marca
		 */
		String marca1 = Utils.geraMarca();
		String marca2 = Utils.geraMarca();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.navegarParaPageInclusaoMarcas();
		pageIncluirMarca.verificarOrtografiaPageIncluirMarcas();
		pageIncluirMarca.incluirMarca(marca1);
		pageMarca.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.validarMarcaNaListagem(marca1);
		/*
		 * Alterar marca
		 */
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.navegarParaPaginaEdicaoMarca(marca1);
		pageEditarMarca.verificarOrtografiaPageEditarMarcas();
		pageEditarMarca.alterarMarca(marca2);
		pageMarca.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.validarMarcaNaListagem(marca2);
	}
	
	@Test
	public void removerMarcaComSucesso(){
		/*
		 * Incluir funcionário
		 */
		String marca = Utils.geraMarca();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.navegarParaPageInclusaoMarcas();
		pageIncluirMarca.verificarOrtografiaPageIncluirMarcas();
		pageIncluirMarca.incluirMarca(marca);
		pageMarca.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.validarMarcaNaListagem(marca);
		/*
		 * Remover funcionário
		 */
		pageMarca.removerMarca(marca);
		pageMenu.acessarMenuCadastrosMarcas();
		pageMarca.verificarOrtografiaPageMarcas();
		pageMarca.validarMarcaRemovido(marca);
	}
}