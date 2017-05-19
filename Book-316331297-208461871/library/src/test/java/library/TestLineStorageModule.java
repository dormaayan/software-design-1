package library;

import com.google.inject.AbstractModule;

import il.ac.technion.cs.sd.book.ext.LineStorage;
import il.ac.technion.cs.sd.book.ext.LineStorageFactory;
import library.TestStorerFactory;

public class TestLineStorageModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LineStorageFactory.class).to(TestStorerFactory.class);
		bind(LineStorage.class).to(TestStorer.class);
	}

}
