package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.tamanhos.PageIncluirTamanho;
import br.com.ecommerce.pages.retaguarda.cadastros.tamanhos.PageTamanhos;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Tamanhos
 * @author Jarbas
 * 
 * */
public class TestCadastrosTamanho extends BaseTest{

	PageMenu           pageMenu           = new PageMenu();
	PageTamanhos       pageTamanhos       = new PageTamanhos();
	PageIncluirTamanho pageIncluirTamanho = new PageIncluirTamanho();
	
	@Test
	public void removerTamanhoP(){
		String tamanho = "P";
		pageMenu.acessarMenuCadastrosTamanhos();
		pageTamanhos.verificarOrtografiaPageTamanhos();
		if (!pageTamanhos.existsTamanho(tamanho)) {
			pageTamanhos.navegarParaPageInclusaoTamanho();
			pageIncluirTamanho.incluirTamanho(tamanho);
			pageTamanhos.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTamanhos();
			pageTamanhos.verificarOrtografiaPageTamanhos();
			pageTamanhos.validarTamanhoFiscalInserido(tamanho);
		}
		pageTamanhos.removerTamanho(tamanho);
		pageMenu.acessarMenuCadastrosTamanhos();
		pageTamanhos.verificarOrtografiaPageTamanhos();
		pageTamanhos.validarTamanhoRemovido(tamanho);
	}
	@Test
	public void removerTamanhoM(){
		String tamanho = "M";
		pageMenu.acessarMenuCadastrosTamanhos();
		pageTamanhos.verificarOrtografiaPageTamanhos();
		if (!pageTamanhos.existsTamanho(tamanho)) {
			pageTamanhos.navegarParaPageInclusaoTamanho();
			pageIncluirTamanho.incluirTamanho(tamanho);
			pageTamanhos.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTamanhos();
			pageTamanhos.verificarOrtografiaPageTamanhos();
			pageTamanhos.validarTamanhoFiscalInserido(tamanho);
		}
		pageTamanhos.removerTamanho(tamanho);
		pageMenu.acessarMenuCadastrosTamanhos();
		pageTamanhos.verificarOrtografiaPageTamanhos();
		pageTamanhos.validarTamanhoRemovido(tamanho);
	}
	@Test
	public void removerTamanhoG(){
		String tamanho = "G";
		pageMenu.acessarMenuCadastrosTamanhos();
		pageTamanhos.verificarOrtografiaPageTamanhos();
		if (!pageTamanhos.existsTamanho(tamanho)) {
			pageTamanhos.navegarParaPageInclusaoTamanho();
			pageIncluirTamanho.incluirTamanho(tamanho);
			pageTamanhos.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTamanhos();
			pageTamanhos.verificarOrtografiaPageTamanhos();
			pageTamanhos.validarTamanhoFiscalInserido(tamanho);
		}
		pageTamanhos.removerTamanho(tamanho);
		pageMenu.acessarMenuCadastrosTamanhos();
		pageTamanhos.verificarOrtografiaPageTamanhos();
		pageTamanhos.validarTamanhoRemovido(tamanho);
	}
}