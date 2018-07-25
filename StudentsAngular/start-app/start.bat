START bat\mongo.bat

SET /a timeElapsed = 0
SET /a mongoIsRunning = 0


netstat -an | FINDSTR ":27017" | FINDSTR LISTENING && set /a mongoIsRunning = 1

:loop1
IF %timeElapsed% EQU 200 (
	echo PROBLEM WITH MONGO
	TIMEOUT 10
	exit 1
)

IF %mongoIsRunning% EQU 0 (
	TIMEOUT 5
	set /a timeElapsed += 5
	netstat -an | FINDSTR ":27017" | FINDSTR LISTENING && set /a mongoIsRunning = 1
	goto:loop1
) ELSE (
	START bat\mysql.bat
	goto:end1
)
:end1

SET /a timeElapsed = 0
SET /a mySQLIsRunning = 0

netstat -an | FINDSTR ":3306 " | FINDSTR LISTENING && set /a mySQLIsRunning = 1

:loop2
IF %timeElapsed% EQU 200 (
	echo PROBLEM WITH MYSQL
	TIMEOUT 10
	exit 1
)
IF %mySQLIsRunning% EQU 0 (
	TIMEOUT 5
	set /a timeElapsed += 5
	netstat -an | FINDSTR ":3306 " | FINDSTR LISTENING && set /a mySQLIsRunning = 1
	goto:loop2
) ELSE (
	START bat\spring.bat
	goto:end2
)
:end2

SET /a timeElapsed = 0
SET /a springIsRunning = 0

netstat -an | FINDSTR ":8080 " | FINDSTR LISTENING && set /a springIsRunning = 1

:loop3
IF %timeElapsed% EQU 200 (
	echo PROBLEM WITH SPRING APPLICATION
	TIMEOUT 10
	exit 1
)
IF %springIsRunning% EQU 0 (
	TIMEOUT 5
	set /a timeElapsed += 5
	netstat -an | FINDSTR ":8080 " | FINDSTR LISTENING && set /a springIsRunning = 1
	goto:loop3
) ELSE (
	START bat\angular.bat
	goto:end3
)
:end3

SET /a timeElapsed = 0
SET /a angularIsRunning = 0

netstat -an | FINDSTR ":4200 " | FINDSTR LISTENING && set /a angularIsRunning = 1

:loop4
IF %timeElapsed% EQU 200 (
	echo PROBLEM WITH ANGULAR APPLICATION
	TIMEOUT 10
	exit 1
)
IF %angularIsRunning% EQU 0 (
	TIMEOUT 5
	set /a timeElapsed += 5
	netstat -an | FINDSTR ":4200 " | FINDSTR LISTENING && set /a angularIsRunning = 1
	goto:loop4
) ELSE (
	START bat\final.bat
	goto:end4
)
:end3



