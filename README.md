# PavOne 🌱

**Turning Clean Air into Digital Value**

PavOne is a carbon credit marketplace that transforms tree planting into verifiable, tradeable carbon credits using AI verification and decentralized storage.

## 🎯 What PavOne Does

- **Plant & Register**: Users register trees with location and species data
- **AI Verification**: Computer vision analyzes tree photos for health and growth
- **Credit Minting**: Verified trees generate carbon credits based on CO₂ sequestration
- **Marketplace**: Trade credits in a transparent, secure marketplace
- **Impact Tracking**: Monitor environmental impact with real-time data

## 🚀 Quick Start

```bash
# Clone and run
git clone <repository-url>
cd pavone
./run.sh
```

**Requirements**: Java 17+, PostgreSQL

## 🌟 Key Features

### 🤖 AI-Powered Verification
- Automated tree health assessment using Google Gemini Vision
- Fraud detection through photo analysis
- Growth estimation for accurate credit calculation

### 🔒 Decentralized Storage
- Photos and reports stored on Walrus network
- Immutable proof of tree existence and health
- Privacy-preserving location hashing

### 💰 Carbon Credit Marketplace
- Mint credits based on verified CO₂ sequestration
- List, buy, and retire credits seamlessly
- Transparent pricing and trading history

### 🌍 Environmental Impact
- Track real carbon offset contributions
- Species-specific sequestration calculations
- Community-driven tree planting initiatives

## 📱 API Overview

```bash
# Register a tree
POST /api/v1/trees

# Upload verification photo  
POST /api/v1/trees/{id}/photos

# Trigger AI verification
POST /api/v1/verify

# Mint carbon credits
POST /api/v1/credits/mint

# Trade in marketplace
POST /api/v1/listings
```

## 🎮 Demo Workflow

1. **Register Tree** → Upload tree data and initial photo
2. **AI Analysis** → System verifies tree health and authenticity  
3. **Credit Minting** → Generate credits based on CO₂ sequestration
4. **Marketplace** → List credits for sale or purchase existing ones
5. **Retirement** → Offset carbon footprint by retiring credits

## 🏗️ Built With

- **Backend**: Java Spring Boot
- **Database**: PostgreSQL  
- **AI**: Google Gemini Vision API
- **Storage**: Walrus decentralized network
- **Auth**: JWT with wallet integration

## 📊 Impact Metrics

- **Precision**: Grams-level carbon credit accuracy
- **Verification**: AI-powered fraud detection
- **Transparency**: Immutable storage on decentralized network
- **Scalability**: RESTful API for easy integration

## 🎯 Use Cases

- **Individual**: Offset personal carbon footprint
- **Corporate**: Meet sustainability goals and ESG requirements  
- **Communities**: Monetize local reforestation efforts
- **Developers**: Integrate carbon offsetting into applications

## 📚 Documentation

- [Technical Setup Guide](docs/WEB3_SETUP_GUIDE.md)
- [Project Implementation Details](docs/PROJECT_SUMMARY.md)
- [Spring Boot Reference](docs/HELP.md)
- [API Demo Commands](sample-data/demo-commands.sh)

## 🌱 Environmental Mission

PavOne bridges the gap between environmental action and economic incentives. By making tree planting profitable and verifiable, we accelerate global reforestation efforts while providing transparent carbon offsetting solutions.

**Every tree planted through PavOne contributes to a healthier planet and a sustainable future.**

---

*Ready to turn your environmental impact into digital value? Start with PavOne today.*