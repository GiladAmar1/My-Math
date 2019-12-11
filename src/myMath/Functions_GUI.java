package myMath;

import java.awt.Color;

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

	private ArrayList<function> cl;

	public  Functions_GUI() {
		// TODO Auto-generated constructor stub
		this.cl=new ArrayList<function>();;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return cl.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cl.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return cl.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return cl.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return cl.toArray();
	}

	@Override
	public  <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return this.toArray(a) ;
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return cl.add(e);

	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return cl.remove(o);

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return cl.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return cl.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return cl.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return cl.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		cl.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(file);
			cl= gson.fromJson(reader,ArrayList.class);
			System.out.println(cl);

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String json = gson.toJson(cl);
		//		System.out.println(json);
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			pw.write(json);
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
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0,ry.get_min(), 0, ry.get_max());
		double rx_steps =(rx.get_max()-rx.get_min())/resolution;
		for (int i = 0; i < this.size(); i++) {
			StdDraw.setPenColor(Colors[i%7]);
			for (double j = rx.get_min(); j <rx.get_max(); j+=rx_steps) {
				double y=cl.get(i).f(j);
				double y2=cl.get(i).f(j+rx_steps);
				StdDraw.line(j,y, j+rx_steps, y2);

			}

		}	
	}

	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

		try {
			initFromFile(json_file);
			StdDraw.setCanvasSize();
			StdDraw.setYscale();
			StdDraw.setXscale();
			drawFunctions(512, 512,new Range(0, 1),new Range(0,1) , 500);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}





}
