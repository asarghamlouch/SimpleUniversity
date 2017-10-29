package server.logic.model;

public class Midterm {
	int number;
	int weightOfMidterm;
	 public Midterm(int number, int weight) {
		 this.number=number;
		 this.weightOfMidterm=weight;
	 }
	  
	 public int getNumberOfMidterm() {
		 return this.number;
	 }
	 public int getWeight() {
		 return this.weightOfMidterm;
	 }
	 
	 public void setNumberOfMidterm(int number) {
		 this.number=number;
	 }
	 public void setWeightOfMidterm(int weight) {
		 this.weightOfMidterm=weight;
	 }
}
