@REM ----------------------------------------------------------------------------
@REM Copyright 2001-2004 The Apache Software Foundation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM ----------------------------------------------------------------------------
@REM

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\jsoup\jsoup\1.11.3\jsoup-1.11.3.jar;"%REPO%"\org\slf4j\slf4j-jdk14\1.7.22\slf4j-jdk14-1.7.22.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.22\slf4j-api-1.7.22.jar;"%REPO%"\com\vk\api\sdk\0.5.12\sdk-0.5.12.jar;"%REPO%"\com\google\code\gson\gson\2.8.1\gson-2.8.1.jar;"%REPO%"\org\asynchttpclient\async-http-client\2.0.33\async-http-client-2.0.33.jar;"%REPO%"\org\asynchttpclient\async-http-client-netty-utils\2.0.33\async-http-client-netty-utils-2.0.33.jar;"%REPO%"\io\netty\netty-buffer\4.0.48.Final\netty-buffer-4.0.48.Final.jar;"%REPO%"\io\netty\netty-codec-http\4.0.48.Final\netty-codec-http-4.0.48.Final.jar;"%REPO%"\io\netty\netty-codec\4.0.48.Final\netty-codec-4.0.48.Final.jar;"%REPO%"\io\netty\netty-handler\4.0.48.Final\netty-handler-4.0.48.Final.jar;"%REPO%"\io\netty\netty-transport\4.0.48.Final\netty-transport-4.0.48.Final.jar;"%REPO%"\io\netty\netty-transport-native-epoll\4.0.48.Final\netty-transport-native-epoll-4.0.48.Final-linux-x86_64.jar;"%REPO%"\io\netty\netty-common\4.0.48.Final\netty-common-4.0.48.Final.jar;"%REPO%"\org\asynchttpclient\netty-resolver-dns\2.0.33\netty-resolver-dns-2.0.33.jar;"%REPO%"\org\asynchttpclient\netty-resolver\2.0.33\netty-resolver-2.0.33.jar;"%REPO%"\org\asynchttpclient\netty-codec-dns\2.0.33\netty-codec-dns-2.0.33.jar;"%REPO%"\org\reactivestreams\reactive-streams\1.0.0\reactive-streams-1.0.0.jar;"%REPO%"\com\typesafe\netty\netty-reactive-streams\1.0.8\netty-reactive-streams-1.0.8.jar;"%REPO%"\org\apache\commons\commons-collections4\4.1\commons-collections4-4.1.jar;"%REPO%"\commons-io\commons-io\2.5\commons-io-2.5.jar;"%REPO%"\org\apache\httpcomponents\httpmime\4.5.3\httpmime-4.5.3.jar;"%REPO%"\org\apache\commons\commons-lang3\3.6\commons-lang3-3.6.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.5.6\httpclient-4.5.6.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.4.10\httpcore-4.4.10.jar;"%REPO%"\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;"%REPO%"\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;"%REPO%"\org\apache\logging\log4j\log4j-slf4j-impl\2.7\log4j-slf4j-impl-2.7.jar;"%REPO%"\org\apache\logging\log4j\log4j-api\2.7\log4j-api-2.7.jar;"%REPO%"\org\apache\logging\log4j\log4j-core\2.7\log4j-core-2.7.jar;"%REPO%"\org\apache\httpcomponents\fluent-hc\4.5.6\fluent-hc-4.5.6.jar;"%REPO%"\com\petersamokhin\vk-bot-java-sdk\0.1.3\vk-bot-java-sdk-0.1.3.jar;"%REPO%"\org\json\json\20170516\json-20170516.jar;"%REPO%"\org\slf4j\slf4j-log4j12\1.7.25\slf4j-log4j12-1.7.25.jar;"%REPO%"\log4j\log4j\1.2.17\log4j-1.2.17.jar;"%REPO%"\com\sparkjava\spark-core\2.6.0\spark-core-2.6.0.jar;"%REPO%"\org\eclipse\jetty\jetty-server\9.4.4.v20170414\jetty-server-9.4.4.v20170414.jar;"%REPO%"\javax\servlet\javax.servlet-api\3.1.0\javax.servlet-api-3.1.0.jar;"%REPO%"\org\eclipse\jetty\jetty-http\9.4.4.v20170414\jetty-http-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-util\9.4.4.v20170414\jetty-util-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-io\9.4.4.v20170414\jetty-io-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-webapp\9.4.4.v20170414\jetty-webapp-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-xml\9.4.4.v20170414\jetty-xml-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-servlet\9.4.4.v20170414\jetty-servlet-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-security\9.4.4.v20170414\jetty-security-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-server\9.4.4.v20170414\websocket-server-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-common\9.4.4.v20170414\websocket-common-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-client\9.4.4.v20170414\websocket-client-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\jetty-client\9.4.4.v20170414\jetty-client-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-servlet\9.4.4.v20170414\websocket-servlet-9.4.4.v20170414.jar;"%REPO%"\org\eclipse\jetty\websocket\websocket-api\9.4.4.v20170414\websocket-api-9.4.4.v20170414.jar;"%REPO%"\com\lukaspradel\steam-web-api\1.2\steam-web-api-1.2.jar;"%REPO%"\commons-lang\commons-lang\2.6\commons-lang-2.6.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.6.3\jackson-databind-2.6.3.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.6.0\jackson-annotations-2.6.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.6.3\jackson-core-2.6.3.jar;"%REPO%"\net\dv8tion\JDA\4.1.1_127\JDA-4.1.1_127.jar;"%REPO%"\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;"%REPO%"\org\jetbrains\annotations\16.0.1\annotations-16.0.1.jar;"%REPO%"\com\neovisionaries\nv-websocket-client\2.9\nv-websocket-client-2.9.jar;"%REPO%"\com\squareup\okhttp3\okhttp\3.13.0\okhttp-3.13.0.jar;"%REPO%"\com\squareup\okio\okio\1.17.2\okio-1.17.2.jar;"%REPO%"\club\minnced\opus-java-api\1.0.4\opus-java-api-1.0.4.jar;"%REPO%"\net\java\dev\jna\jna\4.4.0\jna-4.4.0.jar;"%REPO%"\club\minnced\opus-java-natives\1.0.4\opus-java-natives-1.0.4.jar;"%REPO%"\net\sf\trove4j\trove4j\3.0.3\trove4j-3.0.3.jar;"%REPO%"\org\apache\maven\plugins\maven-install-plugin\2.4\maven-install-plugin-2.4.jar;"%REPO%"\org\apache\maven\maven-plugin-api\2.0.6\maven-plugin-api-2.0.6.jar;"%REPO%"\org\apache\maven\maven-project\2.0.6\maven-project-2.0.6.jar;"%REPO%"\org\apache\maven\maven-settings\2.0.6\maven-settings-2.0.6.jar;"%REPO%"\org\apache\maven\maven-profile\2.0.6\maven-profile-2.0.6.jar;"%REPO%"\org\apache\maven\maven-plugin-registry\2.0.6\maven-plugin-registry-2.0.6.jar;"%REPO%"\org\codehaus\plexus\plexus-container-default\1.0-alpha-9-stable-1\plexus-container-default-1.0-alpha-9-stable-1.jar;"%REPO%"\junit\junit\3.8.1\junit-3.8.1.jar;"%REPO%"\classworlds\classworlds\1.1-alpha-2\classworlds-1.1-alpha-2.jar;"%REPO%"\org\apache\maven\maven-model\2.0.6\maven-model-2.0.6.jar;"%REPO%"\org\apache\maven\maven-artifact-manager\2.0.6\maven-artifact-manager-2.0.6.jar;"%REPO%"\org\apache\maven\maven-repository-metadata\2.0.6\maven-repository-metadata-2.0.6.jar;"%REPO%"\org\apache\maven\wagon\wagon-provider-api\1.0-beta-2\wagon-provider-api-1.0-beta-2.jar;"%REPO%"\org\apache\maven\maven-artifact\2.0.6\maven-artifact-2.0.6.jar;"%REPO%"\org\codehaus\plexus\plexus-utils\3.0.5\plexus-utils-3.0.5.jar;"%REPO%"\org\codehaus\plexus\plexus-digest\1.0\plexus-digest-1.0.jar;"%REPO%"\org\apache\maven\plugins\maven-deploy-plugin\2.8.2\maven-deploy-plugin-2.8.2.jar;"%REPO%"\connect\ChatBotVk\1.0\ChatBotVk-1.0.jar
set EXTRA_JVM_ARGUMENTS=
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="ChatBot" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" connect.Connect %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal

:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%