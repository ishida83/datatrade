package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;  

import crawler.dto.DomInfo;

import junit.framework.TestCase;

public class CrawlerImplTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();   
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDoSearch() {
		Crawler cl = new CrawlerGAJImpl();
//		String aa = cl.downloadPage(cl.verifyUrl("http://item.grainger.cn"));
//		cl.getPageInfo(cl.doBreadthSearch(aa));
		File file = new File("/Users/huangchong/urls.txt");
		try {
			FileReader fr = new FileReader(file);
			String line = null;
			BufferedReader reader = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			Document doc = Jsoup.parse(sb.toString());
			CrawlerGAJPage crawlerPage = new CrawlerGAJPage(doc);
			List<DomInfo> domInfos = crawlerPage.getSubHtmlInfo("summary_category_list", "div.split_list a");
			for(DomInfo domInfo:domInfos){
				String subPageInfo = cl.downloadPage(cl.verifyUrl(domInfo.getUrlAddress()));
				Document subdoc = Jsoup.parse(subPageInfo);
				crawlerPage = new CrawlerGAJPage(subdoc);
				crawlerPage.getSubSubHtmlInfo("div.product_grid_box", "div.product_grid_image", "div.product_grid_number");	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
