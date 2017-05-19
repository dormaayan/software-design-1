package library;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import il.ac.technion.cs.sd.book.ext.LineStorage;

public interface IDoubleKeyDict {
	/**
	 * adds a {@link Triple} to the {@link DoubleKeyDict}. Should only be called
	 * before a store operation Does not save the data persistently.
	 * 
	 * @param t
	 *            the {@link Triple} to be stored
	 */
	public void add(Triple t);

	/**
	 * adds {@link Triple}s to the {@link DoubleKeyDict}. Should only be called
	 * before a store operation Does not save the data persistently.
	 * 
	 * @param ts
	 *            the {@link Triple}s to be stored
	 */
	public void addAll(Collection<Triple> ts);

	/**
	 * Performs an {@link DoubleKeyDict#addAll(Collection)} operation, followed
	 * by a {@link DoubleKeyDict#store()}.
	 * 
	 * @param triples
	 *            the {@link Triple}s to be added
	 */
	public void addAndStore(Collection<Triple> ts);

	/**
	 * Performs the persistent write using the {@link LineStorage}, and prevents
	 * further writes to the {@link DoubleKeyDict}
	 */
	public void store();

	public List<Pair> findByMainKey(String key) throws InterruptedException;

	public Optional<String> findByKeys(String key1, String key2) throws InterruptedException;

	public List<Pair> findBySecondaryKey(String key) throws InterruptedException;

}
