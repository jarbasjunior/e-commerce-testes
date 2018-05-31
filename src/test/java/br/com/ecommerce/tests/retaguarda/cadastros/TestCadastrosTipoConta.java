package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.tiposconta.PageEditarTipoConta;
import br.com.ecommerce.pages.retaguarda.cadastros.tiposconta.PageIncluirTipoConta;
import br.com.ecommerce.pages.retaguarda.cadastros.tiposconta.PageTipoConta;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Tipos de Conta
 * @author Jarbas
 * 
 * */
public class TestCadastrosTipoConta extends BaseTest{
	
	PageMenu             pageMenu      		  = new PageMenu();
	PageTipoConta        pageTipoConta 		  = new PageTipoConta();
	PageEditarTipoConta  pageEditarTipoConta  = new PageEditarTipoConta();
	PageIncluirTipoConta pageIncluirTipoConta = new PageIncluirTipoConta();

	@Test
	public void cadastrarContaDespesaDesativadaComSucesso(){
		String  conta     = "Conta teste "+Utils.geraSigla(3)+" à pagar";
		boolean isAtiva   = false;
		boolean isDespesa = true;
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.navegarParaPaginaIncluirTipoDeConta();
		pageIncluirTipoConta.verificarOrtografiaPageIncluirTipoConta();
		pageIncluirTipoConta.incluirTipoConta(conta);
		pageTipoConta.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarTipoContaInserido(conta, isDespesa, isAtiva);
	}
	
	@Test
	public void cadastrarContaDespesaAtivadaComSucesso(){
		String  conta     = "Conta teste "+Utils.geraSigla(3)+" à pagar";
		boolean isAtiva   = true;
		boolean isDespesa = true;
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.navegarParaPaginaIncluirTipoDeConta();
		pageIncluirTipoConta.verificarOrtografiaPageIncluirTipoConta();
		pageIncluirTipoConta.incluirTipoConta(conta);
		pageTipoConta.validaMsgSucessoInclusao();
		pageTipoConta.ativarTipoConta(conta);
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarTipoContaInserido(conta, isDespesa, isAtiva);
	}
	
	@Test
	public void alterarContaTesteComSucesso(){
		boolean isAtiva    = false;
		boolean isDespesa  = true;
		String  contaAtual = null;
		String  novaConta  = "Conta teste "+Utils.geraSigla(3)+" à pagar";
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		if (!pageTipoConta.existsTipoContaTeste()) {
			contaAtual = "Conta teste "+Utils.geraSigla(3)+" à pagar";
			pageTipoConta.navegarParaPaginaIncluirTipoDeConta();
			pageIncluirTipoConta.incluirTipoConta(contaAtual);
			pageTipoConta.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTiposDeConta();
			pageTipoConta.verificarOrtografiaPageTiposDeConta();
			pageTipoConta.validarTipoContaInserido(contaAtual, isDespesa, isAtiva);
		}else
			contaAtual = pageTipoConta.getTipoContaTeste();
		
		isAtiva   = pageTipoConta.isAtivo(contaAtual);
		isDespesa = pageTipoConta.isDespesa(contaAtual);
		pageTipoConta.navegarParaPaginaEdicaoTipoDeConta(contaAtual);
		pageEditarTipoConta.verificarOrtografiaPageEditarTipoConta();
		pageEditarTipoConta.alterarTipoConta(novaConta);
		pageTipoConta.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarTipoContaInserido(novaConta, isDespesa, isAtiva);
	}
	
	@Test
	public void removerContaTesteComSucesso(){
		boolean isAtiva    = false;
		boolean isDespesa  = true;
		String  conta = null;
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		if (!pageTipoConta.existsTipoContaTeste()) {
			conta = "Conta teste "+Utils.geraSigla(3)+" à pagar";
			pageTipoConta.navegarParaPaginaIncluirTipoDeConta();
			pageIncluirTipoConta.incluirTipoConta(conta);
			pageTipoConta.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosTiposDeConta();
			pageTipoConta.verificarOrtografiaPageTiposDeConta();
			pageTipoConta.validarTipoContaInserido(conta, isDespesa, isAtiva);
		}else
			conta = pageTipoConta.getTipoContaTeste();
		
		pageTipoConta.removerTipoConta(conta);
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarTipoContaRemovido(conta);
	}
	
	@Test
	public void ativarContaCredito(){
		String tipoConta = "Tipo de Conta à receber";
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		if (pageTipoConta.isAtivo(tipoConta)) {
			pageTipoConta.desativarTipoConta(tipoConta);
		}
		pageTipoConta.ativarTipoConta(tipoConta);
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarAtivacaoDeConta(tipoConta);
	}
	
	@Test
	public void desativarContaCredito(){
		String tipoConta = "Tipo de Conta à receber";
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		if (!pageTipoConta.isAtivo(tipoConta)) {
			pageTipoConta.ativarTipoConta(tipoConta);
		}
		pageTipoConta.desativarTipoConta(tipoConta);
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarDesativacaoDeConta(tipoConta);
	}
	
	@Test
	public void ativarContaDebito(){
		String tipoConta = "Tipo de Conta à pagar";
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		if (pageTipoConta.isAtivo(tipoConta)) {
			pageTipoConta.desativarTipoConta(tipoConta);
		}
		pageTipoConta.ativarTipoConta(tipoConta);
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarAtivacaoDeConta(tipoConta);
	}
	
	@Test
	public void desativarContaDebito(){
		String tipoConta = "Tipo de Conta à pagar";
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		if (!pageTipoConta.isAtivo(tipoConta)) {
			pageTipoConta.ativarTipoConta(tipoConta);
		}
		pageTipoConta.desativarTipoConta(tipoConta);
		pageMenu.acessarMenuCadastrosTiposDeConta();
		pageTipoConta.verificarOrtografiaPageTiposDeConta();
		pageTipoConta.validarDesativacaoDeConta(tipoConta);
	}
}