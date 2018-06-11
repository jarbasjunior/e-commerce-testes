package br.com.ecommerce.tests.retaguarda.contas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.contas.receber.PageContasReceber;
import br.com.ecommerce.pages.retaguarda.contas.receber.PageEditarContasReceber;
import br.com.ecommerce.pages.retaguarda.contas.receber.PageIncluirContasReceber;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Contas >> À pagar
 * @author Jarbas
 * 
 * */
public class TestContasReceber extends BaseTest{

	PageMenu                 pageMenu            	  = new PageMenu();
	PageContasReceber        pageContasReceber		  = new PageContasReceber();
	PageEditarContasReceber  pageEditarContasReceber  = new PageEditarContasReceber();
	PageIncluirContasReceber pageIncluirContasReceber = new PageIncluirContasReceber();

	@Test
	public void cadastrarContaCreditoVencidaPagaComSucesso(){
		String  data            = null;
		String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
		String  tipoConta 		= null;
		String  vencimento  	= Utils.formataData(Utils.getDiaAnterior());
		String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas 	= "1";
		String  dataRecebimento = Utils.getDataAtual();
		boolean contaPaga   	= true; 
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		pageContasReceber.navegarParaPageInclusaoContasReceber();
		pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
		tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
		pageContasReceber.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		data = pageContasReceber.getData(notaFiscal);
		pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
	}
	
	@Test
	public void cadastrarContaCreditoVencidaNaoPagaComSucesso(){
		String  data            = null;
		String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
		String  tipoConta 		= null;
		String  vencimento  	= Utils.formataData(Utils.getDiaAnterior());
		String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas 	= "1";
		String  dataRecebimento = "--";
		boolean contaPaga   	= false; 
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		pageContasReceber.navegarParaPageInclusaoContasReceber();
		pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
		tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
		pageContasReceber.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		data = pageContasReceber.getData(notaFiscal);
		pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
	}
	
	@Test
	public void cadastrarContCreditoNaoVencidaPagaComSucesso(){
		String  data            = null;
		String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
		String  tipoConta 		= null;
		String  vencimento  	= Utils.formataData(Utils.getDiaSeguinte());
		String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas 	= "1";
		String  dataRecebimento = Utils.getDataAtual();
		boolean contaPaga       = true; 
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		pageContasReceber.navegarParaPageInclusaoContasReceber();
		pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
		tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
		pageContasReceber.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		data = pageContasReceber.getData(notaFiscal);
		pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
	}
	
	@Test
	public void cadastrarContaNaoVencidaNaoPagaComSucesso(){
		String  data            = null;
		String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
		String  tipoConta 		= null;
		String  vencimento  	= Utils.formataData(Utils.getDiaSeguinte());
		String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas 	= "1";
		String  dataRecebimento = "--";
		boolean contaPaga   	= false; 
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		pageContasReceber.navegarParaPageInclusaoContasReceber();
		pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
		tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
		pageContasReceber.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		data = pageContasReceber.getData(notaFiscal);
		pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
	}
	
