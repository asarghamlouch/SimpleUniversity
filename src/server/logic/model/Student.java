package server.logic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	List<Course> courseCompleted;
    int totalMark;
    University university=new University();
	public Student(int sid,String username,String password){
		this.sid=sid;
		this.password=password;
		this.username=username;
		courses = new ArrayList<Course>();
		courseCompleted = new ArrayList<Course>();
	}
	public Student(String username,String password){
		this.password=password;
		this.username=username;
		courses = new ArrayList<Course>();
		courseCompleted = new ArrayList<Course>();
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
	
		return null;
	}
	@Override
	public int StudentNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String Name() {
		return username;
	}
	@Override
	public List<Course> CurrentCourses() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean IsFullTime() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean IsCreated() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean RegisterCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean DropCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean DeRegisterCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
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

}
