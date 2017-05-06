package library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import il.ac.technion.cs.sd.book.ext.LineStorage;

/** Implements a basic dictionary using a {@link Storer} and binary search. The
 * defualt {@link Storer} is {@link LineStorer}, which is a wrapper to
 * {@link LineStorage} */
public class Dict {
  private final LineStorage storer;
  private boolean initialized = false;
  private List<Pair> pairs = new ArrayList<>();

  public Dict(LineStorage storer) {
    this.storer = storer;
  }
  /** stores a list of pairs in the supplied {@link Storer}. Should only be
   * called once.
   * @param pairs pairs of key value to be stored */
  public void store() {
    assert !initialized;
    initialized = true;
    pairs.stream().sorted().forEachOrdered(element -> {
      storer.appendLine(element.getKey());
      storer.appendLine(element.getValue());
    });
  }
  public void add(Pair p) {
    pairs.add(p);
  }
  public void addAll(Collection<Pair> ps) {
    pairs.addAll(ps);
  }
  /** @param key the key to be searched in the dictionary
   * @return the value that matches key or {@link Optional.empty} otherwise.
   * @throws InterruptedException */
  public Optional<String> find(String key) throws InterruptedException {
    return BinarySearch.of(storer, key,0,storer.numberOfLines());
  }
}
