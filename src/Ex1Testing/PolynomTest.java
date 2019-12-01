package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

class PolynomTest {

	@Test
	void testPolynomString() {
		Polynom p1= new Polynom ("-2x^2-2x+3");
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
		//System.out.println(actual.toString());
		assertEquals(expected, actual, "Add monom fail");
	}

	@Test
	void testSubstract() {
		Polynom actual= new Polynom ("2x^2-2x+3");
		Polynom p2= new Polynom ("-3");
		Polynom expected=new Polynom ("2x^2-2x+6");
		actual.substract(p2);
		//System.out.println("actual.substract(p2) "+actual.toString());
		assertEquals(expected, actual, "Test Substract with two polynoms");
	}
	
	@Test
	void testMultiplyPolynom_able() {

		Polynom p1 = new Polynom ("2x^2-2x+3");
		Polynom p2 = new Polynom ("3x");
		Polynom p3 = new Polynom ("6x^3-6x^2+9x");
		p1.multiply(p2);
		//System.out.println(p1.toString());
		//System.out.println(p3.toString());
		assertEquals(p3,p1,"right");
	}

	@Test
	void testEqualsObject() {
		Polynom p1 = new Polynom("2x^2");
		Object p2 = new Polynom("2x^2");
		assertEquals(p1,p2,"error");
	}

	@Test
	void testRoot() {
		Polynom p1 = new Polynom("x^2-5x+6");
		assertEquals(1.9999999403953552, p1.root(1, 2.5, Monom.EPSILON), "root fail");
	}

	@Test
	void testCopy() {
		Polynom p1 = new Polynom("3x^3+2x^2+x");
		System.out.println(p1.toString());
		Polynom p2 =(Polynom) p1.copy();
		assertEquals(p1,p2,"error");
	}

	@Test
	void testDerivative() {
		Polynom actual= new Polynom ("2x^2-2x+3");
		actual=(Polynom)actual.derivative();
		Polynom expected=new Polynom ("4x-2");
		assertEquals(expected, actual, "Add polynoms failed");
	}

	@Test
	void testArea() {
		Polynom p1 = new Polynom("2x^3+5x^2");
		double ans = p1.area(-2.5,0,Monom.EPSILON);
		assertEquals(ans,6.510416666675519,"error");
	}


	@Test
	void testMultiplyMonom() {
		Polynom actual= new Polynom ("2x^2-2x+3");
		Monom test= new Monom ("2x^2");
		Polynom expected=new Polynom ("4x^4-4x^3+6x");
		actual.multiply(test);
		assertEquals(expected, actual, "multiply polynom with monom fail");
	}

	@Test
	void testToString() {
		Polynom p1 = new Polynom("5x^3+4x^2-5");
		String p2 = "5.0x^3+4.0x^2-5.0";
		assertEquals(p1.toString(),p2);
	}
}

