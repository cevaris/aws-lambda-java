#!/usr/bin/env bash


usage() {
    echo "$0 <JAR_PATH> <LAMBDA_NAME>"
    echo "$0 path/my.jar devStringApiToUpper"
    exit -1
}

JAR_PATH=$1
if [ -z "$JAR_PATH" ]; then
    usage
fi

LAMBDA_NAME=$2
if [ -z "$LAMBDA_NAME" ]; then
    usage
fi

echo -e "$JAR_PATH\n$LAMBDA_NAME"
read -p "continue (y/N)? " CHOICE
CHOICE=$(echo "${CHOICE}" | tr '[:upper:]' '[:lower:]')
case "${CHOICE}" in
  y|yes)
    echo "starting upload..."
    aws lambda update-function-code \
        --function-name ${LAMBDA_NAME} \
        --zip-file "fileb://${JAR_PATH}"

    echo "done updating"
    ;;
  *)
    echo $CHOICE
    echo "choose not to upload, exiting..."
    exit -2
    ;;
esac
