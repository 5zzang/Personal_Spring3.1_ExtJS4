/*
 * @(#)XMLUtil.java
 * Date : Jun 25, 2010
 * Copyright: (C) 2010 by NICSTECH co.,Ltd. All right reserved.
 */

package com.sample.app.common.util;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XMLUtil
 * @author 5zzang
 * @date 2013. 3. 13.
 */
public class XMLUtil {
	/**
	 * DOM 객체를 XML 문자열로 변환하여 리턴한다.
	 * @param doc DOM 객체
	 * @return java.lang.String XML문자열
	 * @throws Exception 
	 */
	public static String getXMLString(Node doc) 
		throws TransformerException, TransformerConfigurationException {
		TransformerFactory tranFactory = TransformerFactory.newInstance();
		Transformer aTransformer = tranFactory.newTransformer();

		StringWriter sw = new StringWriter();
		
		Source src = new DOMSource(doc);
		Result dest = new StreamResult(sw);
		
		aTransformer.transform(src, dest);

		return sw.toString();
	}
	
	/**
	 * 메뉴ID로 해당 노드를 찾아 리턴한다.
	 * @param sourceElm 검색노드
	 * @param attrName 어트리뷰트명
	 * @param sAttrValue 어트리뷰트값
	 * @return org.w3c.dom.Element 메뉴ID의 노드
	 */
	public static Element findElement(Node sourceElm, String attrName, String sAttrValue) {
		if (sourceElm.hasChildNodes()) {
			NodeList nodes = sourceElm.getChildNodes();
			
			for (int i=0;i<nodes.getLength();i++) {
				if (Node.ELEMENT_NODE == nodes.item(i).getNodeType()) {
					Element elm = findElement(nodes.item(i), attrName, sAttrValue);
					
					if (((Element)elm).getAttribute(attrName).equals(sAttrValue)) {
						return (Element) elm;
					}
				}
			}
		} else {
			if (sourceElm.hasAttributes() && ((Element)sourceElm).getAttribute(attrName).equals(sAttrValue)) {
				return (Element) sourceElm;
			}
		}
		
		return (Element) sourceElm;
	}
}
