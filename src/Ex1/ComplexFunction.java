package Ex1;


public class ComplexFunction implements complex_function {
	/**
	 * 
	 */
	function right;
	function left;
	Operation op;

	public ComplexFunction()
	{
		right = left = null;
		op = Operation.None;
	}

	public ComplexFunction(function left)
	{
		this.left = left;
		this.right = null;
		this.op = Operation.None;
	}

	public ComplexFunction(function left,function right,String op)
	{
		this.left = left;
		this.right = right;
		op = op.toLowerCase();
		switch(op)
		{
		case ("plus"):
			this.op=Operation.Plus;
		case ("div"):
			this.op=Operation.Divid;
		case ("mul"):
			this.op=Operation.Times;
		case ("none"):
			this.op=Operation.None;
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

	public ComplexFunction(function left,function right,Operation op)
	{
		this.left = left;
		this.right = right;
		this.op = op;
	}

	//	public ComplexFunction(function l,String o)
	//	{
	//		this.left=l;
	//		this.right=null;
	//		this.op=Operation.None;
	//	}

	@Override
	public double f(double x) {
		return 0;
	}

	@Override
	public function initFromString(String s) {
		if(s.length()==0)
			return null;
		if(s.contains("("))
		{
			ComplexFunction p=new ComplexFunction();
			String op="";
			int index =s.indexOf("(");
			op=s.substring(0,index-1);
			s=s.substring(index+1,s.length());

			if(s.endsWith(")"))
			{
				if(s.contains(","))
				{
					index =s.indexOf(",");
					p.left=p.initFromString(s);
					function right=new Polynom(s.substring(0,index-1));
					return new ComplexFunction(p.left,right,op);	 
				}
			}
		}
		 return new Polynom(s);
		
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
		return this.op+"("+left.toString()+" , "+right.toString()+")";
	}
}
