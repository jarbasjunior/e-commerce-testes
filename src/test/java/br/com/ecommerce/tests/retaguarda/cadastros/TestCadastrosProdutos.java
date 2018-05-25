package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageEditarProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageIncluirProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageProduto;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosProdutos extends BaseTest{

	PageMenu               pageMenu            = new PageMenu();
	PageProduto      	   pageProduto		   = new PageProduto();
	PageEditarProduto      pageEditarProduto   = new PageEditarProduto();
	PageIncluirProduto     pageIncluirProduto  = new PageIncluirProduto();
	PageHomeRetaguarda     pageHomeRetaguarda  = new PageHomeRetaguarda();
	PageHomeLojaVirtual    pageHomeLojaVirtual = new PageHomeLojaVirtual();
	
	@Test
	public void cadastrarProdutoComSucesso(){
		String nome        = "Produto "+Utils.geraSigla(4);
		String qtdMinima   = Utils.geraNumeroEntre(1, 100);
		String codBarras   = Utils.geraNumeroEntre(000000001, 999999999);
		String descricao   = "Teste " + nome;
		String precoCompra = Utils.geraNumeroEntre(1, 599);
		String precoVenda  = Utils.precoVenda(precoCompra);
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.navegarParaPageInclusaoProdutos();
		pageIncluirProduto.verificarOrtografiaPageIncluirProduto();
		pageIncluirProduto.incluirProduto(nome, descricao, precoCompra+",00", precoVenda, qtdMinima, codBarras);
		pageProduto.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarProdutoNaListagem(nome, descricao);
	}
	
//	@Test
//	public void alterarDadosProdutoComSucesso(){
//		String cpf      = Utils.geraCPFSemFormato();
//		String nome     = "Teste";
//		String email    = Utils.geraEmail();
//		String telefone = Utils.formataTelefone(Utils.geraTelefoneBRA());
//		pageMenu.acessarMenuCadastrosFuncionarios();
//		pageProduto.verificarOrtografiaPageProdutos();
//		pageProduto.navegarParaPaginaEdicaoFuncionario(nome);
//		pageEditarProduto.verificarOrtografiaPageEditarFuncionarios();
//		pageEditarProduto.alterarFuncionario(nome, cpf, email, telefone);
//		pageProduto.validaMsgSucessoAlteracao();
//		pageMenu.acessarMenuCadastrosFuncionarios();
//		pageProduto.verificarOrtografiaPageProdutos();
//		pageProduto.validarProdutoNaListagem(nome, cpf, email);
//	}
	
	@Test
	public void removerProdutoComSucesso(){
		/*
		 * Incluir produto
		 */
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		if (!pageProduto.isProdutoTeste()) {
			String nome        = "Produto "+Utils.geraSigla(4);
			String qtdMinima   = Utils.geraNumeroEntre(1, 100);
			String codBarras   = Utils.geraNumeroEntre(555555555, 999999999);
			String descricao   = "Teste " + nome;
			String precoCompra = Utils.geraNumeroEntre(1, 599);
			String precoVenda  = Utils.precoVenda(precoCompra);
			pageProduto.navegarParaPageInclusaoProdutos();
			pageIncluirProduto.verificarOrtografiaPageIncluirProduto();
			pageIncluirProduto.incluirProduto(nome, descricao, precoCompra+",00", precoVenda, qtdMinima, codBarras);
			pageProduto.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosProdutos();
			pageProduto.verificarOrtografiaPageProdutos();
			pageProduto.validarProdutoNaListagem(nome, descricao);
		}
		/*
		 * Remover produto
		 */
		pageProduto.removerProduto(pageProduto.getProdutoTeste());
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarFuncionarioRemovido(pageProduto.getProdutoTeste());
	}
}