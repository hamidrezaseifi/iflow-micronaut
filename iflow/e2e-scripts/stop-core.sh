#!/usr/bin/env bash
# use env -S to allow parameter to bash - doesnt work in AWS build
#
# Use Case: resets the test environment:
#   * stops modules
#   * drops databases 
#   * drops indexes
#
## goto root dir relative to this script
## source constants
cd $(dirname $(readlink -f "$BASH_SOURCE"))/..
. ./e2e-scripts/e2e.rc
initShell

##########################    
## parse command line arguments
parseArguments $@

##########################    
## Script code comes here...
## 

echo
echo "Stopping core..."
echo

stopModules "$PORT_CORE"

echo
echo "Core is stopped."
echo