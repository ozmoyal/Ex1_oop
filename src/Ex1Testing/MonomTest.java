package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;

class MonomTest {

	@Test
	void testMonom() {
		Monom m1 = new Monom("-x^2");
		System.out.println("m1"+m1);

		Monom m2 = new Monom(-1,2);
		assertEquals(m1,m2,"error");
	}
	
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
		System.out.println(actual.toString());
		assertEquals(expected, actual, "Test Multiply Polynom with Monom");
	}
	
	@Test
	void testEqualsMonom() {
		Monom actual= new Monom("2x^2");
		Monom m=new Monom("2x^2");
		Monom expected=new Monom("4x^4");
		actual.multipy(m);
		System.out.println("actual.equals(expected) ?"+actual.equals(expected) );
	}
	
	@Test
	void testf()
	{
		Monom m=new Monom(3,5);
		double expected =m.f(-2);
		double actual =Math.pow(-2, 5)*3;
		System.out.println(m.toString()+ "="+expected +","+actual);
		int [][] monom = {{3,5}, {1,2} ,{-6,1}, {0,2}, {8,0}, {-2,3}};
		int [] x = {0,1,-2,2,10};
		double [] [] res = {{0.0,3.0,-96.0,96.0,300000.0},{0.0,1.0,4.0,4.0,100.0},
				{0.0,-6.0,12.0,-12.0,-60.0},{0.0,0.0,0.0,0.0,0.0},{8,8,8,8,8,8},{0.0,-2.0,16.0,-16.0,-2000.0}};
		for(int i=0; i<monom.length ;i++)
		{
			m= new Monom(monom[i][0],monom[i][1]);
			System.out.println(m.toString());
			for(int j=0; j<x.length; j++) 
			{
				double re=m.f(x[j]);
				System.out.println(+x[j]+": expection : " + res[i][j] + " actual : " +re );
				assertEquals( res[i][j], re);
			}
		}
	}
}
