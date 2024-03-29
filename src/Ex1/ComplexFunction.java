package Ex1;

import java.util.Iterator;
import java.util.Random;

public class ComplexFunction implements complex_function {

	function right;
	function left;
	Operation op;
	private Range r =new Range (-20,20); //for equals use

/**
 *   empty constructor
 *   left=right=null
 *   Operation None 
 */
	public ComplexFunction()
	{
		this.right = this.left = null;
		this.op = Operation.None;
	}
/**
 * constructor that get one  function and  set the function in the left side,
In the right function set NULL ,and in the Operation None
 * @param left
 */
	public ComplexFunction(function left)
	{
		this.left=left;
		this.right=null;
		this.op=Operation.None;
	}
/**
 * basic constructor that get the Operation as a string
 * @param op
 * @param left
 * @param right
 */
	public ComplexFunction(String op, function left , function right)
	{
		if(left !=null)
			this.left=left;
		else
			throw new IllegalArgumentException("Invalid function");
		if(right !=null)
			this.right=right;
		else
			throw new IllegalArgumentException("Invalid function");
		
		op=op.toLowerCase();

		switch(op)
		{
		case "plus":
			this.op=Operation.Plus;
			break;
		case "div":
			this.op=Operation.Divid;
			break;
		case "mul":
			this.op=Operation.Times;
			break;
		case "none":
			this.op=Operation.None;
			if((left !=null)&&(right!=null))
				throw new IllegalArgumentException("Invalid operation");
			break;
		case "comp":
			this.op=Operation.Comp;
			break;
		case "max":
			this.op=Operation.Max;
			break;
		case "min":
			this.op=Operation.Min;
			break;
		default: 
			this.op=Operation.Error;
			break;
		}
	}
/*
 * basic constructor 
 */
	public ComplexFunction(Operation op,function left,function right)
	{
		if(left !=null)
			this.left=left;
		else
			throw new IllegalArgumentException("Invalid function");
		if(right !=null)
			this.right=right;
		else
			throw new IllegalArgumentException("Invalid function");
		if(op.equals(Operation.None))	throw new IllegalArgumentException("Invalid operation");
		this.op = op;

	}
/*
 * returns the Y value of ComplexFunction with the given X
 */
	@Override
	public double f(double x) {
		switch(this.op.toString())
		{
		case "Plus":
			return left.f(x) + right.f(x);
		case "Divid":
			if(right.f(x)==0)throw new IllegalArgumentException("0");
			return left.f(x)/right.f(x);	
		case "Times":
			return left.f(x) * right.f(x);
		case "Comp":
			if(right != null) return left.f(right.f(x));
			return left.f(x);
		case "Max":
			if(right.f(x)>left.f(x))
			{
				return right.f(x);
			}
			return left.f(x);
		case "Min":
			if(right.f(x)<left.f(x))
			{
				return right.f(x);
			}
			return left.f(x);
		case "None":
			if(this.left != null)
				return left.f(x);
		default: 
			throw new IllegalArgumentException("Invalid operation");
		}
	}
/*
 * returns a function created from the given String
 */
	@Override
	public function initFromString(String s) {
		if(s.indexOf("(") == -1 && s.indexOf(")") == -1) 
		{
			return new Polynom (s);
		}
		s=s.replaceAll("\\s+","");
		int firstParen = s.indexOf("(");
		int indexSep = comIndex(s,firstParen);
		String oper = s.substring(0, firstParen);
		function left = initFromString(s.substring(firstParen+1, indexSep));
		function right = initFromString(s.substring(indexSep+1,s.length()-1));
		ComplexFunction ans = new ComplexFunction(oper,left,right);
		return ans;
	}
	/*
	 * This function is an auxiliary function for the initfromstring function,
	 * which returns the comma index, which divides
	 * the complex function into a left function and a right function.
	 */

	private int comIndex(String s, int firstParen) {
		int paren = 1;
		int com = 0;
		int index = firstParen+1;
		while(index<s.length() && paren != com) {
			if(s.charAt(index) == '(') 
			{
				paren++;
			}
			if(s.charAt(index) == ',')
			{
				com++;
			}
			index++;
		}
		return index-1;

	}

	@Override
	public function copy() {
		return new ComplexFunction(this.op,left.copy(),right.copy());
	}

	@Override
	public void plus(function f1) {
		if(this.right != null) {

			this.left =  new ComplexFunction(this.op,this.left,this.right);
			this.right = f1;
			this.op = Operation.Plus;
		}
		else {
			this.right = f1;
			this.op = Operation.Plus;;
		}
	}

	@Override
	public void mul(function f1) {
		if(this.right != null) {
			function cf1 = new ComplexFunction(this.op,this.left,this.right);
			this.left = cf1;
			this.right = f1;
			this.op = Operation.Times;
		}
		else {
			this.right = f1;
			this.op = Operation.Times;
		}

	}

	@Override
	public void div(function f1) {
		if(this.right != null) {
			function cf1 = new ComplexFunction(this.op,this.left,this.right);
			this.left = cf1;
			this.right = f1;
			this.op = Operation.Divid;
		}
		else {
			this.right = f1;
			this.op = Operation.Divid;
		}

	}

	@Override
	public void max(function f1) {
		if(this.right != null) {
			function cf1 = new ComplexFunction(this.op,this.left,this.right);
			this.left = cf1;
			this.right = f1;
			this.op = Operation.Max;
		}
		else {
			this.right = f1;
			this.op = Operation.Max;
		}

	}

	@Override
	public void min(function f1) {
		if(this.right != null) {
			function cf1 = new ComplexFunction(this.op,this.left,this.right);
			this.left = cf1;
			this.right = f1;
			this.op = Operation.Min;
		}
		else {
			this.right = f1;
			this.op = Operation.Min;
		}
	}

	@Override
	public void comp(function f1) {
		if(this.right != null) {
			function cf1 = new ComplexFunction(this.op,this.left,this.right);
			this.left = cf1;
			this.right = f1;
			this.op = Operation.Comp;
		}
		else {
			this.right = f1;
			this.op = Operation.Comp;
		}
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

	public String toString() 
	{
		
		if(this.op.equals(Operation.None))
			if(this.left()!=null)
				return this.left.toString();
		if((left== null)||(right==null)) throw new IllegalArgumentException("Invalid function");
		String ans ="";
		if(this.op.equals(Operation.Divid))
			ans="Div";
		else if(this.op.equals(Operation.Times))
			ans="Mul";
		else
			ans=this.op.toString();
		ans+="("+this.left.toString()+","+this.right.toString()+")";
		return ans;


	}
	public boolean equals(Object cf1) {
		if(!(cf1 instanceof function))
			return false;
		return visualEquals((function)cf1,r);
	}
	/*
	 * An auxiliary function for the equality function which receives a range defined at
	*the top of the class and calculates the X value in both functions, and compares the two functions,
       *If F (X) obtained is different, the function returns false, otherwise true.
	 */
	
	private boolean visualEquals(function cf1, Range r) {
		double i=r.get_min();
		while(i<=r.get_max())
		{
			if(Math.abs(this.f(i)-cf1.f(i))>=Monom.EPSILON)
				return false;
			i+=0.0001;
		}
		return true;
	}
}
