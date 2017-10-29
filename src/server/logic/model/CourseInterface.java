package server.logic.model;

import java.util.List;

public interface CourseInterface {
	String Title();
	int Code(); //6 digits as explained above
	List<Student> Students(); //return list of students currently in the course
	List<Integer> PreRequisites(); //list of course codes required for this one
	//all procedures pertaining to the weight are relative to the final grade for the course
	// which is out of 100. So weights should add up to 100.
	int WeightOfAssignment (int assignmentNumber); //weight of a specific assignment
	int WeightOfMidterm (int midtermNum);
	int WeightOfFinal ();
	int MarkForStudent (Student student); //returns a grade out of 100
	Boolean HasProject(); //hardwired to return false
	Boolean IsFull(); //has reached CapSize
	Boolean AddStudent (Student s); //return false if fails
	Boolean RemoveStudent(Student s); //true if removed
}
