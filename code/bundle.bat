setlocal enabledelayedexpansion

if not "!JAVA8_64_HOME!"=="" (
    set PATH=!JAVA8_64_HOME!\bin;!PATH!
    set JAVA_HOME=!JAVA8_64_HOME!
)

call mvn.cmd validate --batch-mode
call mvn.cmd -Dfile.encoding=UTF-8 -DcreateChecksum=true --batch-mode clean source:jar javadoc:jar repository:bundle-create install
call mvn.cmd test
