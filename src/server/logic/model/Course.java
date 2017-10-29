package server.logic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Course implements CourseInterface, Serializable {
private boolean enforcePrereqs;
private int numberOfMidterms;
private int numberOfAssignments;
private boolean hasAFinal;
private int capSize;
private String title;
private int myCode;
public boolean hasProject;
public List<Student> students;
public List<Integer> prerecs;
public List<Assignment> assignments;
public List<Midterm> midterms;
public Course(int code,String title) {
	this.myCode=code;
	this.title=title;
}
public Course(boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal, int capSize, String title, int myCode) {
	this.enforcePrereqs=enforcePrereqs;
	if (numberOfMidterms<0 || numberOfMidterms>2)
	throw new IllegalArgumentException("The number of midterms should be between 0 and 2");
	this.numberOfMidterms=numberOfMidterms;
	if (numberOfAssignments<0 || numberOfAssignments>5)
    throw new IllegalArgumentException("The number of Assignments should be between 0 and 5");
	this.numberOfAssignments=numberOfAssignments;
	this.hasAFinal=hasAFinal;
	this.hasProject=false;
	if (capSize>25)
	throw new IllegalArgumentException("The maximum capacity of a class is 25");
	this.capSize=capSize;
	this.title=title;
	if (String.valueOf(myCode).length()!=6 && String.valueOf(myCode).startsWith("0"))
	throw new IllegalArgumentException("The code of the course should be 6 digits and doesn't start with zero");
	this.myCode=myCode;
	 students = new ArrayList<Student>();
	 prerecs = new ArrayList<Integer>();
	 assignments=new ArrayList<Assignment>();
	 midterms=new ArrayList<Midterm>();
}
 public Course(String title, int cap) {
	 this.title=title;
	 this.capSize=cap;
 }

@Override
public String toString() {
	return "["+this.title+", "+this.myCode+", "+this.enforcePrereqs+", "+this.numberOfMidterms+", "+this.numberOfAssignments+", "+this.hasAFinal+", "+this.capSize+"]";
}
@Override
public String Title() {
	return this.title;
}
@Override
public int Code() {
return this.myCode;
}
@Override
public List<Student> Students() {
	return students;
}
@Override
public List<Integer>PreRequisites() {
	return prerecs;
}
int totalWeight;
@Override
public int WeightOfAssignment(int assignmentNumber) {
	
	return assignments.get(assignmentNumber-1).getWeight();
}
@Override
public int WeightOfMidterm(int midtermNum) {
	return midterms.get(midtermNum-1).getWeight();
}
@Override
public int WeightOfFinal() {
	int finalWeight=0;
	for(int i=0;i<assignments.size();i++)
		totalWeight+=assignments.get(i).getWeight();
	for(int i=0;i<midterms.size();i++)
		totalWeight+=midterms.get(i).getWeight();
	finalWeight=100-totalWeight;
	if(finalWeight<=0)
		return 0;
	return finalWeight;
}
@Override
public int MarkForStudent(Student student) {	
	return student.addMark(new Course(this.Code(),this.Title()));
}
@Override
public Boolean HasProject() {
	return hasProject;
}
@Override
public Boolean IsFull() {
	if(students.size()<capSize)
	return true;
	return false;
}
@Override
public Boolean AddStudent(Student s) {
if(students.contains(s))
	return true ;
	return false;
}
@Override
public Boolean RemoveStudent(Student s) {
	if(students.contains(s))
		return false;
	return true;
}

}
