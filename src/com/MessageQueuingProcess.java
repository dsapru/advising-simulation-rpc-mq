package com;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessageQueuingProcess {
	/*
	 * @method : main
	 * @input : String[]
	 * @output :  void
	 * @description : Method creates a registry and stores the object of Servant class in registry
	 * 
	 * */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		Registry registry = LocateRegistry.createRegistry(8080); // creates a Registry at port 8080
		Servant ser = new Servant();
		ser.build();
		registry.rebind("hello",ser); // binds the object of the Servant class to registry
	
	}	

}
