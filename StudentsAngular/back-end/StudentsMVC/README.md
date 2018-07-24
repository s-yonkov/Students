# Students with DB #

Enterprise program with simple Web UI. The program is storing the data in a database. The user can choose which database(DB) to use - relational DB(MySQL) or non relational/document oriented DB(MongoDB) or to use both DBs at the same time. The program can be extended to use more databases.


## Possible inputs ##

The user can operate with the program via simple Web page.
1. The user can access which and how many databases to use for the operations - in the current state - MongoDB, MySQL or All at the same time.
2. The user can access all records from the databases. The records will be presented according to the corresponding database. In other words if we have 5 records in the MongoDB they will be printed in the Mongo column on the webpage.
3. The user can access a particular student by ID.


## Possible outputs ##


The output depends on the input format, below are listed example possible outputs:

#### 1. If all records are requested: ####

   * If both databases are working fine and have records all records will be printed in their coresponding column - for example all MongoDB records will be presented in the Mongo column and the same for MySQL records - in the MySQL column.
   * If only one DB is selected all records only from the selected DB will be presented(if any available)
   * If there is a problem with the DB or there are no records in the DB a proper messagae will be printed in the coresponding column like - "Problem with the Database" or "No IDs available" and etc.
   
#### 2. If student by ID is requested: ####

   * If both databases are working fine and have a student with the requested ID the records will be presented in the coresponding column/Mongo or MySQL/.
   * If only one DB is selected and the there is a student with the requested ID it will be presented in the column of the selected DB.
   * If something is wrong with the DB(shutdown or connection problem) a proper messagae will be presented in the DB's column.
   * If the ID is not found in the DB the user will see a proper messagae like "No such ID".
   
#### 3. Recording student in the DB ####

   * If all DBs are working fine and are selected the Student will be saved and the user will see a confirmation messagae in which DB.
   * If there is a problem with the DB the user will receive a messagae for a problem and the Student will not be saved.
   

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

## Technologies ##

   * Java EE
   * Spring boot
   * MVC
   * JDBC
   * Ajax
   * jQuery
   * Java Script
   * MongoDB
   * MySQL
   * Maven
   * Bootstrap   