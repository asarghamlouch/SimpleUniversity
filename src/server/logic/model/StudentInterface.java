package server.logic.model;

import java.util.List;

public interface StudentInterface {
	List<Course> CompletedCourses();
	int StudentNumber();
	String Name();
	List<Course> CurrentCourses();
	Boolean IsFullTime();
	Boolean IsCreated(); //ie has been properly initialized and hooked to the university
	Boolean RegisterCourse(Course course); //attempts to register the student in the selected course
	Boolean DropCourse(Course course); //attempts to delete the student from course and assign DR
	Boolean DeRegisterCourse(Course course);
	//attempts to deregister the student
	// up to 2 weeks after term starts, the course does NOT appear as taken by the student
	// also need procedures that will fire events when the different components of a course are completed
}
