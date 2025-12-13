@echo off
setlocal

rem Use mvnw wrapper if present, otherwise try to use user Maven installation
set MVN_CMD=%~dp0mvnw.cmd
if not exist "%MVN_CMD%" set MVN_CMD=%USERPROFILE%\.maven\maven-3.9.11\bin\mvn.cmd

if not exist "%MVN_CMD%" (
  echo Maven not found. Either install Maven or put it in PATH, or place mvnw/mvnw.cmd in project root.
  exit /b 1
)

rem Ensure we use JDK21 if available
if not defined JAVA_HOME set JAVA_HOME=C:\Users\mihra\.jdk\jdk-21.0.8
set PATH=%JAVA_HOME%\bin;%PATH%

"%MVN_CMD%" -f "%~dp0pom.xml" javafx:run
