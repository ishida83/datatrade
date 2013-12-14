package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;  

import crawler.dto.Commodity;
import crawler.dto.DomInfo;

/**
 * 
 * 
 * */
public class CrawlerGAJImpl implements Crawler {

	private String mainUrl;

	private int deapth;

	private CrawlerPage crawlerPage;

	private Set<String> crawlings;

	private Set<String> crawleds;

	public CrawlerPage getCrawlerPage() {
		return crawlerPage;
	}

	public void setCrawlerPage(CrawlerPage crawlerPage) {
		this.crawlerPage = crawlerPage;
	}

	public void setCrawlings(Set<String> crawlings) {
		this.crawlings = crawlings;
	}

	public void setCrawleds(Set<String> crawleds) {
		this.crawleds = crawleds;
	}

	public int getDeapth() {
		return deapth;
	}

	public void setDeapth(int deapth) {
		this.deapth = deapth;
	}

	public Set<String> getCrawlings() {
		return crawlings;
	}

	public Set<String> getCrawleds() {
		return crawleds;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	@Override
	public String downloadPage(URL pageUrl) {
		HttpURLConnection conn = null;
		StringBuffer sb = new StringBuffer();
		try {
			Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,
					new InetSocketAddress("172.31.1.246", 8080));
			conn = (HttpURLConnection) pageUrl.openConnection(proxy);
			conn.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return sb.toString();
	}

	@Override
	public void run() {

		// Document doc = Jsoup.parse(sb);
		crawlings = new HashSet<String>();
		crawleds = new HashSet<String>();
		// crawlerPage = new CrawlerGAJPage();

		StringTokenizer st = new StringTokenizer("");

		// getHrefs(doBreadthSearch(mainUrl));  
		// for(String crawling:crawlings){
		// while (crawleds.size()<crawlings.size()) {
		// getInfos(crawling);
		// crawleds.add(crawling);
		// }
		// }
		// TODO Auto-generated method stub
	}
   
	@Override
	public List<String> doBreadthSearch(String mainHtml) {
		final List<String> urls = new ArrayList<String>();
		final File file = new File("F:/tools/urls2.txt");
		try {  
			final FileWriter fw = new FileWriter(file);
			Document doc = Jsoup.parse(mainHtml);
			crawlerPage = new CrawlerGAJPage(doc);
			List<DomInfo> domInfos = crawlerPage.getMainHtmlInfo(
					"mall_category_list", "h4 > a");
			for (final DomInfo domInfo : domInfos) {
				Thread aa = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						List<DomInfo> domInfos2 = crawlerPage.getHtmlInfo(
								domInfo.getCategory(), "category_list",  
								"h5 > a");
						for (DomInfo domInfo2 : domInfos2) {
							List<DomInfo> domInfos3 = crawlerPage.getHtmlInfo(
									domInfo2.getCategory(), "category_list");  
							for (DomInfo domInfo : domInfos3) {
								try {  
									fw.write("url:");
									fw.write(domInfo.getUrlAddress());
									fw.write("\n");
									urls.add(domInfo.getUrlAddress());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				});
				try {
					aa.start();
					aa.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		return urls;
	}

	@Override
	public  URL verifyUrl(String url) {
		if (!url.toLowerCase().startsWith("http://"))
			return null;
		URL verifiedUrl = null;
		try {
			verifiedUrl = new URL(url);
		} catch (Exception e) {
			return null;
		}
		return verifiedUrl;
	}

	@Override
	public void getPageInfo(List<String> urls) {
		// TODO Auto-generated method stub
		for(final String url:urls){
			new Thread(new Runnable() {
				@Override
				public void run() {
					String pageInfo = downloadPage(verifyUrl(url));
					Document doc = Jsoup.parse(pageInfo);
					crawlerPage = new CrawlerGAJPage(doc);
					List<DomInfo> domInfos = crawlerPage.getSubHtmlInfo("summary_category_list", "div.split_list a");
					for(DomInfo domInfo:domInfos){
						String subPageInfo = downloadPage(verifyUrl(domInfo.getUrlAddress()));
						Document subdoc = Jsoup.parse(subPageInfo);
						crawlerPage = new CrawlerGAJPage(subdoc);
						List<Commodity> commoditys = crawlerPage.getSubSubHtmlInfo("div.product_grid_box", "div.product_grid_image img", "div.product_grid_number");	
					}
				}
			}).start();
		}
	}
}
