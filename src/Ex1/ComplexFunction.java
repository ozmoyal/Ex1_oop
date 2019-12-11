package Ex1;

import java.util.Iterator;
import java.util.Random;

public class ComplexFunction implements complex_function {

	function right;
	function left;
	Operation op;
	private Range r =new Range (-100,100);
	

	public ComplexFunction()
	{
		this.right = this.left = null;
		this.op = Operation.Error;
	}

	public ComplexFunction(function left)
	{
		this.left=left;
		this.right=null;
		this.op=Operation.None;
	}

	public ComplexFunction(String op, function left , function right)
	{
		this.left=left;
		this.right=right;
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

	public ComplexFunction(Operation op,function left,function right)
	{
		this.left=left;
		this.right=right;
		if(op.equals(Operation.None))	throw new IllegalArgumentException("Invalid operation");
		this.op = op;

	}

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

	@Override
	public function initFromString(String s) {
		s=s.replaceAll("//s+" , "");
		if(s.indexOf("(") == -1 && s.indexOf(")") == -1) 
		{
			Polynom m = new Polynom();
			return m.initFromString(s);
		}
		int firstParen = s.indexOf("(");
		int indexSep = comIndex(s,firstParen);
		String oper = s.substring(0, firstParen);
		function left = initFromString(s.substring(firstParen+1, indexSep));
		function right = initFromString(s.substring(indexSep+1,s.length()-1));
		ComplexFunction ans = new ComplexFunction(oper,left,right);
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

	public String toString() {
		if(this.op.equals(Operation.None))
		{
			if(left!=null)
				return left.toString();
		}
		String ans="";
		if(getOp().toString().equals("Times"))
			ans="Mul";
		else if (getOp().toString().equals("Divid"))
			ans="Div";
		else
			ans=getOp().toString();
		ans+="("+left.toString()+",";
		if(right!=null)
			ans+= right.toString();
		return ans+")";

	}

	public boolean equals(Object cf1) {
		if(!(cf1 instanceof function))
			return false;
		return visualEquals((function)cf1,r);
	}

	private boolean visualEquals(function cf1, Range r) {
		double i=r.get_min();
		while(i<=r.get_max())
		{
			if(Math.abs(this.f(i)-cf1.f(i))>=Monom.EPSILON)
				return false;
			i+=Monom.EPSILON;
		}
		return true;
	}
}
