#!/bin/bash

# Load environment variables from .env.local file
if [ -f .env.local ]; then
    export $(cat .env.local | grep -v '^#' | xargs)
fi

# Run the Spring Boot application
mvn spring-boot:run
