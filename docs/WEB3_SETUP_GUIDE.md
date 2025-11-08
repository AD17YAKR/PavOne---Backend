# 🌐 Web3 Setup Guide for PavOne

## 🚀 Quick Start (No Web3 Required)

**Good News!** PavOne works perfectly with **MOCK implementations** - no real Web3 setup needed for hackathon demo!

### ✅ Current Status
- **Database**: ✅ Ready (PostgreSQL with your credentials)
- **APIs**: ✅ Mock implementations work out of the box
- **Demo**: ✅ Full workflow available

### 🎯 Run Immediately
```bash
./run.sh
# or
java -jar target/pavone-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

---

## 🔧 Optional: Real Web3 Integration

If you want to connect to **real services** later, here's how:

### 1. Walrus Storage (Decentralized Storage)

**What it is**: Decentralized file storage for photos and reports

**Mock vs Real**:
- **Mock** (current): Returns fake CIDs like `bafy1234567890`
- **Real**: Actually stores files on Walrus network

**To get real access**:
1. Visit: https://walrus.site
2. Sign up for testnet access
3. Get API key from dashboard
4. Update: `WALRUS_API_KEY=your_real_key`

### 2. Gemini AI (Google's AI)

**What it is**: AI for analyzing tree photos

**Mock vs Real**:
- **Mock** (current): Returns fake analysis results
- **Real**: Actually analyzes photos with AI

**To get real access**:
1. Visit: https://ai.google.dev
2. Create Google Cloud account
3. Enable Gemini API
4. Get API key
5. Update: `GEMINI_API_KEY=your_real_key`

### 3. Sui Blockchain (Optional)

**What it is**: Blockchain for NFTs and transactions

**Mock vs Real**:
- **Mock** (current): All transactions are database-only
- **Real**: Creates actual blockchain transactions

**To get real access**:
1. Install Sui Wallet: https://sui.io/wallet
2. Create wallet and get private key
3. Get testnet SUI tokens from faucet
4. Update: `SUI_PRIVATE_KEY=your_wallet_key`

---

## 🎮 Demo Scenarios

### Scenario 1: Pure Mock (Recommended for Hackathon)
```bash
# Use current setup - everything works!
./run.sh
./sample-data/demo-commands.sh
```

### Scenario 2: Real AI Only
```bash
# Get Gemini API key, keep others as mock
export GEMINI_API_KEY=your_real_gemini_key
./run.sh
```

### Scenario 3: Full Web3 (Advanced)
```bash
# Get all real keys
export WALRUS_API_KEY=your_walrus_key
export GEMINI_API_KEY=your_gemini_key  
export SUI_PRIVATE_KEY=your_sui_key
./run.sh
```

---

## 🔑 Environment Variables Explained

### Required (Already Set)
```bash
# Database - Already configured for your setup
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
SPRING_DATASOURCE_USERNAME=promptuser
SPRING_DATASOURCE_PASSWORD=1234
```

### Optional Web3 Services
```bash
# Walrus (File Storage)
WALRUS_API_KEY=get_from_walrus_site
WALRUS_API_URL=https://publisher-devnet.walrus.space

# Gemini AI (Photo Analysis)  
GEMINI_API_KEY=get_from_google_ai_studio
GEMINI_API_URL=https://generativelanguage.googleapis.com

# Sui Blockchain (NFTs/Transactions)
SUI_RPC_URL=https://fullnode.testnet.sui.io
SUI_PRIVATE_KEY=get_from_sui_wallet
```

---

## 🎯 Hackathon Strategy

### Phase 1: Demo with Mocks ✅
- **Time**: 5 minutes
- **Setup**: Zero additional setup needed
- **Demo**: Full workflow works perfectly
- **Audience**: Sees complete carbon credit marketplace

### Phase 2: Add Real AI (Optional)
- **Time**: 15 minutes  
- **Setup**: Get Gemini API key
- **Demo**: Real photo analysis
- **Wow Factor**: Actual AI analyzing tree photos

### Phase 3: Full Web3 (If Time Allows)
- **Time**: 30+ minutes
- **Setup**: All real services
- **Demo**: True decentralized app
- **Wow Factor**: Real blockchain transactions

---

## 🚨 Troubleshooting

### "I can't get Web3 services"
**Solution**: Use mocks! They work perfectly for demo.

### "Gemini API not working"
**Solution**: Check API key, or fall back to mock.

### "Blockchain transactions failing"
**Solution**: Use database-only mode (current setup).

### "Demo not impressive enough"
**Solution**: Focus on the complete workflow - tree registration → AI verification → credit minting → marketplace trading. This is impressive regardless of mock vs real!

---

## 💡 Pro Tips

1. **Start with mocks** - they're reliable and fast
2. **Add real services gradually** - one at a time
3. **Have backup plan** - mocks always work
4. **Focus on workflow** - the business logic is the star
5. **Explain the vision** - show what it would be with real Web3

## 🎉 You're Ready!

Your PavOne project is **hackathon-ready** right now. The mock implementations provide a complete, working carbon credit marketplace that demonstrates the full vision without any Web3 complexity.

**Run it now**: `./run.sh` and start your demo! 🚀