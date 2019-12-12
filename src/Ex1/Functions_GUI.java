package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.Gson;


public class Functions_GUI implements functions {
	ArrayList<function> f_List;

	public Functions_GUI()
	{
		f_List= new ArrayList<function>();

	}

	@Override
	public boolean add(function arg0) {
		return f_List.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return f_List.addAll(arg0);
	}

	@Override
	public void clear() {
		this.f_List.clear();

	}

	@Override
	public boolean contains(Object arg0) {
		return f_List.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {

		return f_List.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return f_List.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return this.f_List.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return this.f_List.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.f_List.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.f_List.retainAll(arg0);
	}

	@Override
	public int size() {
		return f_List.size();
	}

	@Override
	public Object[] toArray() {
		return this.f_List.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.f_List.toArray(arg0);
	}

	/**
	 * This function implements reading from file string given as following :
	 * each line begin reading from "f(X)=" and adds each line to the collection 
	 * in way that the function is valid.
	 */
	@Override
	public void initFromFile(String file) throws IOException {
		f_List = new ArrayList<function>();
		BufferedReader br;

		br= new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while (line != null) 
		{
			line = line.substring(line.indexOf("f(x)=")+5);
			line = line.replaceAll("\\s+", "");
			function cf1 = new ComplexFunction();
			cf1 = cf1.initFromString(line);
			f_List.add(cf1);
			line = br.readLine();
		}
		br.close();
	}

	/**
	 * This function write or overwrite to the given string 
	 * file as each function goes into a new line with "f(x)=" at the beginning.
	 */
	@Override
	public void saveToFile(String file) throws IOException {
		Iterator<function> it = f_List.iterator();
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			while(it.hasNext())
			{
				function f = it.next();
				pw.write("f(x)="+f.toString()+"\n");
			}
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

	}

	/**
	 * This function will create a new JFrame with the given parameters 
	 * and draws the functions that are in the collection in random colors.
	 */
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width,height);

		// rescale the coordinate system
		double steps= (Math.abs(rx.get_max())+Math.abs(rx.get_min()))/resolution;

		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		//////// vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double i = rx.get_min(); i <= Math.abs(rx.get_max())+Math.abs(rx.get_min()); i=i+0.5) {
			StdDraw.line(rx.get_min()+i, ry.get_max(), rx.get_min()+i, ry.get_min());
		}
		//////// horizontal  lines
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		for (double i = rx.get_min(); i <= rx.get_max() ; i+=0.5) {
			StdDraw.line(i,ry.get_min(), i,ry.get_max());
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(),0 , rx.get_max(),0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (double i = rx.get_min(); i <= Math.abs(rx.get_max())+Math.abs(rx.get_min()); i++){
			StdDraw.text(i-0.7, -0.7, Integer.toString((int)i));
		}
		//////// y axis	
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(-0.7, i-0.07, Integer.toString((int)i));
		}
		// plot the approximation to the function
		for (int i=0;i<f_List.size();i++)	
		{
			double [][] y=new double[2][resolution];
			double help=rx.get_min();
			//System.out.println(f_List.get(i).toString());
			Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 
			for(int j=0;j<resolution;j++)
			{
				y[0][j]=help;
				y[1][j]=f_List.get(i).f(y[0][j]);
				help=help+steps;
			}
			StdDraw.setPenColor(Colors[i%Colors.length]);
			StdDraw.setPenRadius(0.005);
			for (int j = 0; j <resolution-1; j++) {
				StdDraw.line(y[0][j], y[1][j], y[0][j+1], y[1][j+1]);
			}
		}
	}

	/**
	 * This function draws as the previous function but with parameters 
	 * from given Json file. if the function cannot read parameters 
	 * from Json file it will draw it with the following :
		Width:1000
		Height:600
		Resolution:200
		Range_X:[-10,10]
		Range_Y:[-5,15]
	 */
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(json_file);
			GUI_param parm    = gson.fromJson(reader,GUI_param.class);
			Range rangeX      = new Range(parm.Range_X[0],parm.Range_X[1]);
			Range rangeY      = new Range(parm.Range_Y[0],parm.Range_Y[1]);
			drawFunctions(parm.Width, parm.Height, rangeX, rangeY, parm.Resolution);
			return;
		} 
		catch (FileNotFoundException e) {
			System.err.println("error");
			drawFunctions(1000, 600, new Range(-10,10), new Range(-5,15), 200);
		}
	}
}



