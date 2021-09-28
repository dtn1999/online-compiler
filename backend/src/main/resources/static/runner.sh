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

# echo "create execution directory"
RANDOM_FOLDER=$(pwgen 30 1)
if [ -d "$PWD/code/" ]; then
  ### Take action if $DIR exists ###
  echo ""
else
  ###  Control will jump here if $DIR does NOT exists ###
  mkdir "$PWD/code/"
fi

WORK_DIR=$PWD/code/$RANDOM_FOLDER
mkdir $WORK_DIR

# echo "your code will run in " $WORK_DIR

# variable declaration

FILE_CONTENT=$1
INPUT=$2
LANG=$3
MAX_SIZE=$4

INPUT_FILE="$WORK_DIR/in"
printf "%s" "$INPUT" >> "$INPUT_FILE"
echo
# argument validation

if [ $# -ne 4 ]
then 
echo "not goo d"
fi



create_source_code_file(){
#                echo "create file with user code"
                FILE_LOCATION="$WORK_DIR/file.$LANG"
                touch $FILE_LOCATION
                printf "%s" "$FILE_CONTENT" >> "$FILE_LOCATION"
                CONTAINER_NAME=$(pwgen 30 1)
#                echo "container name is " "$CONTAINER_NAME"
                MAX_SIZE=$(echo "${MAX_SIZE}M")
#                echo "start container..."
}



#run the container according to the language of choice
case $LANG in
	
	cpp)
 		create_source_code_file cpp
		docker run --rm -m "$MAX_SIZE" --memory-swap "$MAX_SIZE" --name "$CONTAINER_NAME" -v "$WORK_DIR":/code -w /code cpp /bin/sh -c "g++ -Wall file.cpp -o a && ./a < in >&1 | tee"

#		echo " "
	 	rm -rf "$WORK_DIR"
;;
   java)
    echo  "run java container"
;;
   py)
		create_source_code_file py
                docker run --rm -m "$MAX_SIZE" --memory-swap "$MAX_SIZE" --name "$CONTAINER_NAME" -v "$WORK_DIR":/code -w /code python python3 -u file.py "$INPUT"
#                echo " "
		rm -rf "$WORK_DIR"
;;
   *)
    echo "no container avalaible for this language"
;;
esac




