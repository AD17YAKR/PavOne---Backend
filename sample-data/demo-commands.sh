#!/bin/bash

# PavOne Demo Commands
# Make sure the application is running on localhost:8080

BASE_URL="http://localhost:8080/api/v1"
WALLET="0xabc123def456"

echo "=== PavOne Demo Script ==="

# 1. Login/Register user
echo "1. Logging in user..."
curl -X POST $BASE_URL/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"wallet\":\"$WALLET\"}"

echo -e "\n"

# 2. Create a tree
echo "2. Creating a tree..."
TREE_RESPONSE=$(curl -s -X POST $BASE_URL/trees \
  -H "Content-Type: application/json" \
  -d '{
    "ownerWallet":"'$WALLET'",
    "species":"Moringa",
    "plantedDate":"2025-01-01",
    "lat":26.9124,
    "lon":75.7873,
    "initialHeightMm":1200,
    "initialDbhMm":30,
    "metadata":{"notes":"Demo tree for hackathon"}
  }')

TREE_ID=$(echo $TREE_RESPONSE | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created tree with ID: $TREE_ID"

echo -e "\n"

# 3. Upload a photo (mock)
echo "3. Uploading photo..."
echo "Sample photo content" > sample-data/demo-photo.txt
PHOTO_RESPONSE=$(curl -s -X POST $BASE_URL/trees/$TREE_ID/photos \
  -H "X-Demo-Wallet: $WALLET" \
  -F "file=@sample-data/demo-photo.txt")

PHOTO_ID=$(echo $PHOTO_RESPONSE | grep -o '"photoId":"[^"]*' | cut -d'"' -f4)
echo "Uploaded photo with ID: $PHOTO_ID"

echo -e "\n"

# 4. Trigger verification
echo "4. Triggering AI verification..."
VERIFY_RESPONSE=$(curl -s -X POST $BASE_URL/verify \
  -H "Content-Type: application/json" \
  -d '{
    "treeId":"'$TREE_ID'",
    "photoId":"'$PHOTO_ID'",
    "requestedBy":"'$WALLET'"
  }')

REPORT_ID=$(echo $VERIFY_RESPONSE | grep -o '"reportId":"[^"]*' | cut -d'"' -f4)
SUGGESTED_GRAMS=$(echo $VERIFY_RESPONSE | grep -o '"suggestedGrams":[0-9]*' | cut -d':' -f2)
echo "Verification complete. Report ID: $REPORT_ID, Suggested grams: $SUGGESTED_GRAMS"

echo -e "\n"

# 5. Mint credits
echo "5. Minting carbon credits..."
CREDIT_RESPONSE=$(curl -s -X POST $BASE_URL/credits/mint \
  -H "Content-Type: application/json" \
  -d '{
    "treeId":"'$TREE_ID'",
    "ownerWallet":"'$WALLET'",
    "grams":'$SUGGESTED_GRAMS',
    "reportId":"'$REPORT_ID'"
  }')

CREDIT_ID=$(echo $CREDIT_RESPONSE | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Minted credit with ID: $CREDIT_ID"

echo -e "\n"

# 6. Check balance
echo "6. Checking credit balance..."
curl -s -X GET $BASE_URL/credits/balance/$WALLET

echo -e "\n"

# 7. Create marketplace listing
echo "7. Creating marketplace listing..."
LISTING_RESPONSE=$(curl -s -X POST $BASE_URL/listings \
  -H "Content-Type: application/json" \
  -d '{
    "creditId":"'$CREDIT_ID'",
    "sellerWallet":"'$WALLET'",
    "price":10.0,
    "currency":"USDC"
  }')

LISTING_ID=$(echo $LISTING_RESPONSE | grep -o '"id":"[^"]*' | cut -d'"' -f4)
echo "Created listing with ID: $LISTING_ID"

echo -e "\n"

# 8. View active listings
echo "8. Viewing active listings..."
curl -s -X GET $BASE_URL/listings

echo -e "\n"

echo "=== Demo Complete ==="
echo "Tree ID: $TREE_ID"
echo "Photo ID: $PHOTO_ID" 
echo "Report ID: $REPORT_ID"
echo "Credit ID: $CREDIT_ID"
echo "Listing ID: $LISTING_ID"