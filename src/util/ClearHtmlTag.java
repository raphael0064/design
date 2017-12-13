package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marlon on 2017/12/12.
 */
public class ClearHtmlTag {

	public String clear(String content) {
		// <p>段落替换为换行
		content = content.replaceAll("<p .*?>", "\r\n");
// <br><br/>替换为换行
		content = content.replaceAll("<br\\s*/?>", "\r\n");
	// 去掉其它的<>之间的东西
		content = content.replaceAll("\\<.*?>", "");
		return content;
	}

	public static void main(String[] args) throws IOException {
		File file = new File("E:\\workspace\\practice\\design\\src\\jvm\\Java 堆内存模型.md");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		ClearHtmlTag clearHtmlTag = new ClearHtmlTag();
		StringBuffer buffer = new StringBuffer();
		while (reader.read() != -1){
			String line = reader.readLine();
			buffer.append(line);
		}

		Document doc = Jsoup.parse(buffer.toString());
//		System.out.println("1");

		Elements spans = doc.getElementsByTag("span");
		for (Element span : spans) {
			System.out.println(span.text());
		}



	}

}
