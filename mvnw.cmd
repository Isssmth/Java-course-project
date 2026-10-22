@echo off
REM Lightweight Maven wrapper launcher.
set MAVEN_VERSION=3.9.11
set MAVEN_BIN=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd
if not exist "%MAVEN_BIN%" (
  echo Maven Wrapper distribution is not installed.
  echo Please use Maven installed on your computer by running: mvn spring-boot:run
  exit /b 1
)
call "%MAVEN_BIN%" %*
