package br.com.ecommerce.config.suite;

import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.Selenium;
import br.com.ecommerce.config.testbase.TestLoginRetaguarda_IT;
import br.com.ecommerce.config.util.Utils;
/**
 * Classe que agrupa todas as classes de teste, funcionando com uma suíte de regressão.
 * @author Jarbas
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

})

public class AllTests {
	
protected static WebDriver driver;
	
	public static Date    fim    			  = null;
	public static Date    inicio 			  = null;
	public static Boolean isAllTestsExecution = false;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		inicio = Calendar.getInstance().getTime();
		isAllTestsExecution = true;
		Selenium.getDriver().navigate().to(Property.URL);
		Selenium.getDriver().manage().window().maximize();
		TestLoginRetaguarda_IT testLogin = new TestLoginRetaguarda_IT();
		testLogin.loginRetaguardaComSucesso();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		Selenium.resetDriver();
		fim = Calendar.getInstance().getTime();
		Utils.calculaTempoDoTest(inicio, fim);
	}

}
