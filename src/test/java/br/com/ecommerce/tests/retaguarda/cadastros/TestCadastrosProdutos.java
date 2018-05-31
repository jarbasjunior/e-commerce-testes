package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageAdicionarImagemProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageAdicionarUnidadeProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageCategoriaProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageEditarProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageImagensProdutos;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageIncluirProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageProduto;
import br.com.ecommerce.pages.retaguarda.cadastros.produtos.PageUnidadeProduto;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Produtos
 * @author Jarbas
 * 
 * */
public class TestCadastrosProdutos extends BaseTest{

	PageMenu            	    pageMenu            		= new PageMenu();
	PageProduto      		    pageProduto		    	    = new PageProduto();
	PageEditarProduto   	    pageEditarProduto   		= new PageEditarProduto();
	PageIncluirProduto  	    pageIncluirProduto  		= new PageIncluirProduto();
	PageHomeRetaguarda  	    pageHomeRetaguarda          = new PageHomeRetaguarda();
	PageUnidadeProduto          pageUnidadeProduto		    = new PageUnidadeProduto();       
	PageImagensProdutos 	    pageImagensProdutos 		= new PageImagensProdutos();
	PageCategoriaProduto        pageCategoriaProduto        = new PageCategoriaProduto();
	PageAdicionarImagemProduto  pageAdicionarImagemProduto  = new PageAdicionarImagemProduto();
	PageAdicionarUnidadeProduto pageAdicionarUnidadeProduto = new PageAdicionarUnidadeProduto();
	
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
	
