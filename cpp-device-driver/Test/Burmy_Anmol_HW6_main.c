/**************************************************************
* Class: CSC-415-02 Fall 2021
* Name: Anmol Burmy
* Student ID: 921143454
* GitHub Name: Burmy
* Project: Assignment 6 - Device Driver
*
* File: Burmy_Anmol_HW6_main.c
*
* Description: Test file for the device driver. Takes the user's
* message and sends it to the module. This message is then displayed
* to the user in the terminal.
*
**************************************************************/

#include<stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<fcntl.h>
#include<string.h>
#include<unistd.h>
#define BUFFER_LENGTH 256

static char receive[BUFFER_LENGTH];             //gets buffer length from module

int main() {

    int ret;
    int fd;
    char stringToSend[BUFFER_LENGTH];

    fd = open("/dev/testDeviceDrive", O_RDWR);  //opens device to read and write

    if (fd < 0) {
        printf("Device Open Error\n");
        perror("Device File Open Error");
        return errno;
    } else {
        printf("Device Open Success. \n");
    }

    printf("\nEnter a short string to send to the kernel: \n");
    scanf("%[^\n]%*c", stringToSend);                    //reads in the string

    printf("\nMessage written to the device: '%s' \n", stringToSend);
    ret = write(fd, stringToSend, strlen(stringToSend)); //sends to lkm

    if (ret < 0) {
        perror("Failed to write message to device\n");
        return errno;
    }

    printf("Press 'ENTER' key to read the message back from the device\n");
    getchar();

    printf("Reading from the device...\n");
    ret = read(fd, receive, BUFFER_LENGTH);             //gets response from lkm

    if (ret < 0) {
        perror("Failed to read message from device\n");
        return errno;
    }

    printf("Converted message: '%s'\n", receive);

    printf("\nClosing Device.\n");
    return 0;
}