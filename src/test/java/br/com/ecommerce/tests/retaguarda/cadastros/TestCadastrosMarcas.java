package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.marcas.PageEditarMarca;
import br.com.ecommerce.pages.retaguarda.cadastros.marcas.PageIncluirMarca;
import br.com.ecommerce.pages.retaguarda.cadastros.marcas.PageMarca;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Marcas
 * @author Jarbas
 * 
 * */
public class TestCadastrosMarcas extends BaseTest{

	PageMenu            pageMenu            = new PageMenu();
	PageMarca 	        pageMarca		    = new PageMarca();
	PageEditarMarca     pageEditarMarca     = new PageEditarMarca();
	PageIncluirMarca    pageIncluirMarca    = new PageIncluirMarca();
	
	@Test
	public void cadastrarMarcaComSucesso(){
		String marca = "Teste Marca "+Utils.geraSigla(3);
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
		String marca1 = "Teste Marca "+Utils.geraSigla(3);
		String marca2 = "Teste Marca "+Utils.geraSigla(3);
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
		String marca = "Teste Marca "+Utils.geraSigla(3);
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