#!/usr/bin/env bash


usage() {
    echo "$0 <JAR_PATH> <LAMBDA_NAME> <HANDLER_NAME>"
    echo "$0 my.jar devStringApiToUpper com.cevaris.awslambda.stringapi.ToUpperHandler::handleRequest"
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

HANDLER_NAME=$3
if [ -z "$HANDLER_NAME" ]; then
    usage
fi

echo -e "$JAR_PATH\n$LAMBDA_NAME\n$HANDLER_NAME"
read -p "continue (y/N)? " CHOICE
CHOICE=$(echo "${CHOICE}" | tr '[:upper:]' '[:lower:]')
case "${CHOICE}" in
  y|yes)
    echo "starting upload..."
    aws lambda create-function \
        --function-name ${LAMBDA_NAME} \
        --zip-file "fileb://${JAR_PATH}" \
        --role arn:aws:iam::755892755226:role/stringApi \
        --handler ${HANDLER_NAME} \
        --runtime java8

    echo "done uploading"
    ;;
  *)
    echo $CHOICE
    echo "choose not to upload, exiting..."
    exit -2
    ;;
esac
