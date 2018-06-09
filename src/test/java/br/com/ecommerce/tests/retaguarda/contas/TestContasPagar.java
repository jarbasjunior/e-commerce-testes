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
	/*
	 * Cadastrar conta vencida e paga         - ok
	 * Cadastrar conta vencida e não paga     - ok
	 * Cadastrar conta não vencida e paga     - ok
	 * Cadastrar conta não vencida e não paga - ok
	 * Editar conta teste                     - ok
	 * Remover conta teste                    - ok
	 * Filtrar contas pagas
	 * Filtrar contas à pagar
	 * Filtra contas vencidas
	 */
	@Test
	public void cadastrarContaVencidaPagaComSucesso(){
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
		pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				                                    notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void cadastrarContaVencidaNaoPagaComSucesso(){
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
		pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void cadastrarContaNaoVencidaPagaComSucesso(){
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
		pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				                                    notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void cadastrarContaNaoVencidaNaoPagaComSucesso(){
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
		pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	@Test
	public void alterarNotaFiscalContaPagarComSucesso(){
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
			pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
					notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
		}else{
			notaFiscal  = pageContasPagar.getNotaFiscalCompra();
			contaPaga   = pageContasPagar.isContaPaga(notaFiscal);
			dataPagto   = pageContasPagar.getDataPagamento(notaFiscal);
			vencimento  = pageContasPagar.getVencimento(notaFiscal);
			valorTotal  = pageContasPagar.getValor(notaFiscal);
			qtdParcelas = pageContasPagar.getValorQtdParcelas(notaFiscal);
			tipoConta_E_Fornecedor.add(pageContasPagar.getTipoConta(notaFiscal));
			tipoConta_E_Fornecedor.add(pageContasPagar.getFornecedor(notaFiscal));
		}
		pageContasPagar.navegarParaPaginaEdicaoContasPagar(notaFiscal);
		pageEditarContasPagar.verificarOrtografiaPageEditarContasPagar();
		pageEditarContasPagar.alterarNotaFiscalCompraProduto(notaFiscal, notaFiscal2);
		pageContasPagar.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
				notaFiscal2, valorTotal, qtdParcelas, vencimento, dataPagto);
	}
	
	
	@Test
	public void removerContaPagarComSucesso(){
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
			pageContasPagar.validarContaPagarNaListagem(contaPaga, tipoConta_E_Fornecedor.get(0), tipoConta_E_Fornecedor.get(1), 
					notaFiscal, valorTotal, qtdParcelas, vencimento, dataPagto);
		}else{
			notaFiscal  = pageContasPagar.getNotaFiscalCompra();
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
		pageContasPagar.removerContaPagar(notaFiscal);
		pageMenu.acessarMenuContasApagar();
		pageContasPagar.verificarOrtografiaPageContasPagar();
		pageContasPagar.validarContaPagarRemovida(notaFiscal);
	}
}