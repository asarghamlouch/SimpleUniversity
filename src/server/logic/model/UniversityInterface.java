package server.logic.model;

import java.util.List;

public interface UniversityInterface {
	
	List<Course> Courses();
	List <Student> Students();
	Course CreateCourse(String name, int cap);
    void RegisterStudentForCourse(Student student, Course aCourse);
	void CancelCourse(Course course); //all students are deregistered
	void DestroyCourse(Course course); //delete from list of university courses
}
