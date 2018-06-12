package com;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class FacultyProcess {
	
	static JFrame mainFrame ; // initializing frame
	static JTextArea Area ; // initializing area
	static JLabel Head ; // initializing the label
	static JScrollPane scroll ;
	
	
	
	/*
	 * @method GUI
	 * @input : none
	 * @output : none
	 * @description : This method prepares the GUI for the Faculty Process
	 * */
	
	static void GUI() {
		
		mainFrame = new JFrame("Advisor Process");  // object of Jframe
		mainFrame.setSize(400,400);  // setting the size
		 mainFrame.setLayout(new GridLayout(2,1));  // setting layout
		 mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		 
		 Head = new JLabel(" Advisor Console" , JLabel.CENTER);
		 Area = new JTextArea();
		 scroll = new JScrollPane(Area);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 mainFrame.add(Head); // adding component
		 mainFrame.add(scroll);
		 mainFrame.setVisible(true); // setting the visibility
		
	}

	/*
	 *@method : main
	 *@input : String[] 
	 *@output : void
	 *@description : This method is used to call chkupdate at regular intervals for updates  
	 * */
	public static void main(String[] args)  {
		GUI();
		Area.setText("Advisor process initialized \n");
		try {
			while(true) {
			chkupdate();  // calls the method
			Thread.sleep(3000); // sleep of 3000 microseconds
			
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); // get the error stream
		}
		
		}
	
	/*
	 *@method : chkupdate
	 *@input : none 
	 *@output : void
	 *@description : This method makes the decision randomly for the course
	 * */
	public static void chkupdate() throws MalformedURLException, RemoteException, NotBoundException {
		HelloService servic = (HelloService) Naming.lookup("rmi://localhost:8080/hello");
		String StudentInfo = servic.chkForRequest();
		String resultSet = " " ;
		if (StudentInfo == null) {
			
			Area.append("No message found \n");
		}
		else {
			Area.append("Processing the Request \n");
			String []eachRequest = StudentInfo.split("#");
			
			for(int i = 0 ; i < eachRequest.length - 1 ; i++ ) {
				
				if(new Random().nextInt(10) < 5 ) {
					eachRequest[i] = eachRequest[i] + " Approved ";
					Area.append(eachRequest[i] + " \n");
				}
				else {
					eachRequest[i] = eachRequest[i] + " Not Approved ";
					Area.append(eachRequest[i] + " \n");
				}
				  resultSet += eachRequest[i] + "#";
				  
			}
			
			if(resultSet != null) {
				String a = servic.resultFromFaculty(resultSet);
				Area.append(a + " / Processed \n");
			}
		}
			 
		}
		
	}
	

