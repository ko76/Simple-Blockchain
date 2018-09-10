all:
	javac -cp lib/gson-2.8.6-SNAPSHOT.jar -d classes src/*.java


clean :
	rm classes/*.class
