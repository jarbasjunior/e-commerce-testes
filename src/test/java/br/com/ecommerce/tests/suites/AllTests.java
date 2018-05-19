package br.com.ecommerce.tests.suites;

import static br.com.ecommerce.config.DriverFactory.getDriver;
import static br.com.ecommerce.config.DriverFactory.resetDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.ecommerce.config.Property;
import br.com.ecommerce.tests.retaguarda.cadastros.TestCadastrosCategorias;
import br.com.ecommerce.tests.retaguarda.dadosgerais.TestDadosGerais;
/**
 * Classe que agrupa todas as classes de teste, funcionando com uma suíte de regressão.
 * @author Jarbas
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestDadosGerais.class,
	TestCadastrosCategorias.class
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
