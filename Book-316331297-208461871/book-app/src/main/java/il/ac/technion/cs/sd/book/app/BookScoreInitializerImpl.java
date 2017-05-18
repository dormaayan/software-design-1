package il.ac.technion.cs.sd.book.app;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import library.DoubleKeyDict;
import library.Pair;
import library.Triple;;

public class BookScoreInitializerImpl implements BookScoreInitializer {

	private DoubleKeyDict dict;
	private Map<Pair, String> tmpStore;
	private final String REVIEWER = "Reviewer";

	public BookScoreInitializerImpl() {
		// this(new DoubleKeyDict());
		tmpStore = new HashMap<>();
	}

	public BookScoreInitializerImpl(DoubleKeyDict dict) {
		this.dict = dict;
		tmpStore = new HashMap<>();
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
				NodeList reviews = ((Element) nNode).getElementsByTagName("Review");
				String reviewerId = ((Element) nNode).getAttribute("Id");
				for (int j = 0; j < reviews.getLength(); j++) {
					String bookName = ((Element) nNode).getElementsByTagName("Id").item(j).getTextContent();
					String bookScore = ((Element) nNode).getElementsByTagName("Score").item(j).getTextContent();
					tmpStore.put(new Pair(reviewerId, bookName), bookScore);
				}
			}

			List<Triple> lst = new ArrayList<>();
			tmpStore.entrySet().stream().forEach(x -> {
				lst.add(new Triple(x.getKey().getKey(), x.getKey().getValue(), x.getValue()));
			});
			//System.out.println(tmpStore.containsKey(new Pair("123", "Foobar")));
			System.out.println(tmpStore);
			//dict.addAndStore(lst);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
