package library;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import il.ac.technion.cs.sd.book.ext.LineStorage;

import static org.mockito.Mockito.*;

public class DictTest {
	@Test()
	public void test0() throws InterruptedException {
		List<Pair> input = new ArrayList<Pair>();
		input.add(new Pair("a", "Dor"));
		input.add(new Pair("b", "Niv"));
		Dict testDict = new Dict(new TestStorer());

		testDict.store(input);
		//
		assertEquals("Niv", testDict.find("b").get());
	}

	@Test()
	public void test1() throws InterruptedException {
		List<Pair> input = new ArrayList<Pair>();
		input.add(new Pair("a", "Dor"));
		input.add(new Pair("b", "Niv"));
		Dict testDict = new Dict(new TestStorer());
		//
		testDict.store(input);
		//
		assertThat(testDict.find("asdf"), is(Optional.empty()));
	}

	@Test()
	public void test2() throws InterruptedException {
		List<Pair> input = new ArrayList<Pair>();
		for (int i = 0; i < 3339; i++) {
			input.add(new Pair(i + "", i + ""));
		}
		Dict testDict = new Dict(new TestStorer());
		//
		testDict.store(input);
		//
		assertEquals(1456 + "", testDict.find("1456").get());
	}
	
	@Test()
	public void test3() throws InterruptedException {
		List<Pair> input = new ArrayList<Pair>();
		input.add(new Pair("b", "Niv"));
		input.add(new Pair("a", "Dor"));
		Dict testDict = new Dict(new TestStorer());

		testDict.store(input);
		//
		assertEquals("Niv", testDict.find("b").get());
	}
	
	
	
	
	// a test using mocking to ensure the storer is used when store is called
	@Test
	public void mockTest1() {
		List<Pair> input = new ArrayList<Pair>();
		input.add(new Pair("a", "Niv"));
		input.add(new Pair("b", "Dor"));
		LineStorage mockStore = mock(LineStorage.class);
		Dict testDict = new Dict(mockStore);
		//
		testDict.store(input);
		//
		verify(mockStore, times(1)).appendLine("a");
		verify(mockStore, times(1)).appendLine("Niv");
		verify(mockStore, times(1)).appendLine("b");
		verify(mockStore, times(1)).appendLine("Dor");
	}

	// a test using mocking to ensure that numberOfLines is only called once
	@Test
	public void mockTest2() throws InterruptedException {
		List<Pair> input = new ArrayList<Pair>();
		input.add(new Pair("a", "Niv"));
		input.add(new Pair("b", "Dor"));
    LineStorage mockStore = mock(LineStorage.class);
		Dict testDict = new Dict(mockStore);
		//
		testDict.store(input);
		testDict.find("a");
		//
		verify(mockStore, times(1)).numberOfLines();
	}

	// a test using mocking to ensure that storer is used in find
	@Test
	public void mockTest3() throws InterruptedException {
		List<Pair> input = new ArrayList<Pair>();
		input.add(new Pair("a", "Niv"));
		input.add(new Pair("b", "Dor"));
    LineStorage mockStore = mock(LineStorage.class);
		Dict testDict = new Dict(mockStore);
		//
		testDict.store(input);
		when(mockStore.numberOfLines()).thenReturn(4);
		when(mockStore.read(0)).thenReturn("a");
		when(mockStore.read(1)).thenReturn("Niv");
		when(mockStore.read(2)).thenReturn("b");
		when(mockStore.read(3)).thenReturn("Dor");
		//
		testDict.find("a");
		//
		verify(mockStore, atLeastOnce()).read(0);
	}
}
