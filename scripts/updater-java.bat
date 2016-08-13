@echo off
cd %~dp0..
echo  updater-java(Java)
@title updater-java
setLocal EnableDelayedExpansion
set CLASSPATH="conf
for /f "tokens=* delims=" %%a in ('dir "*.jar" /b') do (
   set CLASSPATH=!CLASSPATH!;%%a
)
set CLASSPATH=!CLASSPATH!"
echo %CLASSPATH%
java -Xmx512m -Xmn256m -Djava.ext.dirs=lib -cp %CLASSPATH% org.renix.updater.UpdaterMain
