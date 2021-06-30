->Initially we have to install Oracle 11g Express Edition 
from http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
and run the application. During the installation you will be asked for admin credentials enter those(mandatory).
Once this is done open the URL something like this (127.0.0.1:8080/apex/f?p=4950) and enter the admin credentials. 
Then it takes you to create workspace so create a workspace with name(E-Store) and then import the file (E-Store.sql) 
provided in the documentation then the database setup is done.

->Now install Netbeans IDE from (https://netbeans.org/downloads/8.0.2/) and set the environment for the application and choose
Apache Tomcat server next do File->import the file (Internet_Store.zip) into workspace then add ojdbc.jar14 into project library.

->Servlet-Oracle database is established in the code. So, now run the project (main.jsp) where you will be having the options 
of registering a new customer or login. so create a new customer and by using his/her credentials login and make a transaction.

->For our project we need to use Trans_spec table as an input for our algorithm. Now using the file apriori.java providing with 
Trans_spec table as input run the file. So on the console you will be asked with min_support enter an integer value then you will 
look at the frequent itemsets at the end of the output.

-> In my project i took min_support=4 and saved the entire output to (output.txt) provided in documentation. 

-> Whole project is in Sugandam_Project.zip extract this file.
-> In that you will find Internet_Store.zip which is the java source code for the project extract it and import in netbeans.
-> E-Store.sql is a database tables files for oracle.
-> Tables for CSV is a visualization of data in its concerned tables of the database.
-> Sugandam_Projectreport a word file is the project report.
-> R Files are the visualization of how I got the five summary, Boxplot, Outliers.
-> Apriori.txt is an output file for the algorithm.
  