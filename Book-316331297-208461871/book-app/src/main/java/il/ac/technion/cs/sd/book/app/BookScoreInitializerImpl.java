package il.ac.technion.cs.sd.book.app;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import il.ac.technion.cs.sd.book.ext.LineStorageFactory;
import library.DoubleKeyDict;
import library.Pair;

public class BookScoreInitializerImpl implements BookScoreInitializer {

	private DoubleKeyDict dict;
	private Map<Pair,String> tmp;
	private final String REVIEWER = "Reviewer";

	public BookScoreInitializerImpl() {
		//this(new DoubleKeyDict());
		tmp = new HashMap<>();
	}

	public BookScoreInitializerImpl(DoubleKeyDict dict) {
		this.dict = dict;
		tmp = new HashMap<>();
	}

	@Override
	public void setup(String xmlData) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlData)));
		         NodeList reviewers = doc.getElementsByTagName(REVIEWER);
		         for (int i = 0; i < reviewers.getLength(); i++) {
		            Node nNode = reviewers.item(i);
		            NodeList reviews = ((Element)nNode).getElementsByTagName("Review");
		            System.out.println("\nCurrent Element :" 
				               + ((Element)nNode).getAttribute("Id"));
		            for(int j=0;j<reviews.getLength();j++){
			            System.out.println("\nCurrent book name :" 
					               + ((Element)nNode).getElementsByTagName("Id").item(j).getTextContent());
			            System.out.println("\nCurrent book score :" 
					               + ((Element)nNode).getElementsByTagName("Score").item(j).getTextContent());
		            }		            
		         }		            
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
