package com;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StudentProcess {
	
		private static JFrame mainFrame;         // initializing frame
		private static JLabel head;       // initializing label
		private static JLabel studNameLabel;
		private static JLabel CourseNameLabel;
		private static JLabel result;
		private static JTextField studentName , Course ;
		private static JButton send ;
		
	public static void buildGUI() {
		 mainFrame = new JFrame("Student Process"); // initializing the frame
	      mainFrame.setSize(500,500);   // set the size
	      mainFrame.setLayout(new GridLayout(7, 1));   // setting the layout
	      //It will exit the program if the GUI is closed on clicking exit.
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });   
	      head = new JLabel("Welcome to Course Registration" , JLabel.CENTER);
	      studNameLabel = new JLabel("Enter Name");
	      studentName = new JTextField();
	      CourseNameLabel = new JLabel("Enter Course");
	      Course = new JTextField();
	      send = new JButton("Submit Request");
	      result = new JLabel();
	     
	       send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
			HelloService servic = (HelloService) Naming.lookup("rmi://localhost:8080/hello");
			String Sname = studentName.getText();
			String Scourse = Course.getText();
			
			if(Sname != null && Scourse != null) {
			if(servic.fromStudent(Sname, Scourse) == 1) {
				result.setText("Request sent successfully ( " + Sname + " ," + Scourse + " )");
			}
			}
			else {
				result.setText("Enter proper value");
			}
				}
				catch( Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
	      mainFrame.add(head);
	      mainFrame.add(studNameLabel);
	      mainFrame.add(studentName);
	      mainFrame.add(CourseNameLabel);
	      mainFrame.add(Course);
	      mainFrame.add(send);
	      mainFrame.add(result);
	    
	      
	      
	     
	     mainFrame.setVisible(true);   
		
	}

	public static void main(String[] args) {
		
		buildGUI();
	}

}
