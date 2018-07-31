!/bin/bash

echo "1 execution"

./mongo.sh


timeElapsed=0
mongoIsRunning=0
netstat -an | grep 27017 | grep "LISTEN" && mongoIsRunning=1

while [[ $mongoIsRunning -eq 0 ]]; do

   if [ $timeElapsed -eq 200 ]
   then
      echo "PROBLEM WITH MONGO"
      sleep 10
      exit
   fi

   netstat -an | grep 27017 | grep "LISTEN" && mongoIsRunning=1
   sleep 5
   ((timeElapsed+=5))
done

./mysql.sh
echo "After MYSQL"
timeElapsed=0
mysqlIsRunning=0
netstat -an | grep 3306 | grep "LISTEN" && mysqlIsRunning=1

while [[ $mysqlIsRunning -eq 0 ]]; do
   echo "IN MYSQL LOOP"
   if [ $timeElapsed -eq 200 ]
   then
      echo "PROBLEM WITH MYSQL"
      sleep 10
      exit
   fi
   echo "AFTER IF IN MYSQL LOOP"
   netstat -an | grep 3306 | grep "LISTEN" && mysqlIsRunning=1
   sleep 5
   ((timeElapsed+=5))
done
echo "AFTER MYSQL"
./spring.sh

timeElapsed=0
springIsRunning=0
netstat -an | grep 8080 | grep "LISTEN" && springIsRunning=1

while [[ $springIsRunning -eq 0 ]]; do

   if [ $timeElapsed -eq 200 ]
   then
      echo "PROBLEM WITH SPRING"
      sleep 10
      exit
   fi

   netstat -an | grep 8080 | grep "LISTEN" && springIsRunning=1
   sleep 5
   ((timeElapsed+=5))
done

./angular.sh

timeElapsed=0
angularIsRunning=0
netstat -an | grep 4200 | grep "LISTEN" && angularIsRunning=1

while [[ $angularIsRunning -eq 0 ]]; do

   if [ $timeElapsed -eq 200 ]
   then
      echo "PROBLEM WITH ANGULAR"
      sleep 10
      exit
   fi

   netstat -an | grep 4200 | grep "LISTEN" && angularIsRunning=1
   sleep 5
   ((timeElapsed+=5))
done

./chrome.sh

sleep 10

echo "END"


