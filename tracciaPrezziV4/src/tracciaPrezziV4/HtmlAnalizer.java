package tracciaPrezziV4;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlAnalizer extends Scraper {

	static String[] SitiSupportati={"amazon","ebay"};
	
	
	public HtmlAnalizer(String url) {
		super(url);
	}
	
	public String getWebsite(String RawHtml) {
		
		
		int max=-5;
		int threshold=100;
		
		for(String i:SitiSupportati) {
			String[] words = RawHtml.split(i);
		   max=words.length;
		   if(max>threshold)
			   return i;
		}
		return "Sito web non supportato";
		
	}
	public String GetHtmlType(String RawHtml) {/* Con "tipo di Html" intendo per esempio: Sito "Amazon", Pagina "Prodotto singolo"
	 								oppure Sito "Ebay" , Pagina "lista	di prodotti"									*/
		String website=getWebsite(RawHtml);
		
		for(String i:SitiSupportati) {
			/*cerco prodotti supponendo che l'html appartenga al sito SitiSupportati[0], se non trovo nulla provo con SitiSupportati[1]*/
		}
		
		
		return null;

}
	
	
	
	
	
	
	protected static String getNameFromAmazon (String RawHtml){
		
		List<String> selectorName= new ArrayList<String>();
		ArrayList<String> l= new ArrayList<String>();
		l.add("Errore");
                               
		selectorName.add("a-size-large a-spacing-none a-color-secondary");		//per ricerca tramite url
		selectorName.add("a-size-large a-spacing-none");						//per ricerca tramite url
		selectorName.add("a-size-base-plus a-color-base a-text-normal");		//per ricerca tramite keyword
		selectorName.add("a-size-base a-color-base a-text-normal");				//per ricerca tramite keyword  a volte dà dei risultati ma non corrispondono a titoli prodotto
		selectorName.add("a-size-medium a-color-base a-text-normal");			//per ricerca tramite keyword
						
		Document d = new Document("");
		for(int i=0;i<selectorName.size();i++) {
			Elements es = d.getElementsByClass(selectorName.get(i));
		for(Element p : es) {
			l.clear();
        	l.add(p.text());
			if(l.isEmpty()==false) {break;}
							}
												}
        
        return l.get(0);
		}
	
	protected static String getPriceFromDocument (Document d){
		
		List<String> selectorPrice= new ArrayList<String>();
		ArrayList<String> l= new ArrayList<String>();
		l.add("Errore");
                               
		selectorPrice.add("a-size-medium a-color-price priceBlockBuyingPriceString");		//per ricerca tramite url
		selectorPrice.add("a-size-medium a-color-price priceBlockSalePriceString");			//per ricerca tramite url
		selectorPrice.add("a-price-whole");													//per ricerca tramite keyword
		
		for(int i=0;i<selectorPrice.size();i++) {
		Elements es = d.getElementsByClass(selectorPrice.get(i));
			for(Element p : es) {
				l.clear();
        		l.add(p.text());
					if(l.isEmpty()==false) {break;}
			}
			}
        
        
        return l.get(0);
		}
	
	protected static String getLinkFromDocument (Document d) {
		
		String wholeHtml=d.html();
		String[] l= wholeHtml.split("data-asin=");
		Element el=new Element("apples");
		
		for(int i=0;i<l.length;i++) {
			String data_asin =l[i].substring(1, 11);
			el.append(l[i]);
			//Elements el2=el.getElementsByClass("a-link-normal a-text-normal");
			Elements links = el.select("a[href]");
				for(Element link : links )
					if(link.attr("href").contains(data_asin) && !(link.attr("href").contains("amazon-adsystem")) && !(link.attr("href").contains("picassoRedirect"))) {
						return "www.amazon.com"+link.attr("href");
						}
				}
		
		return "Errore";
	}
}

