package Ex1;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 * This is a Math project that contains various functions including class that
 * represent a single Monom (ax^b) that has two variables coefficient and power,
 * and another class that represent Polynom which is a collection of Monoms that
 * are saved in ArrayList which is sorted from highest power to the lowest.
 * Each of those classes has a different method such as adding two Monoms with
 * the same power ,finding a derivative for a Polynom , multiply , equals , root , area etc.
 */
    public class Polynom implements Polynom_able{

	private static final String Comperator  = null;
	/**
	 * Zero (empty polynom)
	 */
	public ArrayList <Monom> monoms;

	/**
	 * this is basic constructor
	 */
	public Polynom() {
		monoms=new ArrayList<Monom>();
		monoms.add(new Monom(Monom.ZERO));
	}

	/**
	 * init a Polynom from a String such as:
	 * We are not support in Polynoms that contains � ( ) { } [ ] < > * /
	 * "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * For example "5x^5-4x^4-3.0x^3+2", "x", "3+1.4X^3-34x" are polynoms that we ae su'
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) 
	{
		monoms=new ArrayList<Monom>();
		String str = ""+s.charAt(0);
		int j=0;
		for (int i = 1; i < s.length(); i++) {
			if(s.charAt(i) == '+' || s.charAt(i) == '-') {
				str =str+ s.substring(j,i);
				Monom sub = new Monom(str);
				monoms.add(sub);
				j=i;
			}
			str = "";
		}
		str =str+ s.substring(j,s.length());
		Monom sub = new Monom(str);
		monoms.add(sub);
		monoms.sort(Monom._Comp);
	}

	/**
	 * function that returns f(x) - the value of the polynom in a giving point
	 * @param x - value
	 * @return the value of the polynom
	 */
	@Override
	public double f(double x) {
		double f=0;
		for(int i=0;i<monoms.size();i++)
		{
			f+=monoms.get(i).f(x);
		}
		return f;
	}

	/**
	 * Adds two polynoms (one is given as argument).
	 * @param p1 - polynom to add.
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it=p1.iteretor();
		Monom m;

		while(it.hasNext())
		{
			m=it.next();
			boolean flag=false;
			for(int i=0;i<monoms.size();i++)
			{
				if(Monom._Comp.compare(monoms.get(i), m)==0)
				{
					monoms.get(i).add(m);
					if(monoms.get(i).isZero())
						monoms.remove(i);
					flag =true;
				}
			}
			if(!flag)
				monoms.add(m);
		}
		monoms.sort(Monom._Comp);
	}

	/**
	 * Adds two monoms (one is given as argument).
	 * @param m1 Monom
	 */
	@Override
	public void add(Monom m1) {
		boolean flag=false;
		for(Monom monoms:monoms)
		{
			if(Monom._Comp.compare(monoms, m1)==0)
			{
				monoms.add(m1);
				flag =true;
			}
		}
		if(!flag)
		{
			monoms.add(m1);
			monoms.sort(Monom._Comp);
		}
	}

	/**
	 * Substract two polynoms (one is given as argument).
	 */
	@Override
	public void substract(Polynom_able p1) {
		p1.multiply(Monom.MINUS1);
		Iterator<Monom> it=p1.iteretor();
		Monom m;
		while(it.hasNext())
		{
			m=it.next();
			boolean flag=false;
			for(int i=0;i<monoms.size();i++)
			{
				if(Monom._Comp.compare(monoms.get(i), m)==0)
				{
					monoms.get(i).add(m);
					if(monoms.get(i).isZero())
						monoms.remove(i);
					flag =true;
				}
			}
			if(!flag)
				monoms.add(m);
		}
		monoms.sort(Monom._Comp);
	}

	/**
	 * Multiply two polynoms (one is given as argument).
	 */
	@Override
	public void multiply(Polynom_able p1) {

		Polynom ans = new Polynom();		
		for(Monom monoms:monoms)
		{
			Iterator<Monom> it=p1.iteretor();
			while(it.hasNext())
			{
				Monom m=new Monom("1");
				m.multipy(it.next());
				m.multipy(monoms);
				ans.add(m);
			}
		}
		ans.cleanZeros();
		monoms=ans.monoms;
		monoms.sort(Monom._Comp);
	}

	/**
	 *
	 * @param p1 - p1
	 * @return true or false
	 */
	@Override

	public boolean equals(Object p1) {
		if(!(p1 instanceof Polynom_able)) {
			return false;
		}
		Iterator<Monom> it=((Polynom) p1).iteretor();
		Monom m=it.next();
		int i=0;
		for(;i<monoms.size()&&it.hasNext();i++)
		{
			if(!monoms.get(i).equals(m))
				return false;
			m=it.next();
		}
		if(i != monoms.size()-1)
			return false;
		if(it.hasNext())
			return false;
		return true;
	}

	/**
	 * checks if the polynom is a zero
	 * @return true or false
	 */
	@Override
	public boolean isZero() {
		if(monoms.isEmpty())
			return true;
		for(Monom monoms:monoms)
			if(!monoms.isZero())
				return false;
		return true;
	}

	/**
	 *
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return the value of the x where f(x) = 0.
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if(x0>=x1)throw new RuntimeException("ERR the input ");
		if(f(x0)*f(x1)>0) throw new RuntimeException("ERR the input ");
		double x2 = ((x0+x1)/2);
		if(Math.abs(f(x2))<=eps) {
			return x2;
		}
		else if(f(x0)*f(x2)<=0) {
			return root(x0,x2,eps);
		}
		return root(x2,x1,eps);
	}

	@Override
	public Polynom_able copy() {
		Polynom_able cp=new Polynom();
		for(int i=0;i<monoms.size();i++)
			cp.add(monoms.get(i));
		return cp;
	}

	/**
	 * Returns the derivative as a polynom
	 * @return div(polynom_able)
	 */
	@Override
	public Polynom_able derivative() {
		Polynom_able div = new Polynom();
		for(int i=0;i<monoms.size();i++)
			{
			Monom m=monoms.get(i).derivative();
			if(!m.isZero())
			div.add(m);
			}
		return div;
	}

	/**
	 *
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the sum of the area above x axis
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if(x0>=x1) return 0;
		if(eps>Math.abs(x0-x1)) throw new RuntimeException("ERR the input ");
		double ans=0;
		while(x0<x1) {
			if(f(x0) > 0) {
				ans += eps * f(x0);
			}
			x0 += eps;
		}
		return ans;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return monoms.iterator();
	}

	@Override
	public void multiply(Monom m1) {
		for(Monom monoms:monoms)
		{
			monoms.multipy(m1);
		}
		monoms.sort(Monom._Comp);

	}
	

	/**
	 * provide a String representation of this polynom. mostly for debugging
	 * @return polynom string
	 */
	public String toString()
	{
		String ans=monoms.get(0).toString();
		String str="";
		for (int i=1;i<monoms.size();i++)
		{
			if(!monoms.get(i).isZero())
			{
				str=monoms.get(i).toString();
				if(monoms.get(i).get_coefficient()<0)
					ans+=str;
				else
					ans+="+"+str;
			}
		}
		return ans;
	}
	public void  cleanZeros()
	{
		for(int i=0;i<monoms.size();i++)
		{
			if(monoms.get(i).isZero())
				monoms.remove(i);
		}
		if(monoms.isEmpty())
			monoms.add(new Monom(Monom.ZERO));
			
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
}
