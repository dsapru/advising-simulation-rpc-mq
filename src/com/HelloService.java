package com;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {
	
	public int fromStudent(String StudentName , String Course)throws RemoteException ; // Method involved in getting data from Student Process
	public String chkForRequest() throws RemoteException ; // Method involved in checking if there is any update by Advisor Process
	public String resultFromFaculty(String b) throws RemoteException ; // Method involved in fetching results of the Advisor and store it.
	public String chkFromFaculty() throws RemoteException ; // Method involved involved in getting the results to Notification Process,
	

}
