# Online Compiler
this project showcase how a simple but *secured*     **Remote code execution server** can be build. The server use docker abstraction to isolate code execution of each user user.
to make the server secure we have taken the following precautions. first the source code of each user is executed in a docker container optimized for the source code language.

1. Maximum size
The space available on the server is a very important resource as it determines a large part of the performance of the server. For this reason each user is allowed a limited amount of space. To realize this usecase we use the -m and --memory-swap options of docker to limit the space used by the docker container.

2. Execution time
The time that each program takes to execute is crucial because if a user executes a code like 
```cpp 
while(true){}
``` 
then the thread responsible for this request will be blocked. For this reason every code submitted to the server must be executed for a certain period of time otherwise the process is aborted.

Unit test were implemented, don't hesitate to have a look

## Tech stack

To build this project we used the following technologies

1. Spring boot for backend
2. Nextjs for the frontend
3. shell, to facilitate the execution of the container and also to setup the backend
4. Docker to isolate code executions

## How to install the app
The app installation is very simple, follow the steps.
before you can use and play around with app make sure you have the following software install on your local environment
- [ x ] [Docker](https://docs.docker.com/)
- [ x ] [Maven](https://maven.apache.org/)
- [ x ] bash (if you are using window you may have to adapt  the shell script  )

1. clone the app
```bash

git clone https://github.com/dtn1999/online-compiler
cd online-compiler

```
2. set up the backend
```bash

cd backend
bash init.sh # needed to create 3 containers for java , cpp, and python
./mvnw spring-boot:run # to start the backend

```
3. set up the frontend
```bash
# from the root folder (online-compiler)
cd frontend
yarn install # or npm install
yarn dev # or npm run dev
```

Happy hacking !!

## TODO
- [ ] when the user submit a code that run an infinite loop the backend stop the code (request execution) but the docker is not stop !!!!!!!
- [ ] add new languages

