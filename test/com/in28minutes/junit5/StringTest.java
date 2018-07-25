package com.in28minutes.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;




class StringTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Close connection to database");
	}

	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Initialize Test Data for " + info.getDisplayName());	
	}
	
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Clean up test data for " + info.getDisplayName());	
	}
	
	@Test
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		
		assertEquals(expectedLength, actualLength);
	}
	
	@Test
	void length_greater_than_zero() {
		assertTrue("ABCD".length()>0);
		assertTrue("ABC".length()>0);
		assertTrue("A".length()>0);
		assertTrue("DEF".length()>0);
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"ABCD", "ABC", "A", "DEF"})
	void length_greater_than_zero_using_parameterized_test(String str) {
		assertTrue(str.length()>0);
	}
	
	@ParameterizedTest(name = "{0} toUpperCase is {1}")
	@CsvSource(value= {"abcd, ABCD", "abc, ABC", "'', ''", "abcdefg, ABCDEFG"})
	void uppercase_test(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value= {"abcd, 4", "abc, 3", "'', 0", "abcdefg, 7"})
	void length_test(String word, int expectedLength) {
		assertEquals(expectedLength, word.length());
	}
	
	
	@Test
	@DisplayName("When length is null, throw an exception")
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, // expect str string variable to be null
				
				() -> {
					str.length();
				}
				
				);
	}
	
	@Test
	void toUpperCase_basic() {
		String str = "abcd";
		String result = str.toUpperCase();
		
		assertEquals("ABCD", result);
	}
	
	@Test
	void contains_basic() {
		String str = "abcdefgh";
		boolean result = str.contains("ijk");
		assertEquals(false, result);
		// assertTrue(result); - optional
	}
	
	@Test 
	void split_basic() {
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		String[] expectedResult = new String[] {"abc", "def", "ghi"};
		
		assertArrayEquals(expectedResult, actualResult);
	}

}
