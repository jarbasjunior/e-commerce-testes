package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.funcionarios.PageEditarFuncionario;
import br.com.ecommerce.pages.retaguarda.cadastros.funcionarios.PageFuncionario;
import br.com.ecommerce.pages.retaguarda.cadastros.funcionarios.PageIncluirFuncionario;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosFuncionarios extends BaseTest{

	PageMenu               pageMenu               = new PageMenu();
	PageFuncionario 	   pageFuncionario		  = new PageFuncionario();
	PageHomeRetaguarda     pageHomeRetaguarda     = new PageHomeRetaguarda();
	PageHomeLojaVirtual    pageHomeLojaVirtual    = new PageHomeLojaVirtual();
	PageEditarFuncionario  pageEditarFuncionario  = new PageEditarFuncionario();
	PageIncluirFuncionario pageIncluirFuncionario = new PageIncluirFuncionario();
	
	@Test
	public void cadastrarFuncionarioComSucesso(){
		String cpf      = Utils.geraCPFSemFormato();
		String nome     = Utils.geraNome();
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
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		String cpf      = Utils.geraCPFSemFormato();
		String nome     = pageFuncionario.getPrimeiroNomeLista();
		String email    = Utils.geraEmail();
		String telefone = Utils.formataTelefone(Utils.geraTelefoneBRA());
		pageFuncionario.navegarParaPaginaEdicaoFuncionario(nome);
		pageEditarFuncionario.verificarOrtografiaPageEditarFuncionarios();
		pageEditarFuncionario.alterarFuncionario(nome, cpf, email, telefone);
		pageFuncionario.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		pageFuncionario.validarFuncionarioNaListagem(nome, cpf, email);
	}
	
	@Test
	public void removerFuncionarioComSucesso(){
		/*
		 * Incluir funcionário
		 */
		String cpf      = Utils.geraCPFSemFormato();
		String nome     = Utils.geraNome();
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
		/*
		 * Remover funcionário
		 */
		pageFuncionario.removerFuncionario(nome);
		pageMenu.acessarMenuCadastrosFuncionarios();
		pageFuncionario.verificarOrtografiaPageFuncionarios();
		pageFuncionario.validarFuncionarioRemovido(nome);
	}
}