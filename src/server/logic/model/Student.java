package server.logic.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable, StudentInterface{
	/**
	 * 
	 */
	int sid;
	String username;
	String password;
	public List<Course> courses;
	boolean isFullTime;
	boolean isPartTime;
	List<Integer> courseCompleted;
    int totalMark;
    University university=new University();
	public Student(int sid,String username,String password){
		this.sid=sid;
		this.password=password;
		this.username=username;
		courses = new ArrayList<Course>();
		courseCompleted = new ArrayList<Integer>();
	}
	public Student(String username,String password){
		this.password=password;
		this.username=username;
		courses = new ArrayList<Course>();
		courseCompleted = new ArrayList<Integer>();
	}
	public String toString(){
		return "["+this.sid+","+this.username+","+this.password+"]";
	}

	
	public void setUserid(int userid) {
		this.sid = userid;
	}


	

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<Course> CompletedCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int StudentNumber() {	
		return sid;
	}

	@Override
	public String Name() {
		return username;
	}

	@Override
	public List<Course> CurrentCourses() {
		while(true)
		{
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream("Courses.ser"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		   Course c = null;
		try {
			c = (Course) in.readObject();
			return courses;
		}
		catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
		   courses.add( c );
		}
		
	}

	@Override
	public Boolean IsFullTime() {
		return isFullTime;
	}

	@Override
	public Boolean IsCreated() {
		if(university.Students().contains(new Student(this.sid,this.username,this.password)))
		return true;
		return false;
	}
    public int addMark(Course course) {
    	int studentMark=0;
    	for(int i=0;i<course.assignments.size();i++)
    		studentMark+=course.assignments.get(i).getWeight();
    	for(int i=0;i<course.midterms.size();i++)
    		studentMark+=course.midterms.get(i).getWeight();
    	studentMark+=course.WeightOfFinal();
    	return studentMark;
    }
	@Override
	public Course SelectCourse(List<Course>courses) {
		return null;
	}

	@Override
	public Boolean RegisterCourse(Course course) {
		return null;
	}

	@Override
	public Boolean DropCourse(Course course) {
		return null;
	}

	@Override
	public Boolean DeRegisterCourse(Course course) {	    
	   if (courses.contains(course))
		return true;
		return false;
	}
}
