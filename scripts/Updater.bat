@echo off
cd /d %~dp0..
echo  updater(Java)
@title updater
setLocal EnableDelayedExpansion
set CLASSPATH="conf
for /f "tokens=* delims=" %%a in ('dir "*.jar" /b') do (
   set CLASSPATH=!CLASSPATH!;%%a
)
set CLASSPATH=!CLASSPATH!"
echo %CLASSPATH%
jre\bin\java -Xmx512m -Xmn256m -Djava.ext.dirs=lib -Dlog4j.configuration=log4j.properties -cp %CLASSPATH% org.renix.updater.UpdaterMain
