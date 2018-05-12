package br.com.ecommerce.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.Assert;

import com.github.javafaker.Faker;

import br.com.ecommerce.config.Property;
import br.com.ecommerce.pages.retaguarda.dashboard.PageHomeRetaguarda;
import br.com.ecommerce.pages.retaguarda.login.PageLoginRetaguarda;

/**
 * Classe com m�todos de apoio, que otimizam a codifica��o das classes de
 * p�gina.
 * 
 * @author jarbas.junior
 *
 */
public abstract class Utils {

	private static boolean 			           isError 			   = false;
	private static final   PageHomeRetaguarda  pageHomeRetagurada  = new PageHomeRetaguarda();
	private static final   PageLoginRetaguarda pageLoginRetagurada = new PageLoginRetaguarda();

	public static void wait(final int iTimeInMillis) {
		try {
			Thread.sleep(iTimeInMillis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void assertEquals(Object atual, Object esperado) {
		try {
			isError = !esperado.toString().equals(atual.toString());
			Assert.assertEquals(esperado, atual);
		} catch (Exception e) {
			assertFail("Erro encontrado: Esperado ["+esperado+"], mas retornou ["+atual+"]");
		}finally{
			if (isError) {
				Log.erro("E R R O . . .");
				Log.info("    ||");
				Log.info("   \\  /");
				Log.info("    **");
				Log.erro("Esperava-se: ["+esperado+"]");
				Log.erro("E retornou.: ["+atual+"]");
				takeScreenshot(removeCaracterEspecial(esperado.toString())+"#"+removeCaracterEspecial(atual.toString()));
				retornarPageLogin();
			}
		}
	}
	
	public static void assertTrue(String message, boolean bol){
		try {
			isError = !bol;
			Assert.assertTrue(message, bol);
		} catch (Exception e) {
			takeScreenshot(message);
			assertFail(message);
		}finally{
			if (isError) {
				Log.erro("E R R O . . .");
				Log.info("    ||");
				Log.info("   \\  /");
				Log.info("    **");
				Log.erro(message);
				retornarPageLogin();
			}
		}
	}
	
	public static void assertFail(String message) {
		Assert.fail(message);
	}
	
	public static String conversorDoubleString(double valor){
		DecimalFormat df = new DecimalFormat("#,###.00");  
		String novoValor = df.format(valor);
		return novoValor;
	}
	
	public static Double conversorStringDouble(String valorString){
		String valorDouble = valorString.replace(",", "");
		return Double.valueOf(valorDouble);
	} 
	
	public static String converterValoresFloatToString(Float value) {
		NumberFormat nf = NumberFormat.getInstance(Locale.ITALY);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(value).replace(",","");
	}
	
	public static String geraSigla(int tamanhoSigla){
		Random random = new Random();
		char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tamanhoSigla; i++) {
			int ch = random.nextInt (letras.length);
		    sb.append (letras [ch]);
		}    
		return sb.toString();    
	}
	
	public static int geraNumeroEntreIntervalo(int min, int max){
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public static String geraNumeroEntre1_99(){
		return converteInteiroParaString(geraNumeroEntreIntervalo(1, 99));
	}
	
	public static String geraNumeroEntre100_999(){
		return converteInteiroParaString(geraNumeroEntreIntervalo(100, 999));
	}
	
	public static String geraNumeroEntre1000_10000(){
		return converteInteiroParaString(geraNumeroEntreIntervalo(1000, 10000));
	}
	
	public static String converteInteiroParaString(int numero){
		return Integer.toString(numero);
	}
	
	public static int converteStringParaInt(String str){
		return Integer.parseInt(str);
	}
	
	public static String removeCaracterEspecial(String s) {
		s = s.replace(".", "");
		s = s.replace(":", "");
		s = s.replace(";", "");
		s = s.replace("/", "");
		s = s.replace("\\", "");
		s = s.replace("ç", "c");
		s = s.replace("{", "c");
		s = s.replace("}", "c");
		s = s.replace("[", "c");
		s = s.replace("]", "c");
		return s;
	}
	
	public static void takeScreenshot(String fileName){
		
		Date 			 data      = new Date();
		Robot 			 robot 	   = null;
	    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy hh mm ss a");
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
       	try {
       		BufferedImage screenShot = robot.createScreenCapture(new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "png", new File(Property.EVIDENCIAS_TESTE_PATH+fileName+ "_"+formatter.format(data)+".png"));
		} catch (IOException e) {
			Log.erro("Erro ao capturar tela");
		}
	}
	
	public static String getAnoAnterior() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
			Calendar calendar = new GregorianCalendar();
			Date d1 = new Date();
			calendar.setTime(d1);
			calendar.add(Calendar.DATE, -600);
			Date d2 = calendar.getTime();
			return formatDate.format(d2);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getDataAtual() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = new GregorianCalendar();
			Date data = new Date();
			calendar.setTime(data);
			return formatDate.format(data);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getAno(int qtdAnosAtras) {
		int dias = qtdAnosAtras * 365;
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
			Calendar calendar = new GregorianCalendar();
			Date d1 = new Date();
			calendar.setTime(d1);
			calendar.add(Calendar.DATE, -dias);
			Date d2 = calendar.getTime();
			return formatDate.format(d2);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String geraNomeEmpresa(){
		Faker fake = new Faker();
		return fake.company().name();
	}
	
	public static String geraCategoria(){
		Faker fake = new Faker();
		return fake.lorem().characters(4, true).toUpperCase();
	}
	
	public static String geraCNPJ(){
		Faker fake = new Faker();
		StringBuilder sb = new StringBuilder(fake.number().digits(14));
		sb.insert(2 , ".");
		sb.insert(6 , ".");
		sb.insert(10, "/");
		sb.insert(15, "-");
		return sb.toString();
	}
	
	public static String geraEmail(){
		Faker fake = new Faker();
		return fake.internet().emailAddress();
	}
	
	public static String geraTelefone(){
		Faker fake = new Faker();
		return fake.phoneNumber().phoneNumber();
	}
	
	public static String geraEndereco(){
		Faker fake = new Faker();
		return fake.address().streetName()    +", "+
			   fake.address().buildingNumber()+", "+
		       fake.address().city()          +"-"+
			   fake.address().stateAbbr()     +", "+
		       fake.address().country();
	}
	
	public static void retornarPageLogin(){
		pageHomeRetagurada.sairDoRetaguarda();
		pageLoginRetagurada.driveNaPaginaLogin();
	}
	
	public static void calculaTempoDoTest(Date tempoInicio, Date tempoFinal) {
		long diferencaHoras    = (tempoFinal.getTime() - tempoInicio.getTime()) / (1000 * 60 * 60);
		long diferencaMinutos  = (tempoFinal.getTime() - tempoInicio.getTime()) / (1000 * 60);
		long diferencaSegundos = (tempoFinal.getTime() - tempoInicio.getTime()) / (1000);
		Log.info("Tempo de execucao:"+ String.format("%02d:%02d:%02d ", diferencaHoras, diferencaMinutos, diferencaSegundos));
	}
}