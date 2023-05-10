echo Stop
for /f "tokens=1" %i in ('jps -m ^| find "discovery-server-1.0-SNAPSHOT.jar"') do ( taskkill /F /PID %i )
rem https://stackoverflow.com/questions/2643901/how-can-we-stop-a-running-java-process-through-windows-cmd
pause