/**************************************************************
* Class: CSC-415-02 Fall 2021
* Name: Anmol Burmy
* Student ID: 921143454
* GitHub Name: Burmy
* Project: Assignment 4 - Word Blast
*
* File: Burmy_Anmol_HW4_main.c
*
* Description: 
* For this assignment, our program is to read War and Peace (a 
* text copy is included with this assignment) and it is to count 
* and tally each of the words that are 6 or more characters long.
*
**************************************************************/

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <string.h>
#include <pthread.h>

#define MAX_SIZE 2000

// You may find this Useful
char * delim = "\"\'.“”‘’?:;-,—*($%)! \t\n\x0A\r";

int count = 0;     
int flag = 0;
int fd, chunk;
pthread_mutex_t mylock = PTHREAD_MUTEX_INITIALIZER;                             

typedef struct wordsCount {                                         // This struct would be the object that would store 
    char *word;                                                     // each word and the amount of times the words occurs.                                               
    int count;
} wordsCount;

struct wordsCount wordsArray[MAX_SIZE];                             // Array that holds structs of words

void addWords(char* token) {                                        // addWords function adds words to the array of structs. 
    int ret;                                                        // Mutex is used in here to protect the shared resources.
    for (int i = 0; i < MAX_SIZE; i++) {                            
        ret = strcasecmp(wordsArray[i].word, token);                // If word is already in the the array of structs,
        if (ret == 0) {                                             // increase its count.                     
            pthread_mutex_lock(&mylock);                             
            wordsArray[i].count++;
            pthread_mutex_unlock(&mylock); 
            break;
        } 
    }
    if (ret != 0) {                                                 // If the word does not exists in the the array of structs, 
        if (count < MAX_SIZE) {                                     // add it to the array and then increase its count.
            pthread_mutex_lock(&mylock);            
            strcpy(wordsArray[count].word, token);
            wordsArray[count].count++;
            pthread_mutex_unlock(&mylock);         
            count++;
        }
    }
}

void *readFile(void* arg){                                          // readFile function will scan through each chunk of the file
    char* buffer =  (char *) malloc(chunk);                          
    int* bufferEnd = (int*) arg;                                    // It will get buffer end for each chunk
    int bufferStart = *bufferEnd - chunk;                           // It will get buffer start for each chunk
    pread(fd, buffer, chunk, bufferStart);                          // It will read from a file descriptor
    char* token;
    while ((token = strtok_r(buffer, delim, &buffer))) {              
        if (strlen(token) >= 6) {                                   // Making sure token in 6 or more characters and 
            addWords(token);                                        // then pass it to the addWords function which 
        }                                                           // will save or update words in an array of “word” structs 
    }
    pthread_exit(NULL);    
}

int main (int argc, char *argv[]) {

    //***TO DO***  Look at arguments, open file, divide by threads
    //             Allocate and Initialize and storage structures

    int fileSize;
    int threadCount = strtol(argv[2], NULL, 10);                    // Number of threads from args

    if(!argv[1] || !argv[2]) {                                      // Inform user if nothing is entered
        printf("Please enter a file name or number of threads\n");
        exit(EXIT_FAILURE);
    }

    if (flag == 0) {                                                // Initialize array of structures
        for (int i = 0; i < MAX_SIZE; i++) {
        wordsArray[i].word = malloc(10);
        wordsArray[i].count = 0;
        }
        flag = 1;
    }
    
    fd = open(argv[1], O_RDONLY);                                   // Opening file descriptor
    fileSize = lseek(fd, 0, SEEK_END);                              // Getting the number of bytes in the file
    chunk = fileSize / threadCount;                                 // Tells us the max # of bytes each thread scans
    lseek(fd, 0, SEEK_SET);                                         // Reset the position back to beginning of file

    unsigned int positionsArray[threadCount];                       // This array will have number of positions that is 
    for (int i = 0; i < threadCount; i++){                          // equal to the number of threads.
        positionsArray[i] = (chunk * (i + 1));
    }

    //**************************************************************
    // DO NOT CHANGE THIS BLOCK
    //Time stamp start
    struct timespec startTime;
    struct timespec endTime;

    clock_gettime(CLOCK_REALTIME, &startTime);
    //**************************************************************
    // *** TO DO ***  start your thread processing
    //                wait for the threads to finish

    pthread_t thread[threadCount];                                  // Creating pthread

    for (int i = 0; i < threadCount; i++) {
        pthread_attr_t attr;                                                    
        pthread_attr_init(&attr);                                   // Init the thread attribute structure to defaults 
        pthread_create(&thread[i], &attr, readFile, &positionsArray[i]);
    }

    for (int i = 0; i < threadCount; i++) {                         // Waiting for each thread to finish
        pthread_join(thread[i], NULL);
    }

    // ***TO DO *** Process TOP 10 and display
    wordsCount temp;

    for (int i = 0; i < MAX_SIZE; i++) {                            // Sort wordsArray in decreasing order
        for (int j = i+1; j < MAX_SIZE; j++) {
            if (wordsArray[i].count < wordsArray[j].count) {
                temp = wordsArray[i];
                wordsArray[i] = wordsArray[j];
                wordsArray[j] = temp;
            }
        }
    }

    printf("\n\n");                                                     
    printf("Word Frequency Count on %s with %d threads\n", argv[1], threadCount);
    printf("Printing top 10 words 6 characters or more.\n");
    for (int i = 0; i < 10; i++) {
        printf("Number %d is %s with a count of %d\n", i+1, wordsArray[i].word, wordsArray[i].count);
    }

    //**************************************************************
    // DO NOT CHANGE THIS BLOCK
    //Clock output
    clock_gettime(CLOCK_REALTIME, &endTime);
    time_t sec = endTime.tv_sec - startTime.tv_sec;
    long n_sec = endTime.tv_nsec - startTime.tv_nsec;
    if (endTime.tv_nsec < startTime.tv_nsec)
        {
        --sec;
        n_sec = n_sec + 1000000000L;
        }

    printf("Total Time was %ld.%09ld seconds\n", sec, n_sec);
    //**************************************************************


    // ***TO DO *** cleanup
    close(fd);                                                      // Close file

    pthread_mutex_destroy(&mylock);                                 // Destory mutex lock

    for (int i = 0; i < MAX_SIZE; i++) {
        free(wordsArray[i].word);
    }
    return 0;
}