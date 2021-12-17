RD "C:\Data\Soft\Tomcat\9\webapps\esm-1.0" /S /Q
del "C:\Data\Soft\Tomcat\9\webapps\esm-1.0.war" /F /Q
pushd .
cd "C:\Data\Java\Workspace\WebSpring\module3\esm"
call gradle build
xcopy "C:\Data\Java\Workspace\WebSpring\module3\esm\build\libs\esm-1.0.war" "C:\Data\Soft\Tomcat\9\webapps\"
popd