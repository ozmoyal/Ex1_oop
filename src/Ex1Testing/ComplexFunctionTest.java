package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {


	@Test
	void testF() {
		function cf1 = new ComplexFunction();
		function p1 = new Polynom("x");
		System.out.println(p1);
		cf1=cf1.initFromString("div(x,2x)");
	
		System.out.println(cf1);
		System.out.println("actual ="+cf1.f(2)+"expected= 8");
		
	}

	@Test

	void testInitFromString() {
		function expected = new ComplexFunction();
		function p1 = new Polynom("x^2");
		ComplexFunction actual = new ComplexFunction(p1,p1,"plus");
		expected=expected.initFromString(actual.toString());
		assertEquals(expected, actual,"error");
	}

	@Test
	void testCopy() {
		function expected = new ComplexFunction();
		function p1 = new Polynom("x^2");
		ComplexFunction actual = new ComplexFunction(p1,p1,"plus");
		expected=actual.copy();
		assertEquals(expected, actual,"error");
	}

	@Test
	void testPlus() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction(p1,p1,"plus");
		ComplexFunction actual= new ComplexFunction(p1);
		actual.plus(p1);
		
		assertEquals(expected.f(1), actual.f(1));


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
		assertEquals(expected, actual);
	}

	@Test
	void testMax() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction(p1,p1,"max");
		ComplexFunction actual= new ComplexFunction(p1);
		actual.max(p1);
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
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction(p1,p1,"comp");
		double actual=cf1.f(2);
		double expected=16;
		assertEquals(expected, actual);
		
	}

	@Test
	void testLeft() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction(p1,p1,"comp");
		ComplexFunction actual= new ComplexFunction(p1);
		actual.comp(p1);
		assertEquals(expected, actual);
		
	}

	@Test
	void testRight() {
		function expected=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction(expected,expected,"comp");
		function actual=cf1.right();
		assertEquals(expected, actual);
		
	}

	@Test
	void testGetOp() {
		function p1=new Polynom("x^2");	
		ComplexFunction cf1 = new ComplexFunction(p1,p1,"plus");
		assertEquals(Operation.Plus, cf1.getOp());
	}

}
