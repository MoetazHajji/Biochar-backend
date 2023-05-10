echo Stop
for /f "tokens=1" %i in ('jps -m ^| find "api-gateway-1.0-SNAPSHOT.jar"') do ( taskkill /F /PID %i )
rem https://stackoverflow.com/questions/2643901/how-can-we-stop-a-running-java-process-through-windows-cmd
pause
rem jps 
rem 13536 RemoteMavenServer36
rem 1536
rem 1648 api-gateway-1.0-SNAPSHOT.jar
rem 11704 discovery-server-1.0-SNAPSHOT.jar
rem 13976 Launcher
rem 14360 QuorumPeerMain
rem 1960 RemoteMavenServer36
rem 13564 Kafka
rem 9436 Jps
rem taskkill /F /pid 12968    