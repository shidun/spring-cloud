#!/bin/bash/env bash
mvn clean package -Dmaven.test.skip=true -U
sudo docker build -t registry.cn-shenzhen.aliyuncs.com/shidun/config .
sudo docker push registry.cn-shenzhen.aliyuncs.com/shidun/config