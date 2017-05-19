package library;

import java.util.ArrayList;

import java.util.List;

import il.ac.technion.cs.sd.book.ext.LineStorage;

/** A storer to be used for storing where a functional storer is needed. Uses a
 * list of strings to simulate the file. Should only be used for testing as it
 * is not persistent. Also emulates the timing of LineStorage if initialized to
 * do so */
public class TestStorer implements LineStorage {
  private final List<String> lst = new ArrayList<>();

  @Override public void appendLine(String line) {
    lst.add(line);
  }
  
  @Override public String read(int lineNumber) {
    String $ = lst.get(lineNumber);
    return $;
  }
  
  @Override public int numberOfLines() {
    return lst.size();
  }
}
