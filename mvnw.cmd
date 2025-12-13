@echo off
setlocal enableextensions enabledelayedexpansion

rem Try the Maven Wrapper JAR if present
if exist "%~dp0\.mvn\wrapper\maven-wrapper.jar" (
  java -jar "%~dp0\.mvn\wrapper\maven-wrapper.jar" %*
  exit /b %errorlevel%
)

rem Try MAVEN_HOME
if defined MAVEN_HOME (
  "%MAVEN_HOME%\bin\mvn" %*
  exit /b %errorlevel%
)

rem Default to user maven installation if present
if exist "%USERPROFILE%\.maven\maven-3.9.11\bin\mvn.cmd" (
  "%USERPROFILE%\.maven\maven-3.9.11\bin\mvn.cmd" %*
  exit /b %errorlevel%
)

echo Maven not found. Please install Maven or run with your system mvn.
exit /b 1
