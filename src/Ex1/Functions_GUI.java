package Ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
String fileName = "output.csv";
		
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
		// TODO Auto-generated method stub
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
	}

	public ComplexFunction get(int i) {
		if ( f_List.get(i)!=null)
			return new ComplexFunction( f_List.get(i));
		return null;
	}

}
