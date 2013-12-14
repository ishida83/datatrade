package crawler;

import java.util.List;

import org.jsoup.nodes.Element;

import crawler.dto.Commodity;
import crawler.dto.DomInfo;

public interface CrawlerPage {

	void getUrlInfo(String url);

	/**
	 * Ч   Ƿ     Чurl
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	void validateUrl(String url);
	
	/**
	 *     
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	List<DomInfo> getMainHtmlInfo(String className, String delim);

	/**
	 *   
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	List<DomInfo> getHtmlInfo(Element link, String className, String delim);

	/**
	 *       
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	List<DomInfo> getHtmlInfo(Element link, String delim);

	/**
	 *  
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	void getCategoryInfo(String sb, String className, String delim);
	
	/**
	 *                     
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	List<DomInfo> getSubHtmlInfo(String id, String delim);
	
	
	/**
	 *                   
	 * 
	 * @author noname
	 * @param String
	 * @return
	 * */
	List<Commodity> getSubSubHtmlInfo(String id, String delim, String delim2);
	
}
