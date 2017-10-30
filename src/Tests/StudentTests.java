package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.model.Student;
import server.logic.model.University;

public class StudentTests {

	@Test
	public void Nametest() {
		Student s=new Student(1,"asar","123456");
		s.Name();
	}
	@Test
	public void Studenttest() {
		Student s=new Student(1,"asar","123456");
		s.StudentNumber();
	}
	@Test
	public void StudentCreatedtest() {
		Student s=new Student(1,"asar","123456");
		University u=new University();
		u.Students().add(s);
		s.IsCreated();
	}
	
}
