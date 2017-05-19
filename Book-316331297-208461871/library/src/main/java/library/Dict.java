package library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;

import il.ac.technion.cs.sd.book.ext.LineStorage;

/**
 * Implements a basic dictionary using a {@link LineStorage} and binary search.
 * Allows adding values until a {@link Dict#store()} is performed, after
 * which data is stored persistently.
 */
public class Dict {
	private final LineStorage storer;
	private boolean initialized = false;
	private List<Pair> pairs = new ArrayList<>();

	@Inject
	public Dict(LineStorage storer) {
		this.storer = storer;
	}

	/**
	 * Performs the persistent write using the {@link LineStorage}, and prevents further writes
	 * to the {@link Dict}
	 */
	public void store() {
		assert !initialized;
		initialized = true;
		pairs.stream().sorted().forEachOrdered(element -> {
			storer.appendLine(element.getKey());
			storer.appendLine(element.getValue());
		});
	}

	/**
	 * performs an {@link Dict#addAll(Collection)} operation, followed by a {@link Dict#store()} operation
	 * 
	 * @param ps
	 *            the {@link Pair}s to be stored
	 */
	public void store(Collection<Pair> ps) {
		addAll(ps);
		store();
	}

	/**
	 * adds a pair to the Dict. Should only be called before a store operation
	 * Does not save the data persistently.
	 * 
	 * @param p
	 *            the {@link Pair} to be stored
	 */
	public void add(Pair p) {
		assert !initialized;
		pairs.add(p);
	}

	/**
	 * adds pairs to the Dict. Should only be called before a store operation
	 * Does not save the data persistently.
	 * 
	 * @param ps
	 *            the {@link Pair}s to be stored
	 */
	public void addAll(Collection<Pair> ps) {
		assert !initialized;
		pairs.addAll(ps);
	}

	/**
	 * @param key
	 *            the key to be searched in the dictionary
	 * @return the value that matches key or {@link Optional.empty} otherwise.
	 * @throws InterruptedException
	 */
	public Optional<String> find(String key) throws InterruptedException {
		return BinarySearch.of(storer, key, 0, storer.numberOfLines());
	}
}
