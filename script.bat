@echo off
rem =====================================
rem Variables
rem =====================================
set TESTAPP_LIB=.\WEB-INF\lib
set FRAMEWORK_PATH=..\Framework
set FRAMEWORK_JAR=%FRAMEWORK_PATH%\lib\framework.jar

set TOMCAT_HOME=/opt/tomcat
set WEBAPPS=%TOMCAT_HOME%\webapps
set WAR_NAME=TestApp.war

rem =====================================
rem Copier le JAR du framework dans TestApp
rem =====================================
if not exist %TESTAPP_LIB% mkdir %TESTAPP_LIB%
copy /y %FRAMEWORK_JAR% %TESTAPP_LIB%
if errorlevel 1 (
    echo Erreur lors de la copie du framework.jar!
    pause
    exit /b 1
)

echo framework.jar copie dans TestApp\WEB-INF\lib avec succes

rem =====================================
rem Créer le WAR
rem =====================================
if exist %WAR_NAME% del /q %WAR_NAME%
jar cvf %WAR_NAME% -C WebContent . -C . WEB-INF
if errorlevel 1 (
    echo Erreur lors de la creation du WAR!
    pause
    exit /b 1
)

echo WAR cree avec succes : %WAR_NAME%

rem =====================================
rem Déployer dans Tomcat
rem =====================================
if exist %WEBAPPS%\%WAR_NAME% del /q %WEBAPPS%\%WAR_NAME%
copy /y %WAR_NAME% %WEBAPPS%
if errorlevel 1 (
    echo Erreur lors de la copie du WAR dans Tomcat!
    pause
    exit /b 1
)

echo Application %WAR_NAME% deployee dans Tomcat\webapps

rem =====================================
rem Redémarrer Tomcat
rem =====================================
echo Redemarrage de Tomcat...
%TOMCAT_HOME%\bin\shutdown.bat
timeout /t 3 >nul
%TOMCAT_HOME%\bin\startup.bat

echo =====================================
echo 🚀 Deploiement termine!
echo URL: http://localhost:8080/TestApp
echo =====================================
pause
