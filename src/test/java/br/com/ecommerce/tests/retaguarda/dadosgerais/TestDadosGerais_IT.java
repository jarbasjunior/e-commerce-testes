package br.com.ecommerce.tests.retaguarda.dadosgerais;

import static br.com.ecommerce.config.DriverFactory.getDriver;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.dadosgerais.PageConfiguracoes;
import br.com.ecommerce.pages.retaguarda.dadosgerais.PageEditarConfiguracoes;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestDadosGerais_IT extends BaseTest {

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
		pageConfiguracoes.conferirOrtografiaDeCamposTelaConfigurcoes();
		pageConfiguracoes.navegarParaEdicaoDeConfiguracoes();
		pageEditarConfiguracoes.alterarDadosDaCompanhia(nome, email, telefone, endereco, cnpj);
		pageConfiguracoes.validarMsgFeedbackSucesso();
		pageMenu.acessarMenuDadosGeraisConfiguracoes();
		pageConfiguracoes.conferirAlteracaoDaCompanhia(nome, email, telefone, endereco, cnpj);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		pageHomeLojaVirtual.conferirDadosCompanhiaNaLojaVirtual(telefone, endereco, email);
		pageHomeRetaguarda.sairDoRetaguarda();
	}
}