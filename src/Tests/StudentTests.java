package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.model.Student;

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

}