	@Test
	public void alterarDadosProdutoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		/*
		 * Inclui produto caso não exista
		 */
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
		String produtoTeste          = "Produto "+Utils.geraSigla(4);
		String descricaoProdutoTeste = "Teste " + produtoTeste;
		pageProduto.navegarParaPaginaEdicaoProduto(pageProduto.getProdutoTeste());
		pageEditarProduto.verificarOrtografiaPageEditarProduto();
		pageEditarProduto.alterarProduto(produtoTeste, descricaoProdutoTeste);
		pageProduto.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarProdutoNaListagem(produtoTeste, descricaoProdutoTeste);
	}
	
	@Test
	public void removerProdutoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		/*
		 * Inclui produto caso não exista
		 */
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
		 * Remove produto
		 */
		pageProduto.removerProduto(pageProduto.getProdutoTeste());
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarProdutoRemovido(pageProduto.getProdutoTeste());
	}
	
	@Test
	public void desativarProdutoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		String nome = null;
		/*
		 * Inclui produto teste ativo caso não exista
		 */
		if (!pageProduto.isProdutoTesteAtivo()) {
			nome               = "Produto "+Utils.geraSigla(4);
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
		}else{
			nome = pageProduto.getProdutoTesteAtivo();
		}
			
		/*
		 * Desativa produto
		 */
		pageProduto.desativarProduto(nome);
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarProdutoDesativado(nome);
	}
	
	@Test
	public void ativarProdutoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		String nome = null;
		/*
		 * Inclui produto teste caso não exista e desativa
		 */
		if (!pageProduto.isProdutoTesteInativo()) {
			nome               = "Produto "+Utils.geraSigla(4);
			String qtdMinima   = Utils.geraNumeroEntre(1, 100);
			String codBarras   = Utils.geraNumeroEntre(555555555, 999999999);
			String descricao   = "Teste " + nome;
			String precoCompra = Utils.geraNumeroEntre(1, 599);
			String precoVenda  = Utils.precoVenda(precoCompra);
			pageProduto.navegarParaPageInclusaoProdutos();
			pageIncluirProduto.verificarOrtografiaPageIncluirProduto();
			pageIncluirProduto.incluirProduto(nome, descricao, precoCompra+",00", precoVenda, qtdMinima, codBarras);
			pageProduto.validaMsgSucessoInclusao();
			pageProduto.desativarProduto(nome);
			pageMenu.acessarMenuCadastrosProdutos();
			pageProduto.verificarOrtografiaPageProdutos();
			pageProduto.validarProdutoDesativado(nome);
		}else{
			nome = pageProduto.getProdutoTesteDesativado();
		}
		/*
		 * Ativa produto
		 */
		pageProduto.ativarProduto(nome);
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarProdutoAtivado(nome);
	}
	
	@Test
	public void adicionarImagemAoProdutoAtivoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		String imagem  = Property.IMAGEM_COMPRA; 
		String produto = null;
		/*
		 * Inclui produto caso não exista
		 */
		if (!pageProduto.isProdutoTesteAtivo()) {
			produto            = "Produto "+Utils.geraSigla(4);
			String qtdMinima   = Utils.geraNumeroEntre(1, 100);
			String codBarras   = Utils.geraNumeroEntre(555555555, 999999999);
			String descricao   = "Teste " + produto;
			String precoCompra = Utils.geraNumeroEntre(1, 599);
			String precoVenda  = Utils.precoVenda(precoCompra);
			pageProduto.navegarParaPageInclusaoProdutos();
			pageIncluirProduto.verificarOrtografiaPageIncluirProduto();
			pageIncluirProduto.incluirProduto(produto, descricao, precoCompra+",00", precoVenda, qtdMinima, codBarras);
			pageProduto.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosProdutos();
			pageProduto.verificarOrtografiaPageProdutos();
			pageProduto.validarProdutoNaListagem(produto, descricao);
		}else
			produto = pageProduto.getProdutoTeste();
		/*
		 * Adiciona imagem
		 */
		pageProduto.irParaPageImagensProduto(produto);
		pageImagensProdutos.verificarOrtografiaPageEditarImagemProduto(produto);
		pageImagensProdutos.irParaPageAdicionarImagensProduto();
		pageAdicionarImagemProduto.verificarOrtografiaPageAdicionarImagemProduto();
		pageAdicionarImagemProduto.adicionarImagemPrincipalAoProduto(imagem);
		/*
		 * Verifica imagem adicionada
		 */
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.irParaPageImagensProduto(produto);
		pageImagensProdutos.verificarOrtografiaPageEditarImagemProduto(produto);
		pageImagensProdutos.validaImagemAdicionada(imagem);
	}
	
	@Test
	public void adicionarCategoriaAoProdutoAtivoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		String produto   = null;
		String categoria = "Mulheres";
		/*
		 * Inclui produto caso não exista
		 */
		if (!pageProduto.isProdutoTesteAtivo()) {
			produto            = "Produto "+Utils.geraSigla(4);
			String qtdMinima   = Utils.geraNumeroEntre(1, 100);
			String codBarras   = Utils.geraNumeroEntre(555555555, 999999999);
			String descricao   = "Teste " + produto;
			String precoCompra = Utils.geraNumeroEntre(1, 599);
			String precoVenda  = Utils.precoVenda(precoCompra);
			pageProduto.navegarParaPageInclusaoProdutos();
			pageIncluirProduto.verificarOrtografiaPageIncluirProduto();
			pageIncluirProduto.incluirProduto(produto, descricao, precoCompra+",00", precoVenda, qtdMinima, codBarras);
			pageProduto.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosProdutos();
			pageProduto.verificarOrtografiaPageProdutos();
			pageProduto.validarProdutoNaListagem(produto, descricao);
		}else
			produto = pageProduto.getProdutoTeste();
		/*
		 * Adiciona categoria
		 */
		pageProduto.navegarParaPageCategoriaProduto(produto);
		pageCategoriaProduto.verificarOrtografiaPageCategoriaProduto(produto);
		pageCategoriaProduto.adicionaCategoriaMulheres(categoria);
		/*
		 * Verifica categoria adicionada
		 */
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.navegarParaPageCategoriaProduto(produto);
		pageCategoriaProduto.verificarOrtografiaPageCategoriaProduto(produto);
		pageCategoriaProduto.validaCategoriaAdicionada(categoria);
	}
	
	@Test
	public void adicionarUnidadeAoProdutoAtivoComSucesso(){
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		String codigo  = Utils.geraNumeroEntre(555555555, 999999999);
		String produto = null;
		/*
		 * Inclui produto 
		 */
		produto            = "Produto "+Utils.geraSigla(4);
		String qtdMinima   = Utils.geraNumeroEntre(1, 100);
		String codBarras   = Utils.geraNumeroEntre(555555555, 999999999);
		String descricao   = "Teste " + produto;
		String precoCompra = Utils.geraNumeroEntre(1, 599);
		String precoVenda  = Utils.precoVenda(precoCompra);
		pageProduto.navegarParaPageInclusaoProdutos();
		pageIncluirProduto.verificarOrtografiaPageIncluirProduto();
		pageIncluirProduto.incluirProduto(produto, descricao, precoCompra+",00", precoVenda, qtdMinima, codBarras);
		pageProduto.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.validarProdutoNaListagem(produto, descricao);
		/*
		 * Adiciona unidade
		 */
		pageProduto.navegarParaPageUnidadeProduto(produto);
		pageUnidadeProduto.verificarOrtografiaPageUnidadeProduto(produto);
		pageUnidadeProduto.navegarParaPageIncluirUnidadeProduto();
		pageAdicionarUnidadeProduto.verificarOrtografiaPageAdicionarUnidadeProduto();
		String cor     = pageAdicionarUnidadeProduto.getCorDisponivel();
		String tamanho = pageAdicionarUnidadeProduto.getTamanhoDisponivel();
		pageAdicionarUnidadeProduto.adicionarUnidadeAoProduto(codigo, cor, tamanho);
		/*
		 * Verifica unidade adicionada
		 */
		pageMenu.acessarMenuCadastrosProdutos();
		pageProduto.verificarOrtografiaPageProdutos();
		pageProduto.navegarParaPageUnidadeProduto(produto);
		pageUnidadeProduto.verificarOrtografiaPageUnidadeProduto(produto);
		pageUnidadeProduto.validaUnidadeAdicionada(codigo, cor, tamanho);
	}
}