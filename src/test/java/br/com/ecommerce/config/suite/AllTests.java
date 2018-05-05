package br.com.ecommerce.config.suite;

import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Utils;
import br.com.ecommerce.retaguarda.testes.cadastros.TestCadastrosCategorias_IT;
import br.com.ecommerce.retaguarda.testes.dadosgerais.TestDadosGerais_IT;
/**
 * Classe que agrupa todas as classes de teste, funcionando com uma suíte de regressão.
 * @author Jarbas
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestDadosGerais_IT.class,
	TestCadastrosCategorias_IT.class
})

public class AllTests {
	
protected static WebDriver driver;
	
	public static Boolean isAllTestsExecution = false;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		isAllTestsExecution = true;
		DriverFactory.getDriver().navigate().to(Property.URL_RETAGUARDA);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		DriverFactory.resetDriver();
	}

}
