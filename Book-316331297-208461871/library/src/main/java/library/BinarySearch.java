package library;

import java.util.Optional;

import il.ac.technion.cs.sd.book.ext.LineStorage;

class BinarySearch {
	/**
	 * performs an efficient binary search on a {@link LineStorage}
	 * @param storer the {@link LineStorage}, written in a format of key in one line,
	 * followed by value in the next, sorted by key
	 * @param key the key to be searched
	 * @param low the line number of the first key, 0 for whole file search
	 * @param high the number of lines used, numberOfLines() for entire file search
	 * @return an Optional with the value saved for key, or Optional.empty() if the
	 * key doesn't exist
	 * @throws InterruptedException if the storer is interrupted
	 */
	static Optional<String> of(LineStorage storer, String key, int low, int high) throws InterruptedException {
		low = low / 2;
		high = high / 2 - 1;
		while (high >= low) {
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
