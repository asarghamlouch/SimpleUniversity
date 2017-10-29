package server.logic.model;

public class ProjectCourse extends Course {

	public ProjectCourse(boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal,
			int capSize, String title, int myCode) {
		super(enforcePrereqs, numberOfMidterms, numberOfAssignments, hasAFinal, capSize, title, myCode);
	}

}
