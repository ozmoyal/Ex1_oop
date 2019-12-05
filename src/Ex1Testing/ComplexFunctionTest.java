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
		cf1 = cf1.initFromString("Plus(2x,2x)");
		System.out.println("actual ="+cf1.f(2)+"expected= 8");
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
		expected= expected.initFromString("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5-1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2+3.1),+0.1x^5 -1.2999999999999998x +5.0)");
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
