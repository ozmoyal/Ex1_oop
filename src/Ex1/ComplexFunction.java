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
	public ComplexFunction(function l,function r,Operation o)
	{
		this.left=l;
		this.right=r;
		this.op=o;
	}
	public ComplexFunction(function l)
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
		
		
		return null;
		
	}

	@Override
	public function copy() {
		return new ComplexFunction(this.left,this.right,this.op);
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
