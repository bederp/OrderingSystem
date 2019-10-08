#!/usr/bin/env bash
echo "Hello, add absolute path and file name to import file : "
read path
# shellcheck disable=SC2164
if [ -f $path ]
then
  printStart
  cd target/ && java -jar OrderingSystem-1.0-SNAPSHOT-shaded.jar $path
else
  echo "Nothing to import, do you want to run app ?(yes/no)"
  read answer
  if [ $answer == "yes" ];
  then
    printStart
    cd target/ && java -jar OrderingSystem-1.0-SNAPSHOT-shaded.jar
  else
    echo "Closing the app ....."
  fi
fi

printStart(){
  echo -e "\nStarting APP.....\n"
}