#!/usr/bin/env bash
aws s3 cp s3://cnu-2k18/harya/application.properties src/main/resources/application.properties
gradle bootRun