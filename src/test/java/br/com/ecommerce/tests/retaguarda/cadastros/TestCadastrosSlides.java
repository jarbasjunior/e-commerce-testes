package br.com.ecommerce.tests.retaguarda.cadastros;

import static br.com.ecommerce.config.DriverFactory.getDriver;
import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.config.Property;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.slides.PageEditarSlide;
import br.com.ecommerce.pages.retaguarda.cadastros.slides.PageIncluirSlide;
import br.com.ecommerce.pages.retaguarda.cadastros.slides.PageSlide;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao Cadastros >> Funcionários
 * @author Jarbas
 * 
 * */
public class TestCadastrosSlides extends BaseTest{

	PageMenu            pageMenu            = new PageMenu();
	PageSlide           pageSlide		    = new PageSlide();
	PageEditarSlide     pageEditarSlide     = new PageEditarSlide();
	PageIncluirSlide    pageIncluirSlide    = new PageIncluirSlide();
	PageHomeLojaVirtual pageHomeLojaVirtual = new PageHomeLojaVirtual();
	
	@Test
	public void cadastrarSlideComSucesso(){
		String titulo      = "OSTENTAÇÃO " +Utils.geraSigla(3);
		String descricao   = "Estamos ostentando ofertas para você";
		String labelBotao  = "QUERO OSTENTAR";
		String urlBotao    = "/products";
		String imagem      = Utils.geraNumeroEntre(1, 4)+".jpg";
		pageMenu.acessarMenuCadastrosSlides();
		pageSlide.verificarOrtografiaPageSlides();
		pageSlide.navegarParaPageInclusaoSlides();
		pageIncluirSlide.verificarOrtografiaPageIncluirFuncionarios();
		pageIncluirSlide.incluirSlide(titulo, descricao, labelBotao, urlBotao, imagem);
		pageSlide.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosSlides();
		pageSlide.verificarOrtografiaPageSlides();
		pageSlide.validarSlideNaListagem(titulo, descricao, labelBotao, urlBotao);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		boolean achouSlide = pageHomeLojaVirtual.validarSlideNaLojaVirtual(titulo, descricao, labelBotao, urlBotao);
		Utils.assertTrue("Slide não encontrado ou dados incorretos na home page da loja virtual", achouSlide);
		Log.info("Slide validado na home page da loja virtual");
	}
	
	@Test
	public void alterarDadosFuncionarioComSucesso(){
		String titulo     = "OSTENTAÇÃO " +Utils.geraSigla(3);
		String novoTitulo = "OSTENTAÇÃO " +Utils.geraSigla(3);
		String descricao  = "Estamos ostentando ofertas para você";
		String labelBotao = "QUERO OSTENTAR";
		String urlBotao   = "/products";
		String imagem     = Utils.geraNumeroEntre(1, 4)+".jpg";
		pageMenu.acessarMenuCadastrosSlides();
		pageSlide.verificarOrtografiaPageSlides();
		/*
		 * Verifica se há slide ostentação, caso não exista 
		 * será criado um para ser excluído posteriormente
		 */
		if (!pageSlide.existsSlideOstentacao()) {
			pageSlide.navegarParaPageInclusaoSlides();
			pageIncluirSlide.verificarOrtografiaPageIncluirFuncionarios();
			pageIncluirSlide.incluirSlide(titulo, descricao, labelBotao, urlBotao, imagem);
			pageSlide.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosSlides();
			pageSlide.verificarOrtografiaPageSlides();
			pageSlide.validarSlideNaListagem(titulo, descricao, labelBotao, urlBotao);
			getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
			pageHomeLojaVirtual.validarSlideNaLojaVirtual(titulo, descricao, labelBotao, urlBotao);
			getDriver().navigate().to(Property.URL_RETAGUARDA);
			pageMenu.acessarMenuCadastrosSlides();
			pageSlide.verificarOrtografiaPageSlides();
		}else
			titulo = pageSlide.getSlideOstentacao();
		/*
		 * Alterar slide
		 */
		pageSlide.navegarParaPaginaEdicaoSlides(titulo);
		pageEditarSlide.verificarOrtografiaPageEditarSlide();
		pageEditarSlide.alterarSlide(novoTitulo);
		pageSlide.validaMsgSucessoAlteracao();
		pageMenu.acessarMenuCadastrosSlides();
		pageSlide.verificarOrtografiaPageSlides();
		pageSlide.validarSlideNaListagem(novoTitulo, descricao, labelBotao, urlBotao);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		boolean achouSlide = pageHomeLojaVirtual.validarSlideNaLojaVirtual(novoTitulo, descricao, labelBotao, urlBotao);
		Utils.assertTrue("Slide não encontrado ou seus dados foram alterados incorretamente na home page da loja virtual", achouSlide);
		Log.info("Slide validado na home page da loja virtual");
	}
	
	@Test
	public void removerSlideComSucesso(){
		String titulo     = "OSTENTAÇÃO " +Utils.geraSigla(3);
		String descricao  = "Estamos ostentando ofertas para você";
		String labelBotao = "QUERO OSTENTAR";
		String urlBotao   = "/products";
		String imagem     = Utils.geraNumeroEntre(1, 4)+".jpg";
		pageMenu.acessarMenuCadastrosSlides();
		pageSlide.verificarOrtografiaPageSlides();
		/*
		 * Verifica se há slide ostentação, caso não exista 
		 * será criado um para ser excluído posteriormente
		 */
		if (!pageSlide.existsSlideOstentacao()) {
			pageSlide.navegarParaPageInclusaoSlides();
			pageIncluirSlide.verificarOrtografiaPageIncluirFuncionarios();
			pageIncluirSlide.incluirSlide(titulo, descricao, labelBotao, urlBotao, imagem);
			pageSlide.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosSlides();
			pageSlide.verificarOrtografiaPageSlides();
			pageSlide.validarSlideNaListagem(titulo, descricao, labelBotao, urlBotao);
			getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
			pageHomeLojaVirtual.validarSlideNaLojaVirtual(titulo, descricao, labelBotao, urlBotao);
			getDriver().navigate().to(Property.URL_RETAGUARDA);
			pageMenu.acessarMenuCadastrosSlides();
			pageSlide.verificarOrtografiaPageSlides();
		}else
			titulo = pageSlide.getSlideOstentacao();
		/*
		 * Remover slide
		 */
		pageSlide.removerSlide(titulo);
		pageMenu.acessarMenuCadastrosSlides();
		pageSlide.verificarOrtografiaPageSlides();
		pageSlide.validarSlideRemovidoRetaguarda(titulo);
		getDriver().navigate().to(Property.URL_LOJA_VIRTUAL);
		boolean achouSlide = pageHomeLojaVirtual.validarSlideRemovidoNaLojaVirtual(titulo);
		Utils.assertFalse("Slide não foi removido da loja virtual", achouSlide);
	}
}