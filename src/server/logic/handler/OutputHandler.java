package server.logic.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import server.logic.handler.model.Output;
import server.logic.model.Course;
import server.logic.model.Student;
import server.logic.tables.CourseTable;
import server.logic.tables.StudentTable;
import utilities.Config;

public class OutputHandler {
	
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int STUDENT = 3;
    public static final int CREATESTUDENT=4;
    public static final int CREATECOURSE=5;
    public static final int CANCELCOURSE=6;
    public static final int DELETESTUDENT=7;
    public static final int REGISTER=8;
    public static final int DROP=9;
    public static final int TAKE=10;
    public static final int CLERKLOGIN=11;
    public static final int STUDENTLOGIN=12;
    public static final int COURSEHASFINAL=14;
    public static final int COURSEHASPRERECS=15;
    Student student;
    
    
    public Output clerkLogin(String input) {
		Output output=new Output("",0);
		if(input.equalsIgnoreCase(Config.CLERK_PASSWORD)){
			output.setOutput("Please choose one option from the following menu: Create course, Creste Student, Cancel course, Destroy Course");
        	output.setState(CLERK);
		}else{
			output.setOutput("Wrong Password!Please Input The Password:");
        	output.setState(CLERKLOGIN);
		}
		return output;
	}
    public Output userLogin(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        //boolean email=strArray[0].contains("@");
        int result=0;
        if(strArray.length!=2){
        	output.setOutput("Your input should in this format:'username,password'");
        	output.setState(STUDENTLOGIN);
        }else{
        	result=StudentTable.getInstance().checkStudent(strArray[0], strArray[1]);
        	if(result==0){
        		output.setOutput("Please choose one of the options: Register, Drop, Deregister");
            	output.setState(STUDENT);
				student=new Student(strArray[0],strArray[1]);
        	}else if(result==1){
        		output.setOutput("Wrong Credentials!Please Input The Username and Password:'username,password'");
            	output.setState(STUDENTLOGIN);
        	}else{
        		output.setOutput("The User Does Not Exist!Please Re-Input The Username and Password:'username,password'");
            	output.setState(STUDENTLOGIN);
        	}
        }
		return output;
	}
    public Output createStudent (String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        //boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=2){
        	output.setOutput("Your input should in this format:'username,password'");
        	output.setState(CREATESTUDENT);
        }else{
        	result=StudentTable.getInstance().createStudent(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		FileInputStream fis = null;
				try {
					fis = new FileInputStream("Students.ser");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
    			ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(fis);
				} catch (IOException e) {
					e.printStackTrace();
				}
    			Student result1 = null;
				try {
					result1 = (Student) ois.readObject();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
    			try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		output.setOutput("Success! You've Created A new Account:"+result1.Name());
        	}else{
        		output.setOutput("The User Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	public Output createCourse (String input) {
		boolean hasPrerecs=false, hasFinal = false;
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        Object result="";
        if(strArray.length!=5 ) {
        output.setOutput("Your input should be in this format:'Course Code, Course Title, Has Final(if no input no), has prerecs(if no input no), course capacity");
    	output.setState(CREATECOURSE);
    	}else { 
    		if(strArray[2].equalsIgnoreCase("yes"))
    			hasFinal=true;
    		else if(strArray[2].equalsIgnoreCase("no"))
    			hasFinal=false;
    		else {
    		output.setOutput("For Having A Final it's Either Yes/NO. Your input should in this format:'Course Code, Course Title, Has Final(if no input no), has prerecs(if no input no), course capacity");
    		return output;}
    		if(strArray[3].equalsIgnoreCase("yes")) {
    			hasPrerecs=true;
    		}
    		if(strArray[3].equalsIgnoreCase("no"))
    			hasPrerecs=false;
    		else {output.setOutput("For Having Prerequisites It's Either Yes/NO. Your input should in this format:'Course Code, Course Title, Has Final(if no input no), has prerecs(if no input no), course capacity");
    		return output;}
    		result=CourseTable.getInstance().createCourse(Integer.parseInt(strArray[0]), strArray[1], hasFinal, hasPrerecs,Integer.parseInt(strArray[4]));
      
    		if(result.equals(true)){
        		output.setOutput("Success! You've Created A New Course"+strArray[2]);
        	}else{
        		output.setOutput("The Course Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
        	
    	}
	public Output cancelCourse(String input) {
        
		return null;
	}
	public Output register(String input) {
		List<Course> courses= new ArrayList<Course>();
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        Object result="";
    	try {FileInputStream fi = new FileInputStream(new File("Courses.ser"));
		ObjectInputStream oi = new ObjectInputStream(fi);

		// Read objects
		while(true) {
		Course cr = (Course) oi.readObject();
		courses.add(cr);
		oi.close();
		fi.close();
		}
		
	} catch (FileNotFoundException e1) {
		System.out.println("File not found");
	} catch (IOException e2) {
		System.out.println("Error initializing stream");
	} catch (ClassNotFoundException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}
		 for(int i=0;i<courses.size();i++) {
	        	System.out.print(courses.get(i).Code());;}
   
        if(strArray.length!=2 ) {
        output.setOutput("Your input should be in this format:(Course Code, Course Title)");
    	output.setState(REGISTER);}
    	else {
		student.courses.add(new Course(Integer.parseInt(strArray[0]),strArray[1]));
        }
		return output;
	}
	
}
