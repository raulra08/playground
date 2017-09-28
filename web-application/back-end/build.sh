#!/bin/bash

mvn clean install
docker build -t raulzuk/balance-service .