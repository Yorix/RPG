@echo off
javac ./src/* -d ./classes
echo Manifest-Version: 1.0 >> manifest
echo Main-Class: Main >> manifest
echo. >> manifest
cd classes
jar -cfm ../RolePlayingGame.jar ../manifest ./* ../app.properties
cd ..
rd /s /q classes
del manifest