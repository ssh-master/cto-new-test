# App Module - Android Client

## Overview
The `app` module is the primary Android application module for the eFootball Tournament Platform. It provides the user interface, handles local data persistence, and coordinates communication with the backend service and Telegram bot integration.

## Architecture
This module follows **Clean Architecture** principles with a clear separation of concerns:

### Package Structure

#### `com.efootball.tournament.config`
**Dependency Injection Configuration**
- `AppModule.java` - Provides application-wide dependencies (Database, DAOs)
- `NetworkModule.java` - Configures Retrofit, OkHttp, and network clients

#### `com.efootball.tournament.data`
**Data Layer - Repositories and Data Sources**
- `local/` - Room database implementation
  - `entity/` - Database entities (TournamentEntity, PlayerEntity, MatchEntity)
  - `dao/` - Data Access Objects for CRUD operations
  - `converter/` - Type converters for Room
  - `AppDatabase.java` - Main database class

#### `com.efootball.tournament.domain`
**Domain Layer - Business Logic**
- `model/` - Pure business domain models
- `repository/` - Repository interfaces (to be implemented)
- `usecase/` - Use case interactors (to be implemented)

#### `com.efootball.tournament.presentation`
**Presentation Layer - UI Components**
- Activities, Fragments, ViewModels following MVVM pattern
- `MainActivity.java` - Main entry point activity

#### `com.efootball.tournament.service`
**Background Services**
- `TelegramBotService.java` - Telegram bot integration using Pengrad library
- WorkManager workers for background tasks (to be added)

#### `com.efootball.tournament.integration`
**External Integration**
- `RestApiClient.java` - REST API client facade for backend communication

#### `com.efootball.tournament.util`
**Utility Classes**
- `DateUtils.java` - Date formatting and manipulation helpers
- Additional utility classes as needed

## Key Technologies

### Dependency Injection
- **Hilt/Dagger** - Compile-time dependency injection framework
- Entry point: `TournamentApplication.java` annotated with `@HiltAndroidApp`

### Local Database
- **Room** - SQLite abstraction layer with reactive RxJava3 support
- Entities: Tournament, Player, Match
- Migration strategy: Schema export enabled for version control

### Background Processing
- **WorkManager** - Reliable background job scheduler
- Hilt integration for worker injection

### Network Communication
- **Retrofit** - Type-safe HTTP client for REST APIs
- **OkHttp** - HTTP client with logging interceptor
- **Gson** - JSON serialization/deserialization

### Telegram Bot Integration
- **Pengrad Telegram Bot API** - Java library for Telegram Bot API
- Real-time notifications and administrative commands

## Configuration

### SDK Versions
- **minSdk**: 21 (Android 5.0 Lollipop)
- **targetSdk**: 34 (Android 14)
- **compileSdk**: 34

### Java Version
- **Source/Target Compatibility**: Java 11

### Build Variants
- **debug** - Development build with debugging enabled
- **release** - Production build with R8 minification

## Database Schema
The Room database (`tournament_database`) includes:

1. **tournaments** - Tournament metadata and state
2. **players** - Player profiles and statistics
3. **matches** - Match scheduling and results

Schema files are exported to `app/schemas/` for version control.

## Dependency Versions
See `build.gradle` (root) for centralized version management:
- Room: 2.6.0
- WorkManager: 2.9.0
- Hilt: 2.48
- Retrofit: 2.9.0
- OkHttp: 4.12.0
- Pengrad Telegram Bot: 6.9.1

## Testing
- **JUnit 4** - Unit testing framework
- **Espresso** - UI testing framework
- **Room Testing** - In-memory database testing

## Future Enhancements
1. Implement Repository pattern with local/remote data sources
2. Add ViewModels and LiveData for reactive UI updates
3. Implement use case interactors for complex business logic
4. Add WorkManager workers for periodic sync and notifications
5. Implement Navigation Component for multi-screen flows
6. Add Jetpack Compose UI (optional migration)

## Contributing
When adding new features:
1. Follow the existing package structure
2. Use Hilt for dependency injection
3. Implement reactive patterns with RxJava3
4. Write unit tests for business logic
5. Use ViewBinding for UI interactions
6. Follow SOLID principles and Clean Architecture guidelines
