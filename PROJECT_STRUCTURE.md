# eFootball Tournament Platform - Project Structure

## Overview
This is an Android multi-module project for managing eFootball tournaments with Telegram bot integration. The architecture follows Clean Architecture principles with a clear separation between presentation, domain, and data layers.

## Module Architecture

```
eFootball Tournament Platform
│
├── app/                          # Android Application Module
│   ├── src/main/java/com/efootball/tournament/
│   │   ├── TournamentApplication.java    # Hilt Application entry point
│   │   │
│   │   ├── config/               # Dependency Injection Configuration
│   │   │   ├── AppModule.java        # App-wide DI (Database, DAOs)
│   │   │   └── NetworkModule.java    # Network DI (Retrofit, OkHttp)
│   │   │
│   │   ├── data/                 # Data Layer
│   │   │   └── local/            # Local persistence (Room)
│   │   │       ├── AppDatabase.java      # Main database class
│   │   │       ├── converter/            # Type converters
│   │   │       ├── dao/                  # Data Access Objects
│   │   │       │   ├── TournamentDao.java
│   │   │       │   ├── PlayerDao.java
│   │   │       │   └── MatchDao.java
│   │   │       └── entity/               # Database entities
│   │   │           ├── TournamentEntity.java
│   │   │           ├── PlayerEntity.java
│   │   │           └── MatchEntity.java
│   │   │
│   │   ├── domain/               # Domain Layer (Business Logic)
│   │   │   ├── model/            # Domain models (pure business objects)
│   │   │   ├── repository/       # Repository interfaces (to be implemented)
│   │   │   └── usecase/          # Use case interactors (to be implemented)
│   │   │
│   │   ├── presentation/         # Presentation Layer (UI)
│   │   │   ├── MainActivity.java     # Main activity
│   │   │   ├── viewmodel/        # ViewModels (to be added)
│   │   │   └── fragment/         # Fragments (to be added)
│   │   │
│   │   ├── service/              # Background Services
│   │   │   ├── TelegramBotService.java   # Telegram bot integration
│   │   │   └── worker/           # WorkManager workers (to be added)
│   │   │
│   │   ├── integration/          # External Service Integration
│   │   │   └── RestApiClient.java    # REST API facade
│   │   │
│   │   └── util/                 # Utility Classes
│   │       └── DateUtils.java        # Date formatting utilities
│   │
│   └── src/main/res/             # Android Resources
│       ├── layout/               # XML layouts
│       ├── values/               # Strings, colors, themes
│       └── drawable/             # Images and icons
│
├── backend/                      # Pure Java Business Logic Module
│   └── src/main/java/com/efootball/tournament/backend/
│       ├── api/                  # REST API contracts (interfaces)
│       │   └── TournamentApiService.java
│       │
│       ├── service/              # Business logic services
│       │   └── TournamentService.java
│       │
│       ├── model/                # Data Transfer Objects (DTOs)
│       │   └── TournamentDto.java
│       │
│       ├── repository/           # Repository interfaces (to be added)
│       │
│       └── util/                 # Pure Java utilities
│
└── plan/                         # Project documentation
    ├── README.md                 # Project overview
    ├── architecture.md           # Architecture details
    ├── database-schema.md        # Database design
    ├── api-design.md             # API specifications
    └── telegram-flows.md         # Telegram bot workflows

```

## Technology Stack

### Android Module (`app`)

#### Core Android
- **Android SDK**: compileSdk 34, targetSdk 34, minSdk 21
- **Java**: Source/Target compatibility Java 11
- **AndroidX**: Core, AppCompat, ConstraintLayout, Material Design

#### Architecture Components
- **Hilt**: Dependency injection framework
- **Room**: SQLite database with RxJava3 support
- **WorkManager**: Background job scheduling
- **Lifecycle**: ViewModel, LiveData (to be added)

