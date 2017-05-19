package il.ac.technion.cs.sd.book.test;

import com.google.inject.AbstractModule;

import library.IDoubleKeyDict;

public class TestDoubleKeyDictModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDoubleKeyDict.class).to(TestDoubleKeyDict.class);
	}

}
