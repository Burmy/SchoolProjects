/**************************************************************
* Class: CSC-415-02 Fall 2021
* Name: Anmol Burmy
* Student ID: 921143454
* GitHub Name: Burmy
* Project: Assignment 6 - Device Driver
*
* File: devDriver.c
*
* Description: This kernel module takes in a message from the user
* and returns that message in all capital letters.
*
**************************************************************/

#include <linux/init.h>
#include <linux/module.h>
#include <linux/device.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/uaccess.h>

#define  DEVICE_NAME "testDeviceDrive"      // device will show up as dev/testDeviceDrive
#define  CLASS_NAME  "deviceDriver"         // class for the device

MODULE_LICENSE("GPL");                      // license type

static int deviceNumber;                // registered device number
static char message[256] = {0};         // stores string from user
static int messageLength;               // length of message
static int devicesOpen;                 // opened devices
static struct class*  myClass  = NULL;  // class struct pointer
static struct device* myDevice = NULL;  // device struct pointer

//driver functions
static int myOpen(struct inode *, struct file *);
static int myRelease(struct inode *, struct file *);
static ssize_t myRead(struct file *, char *, size_t, loff_t *);
static ssize_t myWrite(struct file *, const char *, size_t, loff_t *);
static long myIoCtl(struct file *fs, unsigned int command, unsigned long data);

static struct file_operations fileOperations = {
    .open = myOpen,
    .read = myRead,
    .write = myWrite,
    .release = myRelease,
    .unlocked_ioctl = myIoCtl,
};

//only used when it is initialized and then is freed of memory after it
//completes the function
static int __init myInit(void) {

    printk(KERN_INFO "Initializing LKM...\n");
    deviceNumber = register_chrdev(0, DEVICE_NAME, &fileOperations);//name and fileOperations is registered

    myClass = class_create(THIS_MODULE, CLASS_NAME);                //device class created
                                                                    //device driver file created
    myDevice = device_create(myClass, NULL, MKDEV(deviceNumber, 0), NULL, DEVICE_NAME);
    return 0;

}

//if its used for a built in driver, then this function is not required
static void __exit myExit(void) {

    device_destroy(myClass, MKDEV(deviceNumber, 0));       //deleted device
    class_unregister(myClass);                             //device class unregistered
    class_destroy(myClass);                                //device class removed
    unregister_chrdev(deviceNumber, DEVICE_NAME);          //gets rid of the major number
    printk(KERN_INFO "Exiting module...\n");

}

//called each time a device is open 
static int myOpen(struct inode *inode, struct file *fs) {
    devicesOpen++;
    printk(KERN_INFO "Device #%d is now opened\n", deviceNumber);
    return 0;
}

//called whenever the the message is sent from the user and would
//send it back out to the user
static ssize_t myRead(struct file *fs, char *buffer, size_t len, loff_t *offset) {

    int error_count = 0;
    error_count = copy_to_user(buffer, message, messageLength);

    if (error_count == 0) {             //confirms if no errors to change
        printk(KERN_INFO "Sent %d characters\n", messageLength);
        return (messageLength = 0);     //resets the message length
    } else {                            //error if it is unable to send the message                     
        printk(KERN_INFO "Failed to send %d characters\n", error_count);
        return -EFAULT;
    }

}

//called when the dev is writing to the user
static ssize_t myWrite(struct file *fs, const char *buffer, size_t len, loff_t *offset) {

    char * caps = (char*) buffer;               //capitalizes string

    int i;
    for (i=0; i<len; i++){                      //goes through the characters of the message
        if (buffer[i] >= 'a' && buffer[i] <= 'z') { //checks if char is lower case
            caps[i] = buffer[i] - 0x20;             //converts ascii val to uppercase
        } else {
            caps[i] = buffer[i];
        }
    }

    sprintf(message, "%s", buffer);             //received string with its length
    messageLength = strlen(message);            //stores length of stored message
    printk(KERN_INFO "Received %zu characters from the user\n", len);
    return len;

}

//IoCtl function
static long myIoCtl(struct file *fs, unsigned int command, unsigned long data) {
   return 0;
}

//when the user closes and ends the program
static int myRelease(struct inode *inode, struct file *fs){
    printk(KERN_INFO "Device successfully closed\n");
    return 0;
}

module_init(myInit);
module_exit(myExit);