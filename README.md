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
