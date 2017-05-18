package il.ac.technion.cs.sd.book.app;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import il.ac.technion.cs.sd.book.ext.LineStorageFactory;
import library.DoubleKeyDict;

public class BookScoreInitializerImpl implements BookScoreInitializer {

	private DoubleKeyDict dict;

	public BookScoreInitializerImpl() {
		//this(new DoubleKeyDict());
	}

	public BookScoreInitializerImpl(DoubleKeyDict dict) {
		this.dict = dict;
	}

	@Override
	public void setup(String xmlData) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlData)));
			NodeList reviewers = document.getElementsByTagName("Reviewer");
			int a;
			System.out.println(reviewers.item(0).getNodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
