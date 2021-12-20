/**************************************************************
* Class: CSC-415-02 Fall 2021
* Name: Anmol Burmy
* Student ID: 921143454
* GitHub Name: Burmy
* Project: Assignment 3 - Simple Shell
*
* File: Burmy_Anmol_HW3_main.c
*
* Description: 
* For this assignment we implemented our own shell that runs on 
* top of the regular command-line interpreter for Linux. 
*
**************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define BUFFER_SIZE 180
#define MAX_ARGS 91

int parseInput(char *input, char **arg) {           // Function to parse the input string

    int i = 0;

    arg[0] = strtok(input, " \t\n");                // Using strtok() to parse, takes two parameters, “input” is the string
    while(arg[i] != NULL ) {                        // entered by the user which is to be split. And the second parameter are                 
        arg[++i] = strtok(NULL, " \t\n");           // the character on the basis of which the split will be done,
    }                                               // which in this case is a space (“ ”), tab (\t) and newline (\n).
    
    return i;
}

int main(int argc, char *argv[MAX_ARGS]) {
    
    char *arg[BUFFER_SIZE];
    char *input;
    char *prompt = argv[1];
    int argCount;

    input = malloc(BUFFER_SIZE);                            // Allocate input line buffer

    while (1) {
        
        if (prompt == NULL) {                               // If no value is specified use “> ” as the prompt.
            printf("\n> ");
        } else {                                            // Else prompt from command arguments.
            printf("%s ", prompt);
        }

        fgets(input, BUFFER_SIZE, stdin);                   // Read the user input from the command line.

        int length = 0;
        length = strlen(input); 
        if (length > BUFFER_SIZE) {                         // Check if buffer length is greater than given 180 bytes bufer
            length = BUFFER_SIZE;                           // Truncate the buffer length to 180 bytes.
        }

        if (feof(stdin)) {                                  // If our shell encounters EOF while reading a line of input, 
            exit(EXIT_SUCCESS);                             // it exits gracefully without reporting an error.
        }
        
        argCount = parseInput(input, arg);
        if (argCount == 0) {     
            printf("******* ERROR *******\n");              // If nothing is entered inform the user. 
            printf(" Empty command line!\n");
            continue;
        }

        if (ferror(stdin)) {                                // If shell encounters an error while reading a line of input it 
            printf("     ******* ERROR *******\n");
            printf("Could not read input, aborting!\n");    // reports the error and exit.
            exit(EXIT_FAILURE);
        }

        if (strcmp(arg[0], "exit") == 0) {                  // If the user enters the exit command, shell should terminates.
            free(input);                                    // Free the buffer before terminating.
            exit(EXIT_SUCCESS);
        }

        pid_t ret;
        int exec;
        int status;
        
        switch((ret = fork())) {
            case -1: 
                printf("******* ERROR *******\n");
                printf("Fork failed, aborting!\n");         // Informing the user if fork failed.
                exit(EXIT_FAILURE);

            case 0:                                         // Child    
                exec = execvp(arg[0], arg);                 // Replaces the current process with new process.
                
                if (exec == -1) {
                    printf("******* ERROR *******\n");      // Informing the user if the command entered is invalid.
                    printf("   Invalid command\n");
                    exit(EXIT_FAILURE);
                }
                break;

            default:                                        // Parent
                wait(&status);                              // Let the child run first and check if the child process 
                                                            // died successfully.
                if (WIFEXITED(status)) {                    
                    printf("Child %d, exited with %d\n", ret, WEXITSTATUS(status));
                }
                break;
        }
    }
    return 0;
}
