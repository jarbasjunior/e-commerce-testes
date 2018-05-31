package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.fornecedores.PageEditarFornecedor;
import br.com.ecommerce.pages.retaguarda.cadastros.fornecedores.PageFornecedor;
import br.com.ecommerce.pages.retaguarda.cadastros.fornecedores.PageIncluirFornecedor;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Fornecedores
 * @author Jarbas
 * 
 * */
public class TestCadastrosFornecedores extends BaseTest{

	PageMenu              pageMenu              = new PageMenu();
	PageFornecedor 		  pageFornecedor		= new PageFornecedor();
	PageEditarFornecedor  pageEditarFornecedor  = new PageEditarFornecedor();
	PageIncluirFornecedor pageIncluirFornecedor = new PageIncluirFornecedor();
	
	@Test
	public void cadastrarFornecedorComSucesso(){
		String cnpj        = Utils.geraCNPJ();
		String telefone    = Utils.formataTelefone(Utils.geraTelefoneBRA());
		String razaoSocial = Utils.geraNomeEmpresa();
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.navegarParaPageInclusaoFornecedores();
		pageIncluirFornecedor.verificarOrtografiaPageIncluirFornecedores();
		pageIncluirFornecedor.incluirFornecedor(razaoSocial, cnpj, telefone);
		pageFornecedor.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.validarFornecedorNaListagem(razaoSocial, cnpj, telefone);
	}
	
	@Test
	public void alterarDadosFornecedorComSucesso(){
		String cnpj        = Utils.geraCNPJ();
		String telefone    = Utils.formataTelefone(Utils.geraTelefoneBRA());
		String razaoSocial = "Fornecedor 1";
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.navegarParaPaginaEdicaoFornecedor(razaoSocial);
		pageEditarFornecedor.verificarOrtografiaPageEditarFornecedores();
		pageEditarFornecedor.alterarFornecedor(razaoSocial, cnpj, telefone);
		pageFornecedor.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.validarFornecedorNaListagem(razaoSocial, cnpj, telefone);
	}
	
	@Test
	public void removerFornecedorComSucesso(){
		/*
		 * Incluir fornecedor
		 */
		String cnpj        = Utils.geraCNPJ();
		String telefone    = Utils.formataTelefone(Utils.geraTelefoneBRA());
		String razaoSocial = Utils.geraNomeEmpresa();
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.navegarParaPageInclusaoFornecedores();
		pageIncluirFornecedor.verificarOrtografiaPageIncluirFornecedores();
		pageIncluirFornecedor.incluirFornecedor(razaoSocial, cnpj, telefone);
		pageFornecedor.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.validarFornecedorNaListagem(razaoSocial, cnpj, telefone);
		/*
		 * Remover fornecedor
		 */
		pageFornecedor.removerFornecedor(razaoSocial);
		pageMenu.acessarMenuCadastrosFornecedores();
		pageFornecedor.verificarOrtografiaPageFornecedores();
		pageFornecedor.validarFornecedorRemovido(razaoSocial);
	}
}