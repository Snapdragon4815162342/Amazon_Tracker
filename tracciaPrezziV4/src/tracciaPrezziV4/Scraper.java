package tracciaPrezziV4;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {
	protected 		   String RawHtml="";
	protected static String BaseUri="";
	private 	static String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";
	
	
	
	public Scraper(String url) {
		Document d= new Document(BaseUri );     //creo un documento vuoto
		
		
		if(url.matches("^(https://|http://|ftp://|ftps://)(?!-.)[^\\\\s/\\$.?#].[^\\\\s]*$")) {//Significa che il testo inserito è un url valido
		//non fai nulla
		
		}else {
		url=("https://www.amazon.it/s?k="+url.replace(" ", "+")+"&__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&ref=nb_sb_noss_1");
				}
		
		try {
          
        	d = Jsoup.connect(url).userAgent(userAgent).maxBodySize(0).get();	 
        	} catch (IOException e) {
        		e.printStackTrace();
        							}
		
		setRawHtml(d.html());	
	}


	public String getRawHtml() {
		return RawHtml;
	}


	public void setRawHtml(String rawHtml) {
		RawHtml = rawHtml;
	}


	public String getBaseUri() {
		return BaseUri;
	}


	public void setBaseUri(String baseUri) {
		BaseUri = baseUri;
	}


	public String getUserAgent() {
		return userAgent;
	}


	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	

}
