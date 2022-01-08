package kr.or.ddit.generic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GenericTest {
	public static class Box<T,E> {
	    private T object;
	    private List<E> contents = new ArrayList<>();
	    
	    public void set(T object) { this.object = object; }
	    public T get() { return object; }
	    public void add(E element) {
	    	contents.add(element);
	    }
	    public E getFromList(int index) {
	    	return contents.get(index);
	    }
	    
	    public static <P> void method(P param) {
	    	System.out.println(param.getClass());
	    }
	    
	    public static <C extends Collection> int estimateSize(C param) {
	    	return param.size();
	    }
	}
	
	public static void main(String[] args) {
//		Box box = new Box();
//		box.set(new Date());
//		Object obj = box.get();
//		if(obj instanceof Date) {
//			Date date = (Date) obj;
//		}
		Box<Date,Calendar> box2 = new Box<>();
		box2.set(new Date());
		Date date2 = box2.get();
		box2.add(Calendar.getInstance());
		Calendar cal = box2.getFromList(0);
		
		Box.method("text");
		Box.method(4);
		
		Box.estimateSize(new ArrayList<>());
		Box.estimateSize(new HashSet<>());
	}
}
