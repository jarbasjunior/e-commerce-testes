package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.TiposPagamento.PageTiposPagamento;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Tipos de Pagamento
 * @author Jarbas
 * 
 * */
public class TestCadastrosTiposPagamento extends BaseTest{

	PageMenu                 pageMenu               = new PageMenu();
	PageTiposPagamento       pageTiposPagamento		= new PageTiposPagamento();
	
	@Test
	public void ativarTipoPagamentoBoleto(){
		String tipoPagamento = "Boleto Bancário";
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		if (pageTiposPagamento.isAtivo(tipoPagamento)) {
			pageTiposPagamento.desativarTipoPagamento(tipoPagamento);
		}
		pageTiposPagamento.ativarTipoPagamento(tipoPagamento);
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		pageTiposPagamento.verificarGrupoFiscalAtivo(tipoPagamento);
	}
	
	@Test
	public void desativarTipoPagamentoBoleto(){
		String grupoFiscal = "Boleto Bancário";
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		if (!pageTiposPagamento.isAtivo(grupoFiscal)) {
			pageTiposPagamento.ativarTipoPagamento(grupoFiscal);
		}
		pageTiposPagamento.desativarTipoPagamento(grupoFiscal);
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		pageTiposPagamento.verificarTipoPagamentoInativo(grupoFiscal);
	}
	
	@Test
	public void ativarTipoPagamentoCartaoCredito(){
		String tipoPagamento = "Cartão de Crédito";
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		if (pageTiposPagamento.isAtivo(tipoPagamento)) {
			pageTiposPagamento.desativarTipoPagamento(tipoPagamento);
		}
		pageTiposPagamento.ativarTipoPagamento(tipoPagamento);
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		pageTiposPagamento.verificarGrupoFiscalAtivo(tipoPagamento);
	}
	
	@Test
	public void desativarTipoCartaoCredito(){
		String grupoFiscal = "Cartão de Crédito";
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		if (!pageTiposPagamento.isAtivo(grupoFiscal)) {
			pageTiposPagamento.ativarTipoPagamento(grupoFiscal);
		}
		pageTiposPagamento.desativarTipoPagamento(grupoFiscal);
		pageMenu.acessarMenuCadastrosTiposDePagamentos();
		pageTiposPagamento.verificarOrtografiaPageTiposPagamento();
		pageTiposPagamento.verificarTipoPagamentoInativo(grupoFiscal);
	}
}