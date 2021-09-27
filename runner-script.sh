#/bin/bash
####################################################################################################################################
#                      this script is a utility script to run start and stop the respective container depending on the             #
#                      choosen language                                                                                            #
#                      args:  													   #
#                           $1 language: cpp, java or python                                                                       #
#                           $2 max memory size:                                                                                    #
#                           $3 file content as string                                                                              #
####################################################################################################################################
#colors
BLUE='tput setaf 4'
RED='tput setaf 1'
GREEN='tput setaf 2'
RESET='tput sgr0'

$BLUE; echo "create execution directory"
RANDOM_FOLDER=$(pwgen 30 1)

WORK_DIR=$PWD/input/$RANDOM_FOLDER
mkdir $WORK_DIR

$RESET;

echo "your code will run in " $WORK_DIR

# variable declaration

LANG=$1
MAX_SIZE=$2
FILE_CONTENT=$3


# argument validation

if [ $# -ne 3 ]
then 
echo "not goo d"
fi



create_source_code_file(){
                $GREEN; echo "create file with user code"
                $RESET;
                FILE_LOCATION=$WORK_DIR/file.$1
                touch $FILE_LOCATION
                printf "%s" "$FILE_CONTENT" >> $FILE_LOCATION 
                CONTAINER_NAME=$(pwgen 30 1)
                echo "container name is " $CONTAINER_NAME
                MAX_SIZE=$(echo "${MAX_SIZE}M")
                $GREEN; echo "start container..."
                $RESET;
}



#run the container according to the language of choice
case $LANG in
	
	cpp)
 		create_source_code_file cpp
		docker run --rm -m $MAX_SIZE --memory-swap $MAX_SIZE --name $CONTAINER_NAME -v $PWD/input/$RANDOM_FOLDER:/code -w /code cpp /bin/sh -c "g++ -Wall file.cpp -o a && ./a >&1 | tee"
		echo " "
	 	rm -rf $WORK_DIR
;;
   java)
    echo  "run java container"
;;
   py)
		create_source_code_file py
                docker run --rm -m $MAX_SIZE --memory-swap $MAX_SIZE --name $CONTAINER_NAME -v $PWD/input/$RANDOM_FOLDER:/code -w /code python python3 -u file.py
                echo " "
		rm -rf $WORK_DIR
;;
   *)
    echo "no container avalaible for this language"
;;
esac




