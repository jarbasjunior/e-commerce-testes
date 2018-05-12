package br.com.ecommerce.config.testbase;

import static br.com.ecommerce.config.setup.DriverFactory.getDriver;
import static br.com.ecommerce.config.setup.DriverFactory.resetDriver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import br.com.ecommerce.config.basepage.PageLoginRetaguarda;
import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.suite.AllTests;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
public class BaseTest {
	
	PageHomeRetaguarda  pageHomeRetaguarda  = new PageHomeRetaguarda();
	PageLoginRetaguarda pageLoginRetaguarda = new PageLoginRetaguarda();
	
	@Rule
	public TestName nameTest = new TestName();
	
	@Before
	public void before(){
		Log.msgInicioTeste(nameTest.getMethodName());
		pageLoginRetaguarda.realizarLoginRetaguarda();
		pageHomeRetaguarda.verificaAutenticidadeUsuario();
	}

	@After
	public void after(){
		Log.msgFimTeste(nameTest.getMethodName());
	}
	
	@BeforeClass
	public static void beforeClass(){
		if(!AllTests.isAllTestsExecution){
			getDriver().navigate().to(Property.URL_RETAGUARDA);
		}
	}
	
	@AfterClass
	public static void afterClass(){
		if(!AllTests.isAllTestsExecution){
			resetDriver();
		}
	}
}
