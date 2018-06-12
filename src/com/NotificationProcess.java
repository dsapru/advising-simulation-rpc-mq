package com;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
/*
 * @author : Tushar Deshpande 
 * @ netId : tvd6298
 * @reference : This program take 1 online reference and stackoverflow site for the properties file for databese
 * 			1) www.youtube.com    url : https://www.youtube.com/watch?v=X-bL0S8b6C4
 * 			2) stack overflow 
 * @book reference : Java 2 The Complete Reference 5th edition by HerbertSchildt
 * 
 * */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NotificationProcess {
	static JFrame mainFrame ; // initializing frame
	static JTextArea Area ; // initializing area
	static JLabel Head ; // initializing label
	static JScrollPane scroll ; // initializing scroll
	
	/*
	 * @method GUI
	 * @input : none
	 * @output : none
	 * @description : This method prepares the GUI for the Notification Process
	 * */
	
	static void GUI() {
		
		mainFrame = new JFrame("Notification Process");  // object of Jframe
		mainFrame.setSize(400,400);  // setting the size
		 mainFrame.setLayout(new GridLayout(2, 1));  // setting the layout
		 mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });
		 
		 Head = new JLabel("Notifications" , JLabel.CENTER);
		 Area = new JTextArea();
		 mainFrame.add(Head); // adding a component
		 scroll = new JScrollPane(Area);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		 mainFrame.add(scroll);
		 
		 mainFrame.setVisible(true); // set the visibility
		
	}

	/*
	 *@method : main
	 *@input : String[] 
	 *@output : void
	 *@description : This method is used to call notifyStudent at regular intervals  
	 * */

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {
		GUI();
		Area.setText("Notifiation process initialized \n");
		while(true) {
		notifyStudent(); // call the method
		Thread.sleep(7000); // sleep for 7000 microseconds
		
		}

	}
	
	/*
	 *@method : notifyStudent
	 *@input : none 
	 *@output : void
	 *@description : This method is to notify the Student Process about the decision of Advisor Process 
	 * */
	public static void notifyStudent() throws MalformedURLException, RemoteException, NotBoundException {
		HelloService servic = (HelloService) Naming.lookup("rmi://localhost:8080/hello"); // Get the HelloService Object from Registry
		String notifications = servic.chkFromFaculty(); // call the chkFromFacultyMethod
		
		if(notifications.equalsIgnoreCase("no_data")){
				Area.append("No message found\n");
			
		}
		else {
			
			String []eachRequest = notifications.split("#");
			for(int i = 0 ; i < eachRequest.length - 1 ; i++ ) {
				int j = i+ 1 ;
				Area.append(j +" )"+eachRequest[i] + " \n");
				
			}
			
		}
	}

}
