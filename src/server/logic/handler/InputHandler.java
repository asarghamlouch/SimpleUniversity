package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int STUDENT = 3;
    public static final int CREATESTUDENT=4;
    public static final int CREATECOURSE=5;
    public static final int CANCELCOURSE=6;
    public static final int DELETESTUDENT=7;
    public static final int FORCEREGISTERSTUDENTS=8;
    public static final int REGISTER=9;
    public static final int DROP=10;
    public static final int TAKE=11;
    public static final int CLERKLOGIN=12;
    public static final int STUDENTLOGIN=13;
    
    OutputHandler outputHandler=new OutputHandler();
    
    public ServerOutput processInput(String input, int state) {
    	String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
	      if (state == WAITING) {
	        	output = "Who Are you?Clerk or Student?";
	            state = FINISHWAITING;
	            oo.setOutput(output);
	            oo.setState(state);
	         }else if (state == FINISHWAITING) {
	            if (input.equalsIgnoreCase("clerk")) {
	            	output="Please Input The Password:";
	            	state=CLERKLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("student")) {
	            	output="Please Input Username and Password:'username,password'";
	            	state=STUDENTLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	            else{
	            	output = "Who Are you?Clerk or Student?";
	            	state = FINISHWAITING;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }
	        }
	           else if(state==CLERKLOGIN){
		        	o=outputHandler.clerkLogin(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
		        }else if(state==STUDENTLOGIN){
		        	o=outputHandler.userLogin(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	            } else if (state==CLERK){
		        	if (input.equalsIgnoreCase("create Student")) {
		            	output = "Please Input Student Info:'username,password'";
		            	state=CREATESTUDENT;
		            	oo.setOutput(output);
			            oo.setState(state);
		            }else if (input.equalsIgnoreCase("create Course")) {
		            	output = "Please Input Course Info:'Course Code, Course Title, Has Final(if no input 0), has prerecs(if no input no), course capacity";
		            	state=CREATECOURSE;
		            	oo.setOutput(output);
			            oo.setState(state);
		        	}else if (input.equalsIgnoreCase("cancel course")) {
			            output = "Please Input The Code Of the Course You'd Like To Cancel:'";
			            state=CANCELCOURSE;
			            oo.setOutput(output);
			            oo.setState(state);
		            }else if (input.equalsIgnoreCase("delete student")) {
		            	output = "Please Input The Info of The Student You Like To Delete:";
		            	state=DELETESTUDENT;
		            	oo.setOutput(output);
			            oo.setState(state);
		            }else if(input.equalsIgnoreCase("log out")){
		            	output = "Successfully Log Out!";
		                state = WAITING;
		                oo.setOutput(output);
			            oo.setState(state);	
	                }
		        }else if (state==STUDENT){
		        	if (input.equalsIgnoreCase("Register")) {
		            	output = "Please Choose One Of The Following Courses to register it:(Course Code, Course Title)";
		            	state=REGISTER;
		            	oo.setOutput(output);
			            oo.setState(state);
		            }
		   
		        }else if(state==CREATESTUDENT){
		        	if(input.equalsIgnoreCase("log out")){
		            	output = "Successfully Log Out!";
		                state = WAITING;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else if(input.equalsIgnoreCase("main menu")){
		        		output = "Please choose one option from the following menu: Create course, Create Student, Cancel course, Destroy Course";
		                state = CLERK;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else{
		        		o=outputHandler.createStudent(input);
		        		output=o.getOutput();
		        		state=o.getState();
		        		oo.setOutput(output);
			            oo.setState(state);
		        	}
		        }else if(state==CREATECOURSE){
		        	if(input.equalsIgnoreCase("log out")){
		            	output = "Successfully Log Out!";
		                state = WAITING;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else if(input.equalsIgnoreCase("main menu")){
		        		output = "Please choose one option from the following menu: Create course, Creste Student, Cancel course, Destroy Course";
		                state = CLERK;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else{
		        		o=outputHandler.createCourse(input);
		        		output=o.getOutput();
		        		state=o.getState();
		        		oo.setOutput(output);
			            oo.setState(state);
		        	}
		        
		        }
		        else if(state==DELETESTUDENT){
		        	if(input.equalsIgnoreCase("log out")){
		            	output = "Successfully Log Out!";
		                state = WAITING;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else if(input.equalsIgnoreCase("main menu")){
		        		output = "Please choose one option from the following menu: Create course, Creste Student, Cancel course, Destroy Course";
		                state = CLERK;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else{
		        		o=outputHandler.deleteStudent(input);
		        		output=o.getOutput();
		        		state=o.getState();
		        		oo.setOutput(output);
			            oo.setState(state);
		        	}
		        
		        } else if(state==CANCELCOURSE){
		        	if(input.equalsIgnoreCase("log out")){
		            	output = "Successfully Log Out!";
		                state = WAITING;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else if(input.equalsIgnoreCase("main menu")){
		        		output = "Please choose one option from the following menu: Create course, Creste Student, Cancel course, Destroy Course";
		                state = CLERK;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else{
		        		o=outputHandler.cancelCourse(input);
		        		output=o.getOutput();
		        		state=o.getState();
		        		oo.setOutput(output);
			            oo.setState(state);
		        	}
		        }
		        else if(state==REGISTER){
		        	if(input.equalsIgnoreCase("log out")){
		            	output = "Successfully Log Out!";
		                state = WAITING;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else if(input.equalsIgnoreCase("main menu")){
		        		output = "Please choose one option from the following menu: Create course, Creste Student, Cancel course, Destroy Course";
		                state = CLERK;
		                oo.setOutput(output);
			            oo.setState(state);
		        	}else{
		        		o=outputHandler.register(input);
		        		output=o.getOutput();
		        		state=o.getState();
		        		oo.setOutput(output);
			            oo.setState(state);
		        	}
		        
		        }
    	 return oo;
		}
	
          
    }
