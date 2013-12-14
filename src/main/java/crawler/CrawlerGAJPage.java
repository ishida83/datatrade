package crawler;

//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  

import crawler.dto.Commodity;  
import crawler.dto.DomInfo;

public class CrawlerGAJPage implements CrawlerPage {

	private static final String mainUrl = "http://item.grainger.cn";

	public CrawlerGAJPage(Document doc) {
		this.doc = doc;

	}

	private Document doc;

	public Document getDoc() {
		return doc;
	}

	// public void setDoc(Document doc) {
	// this.doc = doc;
	// }

	@Override
	public void getUrlInfo(String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateUrl(String url) {
		// TODO Auto-generated method stub

	}


	@Override
	public List<DomInfo> getMainHtmlInfo(String className, String delim) {
		List<DomInfo> returnList = new ArrayList<DomInfo>();
		Elements categorys = doc.getElementsByClass(className);
		for (Element category : categorys) {
			DomInfo di = new DomInfo();
			StringBuffer itemUrl = new StringBuffer(mainUrl);
			Element link = category.select(delim).first();
			String href = link.attr("href");
			String categoryUrl = itemUrl.append(href).toString();
			String name = link.attr("name");
			String text = link.text();
			di.setCategory(category);
			di.setCategoryName(text);
			di.setGajCategory(name);
			di.setUrlAddress(categoryUrl);
			returnList.add(di);
		}
		return returnList;
	}

	
	
	
	
	@Override
	public List<DomInfo> getHtmlInfo(Element element, String delim) {
		List<DomInfo> returnList = new ArrayList<DomInfo>();
		Elements links = element.select("li > a");
		for (Element link : links) {
			DomInfo di = new DomInfo();
			StringBuffer itemUrl = new StringBuffer(mainUrl);
			String href = link.attr("href");
			String categoryUrl = itemUrl.append(href).toString();// 
			String name = link.attr("name"); // 
			String text = link.text(); // 
			di.setCategory(link);
			di.setCategoryName(text);
			di.setGajCategory(name);
			di.setUrlAddress(categoryUrl);
			returnList.add(di);
		}
		return returnList;
	}

	@Override
	public void getCategoryInfo(String sb, String className, String delim) {
		// File file = new File("F:/tools/urls.txt");
		// FileWriter fw = null;
		// try {
		// fw = new FileWriter(file);
		Document doc = Jsoup.parse(sb);
		Elements categorys = doc.getElementsByClass("mall_category_list");
		for (Element category : categorys) {
			StringBuffer mainUrl = new StringBuffer("http://item.grainger.cn");
			Element link = category.select("h4 > a").first();
			String href = link.attr("href");
			String categoryUrl = mainUrl.append(href).toString();
			String name = link.attr("name"); 
			String text = link.text(); 
			/****/
			System.out.println(categoryUrl);
			System.out.println(name);
			System.out.println(text);
			/****/
			Elements link2s = category.getElementsByClass("category_list");
			for (Element link2 : link2s) {
				mainUrl = new StringBuffer("http://item.grainger.cn");
				Element link3 = link2.select("h5 > a").first();
				String href2 = link3.attr("href");
				String categoryUrl2 = mainUrl.append(href2).toString();
				String name2 = link3.attr("name"); 
				String text2 = link3.text(); // 
				/****/
				System.out.println(categoryUrl2);
				System.out.println(name2);
				System.out.println(text2);
				/****/
				Elements link4s = link2.select("li > a");
				for (Element link4 : link4s) {
					String href3 = link4.attr("href");
					String categoryUrl3 = mainUrl.append(href3).toString();// 
					String name3 = link4.attr("name"); // 
					String text3 = link4.text(); //
					// fw.write("url:");
					// fw.write(categoryUrl3);
					// fw.write("\n");
					// fw.write(name3);
					// fw.write("/n");
					// fw.write(text3);
					// fw.write("/n");
					/****/
					System.out.println(categoryUrl3);
					System.out.println(name3);
					System.out.println(text3);
					/****/
				}
			}
		}
	}

	@Override
	public List<DomInfo> getHtmlInfo(Element element, String className,
			String delim) {
		// TODO Auto-generated method stub
		List<DomInfo> returnList = new ArrayList<DomInfo>();
		Elements categorys = element.getElementsByClass(className);
		for (Element category : categorys) {
			DomInfo di = new DomInfo();
			StringBuffer itemUrl = new StringBuffer(mainUrl);
			Element link = category.select(delim).first();
			String href = link.attr("href");
			String categoryUrl = itemUrl.append(href).toString();// 
			String name = link.attr("name"); // 
			String text = link.text(); // 
			di.setCategory(category);
			di.setCategoryName(text);
			di.setGajCategory(name);
			di.setUrlAddress(categoryUrl);
			returnList.add(di);
		}
		return returnList;
	}

	@Override
	public List<Commodity> getSubSubHtmlInfo(String id, String delim, String delim2) {
		List<Commodity> commodities = new ArrayList<Commodity>();
		Elements categorys =  doc.select(id);
		for (Element category : categorys) {
			Commodity commodity = new Commodity();
			Element productImage = category.select(delim).first();
			Element productNum = category.select(delim2).first();
			Element productPrice = category.select("div.grid_price_fromto").first();
			Element productBrand = category.select("div.grid_manufacturers").first();
			String href = productImage.select("a").first().attr("href");
			String imgUrl = productImage.select("img").first().attr("abs:src");
			String title = productImage.select("img").first().attr("title");
			StringBuffer itemUrl = new StringBuffer(mainUrl);
			String categoryUrl = itemUrl.append(href).toString();// 
			String price = productPrice.text();
			String brand = productBrand.text();
            String number = productNum.text();
            commodity.setBrand(brand);
            commodity.setCategoryUrl(categoryUrl);
            commodity.setImgUrl(imgUrl);
            commodity.setNumber(number);
            commodity.setPrice(price);
            commodity.setTitle(title);
            commodities.add(commodity);
		}
		// TODO Auto-generated method stub
		return commodities;
	}
	
	
	@Override
	public List<DomInfo> getSubHtmlInfo(String id, String delim) {
		List<DomInfo> returnList = new ArrayList<DomInfo>();
		Element element = doc.getElementById(id);//split_list
		Elements categorys = element.select(delim);
		for (Element category : categorys) {
			DomInfo di = new DomInfo();
			StringBuffer itemUrl = new StringBuffer(mainUrl);
			String href = category.attr("href");
			String categoryUrl = itemUrl.append(href).toString();//
			String text = category.text(); // 
			di.setCategory(category);
			di.setCategoryName(text);
			di.setCategoryNum(-1);
			di.setUrlAddress(categoryUrl);
			returnList.add(di);
		}
		return returnList;
	}
	
}
