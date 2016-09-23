#!/bin/sh


set -e
cd "$( cd "$( dirname "$0" )" && pwd )/.."

cd parent/com.incquerylabs.iot.parent && mvn -P eclipse clean package

