package Ex1;

import java.util.Iterator;

public class ComplexFunction implements complex_function {

	function right;
	function left;
	Operation op;

	public ComplexFunction()
	{
		this.right = this.left = null;
		this.op = Operation.Error;
	}

	public ComplexFunction(function left)
	{
		if(left != null)
			this.left = left.copy();
		else
			this.left=null;

		this.right=null;
		this.op=Operation.None;

	}

	public ComplexFunction(function left,function right,String op)
	{

		if(left != null)
			this.left = left.initFromString(left.toString());
		else
			this.left=null;

		if(right != null)
			this.right = right.initFromString(right.toString());
		else
			this.left=null;

		op = op.toLowerCase();

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


	public ComplexFunction(function left,function right,Operation op)
	{		if(left != null)
		this.left = left.copy();
	else
		this.left=null;

	if(right != null)
		this.right = right.copy();
	else
		this.left=null;
	this.op = op;
	}

	@Override
	public double f(double x) {
		switch(this.op.toString())
		{
		case "Plus":
			if(left!=null)
				if(right!=null)
					return left.f(x) + right.f(x);
				return left.f(x);
				
		case "Divid":
			try 
			{
				return left.f(x) / right.f(x);
			}
			catch(ArithmeticException ex) 
			{ 
				System.out.println(ex.getMessage()); 
			} 
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
		default: 
			throw new IllegalArgumentException("Invalid operation");
		}
	}

	@Override
	public function initFromString(String s) {

		if(s.indexOf("(") == -1 && s.indexOf(")") == -1)
			return new Polynom(s);
		int firstParen = s.indexOf("(");
		int indexSep = comIndex(s,firstParen);
		String oper = s.substring(0, firstParen);
		function left = initFromString(s.substring(firstParen+1, indexSep));
		function right = initFromString(s.substring(indexSep+1,s.length()-1));
		ComplexFunction ans = new ComplexFunction(left,right,oper);
		return ans;
	}

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
		return new ComplexFunction(this.left,this.right,this.op.toString());
	}

	@Override
	public void plus(function f1) {
		if(this.right != null) {
			function cf1 = new ComplexFunction(this.right,this.left,this.op);
			this.left = cf1;
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
			function cf1 = new ComplexFunction(this.right,this.left,this.op);
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
			function cf1 = new ComplexFunction(this.right,this.left,this.op);
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
			function cf1 = new ComplexFunction(this.right,this.left,this.op);
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
			function cf1 = new ComplexFunction(this.right,this.left,this.op);
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
			function cf1 = new ComplexFunction(this.right,this.left,this.op);
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

	public String toString() {
		if(this.op.equals(Operation.None))
		{
			if(left!=null)
				return left.toString();
		}
		String ans= getOp().toString()+"("+left.toString()+",";
		if(left!=null)
			ans+= left.toString();
		return ans+")";

	}
	public boolean equals(Object cf1) {
		if(!(cf1 instanceof function))
				return false;
		return visualEquals((function)cf1,-1,1);
	}

	private boolean visualEquals(function cf1,  double i, double j) {
		while(i<=j)
		{
			if(Math.abs(this.f(i)-cf1.f(i))>=Monom.EPSILON)
				return false;
			i+=Monom.EPSILON;
		}
		return true;
	}

}
