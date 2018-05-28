package br.com.ecommerce.tests.retaguarda.cadastros;

import org.junit.Test;

import br.com.ecommerce.config.BaseTest;
import br.com.ecommerce.pages.lojavirtual.PageHomeLojaVirtual;
import br.com.ecommerce.pages.retaguarda.cadastros.gruposfiscais.PageGruposFiscais;
import br.com.ecommerce.pages.retaguarda.cadastros.gruposfiscais.PageIncluirGruposFiscais;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.dashboard.PageMenu;
import br.com.ecommerce.util.Utils;

/**
 * 
 * Classe de testes com cenários relacionados ao menu DADOS GERAIS
 * @author Jarbas
 * 
 * */
public class TestCadastrosGruposFiscais extends BaseTest{

	PageMenu                 pageMenu               = new PageMenu();
	PageGruposFiscais        pageGruposFiscais		= new PageGruposFiscais();
	PageHomeRetaguarda       pageHomeRetaguarda     = new PageHomeRetaguarda();
	PageHomeLojaVirtual      pageHomeLojaVirtual    = new PageHomeLojaVirtual();
	PageIncluirGruposFiscais pageIncluirGrupoFiscal = new PageIncluirGruposFiscais();
	
	@Test
	public void cadastrarGrupoFiscalAtivoComSucesso(){
		String grupoFiscal = "Grupo Fiscal Teste " + Utils.geraSigla(3);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.navegarParaPageInclusaoGrupoFiscal();
		pageIncluirGrupoFiscal.verificarOrtografiaPageIncluirGrupoFiscal();
		pageIncluirGrupoFiscal.incluirGrupoFiscalAtivo(grupoFiscal);
		pageGruposFiscais.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.validarGrupoFiscalAtivoNaListagem(grupoFiscal);
	}
	
	@Test
	public void cadastrarGrupoFiscalInativoComSucesso(){
		String grupoFiscal = "Grupo Fiscal Teste " + Utils.geraSigla(3);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.navegarParaPageInclusaoGrupoFiscal();
		pageIncluirGrupoFiscal.verificarOrtografiaPageIncluirGrupoFiscal();
		pageIncluirGrupoFiscal.incluirGrupoFiscalInativo(grupoFiscal);
		pageGruposFiscais.validaMsgSucessoInclusao();
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.validarGrupoFiscalInativoNaListagem(grupoFiscal);
	}
	
	@Test
	public void removerGrupoFiscalDiferenteTributadoENaoTributadoComSucesso(){
		String grupoFiscal = null;
		/*
		 * Verifica se existe um terceiro grupo fiscal, se não houver
		 * será criado um.
		 */
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		if (!pageGruposFiscais.existsTerceiroGrupoFiscal()) {
			grupoFiscal = "Grupo Fiscal Teste " + Utils.geraSigla(3);
			pageGruposFiscais.navegarParaPageInclusaoGrupoFiscal();
			pageIncluirGrupoFiscal.verificarOrtografiaPageIncluirGrupoFiscal();
			pageIncluirGrupoFiscal.incluirGrupoFiscalAtivo(grupoFiscal);
			pageGruposFiscais.validaMsgSucessoInclusao();
			pageMenu.acessarMenuCadastrosGruposFiscais();
			pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
			pageGruposFiscais.validarGrupoFiscalAtivoNaListagem(grupoFiscal);
		}else{
			grupoFiscal = pageGruposFiscais.getGrupoFiscalTeste();
		}
		pageGruposFiscais.removerGrupoFiscal(grupoFiscal);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.validarGrupoFiscalRemovido(grupoFiscal);
	}
	
	@Test
	public void ativarGrupoFiscalTributado(){
		String grupoFiscal = "Grupo Tributado";
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		if (pageGruposFiscais.isAtivo(grupoFiscal)) {
			pageGruposFiscais.desativarGrupoFiscal(grupoFiscal);
		}
		pageGruposFiscais.ativarGrupoFiscal(grupoFiscal);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.verificarGrupoFiscalAtivo(grupoFiscal);
	}
	
	@Test
	public void desativarGrupoFiscalTributado(){
		String grupoFiscal = "Grupo Tributado";
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		if (!pageGruposFiscais.isAtivo(grupoFiscal)) {
			pageGruposFiscais.ativarGrupoFiscal(grupoFiscal);
		}
		pageGruposFiscais.desativarGrupoFiscal(grupoFiscal);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.verificarGrupoFiscalInativo(grupoFiscal);
	}
	
	@Test
	public void ativarGrupoFiscalNaoTributado(){
		String grupoFiscal = "Grupo Não tributado";
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		if (pageGruposFiscais.isAtivo(grupoFiscal)) {
			pageGruposFiscais.desativarGrupoFiscal(grupoFiscal);
		}
		pageGruposFiscais.ativarGrupoFiscal(grupoFiscal);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.verificarGrupoFiscalAtivo(grupoFiscal);
	}
	
	@Test
	public void desativarGrupoFiscalNaoTributado(){
		String grupoFiscal = "Grupo Não tributado";
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		if (!pageGruposFiscais.isAtivo(grupoFiscal)) {
			pageGruposFiscais.ativarGrupoFiscal(grupoFiscal);
		}
		pageGruposFiscais.desativarGrupoFiscal(grupoFiscal);
		pageMenu.acessarMenuCadastrosGruposFiscais();
		pageGruposFiscais.verificarOrtografiaPageGruposFiscais();
		pageGruposFiscais.verificarGrupoFiscalInativo(grupoFiscal);
	}
}