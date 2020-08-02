package com.pth.iflow.common.rest.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.pth.iflow.common.exceptions.IflowConfigurationException;

/**
 */
@Component
public class XmlValidator {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private Schema motherSchema;

  /**
   * Instantiates a new xml validator.
   *
   * @param schemaLocations a spring resource path leading to an XSD.
   */
  public XmlValidator(@Value("${iflow.common.rest.xml-validation.schema-locations}") final String... schemaLocations) {
    initSchemas(schemaLocations);
  }

  /**
   * @param schemaLocations
   *
   */
  private void initSchemas(final String[] schemaLocations) {
    final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      final DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
      final Source[] schemaSources = new Source[schemaLocations.length];
      for (int i = 0; i < schemaLocations.length; i++) {
        final String path = schemaLocations[i];
        final URL url = defaultResourceLoader.getResource(path).getURL();
        if (log.isInfoEnabled()) {
          final String tns = getTargetNamespace(url);
          log.info("Registering schema: {} -> target namespace: {}", path, tns);
        }

        final String externalForm = url.toExternalForm();
        schemaSources[i] = new StreamSource(externalForm);
      }

      motherSchema = sf.newSchema(schemaSources);
    }
    catch (final Exception e) {
      throw new IflowConfigurationException(e);
    }

  }

  /**
   * @param url
   * @throws IOException
   */
  private String getTargetNamespace(final URL url) throws ParserConfigurationException, IOException, SAXException {
    final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    try (final InputStream is = url.openStream()) {
      final Document document = documentBuilder.parse(is);

      final String tns = document.getDocumentElement().getAttribute("targetNamespace");
      return tns;
    }

  }

  /**
   */
  public void validate(final String xml) throws XmlValidationException {
    /*
     * BETTER: CK-153: i'm not sure if creating a StreamSource from a reader handles encodings declared in the XML declaration vs. the
     * encoding of the string properly. hence i do a quick check here myself and throw an exception if its not UTF8 -- which is also the
     * XML default.
     *
     * note, i dont want to use a stream here as it makes the production code, logging and testing a lot harder |
     * d41d8cd98f00b204e9800998ecf8427e | TM @ 14.07.2018
     */
    try {
      if (xml.startsWith("<?xml")) {

        if (StringUtils.substringBefore(xml, "?>").toLowerCase().contains("utf-8") == false) {
          throw new XmlValidationException("only UTF-8 encoding is supported for now");
        }
      }

      final Source source = new StreamSource(new StringReader(xml));
      motherSchema.newValidator().validate(source);
    }
    catch (final Exception e) {
      throw new XmlValidationException(e);
    }

  }

}