#### Networking
- **Retrofit 2.9.0**: Type-safe HTTP client
- **OkHttp 4.12.0**: HTTP client with logging
- **Gson 2.10.1**: JSON serialization

#### Telegram Bot
- **Pengrad Telegram Bot API 6.9.1**: Java Telegram Bot library

#### Reactive Programming
- **RxJava3 3.1.8**: Reactive extensions for Java
- **RxAndroid 3.0.2**: Android-specific RxJava bindings

#### Testing
- **JUnit 4.13.2**: Unit testing
- **Espresso 3.5.1**: UI testing
- **Room Testing 2.6.0**: Database testing

### Backend Module (`backend`)

#### Core
- **Java 11**: Pure Java library (no Android dependencies)
- **javax.inject 1**: Standard DI annotations

#### Networking
- **Retrofit 2.9.0**: REST client for future backend
- **OkHttp 4.12.0**: HTTP client
- **Gson 2.10.1**: JSON parsing

#### Telegram Bot
- **Pengrad Telegram Bot API 6.9.1**: Shared bot library

#### Reactive
- **RxJava3 3.1.8**: Platform-independent reactive programming

#### Logging
- **SLF4J 2.0.9**: Simple Logging Facade

#### Testing
- **JUnit 4.13.2**: Unit testing
- **Mockito 5.8.0**: Mocking framework

## Build Configuration

### Root Level
- **Gradle**: 8.2.0
- **Android Gradle Plugin**: 8.2.0
- **Hilt Gradle Plugin**: 2.48

### Module Configuration
- **App Module**: Android Application plugin + Hilt
- **Backend Module**: Java Library plugin

## Dependency Injection Setup

### Hilt Components Hierarchy
```
SingletonComponent (App-level)
├── AppModule           → Database, DAOs
├── NetworkModule       → Retrofit, OkHttp
└── Services            → TelegramBotService, etc.

ActivityComponent (Activity-level)
└── ViewModels          → To be added

WorkerComponent (WorkManager-level)
└── HiltWorkerFactory   → Background workers
```

### Entry Points
1. **TournamentApplication** - `@HiltAndroidApp` annotated application class
2. **MainActivity** - `@AndroidEntryPoint` annotated activity
3. **Future Workers** - `@HiltWorker` annotated WorkManager workers

## Database Schema

### Room Database: `tournament_database`

#### Tables
1. **tournaments** - Tournament metadata
2. **players** - Player profiles and stats
3. **matches** - Match scheduling and results

#### Relationships
- Match → Tournament (Many-to-One)
- Match → Player (Many-to-One for player1 and player2)

#### Migration Strategy
- Schema export enabled to `app/schemas/`
- Version 1: Initial schema
- Future migrations documented in version control

## Package Organization Principles

### Presentation Layer (app/presentation)
- Activities, Fragments
- ViewModels (MVVM pattern)
- UI adapters and view holders
- **Depends on**: Domain layer only

### Domain Layer (app/domain + backend/service)
- Business logic and use cases
- Domain models
- Repository interfaces
- **Depends on**: Nothing (pure business logic)

### Data Layer (app/data)
- Repository implementations
- Local data source (Room)
- Remote data source (Retrofit)
- **Depends on**: Domain layer (implements interfaces)

### Dependency Flow
```
Presentation → Domain ← Data
     ↓           ↓        ↓
    UI      Use Cases  Repositories
```

## Clean Architecture Benefits

### Separation of Concerns
- Each layer has a single, well-defined responsibility
- Changes in one layer don't affect others

### Testability
- Business logic (domain) can be tested without UI or database
- Pure Java backend module enables fast unit tests

### Flexibility
- Easy to swap implementations (Room → REST API)
- Platform-independent business rules

### Maintainability
- Clear structure makes codebase easier to navigate
- New developers can understand architecture quickly

## Development Workflow

### Adding New Features

1. **Define Domain Model** (`backend/model/`)
   - Create DTO for data transfer
   - Define validation rules

