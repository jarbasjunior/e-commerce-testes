package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.tiposdeentrada.PageEditarTipoEntrada;
import br.com.ecommerce.pages.retaguarda.cadastros.tiposdeentrada.PageIncluirTipoEntrada;
import br.com.ecommerce.pages.retaguarda.cadastros.tiposdeentrada.PageTipoEntrada;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Tipos de Conta
 * @author Jarbas
 * 
 * */
public class TestCadastrosTipoEntrada extends BaseTest{
	
	PageMenu               pageMenu      		  = new PageMenu();
	PageTipoEntrada        pageTipoEntrada 		  = new PageTipoEntrada();
	PageEditarTipoEntrada  pageEditarTipoEntrada  = new PageEditarTipoEntrada();
	PageIncluirTipoEntrada pageIncluirTipoEntrada = new PageIncluirTipoEntrada();

	@Test
	public void cadastrarEntradaComSucesso(){
		String tipoEntrada = "Entrada Teste " + Utils.geraSigla(3);
		pageMenu.acessarMenuCadastrosTiposDeEntrada();
		pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
		pageTipoEntrada.navegarParaPaginaIncluirTipoDeEntrada();
		pageIncluirTipoEntrada.verificarOrtografiaPageIncluirTipoDeEntrada();
		pageIncluirTipoEntrada.incluirTipoEntrada(tipoEntrada);
		pageTipoEntrada.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosTiposDeEntrada();
		pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
		pageTipoEntrada.validarTipoEntradaInserido(tipoEntrada);
	}
	
	@Test
	public void alterarEntradaTesteComSucesso(){
		String  entradaAtual = null;
		String  novaEntrada  = "Entrada Teste " + Utils.geraSigla(3);
		pageMenu.acessarMenuCadastrosTiposDeEntrada();
		pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
		if (!pageTipoEntrada.existsTipoEntradaTeste()) {
			entradaAtual = "Entrada Teste " + Utils.geraSigla(3);
			pageTipoEntrada.navegarParaPaginaIncluirTipoDeEntrada();
			pageIncluirTipoEntrada.incluirTipoEntrada(entradaAtual);
			pageTipoEntrada.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTiposDeEntrada();
			pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
			pageTipoEntrada.validarTipoEntradaInserido(entradaAtual);
		}else
			entradaAtual = pageTipoEntrada.getTipoEntradaTeste();
		
		pageTipoEntrada.navegarParaPaginaEdicaoTipoDeEntrada(entradaAtual);
		pageEditarTipoEntrada.verificarOrtografiaPageEditarTipoEntrada();
		pageEditarTipoEntrada.alterarTipoEntrada(novaEntrada);
		pageTipoEntrada.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosTiposDeEntrada();
		pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
		pageTipoEntrada.validarTipoEntradaInserido(novaEntrada);
	}
	
	@Test
	public void removerEntradaTesteComSucesso(){
		String tipoEntrada = null;
		pageMenu.acessarMenuCadastrosTiposDeEntrada();
		pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
		if (!pageTipoEntrada.existsTipoEntradaTeste()) {
			tipoEntrada = "Entrada Teste " + Utils.geraSigla(3);
			pageTipoEntrada.navegarParaPaginaIncluirTipoDeEntrada();
			pageIncluirTipoEntrada.incluirTipoEntrada(tipoEntrada);
			pageTipoEntrada.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTiposDeEntrada();
			pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
			pageTipoEntrada.validarTipoEntradaInserido(tipoEntrada);
		}else
			tipoEntrada = pageTipoEntrada.getTipoEntradaTeste();
		
		pageTipoEntrada.removerTipoConta(tipoEntrada);
		pageMenu.acessarMenuCadastrosTiposDeEntrada();
		pageTipoEntrada.verificarOrtografiaPageTiposDeEntrada();
		pageTipoEntrada.validarTipoEntradaRemovido(tipoEntrada);
	}
}