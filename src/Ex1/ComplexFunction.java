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
		this.right = this.left = null;
		this.op = Operation.None;
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
	{
		this.left = left;
		this.right = right;
		this.op = op;
	}
	public ComplexFunction(String s)
	{
		if(!s.contains("(")&& !s.contains(")"))
		{
			new ComplexFunction (new Polynom (s));
			return;
		}
		int index=s.indexOf("(");
		String op=s.substring(0,index);
		System.out.println(op);
		System.out.println(s.substring(s.indexOf(",")+1,s.length()-1));
		function left =new Polynom(s.substring(index+1,s.indexOf(',')));                    
		function right =new Polynom(s.substring(s.indexOf(",")+1,s.length()-1));
		new ComplexFunction(left,right,op);
		
	}

	@Override
	public double f(double x) {
		
		return left.f(x)+right.f(x);
	}

	@Override
	public function initFromString(String s) {

		if(s.indexOf("(") == -1 && s.indexOf(")") == -1)
			return new Polynom(s);
		int firstParen = s.indexOf("(");
		int indexSep = findSep(s,firstParen);
		String oper = s.substring(0, firstParen);
		function left = initFromString(s.substring(firstParen+1, indexSep));
		function right = initFromString(s.substring(indexSep+1,s.length()-1));
		ComplexFunction ans = new ComplexFunction(left,right,oper);
		return ans;
	}


	private int findSep(String s, int firstParen) {
		// TODO Auto-generated method stub
		int paren = 1;
		int sep = 0;
		int index = firstParen+1;
		while(index<s.length() && paren != sep) {
			if(s.charAt(index) == '(') 
			{
				paren++;
			}
			if(s.charAt(index) == ',')
			{
				sep++;
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
		return getOp().toString()+"("+left.toString()+" , "+right.toString()+")";
	}
}
