set SVN_HOME=E:\workspace\yunxiaotian
set DEPLOY_HOME=%SVN_HOME%\deploy

set time_hh=%time:~0,2%
if /i %time_hh% LSS 10 (set time_hh=0%time:~1,1%)
set filename=%date:~,4%%date:~5,2%%date:~8,2%_%time_hh%%time:~3,2%%time:~6,2%
rem echo test >> %filename%.txt

echo %filename% >> src\main\resources\buildinfo.txt
call mvn -Dmaven.repo.local=../repository clean package

mkdir %DEPLOY_HOME%\%filename%
rem enviromental 


cd %SVN_HOME%\target\yunxiaotian
copy %SVN_HOME%\src-test\main\resources\logback.xml %SVN_HOME%\target\yunxiaotian\WEB-INF\classes\
copy %SVN_HOME%\src-test\main\resources\jdbc.properties %SVN_HOME%\target\yunxiaotian\WEB-INF\classes\
copy %SVN_HOME%\src-test\main\resources\application.properties %SVN_HOME%\target\yunxiaotian\WEB-INF\classes\

%SVN_HOME%\bin\zip -r -q -1 %DEPLOY_HOME%\%filename%\yunxiaotian-test.war *
echo [INFO] yunxiaotian-test.war BUILD SUCCESS


rem enviromental 

cd %SVN_HOME%\target\yunxiaotian
copy %SVN_HOME%\src-prod\main\resources\logback.xml %SVN_HOME%\target\yunxiaotian\WEB-INF\classes\
copy %SVN_HOME%\src-prod\main\resources\jdbc.properties %SVN_HOME%\target\yunxiaotian\WEB-INF\classes\
copy %SVN_HOME%\src-prod\main\resources\application.properties %SVN_HOME%\target\yunxiaotian\WEB-INF\classes\

%SVN_HOME%\bin\zip -r -q -1 %DEPLOY_HOME%\%filename%\yunxiaotian-prod.war *
echo [INFO] yunxiaotian-prod.war BUILD SUCCESS

cd %SVN_HOME%

pause