# Homework 2
 Description: Designed and implemented an instance of the map/reduce computational model on the DBLP Dataset.

### Indra Sai Kiran Valluru [UIN - 652537989]

### Instructions:

### Environment:

The Project was developed using the following environment:
+ **OS** : Windows 10
+ **IDE** : IntelliJ Idea Community edition 2021.2.3
+ **HyperVisor** : Oracle VM VirtualBox
+ **Hadoop Distribution**: [Hortonworks Data Platform (3.0.1) Sandbox](https://www.cloudera.com/downloads/hortonworks-sandbox.html) deployed on Virtual Box

##PreRequisites:
- HDP Sandbox deployed on Virtual Box or VM
- Ability to use SCP and SSH
- SBT should be installed in your system

## Steps to run the project:
1. Clone the repository using the following command:
   ```https://github.com/IndraSK/hadoop_Map_Reduce_logfilegenerator.git```
2. Now, navigate to the directory '' and run the following command:
   ```sbt clean compile assembly```
3. A fat far file will be created in 'LogFileGenerator/target/scala-version'.
4. This jar file has to be copied to the Hadoop File System. The following instructions are for those who have installed hortonworks and are using windows.
5. Start hortonworks in your preferred hypervisor.
6. Visit this link: https://www.cloudera.com/tutorials/learning-the-ropes-of-the-hdp-sandbox.html to setup your id and password.
7. Once started, open your browser and type the link provided in the VM for your hypervisor. 
8. Next use the following command on your terminal or cmd to move jar
```
scp -P 2222 <Jar File Name> root@localhost:~
```
9. Insert Jar and Input file from VM to Hadoop using following command in your localhost terminal
```
hadoop fs -put <Input FileName>
hadoop fs -put <Jar File Name>
```
13. Run the MapReduce Job
```
hadoop jar <Jar File Name> MapandReduce.Job1 <Input File Name> Output.csv
hadoop jar <Jar File Name> MapandReduce.Job2 <Input File Name> Output1.csv
hadoop jar <Jar File Name> MapandReduce.Job3 <Input File Name> Output2.csv
hadoop jar <Jar File Name> MapandReduce.Job4 <Input File Name> Output3.csv
```
14. For Outputs
```
 hadoop fs -cat <output filename>.csv/part-r-00000 

```


## Map-Reduce Tasks Implementation
### Task1 - Distribution of different types of messages across predefined time intervals and injected string instances of the designated regex pattern for these log message type.
The input to the mapper is the entire Log Messages from which it extracts the time intervals and outputs the list of different time intervals as key and number of messages for that type of message.
The reducer receives the output from the mapper, and it sums up the count of messages for each type of message.
+ Command to run this Job
```
hadoop jar LogFileGenerator-assembly-0.1.jar MapandReduce.J1 LogFileGenerator.2021-10-20.log output1.csv
```
+ Command to check output
```
hadoop fs -cat output1.csv/part-r-00000
```
+ Output Snippet
```
DEBUG_16:16:47  3                                                                                                                                                       
DEBUG_16:16:48  2                                                                                                                                                       
DEBUG_16:16:49  5                                                                                                                                                       
DEBUG_16:16:50  4                                                                                                                                                       
DEBUG_16:16:51  8                                                                                                                                                       
DEBUG_16:16:52  9                                                                                                                                                       
DEBUG_16:16:53  9                                                                                                                                                       
DEBUG_16:16:54  9                                                                                                                                                       
DEBUG_16:23:17  3                                                                                                                                                       
DEBUG_16:23:18  6                                                                                                                                                       
DEBUG_16:23:19  4                                                                                                                                                       
DEBUG_16:23:20  7                                                                                                                                                       
DEBUG_16:23:21  10                                                                                                                                                      
DEBUG_16:23:22  8                                                                                                                                                       
DEBUG_16:23:23  11                                                                                                                                                      
ERROR_16:16:47  1                                                                                                                                                       
ERROR_16:16:48  1                                                                                                                                                       
ERROR_16:16:50  1                                                                                                                                                       
ERROR_16:23:17  2                                                                                                                                                       
ERROR_16:23:19  1                                                                                                                                                       
INFO_16:16:46   1                                                                                                                                                       
INFO_16:16:47   17                                                                                                                                                      
INFO_16:16:48   14                                                                                                                                                      
INFO_16:16:49   48                                                                                                                                                      
INFO_16:16:50   52                                                                                                                                                      
INFO_16:16:51   65                                                                                                                                                      
INFO_16:16:52   58                                                                                                                                                      
INFO_16:16:53   58                                                                                                                                                      
INFO_16:16:54   39                                                                                                                                                      
INFO_16:23:17   21                                                                                                                                                      
INFO_16:23:18   53                                                                                                                                                      
INFO_16:23:19   53                                                                                                                                                      
INFO_16:23:20   63                                                                                                                                                      
INFO_16:23:21   52                                                                                                                                                      
INFO_16:23:22   62                                                                                                                                                      
INFO_16:23:23   48                                                                                                                                                      
WARN_16:16:47   10                                                                                                                                                      
WARN_16:16:48   6                                                                                                                                                       
WARN_16:16:49   16                                                                                                                                                      
WARN_16:16:50   19                                                                                                                                                      
WARN_16:16:51   10                                                                                                                                                      
WARN_16:16:52   13                                                                                                                                                      
WARN_16:16:53   15                                                                                                                                                      
WARN_16:16:54   10                                                                                                                                                      
WARN_16:23:17   12                                                                                                                                                      
WARN_16:23:18   19                                                                                                                                                      
WARN_16:23:19   19                                                                                                                                                      
WARN_16:23:20   8                                                                                                                                                       
WARN_16:23:21   16                                                                                                                                                      
WARN_16:23:22   8                                                                                                                                                       
WARN_16:23:23   17 

```
### Task2 - Compute time intervals sorted in the descending order that contained most log messages of the type Error with injected regex pattern string instances
The input to the mapper is the entire Log Messages from which it extracts the time intervals and only type of messages which belong to ERROR category and  outputs the list of different time intervals as key and number of messages for that type of message in  descending order.
To DO: Did not sort the number of messages of type ERROR. 
+ Command to run this Job
```
hadoop jar LogFileGenerator-assembly-0.1.jar MapandReduce.J1 LogFileGenerator.2021-10-20.log output2.csv
```
+ Command to check output
```
hadoop fs -cat output2.csv/part-r-00000
```
+ Output Snippet
```
16:16:47        1                                                                                                                                                       
16:16:48        1                                                                                                                                                       
16:16:50        1                                                                                                                                                       
16:23:17        2                                                                                                                                                       
16:23:19        1 

```
### Task3 - For each message type you will produce the number of the generated log messages
The input to the mapper is the entire Log Messages from which it extracts the type of messages which belong to different category and  outputs the list of different categories as key and number of messages for that type of message.
The reducer receives the output from the mapper, and it sums up the count of messages for each type of message.
+ Command to run this Job
```
hadoop jar LogFileGenerator-assembly-0.1.jar MapandReduce.J3 LogFileGenerator.2021-10-20.log output3.csv
```
+ Command to check output
```
hadoop fs -cat output3.csv/part-r-00000
```
+ Output Snippet
```
DEBUG   98                                                                                                                                                              
ERROR   6                                                                                                                                                               
INFO    704                                                                                                                                                             
WARN    198 

```
### Task4 - Produce the number of characters in each log message for each log message type that contain the highest number of characters in the detected instances of the designated regex pattern.
The input to the mapper is the entire Log Messages from which it extracts the type of messages which belong to different category and  outputs the list of different categories as key and number of characters for that type of message.
The reducer receives the output from the mapper, and it picks the highest number of characters present in messages for each type of message.
+ Command to run this Job
```
hadoop jar LogFileGenerator-assembly-0.1.jar MapandReduce.J4 LogFileGenerator.2021-10-20.log output4.csv
```
+ Command to check output
```
hadoop fs -cat output4.csv/part-r-00000
```
+ Output Snippet
```
DEBUG   160                                                                                                                                                             
ERROR   128                                                                                                                                                             
INFO    172                                                                                                                                                             
WARN    168 

```

## Deployment and Running on AWS
I have uploaded a video on Youtube which demonstrates my deployment and running on AWS.

Link: 
