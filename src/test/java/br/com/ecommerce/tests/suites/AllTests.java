package br.com.ecommerce.tests.suites;

import static br.com.ecommerce.config.DriverFactory.getDriver;
import static br.com.ecommerce.config.DriverFactory.resetDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.ecommerce.config.Property;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosCategorias;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosClientes;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosCores;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosFornecedores;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosFuncionarios;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosGruposFiscais;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosMarcas;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosOpCartaoCredito;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosProdutos;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosSlides;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosTamanho;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosTipoConta;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosTipoEntrada;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosTiposPagamento;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosUnidades;
import br.com.ecommerce.tests.retaguarda.contas.TestContasPagar;
import br.com.ecommerce.tests.retaguarda.dadosgerais.TestDadosGerais;
/**
 * Classe que agrupa todas as classes de teste, funcionando com uma suíte de regressão.
 * @author Jarbas
 *
 */
import br.com.ecommerce.tests.retaguarda.estoque.TestEstoqueCompraProdutos;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestDadosGerais.class,
	TestContasPagar.class,
	TestCadastrosCores.class,
	TestCadastrosMarcas.class,
	TestCadastrosSlides.class,
	TestCadastrosTamanho.class,
	TestCadastrosClientes.class,
	TestCadastrosUnidades.class,
	TestCadastrosProdutos.class,
	TestCadastrosTipoConta.class,
	TestCadastrosCategorias.class,
	TestCadastrosTipoEntrada.class,
	TestEstoqueCompraProdutos.class,
	TestCadastrosFornecedores.class,
	TestCadastrosFuncionarios.class,
	TestCadastrosGruposFiscais.class,
	TestCadastrosTiposPagamento.class,
	TestCadastrosOpCartaoCredito.class
})

public class AllTests {
	
	public static Boolean isAllTestsExecution = false;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		isAllTestsExecution = true;
		getDriver().navigate().to(Property.URL_RETAGUARDA);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		resetDriver();
	}

}
