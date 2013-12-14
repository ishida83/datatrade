package crawler;

import java.net.URL;
import java.util.List;

import crawler.dto.DomInfo;

public interface Crawler extends Runnable {
	
	
	/**
	 * ����ĳһ��page
	 * @author noname
	 * @param Url
	 * @return String
	 * */
	String downloadPage(URL pageUrl);
	
	
	/**
	 * ִ�й������̽���������õ�url��¼��׼������Set������
	 * @author noname
	 * @param String
	 * @return   
	 * */
	List<String> doBreadthSearch(String mainUrl);
	
	
	/**
	 * ִ�й������̽���������õ�url��¼��׼������Set������
	 * @author noname
	 * @param String
	 * @return   
	 * */
	void getPageInfo(List<String> urls);
	/**
	 * ���URL��ʽ(ֻ����HTTP)
	 * @param String
	 * @return URL
	 * */
	URL verifyUrl(String url);
}
