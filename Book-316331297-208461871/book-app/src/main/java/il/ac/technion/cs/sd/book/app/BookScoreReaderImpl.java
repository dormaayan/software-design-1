package il.ac.technion.cs.sd.book.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import library.DoubleKeyDict;
import library.TestStorerFactory;

public class BookScoreReaderImpl implements BookScoreReader {
  
  // TODO: use guice
  private final DoubleKeyDict dict = new DoubleKeyDict(new TestStorerFactory());

  @Override public boolean gaveReview(String reviewerId, String bookId) {
    try {
      return dict.findByKeys(reviewerId, bookId).isPresent();
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public OptionalDouble getScore(String reviewerId, String bookId) {
    try {
      Optional<String> $ = dict.findByKeys(reviewerId, bookId);
      return !$.isPresent() ? OptionalDouble.empty() : OptionalDouble.of(Double.parseDouble($.get()));
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public List<String> getReviewedBooks(String reviewerId) {
    try {
      return dict.findByMainKey(reviewerId).stream().map(p -> p.getKey()).collect(Collectors.toList());
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public Map<String, Integer> getAllReviewsByReviewer(String reviewerId) {
    try {
      Map<String, Integer> $ = new HashMap<>();
      dict.findByMainKey(reviewerId).stream().forEach(p -> $.put(p.getKey(), Integer.parseInt(p.getValue())));
      return $;
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public OptionalDouble getScoreAverageForReviewer(String reviewerId) {
    try {
      List<Double> $ = dict.findByMainKey(reviewerId).stream().map(p -> Double.parseDouble(p.getValue())).collect(Collectors.toList());
      return $.isEmpty() ? OptionalDouble.empty() : OptionalDouble.of(($.stream().reduce(0.0, Double::sum)) / $.size());
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public List<String> getReviewers(String bookId) {
    try {
      return dict.findBySecondaryKey(bookId).stream().map(p -> p.getKey()).collect(Collectors.toList());
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public Map<String, Integer> getReviewsForBook(String bookId) {
    try {
      Map<String, Integer> $ = new HashMap<>();
      dict.findBySecondaryKey(bookId).stream().forEach(p -> $.put(p.getKey(), Integer.parseInt(p.getValue())));
      return $;
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
  
  @Override public OptionalDouble getAverageReviewScoreForBook(String bookId) {
    try {
      List<Double> $ = dict.findBySecondaryKey(bookId).stream().map(p -> Double.parseDouble(p.getValue())).collect(Collectors.toList());
      return $.isEmpty() ? OptionalDouble.empty() : OptionalDouble.of(($.stream().reduce(0.0, Double::sum)) / $.size());
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }
}
