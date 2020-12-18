#!/usr/bin/env bash
# use env -S to allow parameter to bash
#
#
#   This script creates error during workflow and saves the failed customer again.
#   
#   This script expects the CORE service to publish data that is received by the test
#   webserver started by the unit tests.
#   
#   To create an error during workflow _after_ the merge step the publish url is modified
#   to connect to a fancy port - simpy changing the url does not work because of the test webserver
#   listens to the path "/".

##########################
## common startup pattern 
cd $(dirname $(readlink -f "$BASH_SOURCE"))/..
. e2e-scripts/e2e.rc
initShell
parseArguments $@

##########################
## Custom Script code...

##########################
# use -v parameter for maven output

##########################
## script functions

function startCore() {
    setDbEnvVars $MODULE_CORE
    startModules "$MODULE_CORE"
    sleep $IFLOW_E2E_RUN_PAUSE_SMALL
}

##########################
## Main

echo
echo "Running tests..."
echo

#buildModules "$MODULES_NO_COMMON_DB_UI"
buildModules "$MODULES_NO_COMMON_DB_UI"

createModuleAndDatabases "$MODULE_CORE"
sleep 2

# start up all modules
startModules "$MODULE_WORKFLOW $MODULE_PROFILE"

startCore

sleep $IFLOW_E2E_RUN_PAUSE_SMALL

echo "all modules are running ... Starting basic tests ..."
mvn verify $MAVEN_QUIET -P e2eBasic -pl common/
sleep 2

stopModules "$ALL_PORTS_NO_UI"

dropDatabases  "$MODULE_CORE"


handleSuccess
