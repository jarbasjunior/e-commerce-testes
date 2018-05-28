package br.com.ecommerce.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
/**
* @author jarbas.junior
* Define o caminho do driver dos diferentes browsers
* Acessa as IDENTIFICAÇÕES definidas no config.properties e retorna a informação 
*/
public abstract class Property {

	/*
	 * IDENTIFICAÇÕES DE ACESSO AO SISTEMA
	 */
	public static final String USR;
	public static final String EMAIL;
	public static final String PASSWORD;
	public static final String BROWSER_NAME;
	public static final String URL_RETAGUARDA;
	public static final String URL_LOJA_VIRTUAL;
	public static final String CHROME_DRIVE_PATH;
	public static final String FIREFOX_DRIVE_PATH;
	public static final String EVIDENCIAS_TESTE_PATH;

	public static final String PATH_IMAGEM_COMPRA;
	public static final String IMAGEM_COMPRA;
	
	/*
	 * IDENTIFICAÇÕES DA PLANILHA DE DADOS
	 */
	public static final String ARQUIVO_TESTE_XLS;
	public static final String PATH_ARQUIVO_TESTE;

	private static final String PROPERTIES_FILE = "br/com/ecommerce/config.properties";
	
	static{
		
		CHROME_DRIVE_PATH                        = new File("").getAbsolutePath() + "\\src\\test\\resources\\drivers\\chromedriver.exe";
		FIREFOX_DRIVE_PATH                       = new File("").getAbsolutePath() + "\\src\\test\\resources\\drivers\\geckodriver.exe";
		EVIDENCIAS_TESTE_PATH                    = new File("").getAbsolutePath() + "\\src\\test\\resources\\evidencias\\";
		PATH_IMAGEM_COMPRA                       = new File("").getAbsolutePath() + "\\src\\test\\resources\\imagens\\";
		IMAGEM_COMPRA                            = get("imagem.produto");
		USR                                      = get("usr");
		EMAIL                                    = get("email");
		PASSWORD                                 = get("password");
		BROWSER_NAME                             = get("browser.name");
		URL_RETAGUARDA                           = get("site.address.retaguarda");
		URL_LOJA_VIRTUAL                         = get("site.address.loja.virtual");
		ARQUIVO_TESTE_XLS                        = get("arquivo.teste.xls");
		PATH_ARQUIVO_TESTE                       = get("path.arquivo.xls");
	}
	
	/**
	 * Metodo para pegar o valor de alguma propriedade no arquivo de configuracao do Selenium
	 * O caminho e o nome do arquivo pode ser trocados
	 */
	private static String get(String name) {
		Properties properties = new Properties();
		String     value      = null;
		try {
			properties.load(Property.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
		    value = properties.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
