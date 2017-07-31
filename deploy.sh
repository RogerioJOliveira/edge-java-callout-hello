#!/bin/bash
BASEDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
USAGE="Usage: deploy [all|hello]"
ORG="org name"
ENV="env name"

if [ -z "$1" ]; then
	set -- "all"
fi

set -eu

if [ "$1" = "hello" ] || [ "$1" = "all" ] ; then
	cd $BASEDIR/hello-callout
	gradle clean
	gradle build

	cd $BASEDIR
	# rm hello-api/apiproxy/resources/java/*
	cp hello-callout/build/libs/edge-callout-hello.jar hello-api/apiproxy/resources/java/

	apigeetool deployproxy -u $EDGE_USERNAME -p $EDGE_PASSWORD -o $ORG -e $ENV -n hello-api -d ./hello-api
fi

