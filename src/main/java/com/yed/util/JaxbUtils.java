package com.yed.util;


import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

public class JaxbUtils {
    private JAXBContext jaxbContext;

    public JaxbUtils(Class<?>... types) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(types);
    }

    public String toXml(Object root, String encoding) throws JAXBException {
        StringWriter writer = new StringWriter();
        createMarshaller(encoding).marshal(root, writer);
        return writer.toString();
    }

    public String toXml(Collection root, String rootName, String encoding) throws JAXBException {
        CollectionWrapper wrapper = new CollectionWrapper();
        wrapper.collection = root;

        JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(
                new QName(rootName), CollectionWrapper.class, wrapper);

        StringWriter writer = new StringWriter();
        createMarshaller(encoding).marshal(wrapperElement, writer);

        return writer.toString();
    }

    public <T> T fromXml(String xml) throws JAXBException {
        StringReader reader = new StringReader(xml);
        return (T) createUnmarshaller().unmarshal(reader);
    }

    public <T> T fromXml(String xml, boolean caseSensitive) throws JAXBException {
        String fromXml = xml;
        if (!caseSensitive)
            fromXml = xml.toLowerCase();
        StringReader reader = new StringReader(fromXml);
        return (T) createUnmarshaller().unmarshal(reader);
    }
    
   

    public Marshaller createMarshaller(String encoding) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE); // XML 头标识
        if (StringUtils.isNotBlank(encoding)) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        }
        marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                new CharacterEscapeHandler() {
                    public void escape(char[] ch, int start, int length, boolean isAttVal,
                                       Writer writer) throws IOException {
                        writer.write(ch, start, length);
                    }
                });
        return marshaller;
    }

    public Unmarshaller createUnmarshaller() throws JAXBException {
        return jaxbContext.createUnmarshaller();
    }

    public static class CollectionWrapper {
        @SuppressWarnings("unchecked")
        @XmlAnyElement
        protected Collection collection;
    }
}
