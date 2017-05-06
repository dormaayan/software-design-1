package library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import il.ac.technion.cs.sd.book.ext.LineStorage;
import il.ac.technion.cs.sd.book.ext.LineStorageFactory;

public class DoubleKeyDict {
  private Dict mainKeyMap;
  private Dict secondaryKeyMap;
  private LineStorage storer;
  private boolean initialized;

  private class IntegerWrapper {
    public int val;

    public IntegerWrapper() {
      this.val = 0;
    }
  }

  public DoubleKeyDict(LineStorageFactory factory) {
    mainKeyMap = new Dict(factory.open("mainKeyMap"));
    secondaryKeyMap = new Dict(factory.open("secondaryKeyMap"));
    storer = factory.open("valuesMap");
    initialized = false;
  }
  public void store(Collection<Triple> triples) {
    assert !initialized;
    initialized = true;
    final Map<String, List<Pair>> mainKeyToPair = new HashMap<>();
    final Map<String, List<Pair>> secondaryKeyToPair = new HashMap<>();
    triples.stream().forEach(element -> {
      addToMap(mainKeyToPair, element.getKey1(), new Pair(element.getKey2(), element.getValue()));
      addToMap(secondaryKeyToPair, element.getKey2(), new Pair(element.getKey1(), element.getValue()));
    });
    IntegerWrapper currentLine = new IntegerWrapper();
    mainKeyToPair.keySet().stream().sorted().forEachOrdered(key -> {
      int startingLine = currentLine.val;
      mainKeyToPair.get(key).stream().sorted().forEachOrdered(element -> {
        storer.appendLine(element.getKey());
        storer.appendLine(element.getValue());
        currentLine.val += 2;
      });
    });
  }
  private void addToMap(final Map<String, List<Pair>> m, String key, Pair value) {
    if (!m.containsKey(key))
      m.put(key, new ArrayList<>());
    m.get(key).add(value);
  }
}
