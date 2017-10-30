package server.logic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class University implements UniversityInterface, Serializable {
	int UniversityCourses;
	int MaxCoursesForFTStudents;
	int MaxCoursesForPTStudents;
	int PassRate;
    List <Course> courses;
    List <Student> Ustudents;
	public University() {
		if(UniversityCourses<0 || UniversityCourses>25)
			throw new IllegalArgumentException("The number of university courses should be between 0 and 25");
		MaxCoursesForFTStudents=4;
		MaxCoursesForFTStudents=2;
		PassRate=70;
		courses = new ArrayList<Course>();
		Ustudents=new ArrayList<Student>();
	}
	@Override
	public List<Course> Courses() {
		return this.courses;
	}
	@Override
	public List<Student> Students() {
		return Ustudents;
	}
	@Override
	public Course CreateCourse(String name, int cap) {
	Course newCourse=new Course(name,cap);
    return newCourse;
	}
	@Override
	public void RegisterStudentForCourse(Student student, Course aCourse) {
		aCourse.students.add(student);
	}
	@Override
	public void CancelCourse(Course course) {
		course.students.clear();
	}
	@Override
	public void DestroyCourse(Course course) {
		courses.remove(course);
	}
	
	
	
}
