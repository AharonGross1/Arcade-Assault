# Arkanoid
An [Arkanoid](https://en.wikipedia.org/wiki/Arkanoid) game written using java.  
There are 4 different levels in the game, each reachable by finishing the last one. Move the paddle with the arrows and don't let the ball hit the bottom of the screen!  
  
The game makes use of the biuoop.jar file, the graphics used in the game.  
Arkanoid is a simple one player game, written while using java coding style and oop principles.  



## Running the program:
It is assumed that you have already downloaded and installed Java Development Kit (JDK) on your computer.  
If you already have apache ant installed on your computer, simply open the cmd in the file location and enter the command "ant run".  
If you don't have ant, follow these steps in order to install it:  
For Mac users who use brew to install packages (highly recommended in general), you can simply install ant using ```brew install ant``` and move to Verify Installation.  

### For Windows and Linux, proceed as follows:

Download and install [Apache Ant](https://ant.apache.org/bindownload.cgi).

Unzip the zip file to a convenient location ```(e.g. C:\Users\User)``` . To unzip on Windows, you can use [7-zip](https://www.7-zip.org/) or a similar programs.

### Set environment variables (Windows and Linux)
- Create a new environment variable called ANT_HOME that points to the Ant installation folder, in our example, the ```C:\User\User\apache-ant-1.10.12-bin``` folder.

- Append the path to the Apache Ant batch file to the PATH environment variable. In our example this would be the ```C:\User\User\apache-ant-1.10.12-bin\bin``` folder. You can now run ant commands from anywhere on your system.

If you are not sure how to create or modify an environment variable, here are simple guides for [Windows](https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/), [Linux](https://www.serverlab.ca/tutorials/linux/administration-linux/how-to-set-environment-variables-in-linux/), and [Mac](https://youngstone89.medium.com/setting-up-environment-variables-in-mac-os-28e5941c771c) (note you should make it a persistent setting).

### Verify Installation
To verify the successful installation of Apache Ant on your computer, type ```ant -version``` on your command prompt.

You should see an output similar to −
```
Apache Ant(TM) version 1.10.12 compiled on October 13 2021
```
