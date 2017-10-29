package server.logic.tables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Student;

import utilities.Trace;

public class StudentTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();

	  public static class StudentListHolder {
	        static final StudentTable INSTANCE = new StudentTable();
	    }
		public static final StudentTable getInstance() {
	        return StudentListHolder.INSTANCE;
	    }
		public int checkStudent(String string, String string2) {
			int result=0;
			int flag=0;
			int index=0;
			try {FileInputStream fi = new FileInputStream(new File("Students.ser"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			while(true) {
			Student pr1 = (Student) oi.readObject();
			studentList.add(pr1);
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
			for(int i=0;i<studentList.size();i++) {
				if(studentList.get(i).Name().equalsIgnoreCase(string)){
					flag=flag+1;
					index=i;
				}else{
					flag=flag+0;
				}}
			boolean password=studentList.get(index).getPassword().equalsIgnoreCase(string2);
			if(flag!=0 && password){
				result=0;
				Student loggedinStudent=new Student(string,string2);
				}else if(flag==0){
				result=2;
			}else if(password==false){
				result=1;
			}return result;

				
			}
			
		
		
		public Object createStudent(String string, String string2) {
			boolean result=true;
			int flag=0;
			for(int i=0;i<studentList.size();i++){
				String email=(studentList.get(i)).Name();
				if(email.equalsIgnoreCase(string)){
					flag=flag+1;
				}else{
					flag=flag+0;	
				}
			}
			if(flag==0){
				Student newuser=new Student(studentList.size(),string,string2);
				try {
					// write object to file
					FileOutputStream fos = new FileOutputStream("Students.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(newuser);
					oos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				result=studentList.add(newuser);
				logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Success", string,string2));
			}else{
				result=false;
				logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", string,string2));
			}
			return result;
		}

}