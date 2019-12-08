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
		cf1=cf1.initFromString("Plus(x,2x)");
		System.out.println(cf1 +"=");
		System.out.println("ctual ="+cf1.f(2)+"expected= 8");
		function cf2 = new ComplexFunction();
		cf2=cf2.initFromString("Mul(2x,2x)");
		System.out.println(cf2 +"=");
		System.out.println("actual ="+cf2.f(2)+"expected= 16");
		function cf3 = new ComplexFunction();
		cf3=cf3.initFromString("Div(x,2x)");
		System.out.println(cf3 +"=");
		System.out.println("actual ="+cf3.f(2)+"expected= 1");
		function cf4 = new ComplexFunction();
		cf4 = cf4.initFromString("max(3x,4x)");
		System.out.println(cf4 +"=");
		System.out.println("actual ="+cf4.f(2)+"expected= 6");
	}

	@Test

	void testInitFromString() {
		function expected = new ComplexFunction();
		function p1 = new Polynom("x^2");
		System.out.println(p1);
		ComplexFunction l = new ComplexFunction(p1);
		System.out.println("l="+l.toString());
		ComplexFunction actual = new ComplexFunction(p1,p1,"plus");
		System.out.println(actual.toString());
		expected = expected.initFromString("plus(x^2,x^2)");
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
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction(p1,p1,"plus");
		assertEquals(Operation.Plus, cf1.getOp());
	}

}
