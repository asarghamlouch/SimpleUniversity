package server.logic.model;

public class Assignment {
int number;
int weightOfAssignment;
 public Assignment(int number, int weight) {
	 this.number=number;
	 this.weightOfAssignment=weight;
 }
  
 public int getNumberOfAssignment() {
	 return this.number;
 }
 public int getWeight() {
	 return this.weightOfAssignment;
 }
 
 public void setNumberOfAssignment(int number) {
	 this.number=number;
 }
 public void setWeightOfAssignment(int weight) {
	 this.weightOfAssignment=weight;
 }
}
