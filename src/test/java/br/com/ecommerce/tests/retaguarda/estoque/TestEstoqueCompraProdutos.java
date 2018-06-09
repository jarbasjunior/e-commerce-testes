package br.com.ecommerce.tests.retaguarda.estoque;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.pages.retaguarda.estoque.PageCompraProduto;
import br.com.ecommerce.pages.retaguarda.estoque.PageEditarCompraProduto;
import br.com.ecommerce.pages.retaguarda.estoque.PageIncluirCompraProduto;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Estoque >> Compra de Produtos
 * @author Jarbas
 * 
 * */
public class TestEstoqueCompraProdutos extends BaseTest{

	PageMenu            	 pageMenu            	  = new PageMenu();
	PageCompraProduto      	 pageCompraProduto		  = new PageCompraProduto();
	PageEditarCompraProduto  pageEditarCompraProduto  = new PageEditarCompraProduto();
	PageIncluirCompraProduto pageIncluirCompraProduto = new PageIncluirCompraProduto();
	
	@Test
	public void cadastrarCompraProdutoNaoPagaComSucesso(){
		String  desconto       = "0";
		String  parcelas       = "12";
		String  notaFiscal     = Utils.geraNumeroEntre(000000001, 999999999);
		String  dataCompra     = Utils.getAnoAnterior();
		String  dataVencimento = Utils.getDataAtual();
		String  outrasDespesas = "0";
		boolean compraPaga     = false;
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.navegarParaPageInclusaoCompraProdutos();
		pageIncluirCompraProduto.verificarOrtografiaPageIncluirCompraProduto();
		pageIncluirCompraProduto.incluirCompraProduto(notaFiscal, dataCompra, parcelas, dataVencimento, desconto, outrasDespesas, compraPaga);
		pageCompraProduto.validaMsgSucessoInclusao();
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal, dataCompra, compraPaga);
	}
	@Test
	public void cadastrarCompraProdutoPagaComSucesso(){
		String  desconto       = "0";
		String  parcelas       = "12";
		String  notaFiscal     = Utils.geraNumeroEntre(000000001, 999999999);
		String  dataCompra     = Utils.getAnoAnterior();
		String  dataVencimento = Utils.getDataAtual();
		String  outrasDespesas = "0";
		boolean compraPaga     = true;
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.navegarParaPageInclusaoCompraProdutos();
		pageIncluirCompraProduto.verificarOrtografiaPageIncluirCompraProduto();
		pageIncluirCompraProduto.incluirCompraProduto(notaFiscal, dataCompra, parcelas, dataVencimento, desconto, outrasDespesas, compraPaga);
		pageCompraProduto.validaMsgSucessoInclusao();
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal, dataCompra, compraPaga);
	}
	
	@Test
	public void alterarNotaFiscalCompraProdutoComSucesso(){
		String  desconto       = "0";
		String  parcelas       = "12";
		String  notaFiscal     = Utils.geraNumeroEntre(000000001, 999999999);
		String  notaFiscal2    = Utils.geraNumeroEntre(000000001, 999999999);
		String  dataCompra     = Utils.getAnoAnterior();
		String  dataVencimento = Utils.getDataAtual();
		String  outrasDespesas = "0";
		boolean compraPaga     = true;
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		/*
		 * Inclui uma compra de produto caso não exista
		 */
		if (!pageCompraProduto.existsCompraProduto()) {
			pageCompraProduto.navegarParaPageInclusaoCompraProdutos();
			pageIncluirCompraProduto.verificarOrtografiaPageIncluirCompraProduto();
			pageIncluirCompraProduto.incluirCompraProduto(notaFiscal, dataCompra, parcelas, dataVencimento, desconto, outrasDespesas, compraPaga);
			pageCompraProduto.validaMsgSucessoInclusao();
			pageMenu.acessarMenuEstoqueCompraDeProdutos();
			pageCompraProduto.verificarOrtografiaPageCompraProdutos();
			pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal, dataCompra, compraPaga);
		}else{
			notaFiscal = pageCompraProduto.getNotaFiscalCompra();
			dataCompra = pageCompraProduto.getDataNotaFiscalCompra(notaFiscal);
			compraPaga = pageCompraProduto.isCompraPaga(notaFiscal);
		}
		pageCompraProduto.navegarParaPaginaEdicaoCompraProduto(notaFiscal);
		pageEditarCompraProduto.verificarOrtografiaPageEditarCompraProduto();
		pageEditarCompraProduto.alterarNotaFiscalCompraProduto(notaFiscal, notaFiscal2);
		pageCompraProduto.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal2, dataCompra, compraPaga);
	}
	
	@Test
	public void alterarStatusPagamentoCompraProdutoComSucesso(){
		String  desconto       = "0";
		String  parcelas       = "12";
		String  notaFiscal     = Utils.geraNumeroEntre(000000001, 999999999);
		String  dataCompra     = Utils.getAnoAnterior();
		String  dataVencimento = Utils.getDataAtual();
		String  outrasDespesas = "0";
		boolean compraPaga     = true;
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		/*
		 * Inclui uma compra de produto caso não exista
		 */
		if (!pageCompraProduto.existsCompraProduto()) {
			pageCompraProduto.navegarParaPageInclusaoCompraProdutos();
			pageIncluirCompraProduto.verificarOrtografiaPageIncluirCompraProduto();
			pageIncluirCompraProduto.incluirCompraProduto(notaFiscal, dataCompra, parcelas, dataVencimento, desconto, outrasDespesas, compraPaga);
			pageCompraProduto.validaMsgSucessoInclusao();
			pageMenu.acessarMenuEstoqueCompraDeProdutos();
			pageCompraProduto.verificarOrtografiaPageCompraProdutos();
			pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal, dataCompra, compraPaga);
		}else{
			notaFiscal = pageCompraProduto.getNotaFiscalCompra();
			dataCompra = pageCompraProduto.getDataNotaFiscalCompra(notaFiscal);
			compraPaga = pageCompraProduto.isCompraPaga(notaFiscal);
		}
		pageCompraProduto.navegarParaPaginaEdicaoCompraProduto(notaFiscal);
		pageEditarCompraProduto.verificarOrtografiaPageEditarCompraProduto();
		pageEditarCompraProduto.alterarStatusCompraProduto(notaFiscal, compraPaga);
		pageCompraProduto.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal, dataCompra, !compraPaga);
	}
	
	@Test
	public void removerProdutoComSucesso(){
		String  desconto       = "0";
		String  parcelas       = "12";
		String  notaFiscal     = Utils.geraNumeroEntre(000000001, 999999999);
		String  dataCompra     = Utils.getAnoAnterior();
		String  dataVencimento = Utils.getDataAtual();
		String  outrasDespesas = "0";
		boolean compraPaga     = true;
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		/*
		 * Inclui uma compra de produto caso não exista
		 */
		if (!pageCompraProduto.existsCompraProduto()) {
			pageCompraProduto.navegarParaPageInclusaoCompraProdutos();
			pageIncluirCompraProduto.verificarOrtografiaPageIncluirCompraProduto();
			pageIncluirCompraProduto.incluirCompraProduto(notaFiscal, dataCompra, parcelas, dataVencimento, desconto, outrasDespesas, compraPaga);
			pageCompraProduto.validaMsgSucessoInclusao();
			pageMenu.acessarMenuEstoqueCompraDeProdutos();
			pageCompraProduto.verificarOrtografiaPageCompraProdutos();
			pageCompraProduto.validarCompraProdutoNaListagem(notaFiscal, dataCompra, compraPaga);
		}else{
			notaFiscal = pageCompraProduto.getNotaFiscalCompra();
			dataCompra = pageCompraProduto.getDataNotaFiscalCompra(notaFiscal);
			compraPaga = pageCompraProduto.isCompraPaga(notaFiscal);
		}
		/*
		 * Remove compra
		 */
		pageCompraProduto.removerCompraProduto(notaFiscal);
		pageMenu.acessarMenuEstoqueCompraDeProdutos();
		pageCompraProduto.verificarOrtografiaPageCompraProdutos();
		pageCompraProduto.validarCompraRemovida(notaFiscal);
	}
}