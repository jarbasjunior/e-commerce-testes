package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.cores.PageCores;
import br.com.ecommerce.pages.retaguarda.cadastros.cores.PageIncluirCores;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Log;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosCores extends BaseTest{

	PageMenu             pageMenu             = new PageMenu();
	PageCores			 pageCores			  = new PageCores();
	PageIncluirCores     pageIncluirCores     = new PageIncluirCores();
	PageHomeRetaguarda   pageHomeRetaguarda   = new PageHomeRetaguarda();
	PageHomeLojaVirtual  pageHomeLojaVirtual  = new PageHomeLojaVirtual();
	
	@Test
	public void cadastrarCorComSucesso(){
		String cor = "Azul";
		String corHexa = Utils.getColorHexa(cor);
		pageMenu.acessarMenuCadastrosCores();
		pageCores.verificarOrtografiaPageCores();
		if (!pageCores.existsColor(cor)) {
			pageCores.navegarParaPageInclusaoCores();
			pageIncluirCores.verificarOrtografiaPageIncluirCores();
			pageIncluirCores.incluirCor(cor, corHexa);
			pageIncluirCores.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosCores();
			pageCores.verificarOrtografiaPageCores();
			pageCores.validarCorInserida(cor);
		}else
			Log.info("Cor ["+cor+"] já cadastrada no sistema");
	}
	
	@Test
	public void removerCorComSucesso(){
		String cor = "Azul";
		String corHexa = Utils.getColorHexa(cor);
		pageMenu.acessarMenuCadastrosCores();
		pageCores.verificarOrtografiaPageCores();
		if (!pageCores.existsColor(cor)) {
			pageCores.navegarParaPageInclusaoCores();
			pageIncluirCores.verificarOrtografiaPageIncluirCores();
			pageIncluirCores.incluirCor(cor, corHexa);
			pageIncluirCores.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosCores();
			pageCores.verificarOrtografiaPageCores();
			pageCores.validarCorInserida(cor);
		}
		pageCores.removerCor(cor);
		pageMenu.acessarMenuCadastrosCores();
		pageCores.verificarOrtografiaPageCores();
		pageCores.validarCorRemovida(cor);
	}
	
	@Test
	public void adicionarCorExistenteSemSucesso(){
		String cor = "Preto";
		String corHexa = Utils.getColorHexa(cor);
		pageMenu.acessarMenuCadastrosCores();
		pageCores.verificarOrtografiaPageCores();
		if (!pageCores.existsColor(cor)) {
			pageCores.navegarParaPageInclusaoCores();
			pageIncluirCores.verificarOrtografiaPageIncluirCores();
			pageIncluirCores.incluirCor(cor, corHexa);
			pageIncluirCores.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosCores();
			pageCores.verificarOrtografiaPageCores();
			pageCores.validarCorInserida(cor);
		}
		pageMenu.acessarMenuCadastrosCores();
		pageCores.verificarOrtografiaPageCores();
		pageCores.navegarParaPageInclusaoCores();
		pageIncluirCores.verificarOrtografiaPageIncluirCores();
		pageIncluirCores.incluirCor(cor, corHexa);
		Utils.assertFalse("Mensagem exibe sucesso em novo cadastro de cor, a qual já foi inserida anteriormente", pageIncluirCores.isMensagemSucessoInclusao());
		pageMenu.acessarMenuCadastrosCores();
		pageCores.verificarOrtografiaPageCores();
		Utils.assertFalse("Cor que já existe foi inserida novamente", pageCores.existsColor(cor));
	}
}