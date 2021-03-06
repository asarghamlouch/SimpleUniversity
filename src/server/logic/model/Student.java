package server.logic.model;

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
		return courseCompleted;
	}
	@Override
	public int StudentNumber() {
		return this.sid;
	}
	@Override
	public String Name() {
		return username;
	}
	@Override
	public List<Course> CurrentCourses() {
		return courses;
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
  

	@Override
	public Boolean RegisterCourse(Course course) {
		if(courses.contains(course))
		return true;
		return false;
	}

	@Override
	public Boolean DropCourse(Course course) {
		course.students.remove(new Student(this.sid,this.username,this.password));
	if(course.students.contains(new Student(this.sid,this.username,this.password)))
		return false;
	return true;
	}

	@Override
	public Boolean DeRegisterCourse(Course course) {	    
	   if (courses.contains(course))
		return false;
		return true;
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
