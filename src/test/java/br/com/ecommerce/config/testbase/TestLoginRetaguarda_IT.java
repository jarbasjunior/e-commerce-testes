package br.com.ecommerce.config.testbase;

import br.com.ecommerce.config.pagebase.PageLoginRetaguarda;
import br.com.ecommerce.retaguarda.dashboard.PageHomeRetaguarda;

/**
 * 
 * Classe de testes com cenários relacionados a login no retaguarda 
 * @author Jarbas
 * 
 * */
public class TestLoginRetaguarda_IT {

	PageHomeRetaguarda  pageHomeRetaguarda  = new PageHomeRetaguarda();
	PageLoginRetaguarda pageLoginRetaguarda = new PageLoginRetaguarda();
	
	public void loginRetaguardaComSucesso(){
		pageLoginRetaguarda.realizarLoginRetaguarda();
		pageHomeRetaguarda.verificaAutenticidadeUsuario();
	}
}