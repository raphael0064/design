package demo.iptables;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import util.Funtions;

/**
 * 抓取网页工具类
 *
 * @author g-gaojp
 * @date 2016-7-10
 */
public class GetIps {

	/**
	 * 获取网页数据
	 *
	 * @param urlStr  访问地址
	 * @param params  参数
	 * @param charset 字符编码
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr, Map<String, String> params, String charset) throws Exception {
		StringBuilder sb = new StringBuilder();
		if (null != params && params.size() > 0) {
			sb.append("?");
			Entry<String, String> en;
			for (Iterator<Entry<String, String>> ir = params.entrySet().iterator(); ir.hasNext(); ) {
				en = ir.next();
				sb.append(en.getKey() + "=" + URLEncoder.encode(en.getValue(), "utf-8") + (ir.hasNext() ? "&" : ""));
			}
		}
		URL url = new URL(urlStr + sb);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() != 200) {
			throw new Exception("请求异常状态值:" + conn.getResponseCode());
		}
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		Reader reader = new InputStreamReader(bis, charset);
		char[] buffer = new char[2048];
		int len = 0;
		CharArrayWriter caw = new CharArrayWriter();
		while ((len = reader.read(buffer)) > -1)
			caw.write(buffer, 0, len);
		reader.close();
		bis.close();
		conn.disconnect();
		return caw.toString();
	}

	/**
	 * 获取网页数据
	 *
	 * @param urlStr 访问地址
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr, Map<String, String> params) throws Exception {
		StringBuilder sb = new StringBuilder();
		if (null != params && params.size() > 0) {
			sb.append("?");
			Entry<String, String> en;
			for (Iterator<Entry<String, String>> ir = params.entrySet().iterator(); ir.hasNext(); ) {
				en = ir.next();
				sb.append(en.getKey() + "=" + URLEncoder.encode(en.getValue(), "utf-8") + (ir.hasNext() ? "&" : ""));
			}
		}
		URL url = new URL(urlStr + sb);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() != 200)
			throw new Exception("请求异常状态值:" + conn.getResponseCode());
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		Reader reader = new InputStreamReader(bis, "gbk");
		char[] buffer = new char[2048];
		int len = 0;
		CharArrayWriter caw = new CharArrayWriter();
		while ((len = reader.read(buffer)) > -1)
			caw.write(buffer, 0, len);
		reader.close();
		bis.close();
		conn.disconnect();
		//System.out.println(caw);
		return caw.toString();
	}


	/**
	 * 从获得的网页的document中获取指定条件的内容
	 *
	 * @param document
	 * @param condition 条件
	 * @return
	 */
	public static String catchInfomationFromDocument(Document document, String condition, int position) {

		if (document != null) {
			Iterator<Element> iterator = document.select(condition).iterator();
			if (iterator.hasNext()) {
				String str = iterator.next().text();
				return str.substring(position).trim();
			}
		}
		return null;
	}

	/**
	 * 判断从获得的网页的document中<br/>
	 * 获取指定条件的内容是否存在
	 *
	 * @param document
	 * @param condition 条件
	 * @return
	 */
	public static boolean isExistInfomation(Document document, String condition) {

		if (document != null) {
			Iterator<Element> iterator = document.select(condition).iterator();
			if (iterator.hasNext()) {
				return true;
			}
		}
		return false;
	}

	public void printIP(String url, String searchword, String fileLocation) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("searchword", searchword);

		Document doc = print(url, params, fileLocation);
		//获取页码
		int count = 1;
		for (Iterator<Element> ir = doc.select("span").iterator(); ir.hasNext(); ) {
			String context = ir.next().text();
			if (context != null && context.startsWith("页码")) {
				String[] getCounts = context.split("/");
				if (getCounts != null && getCounts.length >= 2) {
					count = Integer.parseInt(getCounts[1]);
				}
			}
		}
		System.out.println("页数：" + count);
		if (count > 1) {
			for (int i = 2; i <= count; i++) {
				params.put("pagecurrent", String.valueOf(i));
				print(url, params, fileLocation);
			}
		}
	}

	public Document print(String url, Map<String, String> params, String fileLocation) {
		String abc = "";
		try {
			abc = httpGet(url, params, "utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = Jsoup.parse(abc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int x = 0;
			for (Iterator<Element> ir = doc.select("tr").iterator(); ir.hasNext(); ) {
				int count = 1;
				String begin_ip = "";
				String end_ip = "";
				for (Iterator<Element> itd = ir.next().select("td").iterator(); itd.hasNext(); ) {
					String context = itd.next().text();
					if (count == 1) {
						begin_ip = context;
					} else if (count == 2) {
						end_ip = context;
					} else {
						System.out.println("INSERT INTO `voyager`.`ipservice_sa` (`start_ip`, `end_ip`, `province`, `city`, `isp` , `start_ip1`, `end_ip1`, `cityid`) VALUES ('" + Funtions.ipv4ToUInt(begin_ip) + "', '" + Funtions.ipv4ToUInt(end_ip) + "', '印度尼西亚', '印度尼西亚', '' , '" + begin_ip + "', '" + end_ip + "', '1200000');");
					}
					count++;
					x++;
				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static void main(String[] args) throws Exception {
		GetIps a = new GetIps();
		a.printIP("http://ip.yqie.com/search.aspx", "印度尼西亚", "c:\\ip_beijing2.csv");
	}
}
