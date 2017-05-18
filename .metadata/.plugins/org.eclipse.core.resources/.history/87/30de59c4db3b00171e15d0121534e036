import static org.junit.Assert.*;

import org.junit.Test;

import il.ac.technion.cs.sd.book.app.BookScoreInitializerImpl;

public class BookScoreInitializerTest {

	@Test
	public void test() {
		BookScoreInitializerImpl init = new BookScoreInitializerImpl();
		init.setup("<Root>" + "<Reviewer Id=\"123\">" + "<Review>" + "<Id>Foobar</Id>" + "<Score>10</Score>"
				+ "</Review>" + "<Review>" + "<Id>Moobar</Id>" + "<Score>3</Score>" + "</Review>" + "</Reviewer>"
				+ "<Reviewer Id=\"123\">" + "<Review>" + "<Id>Boobar</Id>" + "<Score>5</Score>" + "</Review>"
				+ "</Reviewer>" + "</Root>");

	}

}
