package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {

	@Test
	void testComplexFunction() {
		fail("Not yet implemented");
	}

	@Test
	void testComplexFunctionFunctionFunctionOperation() {
		fail("Not yet implemented");
	}

	@Test
	void testF() {
		function cf1 = new ComplexFunction();
		cf1=cf1.initFromString("Plus(2x,2x)");

		System.out.println("actual ="+cf1.f(2)+"expected= 8");
	}



	@Test
	
	void testInitFromString() {

		function expected = new ComplexFunction();
		function p1=new Polynom("x^2");
		function f1=p1.copy();
		System.out.println(p1);
		System.out.println(f1);
		ComplexFunction actual=new ComplexFunction(f1);
		System.out.println(actual.toString());

		expected= expected.initFromString("x^2");
		System.out.println(expected+"="+actual);
		System.out.println(expected.f(-2));
		assertEquals(expected, actual);

	}

	@Test
	void testCopy() {
		
	}

	@Test
	void testPlus() {
		fail("Not yet implemented");
	}

	@Test
	void testMul() {
		fail("Not yet implemented");
	}

	@Test
	void testDiv() {
		fail("Not yet implemented");
	}

	@Test
	void testMax() {
		fail("Not yet implemented");
	}

	@Test
	void testMin() {
		fail("Not yet implemented");
	}

	@Test
	void testComp() {
		fail("Not yet implemented");
	}

	@Test
	void testLeft() {
		fail("Not yet implemented");
	}

	@Test
	void testRight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOp() {
		fail("Not yet implemented");
	}

}
