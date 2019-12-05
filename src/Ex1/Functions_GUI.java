package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
	private ArrayList<function> f_List;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

}
