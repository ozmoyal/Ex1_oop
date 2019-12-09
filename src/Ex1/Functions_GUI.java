package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.Gson;

public class Functions_GUI implements functions {
	ArrayList<function> f_List = new ArrayList<function>();

	@Override
	public boolean add(function arg0) {
		return this.f_List.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return this.f_List.addAll(arg0);
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
		return this.f_List.isEmpty();
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
		return this.f_List.size();
	}

	@Override
	public Object[] toArray() {
		return this.f_List.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.f_List.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {

		String line = "";
		String fileReplace = "f(x)=";
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) 
			{
				String funInfo = line.replace(fileReplace,"");
				System.out.println("funInfo ="+funInfo);
				function cf1 = new ComplexFunction();
				cf1=cf1.initFromString(funInfo);
				System.out.println(cf1);
				f_List.add(cf1);

			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("could not read file");
		}
	}

	@Override
	public void saveToFile(String file) throws IOException {
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<f_List.size();i++)
			{
				sb.append("f(x)=");
				sb.append(f_List.get(i).toString()+"\n");
				pw.write(sb.toString());
			}
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// number of line segments to plot
		StdDraw.setCanvasSize(width,height);
		// rescale the coordinate system
		double[] x = new double[resolution];
		for (int i = 0; i < x.length; i++) {
			x[i] = ( Math.abs((rx.get_max()) + Math.abs(rx.get_min()) )*i / resolution );
		}
	
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());

		//////// vertical lines

		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = 0; i < resolution; i=i+10) {
			StdDraw.line(x[i], ry.get_min(), x[i], ry.get_max());
		}
		//////// horizontal  lines
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		//////// x axis		
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		int zero=(int)Math.abs( (rx.get_max()+rx.get_min()))/2;
		StdDraw.line(rx.get_min(),zero , rx.get_max(),zero );
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (int i = 0; i < resolution; i=i+10) {
			StdDraw.text(x[i]-0.07, -0.07, Integer.toString(i-resolution/2));
		}
		//////// y axis	
		StdDraw.line(x[resolution/2], ry.get_min(), x[resolution/2], ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
			StdDraw.text(x[resolution/2]-0.07, i+0.07, Double.toString(i));
		}

		// plot the approximation to the function
		for (int i=0;i<f_List.size();i++)
		{
			double [] y=new double[resolution];
			System.out.println(f_List.get(i).toString());
			for(int j=0;j<resolution;j++)
			{
				y[j]=f_List.get(i).f(x[j]);
			}
			for (int i1 = 0; i1 < resolution-1; i1++) {
				StdDraw.line(x[i1], y[i1], x[i1+1], y[i1+1]);
			}
			StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.01);
			StdDraw.point(x[resolution/2], 1);
		}
		StdDraw.setPenColor(Color.RED);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(x[resolution/2], 1);

	}

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
			e.printStackTrace();
		}
		drawFunctions(1000, 600, new Range(-10,10), new Range(-5,15), 200);
	
	}
}
	
	   

	