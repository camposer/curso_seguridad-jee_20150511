package xmlsig;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import xmlsig.util.SignatureUtils;

/**
 * Para generar el keystore 
 * keytool -genkeypair -keystore keystore.jks -keyalg RSA
 */
public class XmlSignature {
	public static final String KEYSTORE_PATH = "keystore.jks";
	public static final String KEYSTORE_PASSWORD = "123456";
	public static final String KEYSTORE_KEY = "mykey";
	
	private Key key;
	private X509Certificate cert;
	private List<QName> namesToSign;
	
	public XmlSignature() throws Exception {
		loadKeys();
	}
	
	public static void main(String[] args) throws Exception {
        XmlSignature xmlSig = new XmlSignature();
        String archivo = "personas.xml";
        String nodo = "personas";
        
        byte[] firma = xmlSig.firmar(archivo, nodo);
        System.out.println("firma = " + new String(firma));
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
	
	public byte[] firmar(String archivo, String nodo) throws Exception {
        InputStream sourceDocument = 
                this.getClass().getClassLoader().getResourceAsStream(archivo);
        
        this.namesToSign = new ArrayList<QName>();
        namesToSign.add(new QName(nodo));
        ByteArrayOutputStream baos = SignatureUtils.signUsingStAX(
            sourceDocument, namesToSign, "http://www.w3.org/2000/09/xmldsig#rsa-sha1", key, cert
        );
        
		return baos.toByteArray();
	}
	
	public boolean verificar(String archivo, byte[] firma) throws Exception {
        try {
			SignatureUtils.verifyUsingStAX(
			        new ByteArrayInputStream(firma), namesToSign, cert);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return false;
	}
}
