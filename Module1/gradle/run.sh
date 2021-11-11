############################################################################################
# function to build utils project with gradle
############################################################################################
function build_utils_project() {
  echo "Utils build start"
  (cd utils && gradle build)
}

############################################################################################
# function to deploy dependencies to local repository in multiproject/core/lib
############################################################################################
function copy_dependencies_from_utils_to_multiproject_core() {
  echo "Copying dependencies"
  cp utils/build/libs/utils-1.3.5.jar multiproject/core/lib/utils-1.3.5.jar
  cp utils/build/libs/utils-1.3.5-javadoc.jar multiproject/core/lib/utils-1.3.5-javadoc.jar
  cp utils/build/libs/utils-1.3.5-sources.jar multiproject/core/lib/utils-1.3.5-sources.jar
}

############################################################################################
# function to build multiproject project with gradle
############################################################################################
function build_multiproject() {
  echo "Starting multiproject build"
  (cd multiproject/ && gradle build --info --warning-mode all)
}

#build_utils_project
#copy_dependencies_from_utils_to_multiproject_core
#build_multiproject

echo "Running jar with sample data"
java -jar multiproject/build/libs/multiproject-1.0.jar "12" "79"

