package com.thinkive.base.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public final class XMLHelper {
	private static Logger logger = Logger.getLogger(XMLHelper.class);
	
	public XMLHelper(){
		
	}
	
	
	
	public static Document getDocument(String xmlFile) throws DocumentException{
		if(StringHelper.isEmpty(xmlFile)){
			return null;
		}else{
			File file = new File(xmlFile);
			SAXReader saxReader = new SAXReader();
			return saxReader.read(file);
		}
	}
	
	public static Document getDocument(File xmlFile){
		SAXReader reader = new SAXReader();
		try {
			return reader.read(xmlFile);
		} catch (DocumentException e) {
			logger.error("读取xml文件出错，将返回null", e);
			return null;
		}
	}
	
	public static boolean saveToFile(Document doc, String filePathName,OutputFormat format){
		try {
			String ex = FileHelper.getFullPath(filePathName);
			if(!FileHelper.exists(ex)&& !FileHelper.createDirectory(ex)){
				// 文件不存在，同时创建目录失败
				return false;
			}else{
				XMLWriter writer = new XMLWriter(new FileWriter(new File(filePathName)), format);
				writer.write(doc);
				writer.close();
				return true;
			}
		} catch (IOException e) {
			logger.error("写文件出生错", e);
			return false;
		}
	}
	
	/**
	 * 将document写入到指定 的filePathName.xml
	 * 2017-10-1 13:25:26
	 * @param filePathName
	 * @param doc
	 * @return
	 */
	public static boolean writeToXml(String filePathName, Document doc){
		OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding("GBK");
		return saveToFile(doc, filePathName, format);
	}
}
