package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.clientes.PageCliente;
import br.com.ecommerce.pages.retaguarda.cadastros.clientes.PageEditarCliente;
import br.com.ecommerce.pages.retaguarda.cadastros.clientes.PageIncluirCliente;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosClientes extends BaseTest{

	PageMenu             pageMenu             = new PageMenu();
	PageCliente  		 pageCliente		  = new PageCliente();
	PageEditarCliente    pageEditarCliente    = new PageEditarCliente();
	PageIncluirCliente   pageIncluirCliente   = new PageIncluirCliente();
	PageHomeRetaguarda   pageHomeRetaguarda   = new PageHomeRetaguarda();
	PageHomeLojaVirtual  pageHomeLojaVirtual  = new PageHomeLojaVirtual();
	
	@Test
	public void cadastrarClienteComSucesso(){
		String cpf       = Utils.geraCPFSemFormato();
		String nome      = Utils.geraNome();
		String email     = Utils.geraEmail();
		String senha     = Utils.geraSenha();
		String telefone  = Utils.geraTelefoneBRA();
		String sobrenome = Utils.geraSobrenome();
		pageMenu.acessarMenuCadastrosClientes();
		pageCliente.verificarOrtografiaPageClientes();
		pageCliente.navegarParaPageInclusaoClientes();
		pageIncluirCliente.verificarOrtografiaPageIncluirClientes();
		pageIncluirCliente.incluirCliente(nome, sobrenome, cpf, email, senha, senha, telefone);
		pageCliente.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosClientes();
		pageCliente.verificarOrtografiaPageClientes();
		pageCliente.validarClienteNaListagem(nome, cpf, email, telefone);
	}
	
	@Test
	public void cadastrarClienteSemSucessoComEmailInválido(){
		String cpf       = Utils.geraCPFSemFormato();
		String nome      = Utils.geraNome();
		String email     = Utils.geraSite();
		String senha     = Utils.geraSenha();
		String telefone  = Utils.geraTelefoneBRA();
		String sobrenome = Utils.geraSobrenome();
		pageMenu.acessarMenuCadastrosClientes();
		pageCliente.verificarOrtografiaPageClientes();
		pageCliente.navegarParaPageInclusaoClientes();
		pageIncluirCliente.verificarOrtografiaPageIncluirClientes();
		pageIncluirCliente.incluirCliente(nome, sobrenome, cpf, email, senha, senha, telefone);
		pageIncluirCliente.validaMsgInclusaoSemSucesso();
	}
	
	@Test
	public void alterarDadosClienteComSucesso(){
		String cpf       = Utils.geraCPFSemFormato();
		String nome      = "Fulano";
		String email     = Utils.geraEmail();
		String senha     = Utils.geraSenha();
		String telefone  = Utils.geraTelefoneBRA();
		String sobrenome = Utils.geraSobrenome();
		pageMenu.acessarMenuCadastrosClientes();
		pageCliente.verificarOrtografiaPageClientes();
		pageCliente.alterarCliente(nome);
		pageEditarCliente.alterarCliente(nome, sobrenome, cpf, email, senha, senha, telefone);
		pageCliente.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosClientes();
		pageCliente.verificarOrtografiaPageClientes();
		pageCliente.validarClienteNaListagem(nome, cpf, email, telefone);
	}
}