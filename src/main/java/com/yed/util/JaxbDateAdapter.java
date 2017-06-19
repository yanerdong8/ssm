package com.yed.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JaxbDateAdapter extends XmlAdapter<String, Date> {
	
	private static final String ISO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat dateFormat;
	
	public JaxbDateAdapter() {
		super();
		dateFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
	}	

	@Override
	public Date unmarshal(String v) throws Exception {
		return dateFormat.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return dateFormat.format(v);
	}

}
