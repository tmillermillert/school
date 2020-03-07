I used mysql-connector-java-8.0.17.jar and com.mysql.cj.jdbc.Driver.
Compile and run all programs from src folder.

USAGE: 	java ser322.JdbcLab <url> <user> <passwd> <driver> query1
		java ser322.JdbcLab <url> <user> <pwd> <driver> query2 <DeptNo>
		java ser322.JdbcLab <url> <user> <pwd> <driver> dml1 <customer id> <product id> <name> <quantity>


		java ser322.JdbcLab <url> <user> <pwd> <driver> export <filename>

		This program assumes a file name ASSIGNMENT5.xml in the src folder.
		java ser322.JdbcLab2 DEPTNO

examples

javac -d . ser322.JdbcLab
javac -d . ser322.JdbcLab2

java ser322.JdbcLab query1
java ser322.JdbcLab query2 10
java ser322.JdbcLab dml1 11 "Tanner Miller" 11 11
java ser322.JdbcLab export ASSIGNMENT5.xml


java ser322.JdbcLab2 10