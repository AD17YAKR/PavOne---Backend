#!/bin/bash

echo "=== PavOne - Carbon Credit Marketplace ==="
echo "Building and running the application..."

# Build the project
./mvnw clean package -DskipTests

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "Build successful! Starting application..."
    
    # Run the application with local profile
    java -jar target/pavone-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
else
    echo "Build failed! Please check the errors above."
    exit 1
fi