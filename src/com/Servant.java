package com;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Servant extends UnicastRemoteObject implements HelloService{

	/*
	 * This module defines the methods in the interface HelloService.
	 */
	static JFrame mainFrame ; // initializing frame
	static JTextArea Area ; // initializing area
	static JLabel Head ; // initializing label
	static JScrollPane scroll ;
	static CopyOnWriteArrayList<String> data_store = new CopyOnWriteArrayList<String>(); //Array List where everything will be stored.
	
	private static final long serialVersionUID = 1L;
	
	public Servant() throws RemoteException {
		super();
		
	}
	
	/*
	 * @method GUI
	 * @input : none
	 * @output : none
	 * @description : This method prepares the GUI for the Server 
	 * */
	
	static void GUI() {
		
		mainFrame = new JFrame("Server");  // object of Jframe
		mainFrame.setSize(400,400);  // setting the size
		 mainFrame.setLayout(new GridLayout(2, 1));  // setting the layout
		 mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		 
		 Head = new JLabel("Server Log" , JLabel.CENTER);
		 Area = new JTextArea();
		 mainFrame.add(Head); // add a component
		 scroll = new JScrollPane(Area);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		 mainFrame.add(scroll);
		 
		 mainFrame.setVisible(true); // setting the visibility
		
	}

	/*
	 *@ Method : build
	 *@input : none 
	 *@output : void
	 *@description : As soon as the server starts, the method shall load the data from file to main memory.
	 *  
	 * */
	
	public  void build() {
		
		GUI();
		Area.setText("Server process initialized \n");
		File file1 = new File("StudentRequest.txt"); // initialize the file 
		File file2 = new File("FacultyResults.txt"); // initialize the file
		String data ;
		BufferedReader br = null ;
		 try {
		FileReader reader1 = new FileReader(file1); // initialize a file reader 
		
		
		
		if(file1.exists()) {
			br = new BufferedReader(reader1);
			
				while (( data = br.readLine()) != null) {
				   data_store.add(data);           // add the data available from file to main memory
				}
			 reader1.close(); // closing the file handler
			 br.close();
				}
				} catch (IOException e) {
					
				}
		 try {
			 FileReader reader2 = new FileReader(file2); 
			 if(file2.exists()) {
					br = new BufferedReader(reader2); // get a buffered reader
					
						while (( data = br.readLine()) != null) {
						   data_store.add(data);  // stores the data in memory
						}
					 reader2.close(); // closing the file handler
					 br.close();
			 }
		} catch (Exception e) {
			
		}
		}
	/*
	 *@method : deleteData
	 *@input : none
	 *@output : none
	 *@description : This method removes the data from Main memory
	 * 
	 * */
	
	public void deleteData(String value) {
		if(value != null) {  // check if null
			if(value.equalsIgnoreCase("Req")) {
				int i = 0 ;
				for(String data : data_store) {
					if(data.indexOf("Approved") == -1 ) {
						data_store.remove(i);           // remove the data elements 
						i++;
					}
				}
			}
			if(value.equalsIgnoreCase("Res")) {
				int i = 0 ;
				for(String data : data_store) {
					if(data.indexOf("Approved") != -1 ) {
						data_store.remove(i);        // remove the data elements
						i++;
					}
				}
			}
		}
	}
	
	
	/*
	 *@ Method : fromStudent
	 *@input : Student Name  , Course 
	 *@output : Integer : Successful : 1 , Unsuccessful : 0
	 *@Description : Method utilized to fetch data from student process and store it in a file  
	 * */
	@Override
	public int fromStudent(String StudentName, String Course) throws RemoteException {
		if(StudentName == null && Course == null) {
			return 0; // return 0 if data is invalid
				}
		try {
			Area.append("Request received from student " + StudentName + " " + Course + " \n");
			FileWriter file = new FileWriter("StudentRequest.txt",true); // get the file writer
			String a = StudentName + " " + Course;
			BufferedWriter bw = new BufferedWriter(file);  // get the BufferedWriter instance
			data_store.add(a); // adding data to main memory ArrayList
			bw.write(a); // writing data to a file writer
			bw.newLine(); // write a new line to file
			bw.close(); // closing the file 
			
		} catch (IOException e) {
			return 0;
		}
		
		return 1; // return successful if data is written to the file
	}
	
	
	/*
	 *@method : chkForRequest
	 *@input : none 
	 *@output : String :- requests to Advisor
	 *@description : Method is used to check if there is any update in process by Faculty process
	 *  
	 * */
	@Override
	public String chkForRequest() throws RemoteException {
		BufferedReader br = null ;
		String CurrentLine = " " ;
		String StudentInfo  = " " ;
		File file = new File("StudentRequest.txt"); // Get the file handler
		try {
			Area.append("Advisor Process is checking for requests\n");
			FileReader reader = new FileReader(file) ; // initialize the FileReader
			if(file.exists()) {
				Area.append("Request Found \n");
			br = new BufferedReader(reader);
			 while (( CurrentLine = br.readLine()) != null) {
                StudentInfo += CurrentLine + "# " ;}
			 reader.close(); 
			 deleteData("Req");
			 file.delete(); 
			 return StudentInfo ; 
			 
			}
			else {
				Area.append("No Request Found \n");
				return null ; // If file is non-existent, exit and return null
			}
		} catch (Exception e) {	}
		return null;
		
	}
	
	/*
	 *@method : resultFromFaculty
	 *@input : String :- result from faculty process 
	 *@output : String :- result
	 *@description : Method is used to get the results of the Advisor and store it in a file  
	 * */

	@Override
	public String resultFromFaculty(String resultSet) throws RemoteException {
		if(resultSet == null) {
			return "No values in Advisor Process" ; // return, if data is invalid
				}
		
		try {
			
			FileWriter file = new FileWriter("FacultyResults.txt",true);
			BufferedWriter bw = new BufferedWriter(file); 
			String a[] = resultSet.split("#"); 
			
			for(int i = 0 ; i < a.length ; i++) 	{
				data_store.add(a[i]);
				bw.write(a[i]); 
				bw.newLine(); 
			}
		
			bw.close();
			Area.append("Result received from Advisor. Save Successful. \n");
			return "Successfully Stored" ;
		} catch (IOException e) {
		}
		return "success" ;
		
		
	}
	
	/*
	 *@method : chkFromFaculty
	 *@input : None
	 *@output : String :- result
	 *@description : Method used to get the results to Notification Process  
	 * */
	@Override
	public String chkFromFaculty() throws RemoteException {
		BufferedReader br = null ; 
		String CurrentLine = " " ;
		String StudentInfo  = " " ;
		File file = new File("FacultyResults.txt");
		try {
			FileReader reader = new FileReader(file) ;
			if(file.exists()) { // if file is existent
				Area.append("Result sent to Notification \n");
			br = new BufferedReader(reader);
			 while (( CurrentLine = br.readLine()) != null) {
                StudentInfo += CurrentLine + "# " ; }
			 reader.close();
			 deleteData("Res");
			 file.delete(); 
			 return StudentInfo ; 			 
			}
			else {
				Area.append("No update for Notification \n");
				return "no_data" ;  // return no date if file is non existent.
			}
		} catch (Exception e) {
			
			return "no_data" ;
		}
	}

}
