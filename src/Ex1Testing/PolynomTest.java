package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;

class PolynomTest {

	@Test
	void testPolynomString() {
		Polynom p1= new Polynom ("2x^2-2x+3");
	}


	@Test
	void testAddPolynom_able() {
		Polynom actual= new Polynom ("2x^2-2x+3");
		Polynom p2= new Polynom ("4x^2+4");
		Polynom expected=new Polynom ("6x^2-2x+7");
		actual.add(p2);
		assertEquals(expected, actual, "Add polynoms fail");
	}

	@Test
	void testAddMonom() {
		Polynom actual = new Polynom ("2x^2-2x+3");
		Monom m = new Monom ("2");
		Polynom expected = new Polynom ("2x^2-2x+5");
		actual.add(m);
		assertEquals(expected, actual, "Add monom fail");
	}

	@Test
	void testSubstract() {
		Polynom actual = new Polynom ("2x^2-2x+3");
		Polynom p2 = new Polynom ("4x^2+4");
		Polynom expected = new Polynom ("-2x^2-2x-1");
		actual.substract(p2);
		assertEquals(expected, actual, "Substract fail");
	}

	@Test
	void testMultiplyPolynom_able() {
		Polynom actual= new Polynom ("2x^2-2x+3");
		Polynom p2= new Polynom ("2");
		Polynom expected=new Polynom ("4x^2-4x+6");
		actual.add(p2);
		assertEquals(expected, actual, "multiply fail");
	}

	@Test
	void testEqualsObject() {
		Polynom p1 = new Polynom("2x^2");
		Object p2 = new Polynom("2x^2");
		Object p3= new Object();
		System.out.println("p1.equals(p2)="+p1.equals(p2));
		System.out.println("p1.equals(p3)="+p1.equals(p3));
	}

<<<<<<< HEAD
	@Test
	void testRoot() {
		Polynom p1 = new Polynom("1.5x^2-8-5x");
		Monom p2 = new Monom("2");
		System.out.println(" "+p1.root(-2, 1, Monom.EPSILON));
		assertEquals(-1.181334582, p1.root(-2, 1, Monom.EPSILON), "root fail");
	}
=======
@Test
void testRoot() {
	Polynom p1 = new Polynom("1.5x^2-8-5x");
	Monom p2 = new Monom("2");
	System.out.println(" "+p1.root(-2, 1, Monom.EPSILON));
	assertEquals(-1.181334582, p1.root(-2, 1, Monom.EPSILON), "root fail");
}
>>>>>>> 525d0ed1864ceddbe094724eae3b97bcbdafd3f6

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testDerivative() {
		fail("Not yet implemented");
	}

	@Test
	void testArea() {
		fail("Not yet implemented");
	}


	@Test
	void testMultiplyMonom() {
		fail("Not yet implemented");
	}

<<<<<<< HEAD
	@Test
	void testToString() {
		fail("Not yet implemented");
	}
}
=======
@Test
void testToString() {
	fail("Not yet implemented");
}}
>>>>>>> 525d0ed1864ceddbe094724eae3b97bcbdafd3f6
