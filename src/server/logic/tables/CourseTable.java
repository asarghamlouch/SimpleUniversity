package server.logic.tables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import server.logic.model.Course;
import server.logic.model.Student;
import utilities.Trace;

public class CourseTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Course> courseList=new ArrayList<Course>();
	 public static class CourseListHolder {
	        private static final CourseTable INSTANCE = new CourseTable();
	    }

	public static CourseTable getInstance() {
		 return CourseListHolder.INSTANCE;
	}
	
	public Object createCourse(int code, String title, boolean hasFinal, boolean hasPrerecs, int cap) {
		int low1=0;
		int high1=3;
		Random r1=new Random();
		int numbOfMid = 0;
		int low2=0;
		int high2=6;
		Random r2= new Random();
		int numbOfAs=0;
		boolean result=true;
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			int c=(courseList.get(i)).Code();
			if(String.valueOf(c).equalsIgnoreCase(String.valueOf(code))){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			numbOfMid=r1.nextInt(high1-low1) + low1;
			numbOfAs=r2.nextInt(high2-low2) + low2;		
			Course newcourse=new Course(hasPrerecs, numbOfMid, numbOfAs, hasFinal, cap, title, code );
			try {
				// write object to file
				FileOutputStream fos = new FileOutputStream("Courses.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(newcourse);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result=courseList.add(newcourse);
			logger.info(String.format("Operation:Create New Course;Course Info:[%d,%s,%s,%s,%d,%d,%d];State:Success", code,title, hasFinal, hasPrerecs, cap,numbOfMid,numbOfAs));
		}
		else{
			result=false;
			logger.info(String.format("Operation:Create New Course;Course Info:[%d,%s,%s,%s,%d,%d,%d];State:Fail;Reason:The User already existed.", code,title, hasFinal, hasPrerecs, cap,numbOfMid,numbOfAs));
		}
		return result;
	}

	public Object cancelCourse(String string, String string2) {
		boolean result=true;
		List<Course> courses= new ArrayList<Course>();
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			String email=(courseList.get(i)).Title();
			if(email.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			try {FileInputStream fi = new FileInputStream(new File("Courses.ser"));
    		ObjectInputStream oi = new ObjectInputStream(fi);
    		// Read objects
    		while(true) {
    		Course cr = (Course) oi.readObject();
    		courses.add(cr);
    		oi.close();
    		fi.close();
    		}
    		
    	} catch (FileNotFoundException e1) {
    		System.out.println("File not found");
    	} catch (IOException e2) {
    		System.out.println("Error initializing stream");
    	} catch (ClassNotFoundException e3) {
    		// TODO Auto-generated catch block
    		e3.printStackTrace();
    	}
    		 for(int i=0;i<courses.size();i++) {
    	       if(String.valueOf(courses.get(i).Code()).equals(string) && courses.get(i).Title().equals(string2))
    	     courses.remove(courses.get(i));}
    	       try {
				new FileOutputStream("Students.ser").close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
    		 }
    	       for(int i=0;i<courses.size();i++) {
    	       try {
					// write object to file
					FileOutputStream fos = new FileOutputStream("Students.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(courses.get(i));
					oos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}}
    	       logger.info(String.format("Operation:Delete Course:[%s,%s];State:Success", string,string2));
		}else{
			result=false;
        	
		}
		return result;
	}

}
