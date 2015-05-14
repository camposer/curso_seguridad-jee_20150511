package xmlsig;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;

import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;

import xmlsig.util.SignatureUtils;

/**
 * Para generar el keystore 
 * keytool -genkeypair -keystore keystore.jks -keyalg RSA
 */
public class XmlSignatureDom {
	public static final String KEYSTORE_PATH = "keystore.jks";
	public static final String KEYSTORE_PASSWORD = "123456";
	public static final String KEYSTORE_KEY = "mykey";
	private static final String URI_ROOT_NODE = "urn:example:personas";
	
	private Key key;
	private X509Certificate cert;
	private List<QName> namesToSign;
	private Document document;
	
	public XmlSignatureDom() throws Exception {
		loadKeys();
	}
	
	public static void main(String[] args) throws Exception {
        XmlSignatureDom xmlSig = new XmlSignatureDom();
        String archivo = "personas.xml";
        String archivoSalida = "personas-firmado.xml";
        String nodo = "personas";
        
        byte[] firma = xmlSig.firmar(archivo, nodo, archivoSalida);
        
        if (xmlSig.verificar(archivo, firma))
        	System.out.println("Es válido");
        else
        	System.out.println("No es válido");
	}
	
	private void loadKeys() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("jks");
        keyStore.load(
            this.getClass().getClassLoader().getResource(KEYSTORE_PATH).openStream(), 
            KEYSTORE_PASSWORD.toCharArray()
        );
        this.key = keyStore.getKey(KEYSTORE_KEY, KEYSTORE_PASSWORD.toCharArray());
        this.cert = (X509Certificate)keyStore.getCertificate(KEYSTORE_KEY);
	}
	
	public byte[] firmar(String archivo, String nodo, String archivoSalida) throws Exception {
        InputStream sourceDocument = 
                this.getClass().getClassLoader().getResourceAsStream(archivo);
        
        DocumentBuilder builder = XMLUtils.createDocumentBuilder(false);
        document = builder.parse(sourceDocument);        
        namesToSign = new ArrayList<QName>();
        namesToSign.add(new QName(URI_ROOT_NODE, nodo));
        SignatureUtils.signUsingDOM(
            document, namesToSign, "http://www.w3.org/2000/09/xmldsig#rsa-sha1", key, cert
        );
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLUtils.outputDOM(document, baos);
        
        byte[] firma = baos.toByteArray();
        
        Files.write(
        		new File(archivoSalida).toPath(),
        		firma,
        		StandardOpenOption.CREATE);
        
        return firma;
	}
	
	public boolean verificar(String archivo, byte[] firma) throws Exception {
        try {
            SignatureUtils.verifyUsingDOM(document, namesToSign, cert);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return false;
	}
}
