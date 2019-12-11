package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sun.tools.sjavac.server.SysInfo;

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
	
		String str="f(x)=div(x,2x)";
		str=str.replace("f(x)=","");
		System.out.println(str);
		System.out.println(cf1);
		System.out.println("actual ="+cf1.f(2)+"expected= 8");
		
	}

	@Test

	void testInitFromString() {
		function expected = new ComplexFunction();
		function p1 = new Polynom("x^2");
		Polynom p =new Polynom("x");
		ComplexFunction actual = new ComplexFunction("plus",p1,p1);
		expected=expected.initFromString(actual.toString());
		ComplexFunction cf=new 	ComplexFunction("plus",p,expected);
		System.out.println(cf);
		assertEquals(expected, actual,"error");
	}

	@Test
	void testCopy() {
		function expected = new ComplexFunction();
		function p1 = new Polynom("x^2");
		ComplexFunction actual = new ComplexFunction("plus",p1,p1);
		expected=actual.copy();
		assertEquals(expected, actual,"error");
	}

	@Test
	void testPlus() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction("plus",p1,p1);
		ComplexFunction actual= new ComplexFunction(p1);
		actual.plus(p1);
		
		assertEquals(expected.f(1), actual.f(1));


	}

	@Test
	void testMul() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction("mul",p1,p1);
		ComplexFunction actual= new ComplexFunction(p1);
		actual.mul(p1);
		assertEquals(expected, actual);
	}

	@Test
	void testDiv() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction("div",p1,p1);
		ComplexFunction actual= new ComplexFunction(p1);
		actual.div(p1);
		assertEquals(expected, actual);
	}

	@Test
	void testMax() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction("max",p1,p1);
		ComplexFunction actual= new ComplexFunction(p1);
		actual.max(p1);
		assertEquals(expected, actual);
	}

	@Test
	void testMin() {
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("plus",p1,p1);
		cf1.min(p1);
		double actual=cf1.f(2);
		double expected=4;
		assertEquals(expected, actual);
	}

	@Test
	void testComp() {
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("comp",p1,p1);
		double actual=cf1.f(2);
		double expected=16;
		assertEquals(expected, actual);
	}

	@Test
	void testLeft() {
		function p1=new Polynom("x^2");
		ComplexFunction expected = new ComplexFunction("comp",p1,p1);
		ComplexFunction actual= new ComplexFunction(p1);
		actual.comp(p1);
		assertEquals(expected, actual);
		
	}

	@Test
	void testRight() {
		function expected=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("comp",expected,expected);
		function actual=cf1.right();
		assertEquals(expected, actual);
		
	}

	@Test
	void testGetOp() {
		function p1=new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("plus",p1,p1);
		assertEquals(Operation.Plus, cf1.getOp());
	}

}
