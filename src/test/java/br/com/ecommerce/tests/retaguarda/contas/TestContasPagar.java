package br.com.ecommerce.tests.retaguarda.contas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.contas.pagar.PageContasPagar;
import br.com.ecommerce.pages.retaguarda.contas.pagar.PageEditarContasPagar;
import br.com.ecommerce.pages.retaguarda.contas.pagar.PageIncluirContasPagar;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Contas >> À pagar
 * @author Jarbas
 * 
 * */
public class TestContasPagar extends BaseTest{

	PageMenu               pageMenu            	  = new PageMenu();
	PageContasPagar        pageContasPagar		  = new PageContasPagar();
	PageEditarContasPagar  pageEditarContasPagar  = new PageEditarContasPagar();
	PageIncluirContasPagar pageIncluirContasPagar = new PageIncluirContasPagar();

	@Test
	public void cadastrarContaDebitoVencidaPagaComSucesso(){
		String  data        = null;
		String  dataPagto   = Utils.getDataAtual();
		String  vencimento  = Utils.formataData(Utils.getDiaAnterior());
		String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas = "1";
		boolean contaPaga   = true; 
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.navegarParaPageInclusaoContasPagar();
		pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
		tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
		pageContasPagar.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		data = pageContasPagar.getData(notaFiscal);
		pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				                                    notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void cadastrarContaDebitoVencidaNaoPagaComSucesso(){
		String  data        = null;
		String  dataPagto   = "--";
		String  vencimento  = Utils.formataData(Utils.getDiaAnterior());
		String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas = "1";
		boolean contaPaga   = false; 
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.navegarParaPageInclusaoContasPagar();
		pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
		tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
		pageContasPagar.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		data = pageContasPagar.getData(notaFiscal);
		pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void cadastrarContaDebitoNaoVencidaPagaComSucesso(){
		String  data        = null;
		String  dataPagto   = Utils.getDataAtual();
		String  vencimento  = Utils.formataData(Utils.getDiaSeguinte());
		String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas = "1";
		boolean contaPaga   = true; 
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.navegarParaPageInclusaoContasPagar();
		pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
		tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
		pageContasPagar.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		data = pageContasPagar.getData(notaFiscal);
		pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				                                    notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void cadastrarContaDebitoNaoVencidaNaoPagaComSucesso(){
		String  data        = null;
		String  dataPagto   = "--";
		String  vencimento  = Utils.formataData(Utils.getDiaSeguinte());
		String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas = "1";
		boolean contaPaga   = false; 
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.navegarParaPageInclusaoContasPagar();
		pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
		tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
		pageContasPagar.validaMsgSucessoInclusao();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		data = pageContasPagar.getData(notaFiscal);
		pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void alterarNotaFiscalContaDebitoComSucesso(){
		String  data        = null;
		String  dataPagto   = "--";
		String  vencimento  = Utils.getDataAtual();
		String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
		String  notaFiscal2 = Utils.geraNumeroEntre(000000001, 999999999);
		String  qtdParcelas = "1";
		boolean contaPaga   = false; 
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		/*
		 * Verifica se há conta teste cadastrada, se não houver
		 * será criada uma para ser alterda posteriormente
		 */
		if (!pageContasPagar.existsRegistroContaPagar()) {
			pageContasPagar.navegarParaPageInclusaoContasPagar();
			pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
			tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
			pageContasPagar.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasApagar();
			pageContasPagar.verificarOrtografiaPageContasPagar();
			data = pageContasPagar.getData(notaFiscal);
			pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
					notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
		}else{
			notaFiscal  = pageContasPagar.getNotaFiscalConta();
			data 		= pageContasPagar.getData(notaFiscal);
			contaPaga   = pageContasPagar.isContaPaga(notaFiscal);
			dataPagto   = pageContasPagar.getDataPagamento(notaFiscal);
			vencimento  = pageContasPagar.getVencimento(notaFiscal);
			valorTotal  = pageContasPagar.getValor(notaFiscal);
			qtdParcelas = pageContasPagar.getValorQtdParcelas(notaFiscal);
			tipoConta_E_Fornecedor.add(pageContasPagar.getTipoConta(notaFiscal));
			tipoConta_E_Fornecedor.add(pageContasPagar.getFornecedor(notaFiscal));
		}
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.navegarParaPaginaEdicaoContasPagar(notaFiscal);
		pageEditarContasPagar.verificarOrtografiaPageEditarContasPagar();
		pageEditarContasPagar.alterarNotaFiscalCompraProduto(notaFiscal, notaFiscal2);
		pageContasPagar.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				notaFiscal2, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void removerContaDebitoComSucesso(){
		String  data        = null;
		String  dataPagto   = "--";
		String  vencimento  = Utils.getDataAtual();
		String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
		String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
		String  qtdParcelas = "1";
		boolean contaPaga   = false; 
		List<String> tipoConta_E_Fornecedor = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		/*
		 * Verifica se há conta teste cadastrada, se não houver
		 * será criada uma para ser excluída posteriormente
		 */
		if (!pageContasPagar.existsRegistroContaPagar()) {
			pageContasPagar.navegarParaPageInclusaoContasPagar();
			pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
			tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
			pageContasPagar.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasApagar();
			pageContasPagar.verificarOrtografiaPageContasPagar();
			pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
					notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
		}else{
			notaFiscal  = pageContasPagar.getNotaFiscalConta();
			data        = pageContasPagar.getData(notaFiscal);
			contaPaga   = pageContasPagar.isContaPaga(notaFiscal);
			dataPagto   = pageContasPagar.getDataPagamento(notaFiscal);
			vencimento  = pageContasPagar.getVencimento(notaFiscal);
			valorTotal  = pageContasPagar.getValor(notaFiscal);
			qtdParcelas = pageContasPagar.getValorQtdParcelas(notaFiscal);
			tipoConta_E_Fornecedor.add(pageContasPagar.getTipoConta(notaFiscal));
			tipoConta_E_Fornecedor.add(pageContasPagar.getFornecedor(notaFiscal));
		}
		/*
		 * Remove conta
		 */
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.removerContaPagar(notaFiscal);
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.validarContaPagarRemovida(notaFiscal);
	}
	
	@Test
	public void listarContasAPagarComSucesso(){
		List<String> notasFiscaisAPagar = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		if (!pageContasPagar.existsContaAPagar()) {
			String  data        = null;
			String  dataPagto   = "--";
			String  vencimento  = Utils.formataData(Utils.getDiaSeguinte());
			String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
			String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
			String  qtdParcelas = "1";
			boolean contaPaga   = false; 
			List<String> tipoConta_E_Fornecedor = new ArrayList<>();
			pageContasPagar.navegarParaPageInclusaoContasPagar();
			pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
			tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
			pageContasPagar.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasApagar();
			pageContasPagar.verificarOrtografiaPageContasPagar();
			data = pageContasPagar.getData(notaFiscal);
			pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
					notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
		}
		notasFiscaisAPagar = pageContasPagar.getNotasContasAPagar();
		pageContasPagar.filtrarContasAPagar();
		pageContasPagar.verificaFiltroContasAPagar(notasFiscaisAPagar);
	}
	
	@Test
	public void listarContasPagasComSucesso(){
		List<String> notasFiscaisPagas = new ArrayList<>();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		if (!pageContasPagar.existsContaPaga()) {
			String  data        = null;
			String  dataPagto   = Utils.getDataAtual();
			String  vencimento  = Utils.formataData(Utils.getDiaSeguinte());
			String  notaFiscal  = Utils.geraNumeroEntre(000000001, 999999999);
			String  valorTotal  = Utils.geraNumeroEntre(1, 999)+".0";
			String  qtdParcelas = "1";
			boolean contaPaga   = true; 
			List<String> tipoConta_E_Fornecedor = new ArrayList<>();
			pageContasPagar.navegarParaPageInclusaoContasPagar();
			pageIncluirContasPagar.verificarOrtografiaPageIncluirContasPagar();
			tipoConta_E_Fornecedor = pageIncluirContasPagar.incluirContasPagar(qtdParcelas, vencimento.substring(0, 2), notaFiscal, dataPagto, valorTotal);
			pageContasPagar.validaMsgSucessoInclusao();
			pageMenu.acessarMenuContasApagar();
			pageContasPagar.verificarOrtografiaPageContasPagar();
			data = pageContasPagar.getData(notaFiscal);
			pageContasPagar.validarContaPagarNaListagem(contaPaga, data, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
					notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
		}
		notasFiscaisPagas = pageContasPagar.getNotasContasPagas();
		pageContasPagar.filtrarContasPagas();
		pageContasPagar.verificaFiltroContasPagas(notasFiscaisPagas);
	}
}