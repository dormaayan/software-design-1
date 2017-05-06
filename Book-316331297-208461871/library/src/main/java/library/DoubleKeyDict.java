package library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    
    public String toString(){
      return val+"";
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
    storeDict(mainKeyMap,mainKeyToPair, currentLine);
    storeDict(secondaryKeyMap,secondaryKeyToPair, currentLine);
  }
  
  private void storeDict(final Dict dict,final Map<String, List<Pair>> m, IntegerWrapper currentLine) {
    m.keySet().stream().sorted().forEachOrdered(key -> {
      int startingLine = currentLine.val;
      m.get(key).stream().sorted().forEachOrdered(element -> {
        storer.appendLine(element.getKey());
        storer.appendLine(element.getValue());
        currentLine.val += 2;
      });
      dict.add(new Pair(key,startingLine+","+currentLine));
    });
    dict.store();
  }
 
  private void addToMap(final Map<String, List<Pair>> m, String key, Pair value) {
    if (!m.containsKey(key))
      m.put(key, new ArrayList<>());
    m.get(key).add(value);
  }
  
  public List<Pair> findByMainKey(String key) throws InterruptedException{
    return findByKey(mainKeyMap,key);
  }
  
  public Optional<Pair> findByKeys(String key1, String key2) throws InterruptedException{
    return findByKey(mainKeyMap,key1).stream().filter( p -> p.getKey().equals(key2)).findFirst();
  }
  
  public List<Pair> findBySecondaryKey(String key) throws InterruptedException{
    return findByKey(secondaryKeyMap,key);
  }

  private List<Pair> findByKey(Dict d,String key) throws InterruptedException {
    List<Pair> $ = new ArrayList<>();
    if(!d.find(key).isPresent())
      return $;
    String[] lines = d.find(key).get().split(",");
    int end = Integer.parseInt(lines[1]);
    for(int i = Integer.parseInt(lines[0]) ; i<end ;i+=2 )
      $.add(new Pair(storer.read(i),storer.read(i+1)));
    return $;
  }
}
