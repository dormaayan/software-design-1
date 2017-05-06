package library;

import java.util.Optional;

import il.ac.technion.cs.sd.book.ext.LineStorage;

class BinarySearch {
  static Optional<String> of(LineStorage storer, String key) throws InterruptedException {
    int size = storer.numberOfLines();
    assert size % 2 == 0;
    for (int high = (size / 2) - 1, low = 0; high >= low;) {
      int mid = (low + high) / 2;
      String current = storer.read(2 * mid);
      int comparison = current.compareTo(key);
      if (comparison == 0)
        return Optional.of(storer.read(2 * mid + 1));
      if (comparison < 0)
        low = mid + 1;
      else
        high = mid - 1;
    }
    return Optional.empty();
  }
}
