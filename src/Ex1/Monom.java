
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	//**The function accepts an ax ^ b or aX ^ b string, where the a represents an actual number and b is a natural number greater than 0.
	//Example of possible strings "2x^2" /"4x"/"x"/"2"
	public Monom(String s) {
		s.toLowerCase();
		if(s.contains("^"))
		{
			if(s.contains("x^"))
			{
				if(s.charAt(0)=='x')
				{
					s=s.substring(2, s.length());
					int b=Integer.parseInt(s);
					this.set_coefficient(1);
					this.set_power(b);
					return;
				}
				String [] sNum= s.split("x");
				if(sNum.length >2) {throw new RuntimeException("ERR the input ");}
				double a=Double.parseDouble(sNum[0]);
				sNum[1]=sNum[1].substring(1);
				int b=Integer.parseInt(sNum[1]);
				this.set_coefficient(a);
				this.set_power(b);

			}
			else throw new RuntimeException("ERR the input ");
		}
		else if (s.contains("x"))
		{
			if(s.equals("x") || s.equals("+x")) 
			{
				this.set_coefficient(1);
				this.set_power(1);
				return;
			}
			String [] sNum= s.split("x");
			if(sNum.length >1) {throw new RuntimeException("ERR the input ");}

			if(sNum[0].length()==1)
			{	if(sNum[0].equals("-")) sNum[0]="-1.0";	
			}

			double a=Double.parseDouble(sNum[0]);
			this.set_coefficient(a);
			this.set_power(1);
		}
		else
		{
			double a=Double.parseDouble(s);
			this.set_coefficient(a);
			this.set_power(0);
		}
	}
//**adding monoms if the power is different throw Err
	public void add(Monom m) {
		if(this.get_power() != m.get_power())
			throw new RuntimeException("ERR the power of Monom should not be difrfrent "); 
		this.set_coefficient(this.get_coefficient()+m.get_coefficient());

	}
	//**adding monoms
	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());}

	public String toString() {
		String ans = "";
		if(this.get_coefficient()==0)
			ans=""+0;
		else if(this.get_power()==0)
			ans=""+String.valueOf(this.get_coefficient());
		else if(this.get_power()==1)
			ans=""+String.valueOf(this.get_coefficient())+'x';
		else
			ans=""+String.valueOf(this.get_coefficient()) +"x^"+this.get_power();
		return ans;
	}
	//**boolean function Compares the monoms up to epsilon
	public boolean equals(Object m)
	{
		if(!(m instanceof Monom)) {
			return false;
		}
		Monom m1=(Monom) m;
		if(Math.abs(m1.get_coefficient()-this.get_coefficient())>=EPSILON)
			return false;
		if((m1.get_coefficient()==0)&&(this.get_coefficient())==0)
			return true;
		if(m1.get_power() !=this.get_power())
			return false;
		return true;


	}

	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}
	
	@Override
	public function copy() {
		return new Monom(this.toString());
	}


}
