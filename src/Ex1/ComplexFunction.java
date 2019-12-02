package Ex1;

public class ComplexFunction implements complex_function {
	function right;
	function left;
	Operation op;
	public ComplexFunction()
	{
		this.right=null;
		this.left=null;
		this.op=Operation.Error;
	}
	public ComplexFunction(function l,function r,String o)
	{
		this.left=l;
		this.right=r;
		o.toLowerCase();
		switch(o)
		{
		case ("plus"):
			this.op=Operation.Plus;
		case ("divid"):
			this.op=Operation.Divid;
		case ("times"):
			this.op=Operation.Times;
		case ("nune"):
			this.op=Operation.None;
		case ("error"):
			this.op=Operation.Error;
		case ("comp"):
			this.op=Operation.Comp;
		case ("max"):
			this.op=Operation.Max;
		case ("min"):
			this.op=Operation.Min;
		default: 
			this.op=Operation.Error;

		}
	}
	public ComplexFunction(function l,String o)
	{
		this.left=l;
		this.right=null;
		this.op=Operation.None;
	}

	@Override
	public double f(double x) {
		return 0;

	}

	@Override
	public function initFromString(String s) {

		s=s.toLowerCase();
		function left=new Polynom();
		String op="";
		if(s.contains("("))
		{
			s.charAt(s.length()-1);
			if(s.charAt(s.length())==')')
			{
				int index =s.indexOf("(");
				op=s.substring(0,index-1);
				s=s.substring(index+1,s.length()-2);
				left=this.initFromString(s);
			}
			if(s.contains(","))
			{
				int index=s.indexOf(",");
				function right=new Polynom(s.substring(index+1,s.length()));
				left=new Polynom(s.substring(0,index-1));
				return new ComplexFunction(left,right,op);


			}
			left=new Polynom(s);
			return new ComplexFunction(left,op);




		}

		return null;

	}

	@Override
	public function copy() {
		return new ComplexFunction(this.left,this.right,this.op.toString());
	}

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub

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

}
