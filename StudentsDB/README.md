# Students with DB #

A simple program which is parsing info from JSON file(the path of the file is passed by the user) into Java objects. The program is storing the data in a database. The user can choose which database(DB) to use - relational DB(MySQL) or non relational/document oriented DB(MongoDB) or to use both DBs at the same time.



## Possible inputs ##



The program accepts up to ***3*** arguments.
1. The first argument should always be a valid path to a file like ```C:\ExampleFolder\TargetFolder\targetFile.json```.
2. The second argument is optional and should be an integer number and a valid ID from the file.
3. The third argument is also optional and should be a short word for which DB to be used or if both DB should be used - possible values of the third argument - ```Mongo```, ```MySql``` or ```Both```. The input is ***not*** case sensitive.


## Possible outputs ##


The output depends on the input format, below are listed example possible outputs:

#### 1. One argument passed: ####

   * If only one argument is passed and it is a valid path to a file the program will read the file, store the info in a DB and print the content on the console.
   * If only one argument is passed but it is not a valid path, the program will notify for the invalid argument and will print the content of the DB if it is not empty.
   * If only one argument is passed but it is not a valid path and the DB is empty the program will indicate for an error.
   
#### 2. Two arguments passed: ####

   * If both arguments are valid - the path is correct and the second argument is valid integer and existing ID in the file, the program will print only the record with this ID
   * If the second argument is not valid the program will print an error messagae ```Requested student does not exist in the data.``` and will print all records
   * If the path is incorrect the program will behave as in the previous point.
   
#### 3. Three arguments passed ####

   * The third argument is for choosing which DB to be used(or both) for storing the data. ```Mongo``` or ```MySql``` will store the data  respectively in Mongo DB or in MySQL, if ```Both``` is passed the data will be stored in both DBs.
   

## Technical requirements ##

In order the code to work locally some aditional software should be installed/setup

### Instal Java 8 ###

* **Download** - https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

### Instal and setup Maven ###

Maven should be installed in order all dependencies(needed libraries and etc) to be downloaded.

* **Download** - Maven can be downloaded from http://maven.apache.org/download.cgi
* **Installing** - It can be easily installed and adjusted by following this example video - 

<a href="http://www.youtube.com/watch?feature=player_embedded&v=Jtj-0yhox5s
" target="_blank"><img src="http://img.youtube.com/vi/Jtj-0yhox5s/0.jpg" 
alt="" width="240" height="180" border="10" /></a>

### Install and setup MongoDB ###

* **Download** - https://www.mongodb.com/download-center?jmp=nav#atlas
* **Installing** - 

<a href="http://www.youtube.com/watch?feature=player_embedded&v=1uFY60CESlM
" target="_blank"><img src="http://img.youtube.com/vi/1uFY60CESlM/0.jpg" 
alt="" width="240" height="180" border="10" /></a>

### Install and setup XAMPP ###

* **Download** - https://www.apachefriends.org/index.html
* **Installing** - 

<a href="http://www.youtube.com/watch?feature=player_embedded&v=N6ENnaRotmo
" target="_blank"><img src="http://img.youtube.com/vi/N6ENnaRotmo/0.jpg" 
alt="" width="240" height="180" border="10" /></a>


