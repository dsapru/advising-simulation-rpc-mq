# advising-simulation-rpc-mq
A. Development 
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
•	Programming language: Java
•	Server- Port number 8080.
•	Communication model: Remote Method Invocation.
•	A regular text file (.txt) is used for storing.

B. Running the project 
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
•	Run the com.MessageQueuingProcess.java to start the Message Queuing Server.
  Please Note: Any process can be executed after this step, in any order.
•	Run the com.StudentProcess.java to open Student Process.
•	Run the com.FacultyProcess.java to open Advisor Process.
•	Run the com.NotificationProcess.java to open Notification Process.

C. Testing & Output
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
•	Upon running the Message Queuing Server, the GUI must display that server is up and running.
•	After running the Student Process, GUI must ask you to enter the name and the course and submit your request.
•	The Faculty Process shall approve/disapprove in a random fashion due to the presence of the random methods.
•	The outcome/result from the Advisor/Faculty must be displayed on the Notification Process.
•	In the absence/closure of Advisor/Faculty, the requests sent from the Student Process will be stored, and sent to the Advisor/Faculty, once the Process is restarted.
•	The same can be done with the Notification Process, it shall be updated automatically, once the process is resumed/restarted again.

D. Limitations
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
•	In certain older systems, it has been noticed that the RMI registry is required to initialized using the command prompt. 
•	Also, the other alternative can be to add the rmiregistry.exe into the External Tools Configuration using the path of the same, which is usually like C:\Program Files\Java\jdk1.8.0_144\bin\rmiregistry.exe
