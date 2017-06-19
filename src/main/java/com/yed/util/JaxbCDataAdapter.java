package com.yed.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbCDataAdapter extends XmlAdapter<String, String> {
    @Override
    public String marshal(String str) throws Exception {
        return "<![CDATA[" + str + "]]>";
    }

    @Override
    public String unmarshal(String str) throws Exception {
        return str;
    }
}
