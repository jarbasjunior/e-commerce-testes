package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.unidades.PageEditarUnidade;
import br.com.ecommerce.pages.retaguarda.cadastros.unidades.PageIncluirUnidade;
import br.com.ecommerce.pages.retaguarda.cadastros.unidades.PageUnidade;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Tipos de Conta
 * @author Jarbas
 * 
 * */
public class TestCadastrosUnidades extends BaseTest{
	
	PageMenu           pageMenu      	  = new PageMenu();
	PageUnidade        pageUnidade 		  = new PageUnidade();
	PageEditarUnidade  pageEditarUnidade  = new PageEditarUnidade();
	PageIncluirUnidade pageIncluirUnidade = new PageIncluirUnidade();

	@Test
	public void cadastrarUnidadeTesteComSucesso(){
		String unidade    = "Unidade Teste " + Utils.geraSigla(4);
		String abreviacao = "Abreviação " + unidade;
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		pageUnidade.navegarParaPaginaIncluirUnidade();
		pageIncluirUnidade.verificarOrtografiaPageIncluirUnidade();
		pageIncluirUnidade.incluirUnidade(unidade, abreviacao);
		pageUnidade.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		pageUnidade.validarUnidadeInseridaAtivada(unidade, abreviacao);
	}
	
	@Test
	public void alterarAtivandoUnidadeTesteComSucesso(){
		String unidadeAtual = null;
		String novaUnidade  = "Unidade Teste " + Utils.geraSigla(4);
		String abreviacao   = null;
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		if (!pageUnidade.existsUnidadeTeste()) {
			unidadeAtual = "Unidade Teste " + Utils.geraSigla(3);
			abreviacao   = "Abreviação " + unidadeAtual; 
			pageUnidade.navegarParaPaginaIncluirUnidade();
			pageIncluirUnidade.incluirUnidade(unidadeAtual, abreviacao);
			pageUnidade.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosUnidades();
			pageUnidade.verificarOrtografiaPageUnidade();
			pageUnidade.validarUnidadeInseridaAtivada(unidadeAtual, abreviacao);
		}else{
			unidadeAtual = pageUnidade.getUnidadeTeste();
			abreviacao   = "Abreviação " + novaUnidade;
		}
		if (!pageUnidade.isAtiva(unidadeAtual)) {
			pageUnidade.ativarUnidadeTeste(unidadeAtual);
		}
		pageUnidade.navegarParaPaginaEdicaoUnidade(unidadeAtual);
		pageEditarUnidade.verificarOrtografiaPageEditarUnidade();
		pageEditarUnidade.alterarUnidade(novaUnidade, abreviacao);
		pageUnidade.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		pageUnidade.validarUnidadeInseridaAtivada(novaUnidade, abreviacao);
	}
	
	@Test
	public void removerUnidadeTesteComSucesso(){
		String unidade    = null;
		String abreviacao = null;
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		if (!pageUnidade.existsUnidadeTeste()) {
			unidade    = "Unidade Teste " + Utils.geraSigla(3);
			abreviacao = "Abreviação " + unidade; 
			pageUnidade.navegarParaPaginaIncluirUnidade();
			pageIncluirUnidade.incluirUnidade(unidade, abreviacao);
			pageUnidade.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosUnidades();
			pageUnidade.verificarOrtografiaPageUnidade();
			pageUnidade.validarUnidadeInseridaDesativada(unidade, abreviacao);
		}else{
			unidade = pageUnidade.getUnidadeTeste();
		}
		pageUnidade.removerUnidade(unidade);
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		pageUnidade.validarUnidadeRemovida(unidade);
	}
	
	@Test
	public void ativarUnidadeTesteComSucesso(){
		String unidade    = null;
		String abreviacao = null;
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		if (!pageUnidade.existsUnidadeTeste()) {
			unidade    = "Unidade Teste " + Utils.geraSigla(3);
			abreviacao = "Abreviação " + unidade; 
			pageUnidade.navegarParaPaginaIncluirUnidade();
			pageIncluirUnidade.incluirUnidade(unidade, abreviacao);
			pageUnidade.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosUnidades();
			pageUnidade.verificarOrtografiaPageUnidade();
			pageUnidade.validarUnidadeInseridaAtivada(unidade, abreviacao);
		}else{
			unidade    = pageUnidade.getUnidadeTeste();
			abreviacao = pageUnidade.getAbreviacaoTeste(unidade);
		}
		if (pageUnidade.isAtiva(unidade)) {
			pageUnidade.desativarUnidadeTeste(unidade);
		}
		pageUnidade.ativarUnidadeTeste(unidade);
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		pageUnidade.validarUnidadeInseridaAtivada(unidade, abreviacao);
	}
	
	@Test
	public void desativarUnidadeTesteComSucesso(){
		String unidade    = null;
		String abreviacao = null;
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		if (!pageUnidade.existsUnidadeTeste()) {
			unidade    = "Unidade Teste " + Utils.geraSigla(3);
			abreviacao = "Abreviação " + unidade; 
			pageUnidade.navegarParaPaginaIncluirUnidade();
			pageIncluirUnidade.incluirUnidade(unidade, abreviacao);
			pageUnidade.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosUnidades();
			pageUnidade.verificarOrtografiaPageUnidade();
			pageUnidade.validarUnidadeInseridaDesativada(unidade, abreviacao);
		}else{
			unidade    = pageUnidade.getUnidadeTeste();
			abreviacao = pageUnidade.getAbreviacaoTeste(unidade);
		}
		if (!pageUnidade.isAtiva(unidade)) {
			pageUnidade.ativarUnidadeTeste(unidade);
		}
		pageUnidade.desativarUnidadeTeste(unidade);
		pageMenu.acessarMenuCadastrosUnidades();
		pageUnidade.verificarOrtografiaPageUnidade();
		pageUnidade.validarUnidadeInseridaDesativada(unidade, abreviacao);
	}
	
}