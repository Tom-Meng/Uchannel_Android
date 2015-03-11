package com.uchanneltv;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SubCategoryXMLHandler extends DefaultHandler {

	boolean currentElement = false;
	String currentValue = "";

	String cartId;
	String customerId;
	String email;
	BeanSubCategory mBeanSubCategory;
	ArrayList<BeanSubCategory> mArrayList;

	public ArrayList<BeanSubCategory> getCartList() {
		return mArrayList;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		currentElement = true;

		if (qName.equals("dict")) {
			mArrayList = new ArrayList<BeanSubCategory>();
		} else if (qName.equals("data")) {
			mBeanSubCategory = new BeanSubCategory();
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		currentElement = false;

		if (qName.equalsIgnoreCase("key"))
			mBeanSubCategory.setTitlekey(currentValue.trim());
		else if (qName.equalsIgnoreCase("string"))
			mBeanSubCategory.setTitlevalue(currentValue.trim());
		else if (qName.equalsIgnoreCase("data"))
			mArrayList.add(mBeanSubCategory);
		currentValue = "";
	}

	public void characters(char [ ] ch, int start, int length) throws SAXException {

		if (currentElement) {
			currentValue = currentValue + new String(ch, start, length);
		}

	}

}
