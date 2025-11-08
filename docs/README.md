# PavOne Technical Documentation

This folder contains detailed technical documentation for developers and implementers.

## 📋 Documentation Index

### Setup & Configuration
- **[WEB3_SETUP_GUIDE.md](WEB3_SETUP_GUIDE.md)** - Complete setup guide for Web3 services (Walrus, Gemini AI, Sui blockchain)
- **[HELP.md](HELP.md)** - Spring Boot framework references and Maven documentation

### Implementation Details  
- **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** - Complete project status, architecture, and implementation details

### API & Demo
- **[../sample-data/demo-commands.sh](../sample-data/demo-commands.sh)** - Complete API demo workflow with cURL commands
- **[PavOne-Postman-Collection.json](PavOne-Postman-Collection.json)** - Postman collection for API testing

## 🏗️ Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Spring Boot   │    │   PostgreSQL    │
│   (Future)      │◄──►│   Backend       │◄──►│   Database      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  External APIs  │
                    │  • Walrus       │
                    │  • Gemini AI    │
                    │  • Sui (Future) │
                    └─────────────────┘
```

## 🔧 Tech Stack

- **Java 17** + Spring Boot 3.5.7
- **PostgreSQL** with JSON support
- **JWT** authentication
- **WebFlux** for async API calls
- **Maven** build system

## 🚀 Quick Development Setup

```bash
# 1. Start PostgreSQL
# 2. Create database 'pavone'
# 3. Run application
./run.sh

# 4. Test with demo commands
./sample-data/demo-commands.sh
```

## 📊 Database Schema

Key entities:
- **Users** - Wallet-based user accounts
- **Trees** - Registered trees with location data
- **Photos** - Tree photos stored on Walrus
- **Reports** - AI verification results
- **Credits** - Minted carbon credits (in grams)
- **Listings** - Marketplace entries

## 🔌 API Endpoints

### Core Flows
1. **Tree Registration** → `POST /api/v1/trees`
2. **Photo Upload** → `POST /api/v1/trees/{id}/photos`  
3. **AI Verification** → `POST /api/v1/verify`
4. **Credit Minting** → `POST /api/v1/credits/mint`
5. **Marketplace Trading** → `POST /api/v1/listings`

### Authentication
- JWT-based with wallet addresses
- Mock implementation for hackathon demo
- `X-Demo-Wallet` header for testing

## 🎯 Implementation Status

✅ **Complete**: All core features implemented and tested  
✅ **Database**: Full schema with relationships  
✅ **APIs**: All endpoints functional with mock integrations  
✅ **Demo**: Complete workflow available  
🔄 **Optional**: Real Web3 service integration  

## 🔍 Code Organization

```
src/main/java/cfd/debugsoul/pavone/
├── config/          # Security & WebClient configuration
├── controller/      # REST API endpoints
├── dto/            # Request/Response objects
├── entity/         # JPA entities
├── repository/     # Data access layer
├── service/        # Business logic
└── util/           # JWT utilities
```

## 🧪 Testing

- **Unit Tests**: Service layer testing
- **Integration Tests**: Full API workflow
- **Demo Script**: End-to-end demonstration
- **Mock Services**: Walrus and Gemini AI mocks

## 📈 Performance Considerations

- **Database**: UUID primary keys, JSON metadata storage
- **Storage**: Async file uploads to Walrus
- **AI**: Batched verification requests
- **Caching**: JWT token validation caching

## 🔒 Security Features

- **Location Privacy**: Hashed coordinates
- **JWT Authentication**: Secure API access
- **Input Validation**: All endpoints protected
- **CORS Configuration**: Controlled cross-origin access

---

For specific implementation questions, refer to the individual documentation files or examine the source code in the respective packages.