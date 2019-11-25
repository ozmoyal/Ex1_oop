package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;

class MonomTest {


	@Test
	void testIsZero() {
		Monom zero= new Monom ("0");
		System.out.println("zero.isZero() ? "+zero.isZero());
	}

	@Test
	void testAdd() {
		Monom actual= new Monom ("2x^2");
		Monom m=new Monom ("2x^2");
		Monom expected=new Monom ("4x^2");
		actual.add(m);
		assertEquals(expected, actual, "Test Add Polynom with Monom");
	}

	@Test
	void testMultipy() {
		Monom actual= new Monom ("2x^2");
		Monom m=new Monom ("2x^2");
		Monom expected=new Monom ("4x^4");
		actual.multipy(m);
		assertEquals(expected, actual, "Test Multiply Polynom with Monom");
	}

	

	@Test
	void testEqualsMonom() {
			Monom actual= new Monom ("2x^2");
		Monom m=new Monom ("2x^2");
		Monom expected=new Monom ("4x^4");
		actual.multipy(m);
		System.out.println("actual.equals(expected) ?"+actual.equals(expected) );
	}
		

	
	



}
