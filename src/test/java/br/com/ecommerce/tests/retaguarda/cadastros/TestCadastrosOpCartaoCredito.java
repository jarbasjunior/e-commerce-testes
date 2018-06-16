package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.retaguarda.cadastros.operadorascartaocredito.PageOpCartoesCredito;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Grupos Fiscais
 * @author Jarbas
 * 
 * */
public class TestCadastrosOpCartaoCredito extends BaseTest{

	PageMenu             pageMenu             = new PageMenu();
	PageOpCartoesCredito pageOpCartoesCredito = new PageOpCartoesCredito();
	
	
	@Test
	public void ativarOperadoraMasterCard(){
		String operadora = "MasterCard";
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		if (pageOpCartoesCredito.isAtiva(operadora)) {
			pageOpCartoesCredito.desativarOperadora(operadora);
		}
		pageOpCartoesCredito.ativarOperadora(operadora);
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		pageOpCartoesCredito.verificarOperadoraCartaoAtivada(operadora);
	}
	
	@Test
	public void desativarOperadoraMasterCard(){
		String operadora = "MasterCard";
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		if (!pageOpCartoesCredito.isAtiva(operadora)) {
			pageOpCartoesCredito.ativarOperadora(operadora);
		}
		pageOpCartoesCredito.desativarOperadora(operadora);
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		pageOpCartoesCredito.verificarOperadoraCartaoDesativada(operadora);
	}
	
	@Test
	public void ativarOperadoraVisa(){
		String operadora = "Visa";
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		if (pageOpCartoesCredito.isAtiva(operadora)) {
			pageOpCartoesCredito.desativarOperadora(operadora);
		}
		pageOpCartoesCredito.ativarOperadora(operadora);
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		pageOpCartoesCredito.verificarOperadoraCartaoAtivada(operadora);
	}
	
	@Test
	public void desativarOperdoraVisa(){
		String operadora = "Visa";
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		if (!pageOpCartoesCredito.isAtiva(operadora)) {
			pageOpCartoesCredito.ativarOperadora(operadora);
		}
		pageOpCartoesCredito.desativarOperadora(operadora);
		pageMenu.acessarMenuCadastrosOpCartaoCredito();
		pageOpCartoesCredito.verificarOrtografiaPageOpCartoesCredito();
		pageOpCartoesCredito.verificarOperadoraCartaoDesativada(operadora);
	}
}