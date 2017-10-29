package Tests;

import org.junit.Test;

import server.logic.model.Assignment;
import server.logic.model.Course;
import server.logic.model.Midterm;
import server.logic.model.Student;

public class CourseTest {


	@Test
	public void codeTest() {
		Course course1=new Course(false, 1,3,false,10,"operating Systems",123456);
		course1.Code();
	}//returning the course Code'succeed';
	@Test
	public void titleTest() {
		Course course1=new Course(false, 1,3,false,10,"operating Systems",123456);
		course1.Title();
	}//returning the course title 'succeed'
	@Test
	public void students() {
		Course course1=new Course(false, 1,3,false,10,"operating Systems",123456);
		Student s1=new Student(1,"asar","123456");
		Student s2=new Student(2,"tom","123456");
		if(course1.AddStudent(s1))
		   course1.Students().add(s1);
		if(course1.AddStudent(s2))
			   course1.Students().add(s2);
	}//adding students to a certain course and returning the list of students 'succeed'
	@Test
	public void prerecsTest() {
		Course course1=new Course(false, 1,3,false,10,"java",325698);
		Course course2=new Course(true, 0,5,false,20,"object oriented",789456);
		course2.prerecs.add((Integer)course1.Code());
	}//adding a prereq for the Object Oriented course and returning the list of prereqs
	@Test
	public void weightOfAssignmentTest() {
		Course course1=new Course(false, 1,3,false,10,"java",325698);
		Assignment a1=new Assignment (1, 20);
		Assignment a2=new Assignment (2, 10);
		course1.assignments.add(a1);
		course1.assignments.add(a2);
		course1.WeightOfAssignment(a1.getNumberOfAssignment());
		course1.WeightOfAssignment(a2.getNumberOfAssignment());
	}
	@Test
	public void weightOfMidtermTest() {
		Course course1=new Course(false, 1,3,false,10,"java",325698);
		Midterm m1=new Midterm (1, 20);
		Midterm m2=new Midterm (2, 10);
		course1.midterms.add(m1);
		course1.midterms.add(m2);
		course1.WeightOfMidterm(m1.getNumberOfMidterm());
		course1.WeightOfMidterm(m2.getNumberOfMidterm());
	}
	@Test
	public void weightOfFinalTest() {
		Course course1=new Course(false, 1,3,false,10,"java",325698);
		Midterm m1=new Midterm (1, 40);
		Midterm m2=new Midterm (2, 10);
		course1.midterms.add(m1);
		course1.midterms.add(m2);
		Assignment a1=new Assignment (1, 40);
		Assignment a2=new Assignment (2, 10);
		course1.assignments.add(a1);
		course1.assignments.add(a2);
		course1.WeightOfFinal();//no final since the weight of midterm and assignments is already 100
		Course course2=new Course(false, 1,3,false,10,"Os",125698);
		Midterm m3=new Midterm (1, 10);
		Midterm m4=new Midterm (2, 10);
		course1.midterms.add(m3);
		course1.midterms.add(m4);
		Assignment a3=new Assignment (1, 40);
		Assignment a4=new Assignment (2, 10);
		course2.assignments.add(a3);
		course2.assignments.add(a4);
		course2.WeightOfFinal();
		
	}
	

}
