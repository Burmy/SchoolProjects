# java-tankgame

## Version of Java Used: Java version 16.0.1.

## IDE used: IntelliJ IDEA

## Steps to Import project into ID:
To build/import the project, go to my repo and click the green “Clone or Download”
button on your repo’s home page. Then select either HTTPS or SSH, If you are not
sure what SSH keys are then use the HTTPS method. Copy the link and then open
5
your terminal. This will either be Git Bash on Windows, Terminal on Mac or Linux,
or the terminal for your Windows Linux Subsystem if you have it installed. In your
terminal type: git clone repo_url_you_copied. Once the repo is cloned, follow the
steps below to import your project into IntelliJ IDEA.
1. Select Import Project
2. Select the “java-tank-game” folder as the source root of your project.
3. Keep the “Create project from existing resources” radio button selected.
4. Now keep clicking “NEXT” until the import is finished.

## Steps to Build your Project:
After importing the project, to build the JAR, first we need to make sure our src and
resource folders are marked correctly. So in order to do that -
1. Click on “FIle” on top right and select “Project Structure”.
2. From the Project Structure window, select “Modules” and make sure that the
   “src” and “resources” are marked as Sources and Resources respectively.
3. After that to build JAR from the same window, select “Artifacts”, then click on
   +.
4. Select “JAR” then “From modules with dependencies” and then select your
   main class and click ok.
5. After clicking “Apply”, click on “Build” on top of the window and select “Build
   Artifacts” and then “Build”.

## Steps to run your Project:
After you have built your JAR, you can press the play button on top of the editor
window. This will bring up a window of the tank game and you can start playing.

## Controls to play your Game:

|               | Player 1 | Player 2 |
|---------------|----------|----------|
|  Forward      |'W' Key     | 'UP' Key|    
|  Backward     | 'S'  Key      |  'DOWN'  Key|  
|  Rotate left  | 'A' Key      | 'LEFT' Key|   
|  Rotate Right |  'D' Key      | 'RIGHT' Key|  
|  Shoot        | 'SPACE' Key       | 'ENTER' Key|  

<!-- you may add more controls if you need to. -->
