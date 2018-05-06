package br.com.ecommerce.config.basepage;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import br.com.ecommerce.config.setup.Property;
import br.com.ecommerce.config.setup.DriverFactory;
import br.com.ecommerce.config.util.Log;
import br.com.ecommerce.config.util.Utils;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;

public abstract class BasePage<T> {

	private static final PageHomeRetaguarda  pageHomeRetagurada  = new PageHomeRetaguarda();
	private static final PageLoginRetaguarda pageLoginRetagurada = new PageLoginRetaguarda(); 
	private static final String URL_RETAGURADA                   = Property.URL_RETAGUARDA;
	private static final int    LOAD_TIMEOUT                     = 14;
	private static final int    INTERVALO_VERIFICACAO            = 2;
	private String windowHandleJanelaInicial;
	private static final Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver())
				    								.withTimeout( LOAD_TIMEOUT         , TimeUnit.SECONDS) // Tempo limite (segundos)
				    								.pollingEvery(INTERVALO_VERIFICACAO, TimeUnit.SECONDS) // Intervalo de tempo de cada busca (segundos) 
				    								.ignoring(NoSuchElementException.class);  

	public BasePage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	public T abrirPagina(Class<T> clazz) {
		T pagina = PageFactory.initElements(DriverFactory.getDriver(), clazz);
		DriverFactory.getDriver().navigate().to(URL_RETAGURADA);
		return pagina;
	}

	public void preencherCampo(WebElement element, String value) {
		try {
			aguardarElementoVisivel(element);
			element.clear();
			element.sendKeys(value);
		} catch (WebDriverException e) {
			erroPreenchimento(element, value);
		}
	}
	
	public void aguardarElementoVisivel(WebElement element) {
		try {
			final WebElement e = element;
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return e;
				}
			});
			WebDriverWait driverWait = new WebDriverWait(DriverFactory.getDriver(),
			LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			erroEspera(element);
		}
	}
	
	public void click(WebElement element) {
		try {
			aguardarElementoVisivel(element);
			element.click();
		} catch (WebDriverException e) {
			erroClick(element);
		}
	}
	
	public String getTextValueAtributo(WebElement element) {
		try {
			return element.getAttribute("value");
		} catch (Exception e) {
			erroGetTextAtributo(element);
			return null;
		}
	}
	
	public String getTextElement(WebElement element) {
		try {
			String s = element.getText(); 
			return s;
		} catch (Exception e) {
			erroGetText(element);
			return null;
		}
	}

	public void confirmarAlerta() {
		try {
			Alert alert = DriverFactory.getDriver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			erroConfirmaAlerta();
		}
	}

	public void esperarElementoDesaparecer(By elemento, int qtdSegundos){
		try {
			int segundosEspera      = 0;
			int segundosRegressivos = qtdSegundos;
			while (isVisibility(elemento) || segundosEspera == qtdSegundos) {
				Log.info("Aguardando mensagem de espera desaparecer");
				Utils.wait(1000);
				segundosEspera++;
				Log.info("Tempo de espera restante ["+segundosRegressivos+"]");
				segundosRegressivos--;
				if (!isVisibility(elemento) || segundosEspera == qtdSegundos) {
					Log.info("Mensagem de espera removida");
					break;
				}
			}
		} catch (Exception e) {
			Log.info("Erro");
			Assert.fail("Error!");
		}
	}

	public boolean isVisibility(By locator) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(locator);
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void selecionarValorComboTexto(WebElement element, String textVisible){
		try{
			new Select(element).selectByVisibleText(textVisible);
		}catch(NoSuchElementException e){
			erroSelecaoCombo(element, textVisible);
			Utils.takeScreenshot("SelecionarComboTexto");
		}
	}
	
	public void selecionarValorComboValue(WebElement element, String valueVisible){
		try{
			new Select(element).selectByValue(valueVisible);
		}catch(NoSuchElementException e){
			erroSelecaoCombo(element, valueVisible);
			Utils.takeScreenshot("SelecionarComboValue");
		}
	}
	
	public void pageDown(WebElement e){
		for (int i = 0; i < 5; i++) {
			e.sendKeys(Keys.PAGE_DOWN);
		}
		Utils.wait(500);
	}
	
	public void pageUp(WebElement e){
		for (int i = 0; i < 5; i++) {
			e.sendKeys(Keys.PAGE_UP);
		}
		Utils.wait(500);
	}
	
	public void tab(WebElement e){
		e.sendKeys(Keys.TAB);
	}
	
	public boolean isElementPresent(WebElement element){
        try{
            int counter = 0;
            while(counter < LOAD_TIMEOUT){
                Thread.sleep(INTERVALO_VERIFICACAO);
                counter++;
                try{
                    if(element.isDisplayed()){
                        return true;
                    }else{
                        continue;
                    }
                }catch(Exception e){
                    continue;
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
	
	public void clicarBotaoDireito(WebElement elemento) {
		Actions action = new Actions(DriverFactory.getDriver());
		action.contextClick(elemento).build().perform();
	}
	
	public void doubleclick(WebElement elemento) {
		Actions action = new Actions(DriverFactory.getDriver());
		action.doubleClick().build().perform();
	}

	public void moverCursorPara(WebElement elemento) {
		Actions action = new Actions(DriverFactory.getDriver());
		aguardarElementoVisivel(elemento);
		action.moveToElement(elemento).build().perform();
	}
	
	public boolean existText(WebElement elemento, String texto) {
		aguardarElementoVisivel(elemento);
		return elemento.getText().contains(texto);
	}

	public void voltarPagina() {
		DriverFactory.getDriver().navigate().back();
	}

	public void alternarJanela() {
		windowHandleJanelaInicial = DriverFactory.getDriver().getWindowHandle();
		Set<String> windowHandles = DriverFactory.getDriver().getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				DriverFactory.getDriver().switchTo().window(windowHandle);
			}
		}
		setWindowHandleJanelaInicial(windowHandleJanelaInicial);
	}
	
	public void retonarJanelaOriginal() {
		DriverFactory.getDriver().switchTo().window(getWindowHandleJanelaInicial());
	}

	public String getWindowHandleJanelaInicial() {
		return windowHandleJanelaInicial;
	}

	public void setWindowHandleJanelaInicial(String windowHandleJanelaInicial) {
		this.windowHandleJanelaInicial = windowHandleJanelaInicial;
	}

	public void selecionarFrameID(int idFrame) {
		DriverFactory.getDriver().switchTo().frame(idFrame);
	}
	
	public void selecionarFrameNameOrID(String stringFrame) {
		DriverFactory.getDriver().switchTo().frame(stringFrame);
	}
	
	public void selecionarFrameWebElement(WebElement element) {
		DriverFactory.getDriver().switchTo().frame(element);
	}
	
	public void retornarFramePai() {
		DriverFactory.getDriver().switchTo().defaultContent();
	}


	public WebElement getElement(By by) {
		return DriverFactory.getDriver().findElement(by);
	}
	
	public void erroPreenchimento(WebElement element, String value) {
		erro();
		Log.erro(element.toString().substring(45, element.toString().length()-2)+"]. não encontrado, valor ["+value+"] não pôde ser preenchido.");
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
		Assert.fail(element.toString().substring(45, element.toString().length()-2)+"]. não encontrado, valor ["+value+"] não pôde ser preenchido.");
	}
	public void erroEspera(WebElement element) {
		erro();
		Utils.takeScreenshot("erroEspera");
		Log.erro("Tempo excedido ("+LOAD_TIMEOUT+"s) para aguardar elemento "+element.toString().substring(45, element.toString().length()-1)+"");
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
		Assert.fail("Tempo excedido ("+LOAD_TIMEOUT+"s) para aguardar elemento "+element.toString().substring(45, element.toString().length()-1)+"");
	}
	
	public void erroClick(WebElement element) {
		erro();
		Log.erro("Erro ao clicar no elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
		Assert.fail("Erro ao clicar no elemento "+element.toString().substring(45, element.toString().length()-2)+"].");
	}
	
	public void erroGetTextAtributo(WebElement element) {
		erro();
		Utils.takeScreenshot("erroGetTextAtributo");
		Log.erro("Erro ao buscar texto de atributo do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
		Assert.fail("Erro ao buscar texto de atributo do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
	}
	
	public void erroGetText(WebElement element) {
		erro();
		Utils.takeScreenshot("erroGetText");
		Log.erro("Erro ao buscar texto do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
		Assert.fail("Erro ao buscar texto do elemento: "+element.toString().substring(45, element.toString().length()-2)+"].");
	}
	
	public void erroConfirmaAlerta() {
		erro();
		Utils.takeScreenshot("erroAlerta");
		Log.erro("Erro ao realizar a confirmacao do Alerta");
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
		Assert.fail("Erro ao realizar a confirmacao do Alerta");
	}
	
	public void erroSelecaoCombo(WebElement element, String valor) {
		erro();
		Utils.takeScreenshot("erroSelectCombo");
		Log.erro("Erro ao selecionar elemento do combo: "+element.toString().substring(45, element.toString().length()-2)+"], com o valor: "+valor);
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
		Assert.fail("Erro ao selecionar elemento do combo: "+element.toString().substring(45, element.toString().length()-2)+"], com o valor: "+valor);
	}
	
	public void erro() {
		Log.erro("E R R O ...");
		Log.info("    ||");
		Log.info("   \\  /");
		Log.info("    **");
	}
}