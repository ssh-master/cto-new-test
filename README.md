# eFootball Tournament Platform

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Java](https://img.shields.io/badge/Language-Java%2011-orange.svg)](https://www.oracle.com/java/)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-21-blue.svg)](https://developer.android.com/about/versions/lollipop)
[![Target SDK](https://img.shields.io/badge/Target%20SDK-34-blue.svg)](https://developer.android.com/about/versions/14)

An Android-first tournament management platform for eFootball competitions with integrated Telegram bot support. Built with clean architecture principles and a modular design for scalability and maintainability.

## 🎯 Project Vision

The eFootball Tournament Platform coordinates multi-phase tournaments, automates match scheduling, and provides a seamless experience for organizers and participants. The platform targets an Android-first mobile experience complemented by a modular backend architecture designed for future REST API migration.

## 🏗️ Architecture

### Multi-Module Structure

```
├── app/        # Android application module (presentation + data layer)
└── backend/    # Pure Java business logic module (domain layer)
```

**Key Benefits:**
- **Separation of Concerns**: Business logic decoupled from Android framework
- **Testability**: Pure Java backend enables fast unit testing
- **Reusability**: Backend module can be reused in future server deployments
- **Clean Architecture**: Clear boundaries between layers

### Technology Stack

#### Android Module
- **Hilt (Dagger)** - Dependency Injection
- **Room** - Local SQLite database with RxJava3 support
- **WorkManager** - Background job scheduling
- **Retrofit + OkHttp** - REST API client for future backend integration
- **Material Design** - Modern Android UI components

#### Backend Module
- **Pure Java 11** - No Android dependencies
- **Pengrad Telegram Bot API** - Telegram integration
- **RxJava3** - Reactive programming
- **SLF4J** - Logging framework

#### Build System
- **Gradle 8.2.0** - Build automation
- **Android Gradle Plugin 8.2.0**
- **Java 11** - Source and target compatibility

## 📱 Features

### Current Implementation
- ✅ Multi-module project structure
- ✅ Hilt dependency injection setup
- ✅ Room database with entities (Tournament, Player, Match)
- ✅ Telegram bot service integration
- ✅ Network configuration (Retrofit + OkHttp)
- ✅ Clean architecture package structure

### Planned Features
- 🚧 Tournament lifecycle management (8 phases)
- 🚧 Player registration and management
- 🚧 Automated match scheduling
- 🚧 Real-time notifications via Telegram
- 🚧 Leaderboard and statistics
- 🚧 Data import/export functionality
- 🚧 GitHub integration for Hall of Fame

## 📦 Module Details

### App Module
The Android application module containing:
- **Presentation Layer**: Activities, Fragments, ViewModels (MVVM)
- **Data Layer**: Room database, repositories
- **Service Layer**: Background services, WorkManager workers
- **Integration Layer**: REST API clients, Telegram bot service

[📖 Read more in app/README.md](app/README.md)

### Backend Module
Pure Java business logic module containing:
- **Service Layer**: Tournament management, match scheduling
- **Model Layer**: DTOs for data transfer
- **API Contracts**: Interfaces for future REST endpoints
- **Repository Interfaces**: Abstract data access

[📖 Read more in backend/README.md](backend/README.md)

## 🚀 Getting Started

### Prerequisites
- **Android Studio** Arctic Fox (2020.3.1) or newer
- **JDK 11** or higher
- **Android SDK** with API 34
- **Gradle** 8.2+ (included via wrapper)

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd eFootball-Tournament-Platform
   ```

2. **Open in Android Studio**
   - File → Open → Select project root directory
   - Android Studio will automatically sync Gradle

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run on device/emulator**
   - Select `app` configuration
   - Click Run (▶️) or press Shift+F10

### Configuration

#### Telegram Bot (Optional for development)
To enable Telegram bot features:
1. Create a bot via [@BotFather](https://t.me/botfather)
2. Add bot token to your configuration (future: environment variables)
3. Initialize in `TelegramBotService`

#### Backend API (Future)
Update base URL in `NetworkModule.java`:
```java
private static final String BASE_URL = "https://your-api-url.com/";
```

## 📐 Project Structure

```
com.efootball.tournament/
├── TournamentApplication.java    # Application entry point (Hilt)
├── config/                       # Dependency injection modules
├── data/                         # Data layer (Room, repositories)
├── domain/                       # Domain models and use cases
├── presentation/                 # UI layer (Activities, Fragments)
├── service/                      # Background services
├── integration/                  # External API integration
└── util/                         # Utility classes
```

[📖 Detailed structure in PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md)

## 🧪 Testing

### Running Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest
```

### Test Coverage
- Backend services: Pure Java unit tests
- Room database: Instrumented tests with in-memory DB
- ViewModels: Unit tests with Mockito (to be added)
- UI: Espresso instrumented tests (to be added)

## 🔧 Development

### Code Style
- Follow Android/Java code conventions
- Use meaningful names for classes, methods, and variables
- Add JavaDoc comments for public APIs
- Keep methods focused and concise

### Architecture Guidelines
1. **Separation of Concerns**: Each layer has a single responsibility
2. **Dependency Injection**: Use Hilt for all dependencies
3. **Reactive Programming**: Prefer RxJava over callbacks
4. **Clean Architecture**: Maintain clear layer boundaries
5. **No Android in Backend**: Backend module must remain pure Java

### Adding New Features

1. Define domain model in backend module
2. Implement business logic in service classes
3. Create Room entities and DAOs
4. Implement repository pattern
5. Create ViewModels for UI interaction
6. Build UI components

## 📚 Documentation

- [App Module Guide](app/README.md) - Android module details
- [Backend Module Guide](backend/README.md) - Business logic module details
- [Project Structure](PROJECT_STRUCTURE.md) - Complete architecture overview
- [Planning Documents](plan/) - Project roadmap and specifications

## 🛣️ Roadmap

### Phase 1: Foundation ✅
- [x] Multi-module project setup
- [x] Dependency injection (Hilt)
- [x] Database schema (Room)
- [x] Network configuration
- [x] Telegram bot integration setup

### Phase 2: Core Features 🚧
- [ ] Tournament CRUD operations
- [ ] Player registration system
- [ ] Match scheduling engine
- [ ] Telegram bot command handlers
- [ ] Background sync with WorkManager

### Phase 3: Advanced Features 📅
- [ ] Eight-phase tournament lifecycle
- [ ] Automated notifications
- [ ] Leaderboard and statistics
- [ ] Data import/export
- [ ] Configuration management

### Phase 4: Backend Migration 📅
- [ ] Deploy backend module as REST service
- [ ] Migrate to server-side PostgreSQL
- [ ] Implement authentication
- [ ] WebSocket for real-time updates

### Phase 5: Production 📅
- [ ] GitHub integration (Hall of Fame)
- [ ] CI/CD pipeline
- [ ] Performance optimization
- [ ] Production deployment

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Follow the code style and architecture guidelines
4. Write tests for new functionality
5. Commit your changes (`git commit -m 'Add amazing feature'`)
6. Push to the branch (`git push origin feature/amazing-feature`)
7. Open a Pull Request

### Contribution Guidelines
- Maintain clean architecture principles
- Write unit tests for business logic
- Update documentation for new features
- Follow existing code patterns
- No Android dependencies in backend module

## 📄 License

[Add your license information here]

## 👥 Team & Contact

[Add team information and contact details]

## 🙏 Acknowledgments

### Technologies
- [Android Jetpack](https://developer.android.com/jetpack) - Modern Android components
- [Hilt](https://dagger.dev/hilt/) - Dependency injection
- [Room](https://developer.android.com/training/data-storage/room) - Database abstraction
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client
- [Pengrad Telegram Bot API](https://github.com/pengrad/java-telegram-bot-api) - Telegram integration
- [RxJava](https://github.com/ReactiveX/RxJava) - Reactive extensions

### Inspiration
Built following clean architecture principles as outlined by Robert C. Martin (Uncle Bob) and Android architecture best practices.

---

**Status**: 🚧 Active Development | **Version**: 1.0.0-alpha | **Last Updated**: October 2024
