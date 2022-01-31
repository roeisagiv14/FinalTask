#!/usr/bin/env bash
set -e

if [ $# -ne 1 ]; then
  echo 1>&2 "$0: missing rootDir argument"
  exit 2
fi
rootDir=$1

rootSpecFile="${rootDir}/openApi/service.yaml"
outputSpecFile="${rootDir}/openApi/generated/service.yaml"

swaggerCombineCommand=swagger-combine

if [[ -z "${COMBINE_OPENAPI_SPECS_ENABLED}" ]]; then
  echo "COMBINE_OPENAPI_SPECS_ENABLED environment variable is not declared."
else
  if ! [ -x "$(command -v ${swaggerCombineCommand})" ]; then
    echo "Warning: ${swaggerCombineCommand} is not installed." >&2
    exit 0
  fi

  ${swaggerCombineCommand} "${rootSpecFile}" -o "${outputSpecFile}"
fi