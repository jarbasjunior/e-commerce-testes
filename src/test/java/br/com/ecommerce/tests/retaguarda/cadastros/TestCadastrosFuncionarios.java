package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.funcionarios.PageEditarFuncionario;
import br.com.ecommerce.pages.retaguarda.cadastros.funcionarios.PageFuncionario;
import br.com.ecommerce.pages.retaguarda.cadastros.funcionarios.PageIncluirFuncionario;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Funcionários
 * @author Jarbas
 * 
 * */
public class TestCadastrosFuncionarios extends BaseTest{

	PageMenu               pageMenu               = new PageMenu();
	PageFuncionario 	   pageFuncionario		  = new PageFuncionario();
	PageEditarFuncionario  pageEditarFuncionario  = new PageEditarFuncionario();
	PageIncluirFuncionario pageIncluirFuncionario = new PageIncluirFuncionario();
	
	@Test
	public void cadastrarFuncionarioComSucesso(){
		String cpf      = Utils.geraCPFSemFormato();
		String nome     = Utils.geraNome() + " Teste";
		String email    = Utils.geraEmail();
		String telefone = Utils.formataTelefone(Utils.geraTelefoneBRA());
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		pageFuncionario.navegarParaPageInclusaoFuncionarios();
		pageIncluirFuncionario.verificarOrtografiaPageIncluirFuncionarios();
		pageIncluirFuncionario.incluirFuncionario(nome, cpf, email, telefone);
		pageFuncionario.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		pageFuncionario.validarFuncionarioNaListagem(nome, cpf, email);
	}
	
	@Test
	public void alterarDadosFuncionarioComSucesso(){
		String cpf      = Utils.geraCPFSemFormato();
		String nome     = Utils.geraNome() + " Teste";
		String novoNome = Utils.geraNome() + " Teste";
		String email    = Utils.geraEmail();
		String telefone = Utils.formataTelefone(Utils.geraTelefoneBRA());
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		/*
		 * Inclui funcionário caso não exista, para ser alterado posteriormente
		 */
		if (!pageFuncionario.existsFuncionarioTeste()) {
			pageFuncionario.navegarParaPageInclusaoFuncionarios();
			pageIncluirFuncionario.verificarOrtografiaPageIncluirFuncionarios();
			pageIncluirFuncionario.incluirFuncionario(nome, cpf, email, telefone);
			pageFuncionario.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosFuncionarios();
			pageFuncionario.verificarOrtografiaPageFuncionarios();
			pageFuncionario.validarFuncionarioNaListagem(nome, cpf, email);
		}else
			nome = pageFuncionario.getFuncionarioTeste();
		
		pageFuncionario.navegarParaPaginaEdicaoFuncionario(nome);
		pageEditarFuncionario.verificarOrtografiaPageEditarFuncionarios();
		pageEditarFuncionario.alterarFuncionario(novoNome, cpf, email, telefone);
		pageFuncionario.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		pageFuncionario.validarFuncionarioNaListagem(novoNome, cpf, email);
	}
	
	@Test
	public void removerFuncionarioComSucesso(){
		String cpf      = Utils.geraCPFSemFormato();
		String nome     = Utils.geraNome() + " Teste";
		String email    = Utils.geraEmail();
		String telefone = Utils.formataTelefone(Utils.geraTelefoneBRA());
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		/*
		 * Inclui funcionário caso não exista, para ser excluído posteriormente
		 */
		if (!pageFuncionario.existsFuncionarioTeste()) {
			pageFuncionario.navegarParaPageInclusaoFuncionarios();
			pageIncluirFuncionario.verificarOrtografiaPageIncluirFuncionarios();
			pageIncluirFuncionario.incluirFuncionario(nome, cpf, email, telefone);
			pageFuncionario.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosFuncionarios();
			pageFuncionario.verificarOrtografiaPageFuncionarios();
			pageFuncionario.validarFuncionarioNaListagem(nome, cpf, email);
		}else
			nome = pageFuncionario.getFuncionarioTeste();
		/*
		 * Remover funcionário
		 */
		pageFuncionario.removerFuncionario(nome);
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		pageFuncionario.validarFuncionarioRemovido(nome);
	}
}