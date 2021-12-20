# Adding the JUnit 5 Jars to your project.

### InteliJ
In order to use the Unit tests given you will need to let intelij know where to find the needed jars. (jars are just java version of an archived folder or zip file.). 

Intelij will actually find these jars for you while imporiting the project.

If for some reason it does not, you can add the jars in the following way:

1. Complete the process for "create project from existing resources"
2. Then go to File -> Project Structure.
3. Inside project Structure select the "Libraries" tab on the left.
4. You will see a "+" symbol near the top of the window. CLick this symbol.
5. A little window will popup asking what kind of library you want to import. Select "jar"
6. After selection jar, a file selector will appear, navigate to the folder calcualtor/src/test/resources
7. Add ALL jars in this folder. Then hit OK/Accept until all windows for project structure are closed.
8. Now all jar needed to run unit test should be attached to your project.
9. To run unit tests you can simply select any of the files in test/java. Also you may right-slick on the java folder inside test and select run all tests to run all given unit tests.
