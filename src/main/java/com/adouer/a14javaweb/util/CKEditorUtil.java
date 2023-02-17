package com.adouer.a14javaweb.util;

public class CKEditorUtil {

	public static String getTextFromHtml(String htmlStr){

		//定义script的正则表达式，去除js可以防止注入
		String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";

		//定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
		String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";

		//定义HTML标签的正则表达式，去除标签，只提取文字内容
		String htmlRegex="<[^>]+>";

		//定义空格,回车,换行符,制表符
		String spaceRegex = "\\s*|\t|\r|\n";

		htmlStr = htmlStr.replaceAll(scriptRegex, "");
		htmlStr = htmlStr.replaceAll(styleRegex, "");
		htmlStr = htmlStr.replaceAll(htmlRegex, "");
		htmlStr = htmlStr.replaceAll(spaceRegex, "");

		htmlStr = htmlStr.replaceAll(" ","");

		return htmlStr;
	}

	public static void main(String[] args) {
		String htmlStr = "<p>123<em><strong>aaaaa</strong></em>a456</p>";
		String textStr = getTextFromHtml(htmlStr);
		System.out.println(textStr);
	}

}
