package br.com.ecommerce.retaguarda.testes.dadosgerais;

import org.junit.Test;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.testbase.BaseTestCase;
import br.com.ecommerce.config.util.Utils;
import br.com.ecommerce.lojavirtual.pages.PageHomeLojaVirtual;
import br.com.ecommerce.retaguarda.pages.dadosgerais.PageConfiguracoes;
import br.com.ecommerce.retaguarda.pages.dadosgerais.PageEditarConfiguracoes;
import br.com.ecommerce.retaguarda.pages.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.retaguarda.pages.dashboard.PageMenu;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestDadosGerais_IT extends BaseTestCase {

	PageMenu                pageMenu                = new PageMenu();
	PageConfiguracoes       pageConfiguracoes       = new PageConfiguracoes();
	PageHomeRetaguarda      pageHomeRetaguarda      = new PageHomeRetaguarda();
	PageHomeLojaVirtual     pageHomeLojaVirtual     = new PageHomeLojaVirtual();
	PageEditarConfiguracoes pageEditarConfiguracoes = new PageEditarConfiguracoes();
	
	@Test
	public void alterarConfiguracoesDaCompanhiaComSucesso(){
		String nome     = Utils.geraNomeEmpresa();
		String cnpj     = Utils.geraCNPJ();
		String email    = Utils.geraEmail();
		String telefone = Utils.geraTelefone();
		String endereco = Utils.geraEndereco();
		pageMenu.acessarMenuDadosGeraisConfiguracoes();
		pageConfiguracoes.navegarParaEdicaoDeConfiguracoes();
		pageEditarConfiguracoes.alterarDadosDaCompanhia(nome, email, telefone, endereco, cnpj);
		pageConfiguracoes.validarMsgFeedbackSucesso();
		pageMenu.acessarMenuDadosGeraisConfiguracoes();
		pageConfiguracoes.conferirDadosDaCompanhia(nome, email, telefone, endereco, cnpj);
		Selenium.getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirDadosLojaVirtual(telefone, endereco, email);
	}
}