	@Test
	public void alterarNotaFiscalContaReceberComSucesso(){
		String  data            = null;
		String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
		String  tipoConta 		= null;
		String  vencimento  	= Utils.getDataAtual();
		String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
		String  notaFiscal2 	= Utils.geraNumeroEntre(000000001, 999999999);
		String  qtdParcelas 	= "1";
		String  dataRecebimento = "--";
		boolean contaPaga   = false; 
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		/*
		 * Verifica se há conta teste cadastrada, se não houver
		 * será criada uma para ser alterda posteriormente
		 */
		if (!pageContasReceber.existsRegistroContaReceber()) {
			pageContasReceber.navegarParaPageInclusaoContasReceber();
			pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
			tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
			pageContasReceber.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasAreceber();
			pageContasReceber.verificarOrtografiaPageContasReceber();
			data = pageContasReceber.getData(notaFiscal);
			pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
		}else{
			notaFiscal  	= pageContasReceber.getNotaFiscalConta();
			data       		= pageContasReceber.getData(notaFiscal);
			devedor 		= pageContasReceber.getDevedor(notaFiscal);
			tipoConta   	= pageContasReceber.getTipoConta(notaFiscal);
			contaPaga   	= pageContasReceber.isContaPaga(notaFiscal);
			vencimento  	= pageContasReceber.getVencimento(notaFiscal);
			valorTotal  	= pageContasReceber.getValor(notaFiscal);
			qtdParcelas 	= pageContasReceber.getValorQtdParcelas(notaFiscal);
			dataRecebimento = pageContasReceber.getDataPagamento(notaFiscal);
		}
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.navegarParaPaginaEdicaoContasReceber(notaFiscal);
		pageEditarContasReceber.verificarOrtografiaPageEditarContasReceber();
		pageEditarContasReceber.alterarNotaFiscalCompraProduto(notaFiscal, notaFiscal2);
		pageContasReceber.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal2, valorTotal, qtdParcelas, vencimento, dataRecebimento);
	}
	
	
	@Test
	public void removerContaReceberComSucesso(){
		String  data            = null;
		String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
		String  tipoConta 		= null;
		String  vencimento  	= Utils.getDataAtual();
		String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas 	= "1";
		String  dataRecebimento = "--";
		boolean contaPaga   	= false; 
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		/*
		 * Verifica se há conta teste cadastrada, se não houver
		 * será criada uma para ser excluída posteriormente
		 */
		if (!pageContasReceber.existsRegistroContaReceber()) {
			pageContasReceber.navegarParaPageInclusaoContasReceber();
			pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
			tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
			pageContasReceber.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasAreceber();
			pageContasReceber.verificarOrtografiaPageContasReceber();
			data = pageContasReceber.getData(notaFiscal);
			pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
		}else{
			notaFiscal  	= pageContasReceber.getNotaFiscalConta();
			data            = pageContasReceber.getData(notaFiscal);
			devedor 		= pageContasReceber.getDevedor(notaFiscal);
			tipoConta   	= pageContasReceber.getTipoConta(notaFiscal);
			contaPaga   	= pageContasReceber.isContaPaga(notaFiscal);
			vencimento  	= pageContasReceber.getVencimento(notaFiscal);
			valorTotal  	= pageContasReceber.getValor(notaFiscal);
			qtdParcelas 	= pageContasReceber.getValorQtdParcelas(notaFiscal);
			dataRecebimento = pageContasReceber.getDataPagamento(notaFiscal);
		}
		/*
		 * Remove conta
		 */
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.removerContaReceber(notaFiscal);
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		pageContasReceber.validarContaReceberRemovida(notaFiscal);
	}
	
	@Test
	public void listarContasAreceberComSucesso(){
		List<String> notasFiscaisAReceber = new ArrayList<>();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		if (!pageContasReceber.existsContaCreditoAreceber()) {
			String  data            = null;
			String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
			String  tipoConta 		= null;
			String  vencimento  	= Utils.formataData(Utils.getDiaAnterior());
			String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
			String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
			String  qtdParcelas 	= "1";
			String  dataRecebimento = "--";
			boolean contaPaga   	= false; 
			pageContasReceber.navegarParaPageInclusaoContasReceber();
			pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
			tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
			pageContasReceber.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasAreceber();
			pageContasReceber.verificarOrtografiaPageContasReceber();
			data = pageContasReceber.getData(notaFiscal);
			pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
		}
		notasFiscaisAReceber = pageContasReceber.getNotasContasCreditoAreceber();
		pageContasReceber.filtrarContasAreceber();
		pageContasReceber.verificaFiltroContasAreceber(notasFiscaisAReceber);
	}
	
	@Test
	public void listarContasRecebidasComSucesso(){
		List<String> notasFiscaisRecebidas = new ArrayList<>();
		pageMenu.acessarMenuContasAreceber();
		pageContasReceber.verificarOrtografiaPageContasReceber();
		if (!pageContasReceber.existsContaRecebida()) {
			String  data            = null;
			String  devedor         = Utils.geraNome() + " " +Utils.geraSobrenome();
			String  tipoConta 		= null;
			String  vencimento  	= Utils.formataData(Utils.getDiaAnterior());
			String  notaFiscal  	= Utils.geraNumeroEntre(000000001, 999999999);
			String  valorTotal  	= Utils.geraNumeroEntre(1, 999)+".0";
			String  qtdParcelas 	= "1";
			String  dataRecebimento = Utils.getDataAtual();
			boolean contaPaga   	= true; 
			pageContasReceber.navegarParaPageInclusaoContasReceber();
			pageIncluirContasReceber.verificarOrtografiaPageIncluirContasReceber();
			tipoConta = pageIncluirContasReceber.incluirContasReceber(qtdParcelas, vencimento.substring(0, 2), devedor, notaFiscal, dataRecebimento, valorTotal);
			pageContasReceber.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasAreceber();
			pageContasReceber.verificarOrtografiaPageContasReceber();
			data = pageContasReceber.getData(notaFiscal);
			pageContasReceber.validarContaReceberNaListagem(contaPaga, data, tipoConta, devedor, notaFiscal, valorTotal, qtdParcelas, vencimento, dataRecebimento);
		}
		notasFiscaisRecebidas = pageContasReceber.getNotasContasRecebidas();
		pageContasReceber.filtrarContasRecebidas();
		pageContasReceber.verificaFiltroContasRecebidas(notasFiscaisRecebidas);
	}
}