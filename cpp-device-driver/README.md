# CSC415-Device-Driver
**Background**:

The Linux operating system consists of the Kernel some other minor components and then a large number of Device Drivers.  Device drivers are the key to how various hardware devices interface with the computer.

**Task**:

Develop a skeleton device driver that can be loaded and run in Linux.  Then add some minor functionality to the device driver such as the user/application passing in a value to the device driver and the device driver returns that nth word from a file.  Include a document on how to build, load and interact with the device driver along with screen shots of output.

**Requirements**:
It must be written in C.  It must be a valid an loadable device driver with at least some minimal user/application functionality. That includes an open, release, read, write, and at least one ioctl command.  It must also be able to be unloaded, and indicate that it has unloaded from the system.  Make use of the printk and copy_to_user functions.

This must be run in the linux virtual machine.

You should also write a user application that utilizes your device driver.

Create TWO directories in your GitHub:  The first is `Module` that has your kernel module.  The second is `Test` that has your test user application.

The writeup should have clear instructions on how to build your kernel module and your test program and how to install the kernel module and how to use your test program.

**Example**:
Encrypt data, read and write a string, use ioctl to determine to encrypt or decrypt and to set an key. Os if set to encrypt, write a string, then read back the encrypted data.  Switch ioctl to decrypt then write the encrypted string and read back the original message (decrypted).

**NOTE:**
No basic calculator allowed!

**Submission**:

Submit your write-up as a PDF on iLearn, your code and Makefile (modify that provided) in Git along with the PDF.

**Hint**:
The provided module makefile is a key to building kernel modules.

**Grading**:

This project will be graded based on meeting the following rubric. 

**Rubric**: 
|Component 	| Points |
|:------------------------------------------------------------------------|------:|
|Loadable Device Driver Skeleton                                          |	 30   |
|Correctness of load and unload functions                                 |	 10   |
|Sample  - simple user/application interaction with the device driver     |  	20  |
|Inline comments - meaningful describing concepts and functionality       | 	18  |
|Standard Header a define in all prior projects for each file             |  	2   |
|Write-up of project                                                      |  10   |
|Screen shots showing all elements of functionality                       |       |
|     (include load and unload as well as application interaction         |  10   |

	

	
