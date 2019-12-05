package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
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
		System.out.println(cf1 +"=");

		System.out.println("actual ="+cf1.f(2)+"expected= 8");
	}



	@Test

	void testInitFromString() {

		function expected = new ComplexFunction();
		function p1=new Polynom("x^2");
		System.out.println(p1);
		ComplexFunction l=new ComplexFunction(p1);
		System.out.println("l="+l.toString());

		ComplexFunction actual=new ComplexFunction(p1,p1,"plus");
		System.out.println(actual.toString());
		expected= expected.initFromString("Plus(1.0x^2,1.0x^2)");
		System.out.println(expected);
		System.out.println(expected.f(-2));
		assertEquals(expected, actual);

	}

	@Test
	void testCopy() {

		fail("Not yet implemented");
	}

	@Test
	void testPlus() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction(p1,p1,"plus");
		ComplexFunction actual= new ComplexFunction(p1);
		actual.plus(p1);
		System.out.println("expected "+expected+"actual "+actual);
		System.out.println("expected "+expected.f(1)+"actual "+actual.f(1));
		assertEquals(expected, actual);


	}

	@Test
	void testMul() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction(p1,p1,"mul");
		ComplexFunction actual= new ComplexFunction(p1);
		actual.mul(p1);
		assertEquals(expected, actual);
	}

	@Test
	void testDiv() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction(p1,p1,"div");
		ComplexFunction actual= new ComplexFunction(p1);
		actual.div(p1);

		System.out.println(actual.f(0));
		System.out.println("expected "+expected+"actual "+actual);
		System.out.println("expected "+expected.f(1)+"actual "+actual.f(1));
		assertEquals(expected.f(1), actual.f(1));;

	}

	@Test
	void testMax() {
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction(p1,p1,"plus");
		cf1.max(p1);
		double actual=cf1.f(2);
		double expected=8;
		assertEquals(expected, actual);

	}

	@Test
	void testMin() {
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction(p1,p1,"plus");
		cf1.min(p1);
		double actual=cf1.f(2);
		double expected=4;
		assertEquals(expected, actual);
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
		ComplexFunction cf1 = new ComplexFunction(p1,p1,"plus");
		
		assertEquals(Operation.Plus, cf1.getOp());
	}

}
