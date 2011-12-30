#!/bin/sh
VERSION='0.1'
NAME=ajaxupload-${VERSION}

play build-module
rm -rf ~/play/modules/${NAME}
mkdir ~/play/modules/${NAME}
mv dist/${NAME}.zip ~/play/modules/${NAME}/
cd ~/play/modules/${NAME}/
unzip ${NAME}.zip
rm ${NAME}.zip






