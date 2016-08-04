#!/bin/sh

if [ $# -eq 0 ]; then
    echo "No arguments supplied!"
    exit 0
fi

set -e
# cd "$( cd "$( dirname "$0" )" && pwd )/.."

LEAP_SDK_FOLDER="/opt/leapmotion"

mkdir -p $LEAP_SDK_FOLDER

tar -xvzf $1 -C $LEAP_SDK_FOLDER --strip 1

MACHINE_TYPE=`uname -m`
if [ ${MACHINE_TYPE} == 'x86_64' ]; then
  cd $LEAP_SDK_FOLDER && dpkg --install Leap-*-x64.deb
else
  cd $LEAP_SDK_FOLDER && dpkg --install Leap-*-x86.deb
fi