2. **Implement Business Logic** (`backend/service/`)
   - Write service class with business rules
   - Add unit tests

3. **Create Database Entity** (`app/data/local/entity/`)
   - Define Room entity
   - Update AppDatabase with entity

4. **Create DAO** (`app/data/local/dao/`)
   - Define data access methods
   - Use RxJava3 for reactive queries

5. **Implement Repository** (future)
   - Combine local and remote data sources
   - Handle caching and synchronization

6. **Create Use Case** (future)
   - Orchestrate business logic
   - Call repository methods

7. **Implement ViewModel** (future)
   - Expose LiveData/StateFlow to UI
   - Handle UI events

8. **Build UI** (`app/presentation/`)
   - Create Activity/Fragment
   - Bind to ViewModel

### Testing Strategy

#### Unit Tests
- Backend services (pure Java)
- ViewModels (with MockK/Mockito)
- Use cases and business logic

#### Integration Tests
- Room database operations
- Repository implementations
- API client interactions

#### UI Tests
- Espresso for Android UI
- User workflow testing

## Future Migration Path

### Phase 1: Current State
```
Android App
├── UI Layer (Activities, Fragments)
├── Backend Module (Business Logic)
└── Room Database (Local Persistence)
```

### Phase 2: REST Backend
```
Android App                    Backend Service
├── UI Layer                   ├── REST Controllers
├── ViewModels                 ├── Backend Module (reused)
├── Repositories               └── PostgreSQL Database
│   ├── Local (Room - cache)
│   └── Remote (Retrofit)
```

### Phase 3: Distributed System
```
Load Balancer
├── Backend Service 1
├── Backend Service 2
└── Backend Service N
    ├── REST APIs
    ├── WebSocket (real-time)
    ├── Telegram Bot Service
    └── Database Cluster
```

## Configuration Management

### Build Variants
- **debug**: Development with logging enabled
- **release**: Production with R8 optimization

### Environment Variables (Future)
- API base URLs
- Telegram bot tokens
- Feature flags
- Build-specific configuration

### ProGuard/R8
- Configured for all dependencies
- Keeps model classes and DI annotations
- See `app/proguard-rules.pro`

## CI/CD Considerations (Future)

### Continuous Integration
- Compile check on every commit
- Run unit tests
- Run instrumented tests
- Lint and static analysis

### Continuous Deployment
- Build APK/AAB
- Deploy to internal testing
- Promote to production after QA

## Contributing Guidelines

### Code Style
- Follow Android/Java conventions
- Use meaningful variable/method names
- Add JavaDoc for public APIs
- Keep methods focused and small

### Architecture Rules
1. **Never** import Android classes in backend module
2. **Always** use dependency injection (Hilt)
3. **Prefer** reactive streams (RxJava) over callbacks
4. **Implement** interfaces for loose coupling
5. **Write** tests for new features

### Pull Request Checklist
- [ ] Code compiles without warnings
- [ ] Unit tests pass
- [ ] New features have tests
- [ ] Documentation updated
- [ ] Follows existing patterns
- [ ] No Android dependencies in backend module

## Resources

### Documentation
- [App Module README](app/README.md)
- [Backend Module README](backend/README.md)
- [Planning Documents](plan/)

### External Resources
- [Android Architecture Guide](https://developer.android.com/topic/architecture)
- [Hilt Documentation](https://dagger.dev/hilt/)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Pengrad Telegram Bot API](https://github.com/pengrad/java-telegram-bot-api)

## Version History

### Version 1.0.0 (Current)
- Initial project structure
- Multi-module setup (app + backend)
- Hilt dependency injection
- Room database baseline
- Telegram bot integration placeholder
- Network configuration (Retrofit + OkHttp)

### Future Versions
- 1.1.0: Complete CRUD operations for tournaments
- 1.2.0: Player registration and management
- 1.3.0: Match scheduling implementation
- 2.0.0: REST backend migration
- 3.0.0: WebSocket real-time updates
