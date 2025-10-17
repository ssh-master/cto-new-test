# Backend Module - Pure Java Business Logic

## Overview
The `backend` module is a pure Java library module that encapsulates the core business logic for the eFootball Tournament Platform. It is intentionally decoupled from Android-specific frameworks to enable:

1. **Code Reusability** - Business logic can be shared between Android client and future server deployments
2. **Testability** - Pure Java code is easier to test without Android dependencies
3. **Migration Path** - Smooth transition to RESTful backend services
4. **Separation of Concerns** - Clear boundary between presentation/platform and business logic

## Architecture Principles

### Clean Architecture
This module represents the **Domain** and **Application Service** layers:
- No Android framework dependencies
- Framework-agnostic business rules
- Dependency inversion (depends on abstractions, not implementations)

### SOLID Principles
- **Single Responsibility** - Each service has one clear purpose
- **Open/Closed** - Extensible through interfaces
- **Liskov Substitution** - Interface-based design
- **Interface Segregation** - Focused, role-specific interfaces
- **Dependency Inversion** - Depend on abstractions (interfaces)

## Package Structure

### `com.efootball.tournament.backend.api`
**REST API Contracts**
- Interface definitions for future REST endpoints
- `TournamentApiService.java` - Tournament REST API contract
- Additional API interfaces to be added as needed

### `com.efootball.tournament.backend.service`
**Business Logic Services**
- `TournamentService.java` - Tournament lifecycle management
- `MatchSchedulingService.java` - Match scheduling algorithms (to be added)
- `PlayerRegistrationService.java` - Player registration logic (to be added)
- `NotificationService.java` - Notification dispatching (to be added)

### `com.efootball.tournament.backend.model`
**Data Transfer Objects (DTOs)**
- `TournamentDto.java` - Tournament data transfer object
- `PlayerDto.java` - Player data transfer object (to be added)
- `MatchDto.java` - Match data transfer object (to be added)

### `com.efootball.tournament.backend.repository`
**Repository Interfaces**
- Abstract data access contracts (to be implemented by app module)
- Enables dependency inversion

### `com.efootball.tournament.backend.util`
**Utility Classes**
- Pure Java utilities (validation, algorithms, etc.)
- No framework dependencies

## Key Technologies

### Core Java
- **Java 11** - Source and target compatibility
- Pure Java Standard Library - No Android APIs

### Network Communication
- **Retrofit** - REST client for future backend communication
- **OkHttp** - HTTP client
- **Gson** - JSON serialization

### Telegram Bot API
- **Pengrad Telegram Bot API** - Bot integration
- Shared between Android and future backend deployments

### Reactive Programming
- **RxJava3** - Reactive streams for asynchronous operations
- Framework-agnostic reactive patterns

### Dependency Injection
- **javax.inject** - JSR-330 standard annotations
- Compatible with Hilt, Dagger, Spring, etc.

### Logging
- **SLF4J** - Simple Logging Facade for Java
- Implementation-agnostic logging

## Use Cases

### Current Use
The Android `app` module depends on this module to:
- Execute business logic without Android framework coupling
- Share models (DTOs) between layers
- Prepare for future backend migration

### Future REST Migration
When migrating to a RESTful backend:

1. **Deploy as Standalone Service**
   ```
   backend/ → Standalone JAR/WAR
            → Deployed on server (Tomcat, Jetty, etc.)
            → Exposes REST APIs
   ```

2. **Android Client Connects via Retrofit**
   ```
   Android App → Retrofit → REST Backend
               ↓
           Local Room DB (offline cache)
   ```

3. **Shared Business Logic**
   - Tournament validation rules
   - Match scheduling algorithms
   - Scoring calculations
   - All remain in this module

## Eight-Phase Tournament Lifecycle

The backend module will implement the complete tournament lifecycle:

1. **Registration Phase** - Player sign-up and eligibility checks
2. **Group Stage** - Initial round-robin matches
3. **Qualification Round** - Determining bracket seeding
4. **Round of 16** - Single elimination begins
5. **Quarter-Finals** - Top 8 compete
6. **Semi-Finals** - Top 4 compete
7. **Finals** - Championship match
8. **Results & Publishing** - Hall of Fame, GitHub integration

Each phase has distinct business rules implemented in service classes.

## Testing

### Unit Testing
- **JUnit 4** - Testing framework
- **Mockito** - Mocking framework for dependencies
- Focus on testing business logic without Android

### Test Coverage Goals
- Service classes: 80%+ coverage
- DTO validation: 100% coverage
- Algorithm correctness: Critical path testing

## Migration Strategy

### Phase 1 (Current)
- Backend module as library dependency
- Business logic executed locally in Android app

### Phase 2 (Future)
- Deploy backend module as standalone service
- Implement REST controllers (Spring Boot/Micronaut)
- Android app migrates to REST API calls
- Room DB becomes offline cache only

### Phase 3 (Future)
- Add PostgreSQL/MySQL for server-side persistence
- Implement authentication/authorization
- Add WebSocket support for real-time updates
- Scale horizontally with load balancers

## Dependencies

### Network & Serialization
- Retrofit 2.9.0
- OkHttp 4.12.0
- Gson 2.10.1

### Telegram Bot
- Pengrad Telegram Bot API 6.9.1

### Reactive Programming
- RxJava3 3.1.8

### Dependency Injection
- javax.inject 1

### Logging
- SLF4J API 2.0.9
- SLF4J Simple 2.0.9

### Testing
- JUnit 4.13.2
- Mockito 5.8.0

## Design Patterns

### Service Layer Pattern
- Encapsulate business logic in service classes
- Services are stateless and thread-safe

### Data Transfer Object (DTO)
- Immutable data carriers
- Clear contracts between layers

### Repository Pattern
- Abstract data persistence
- Enable swapping implementations (Room → REST → SQL)

### Dependency Inversion
- Services depend on repository interfaces
- Implementations injected at runtime

## Contributing

### Guidelines
1. **Keep it Pure** - No Android framework dependencies
2. **Interface-First** - Define contracts before implementations
3. **Test Coverage** - Write unit tests for all business logic
4. **Documentation** - JavaDoc all public APIs
5. **Immutability** - Prefer immutable DTOs
6. **Thread Safety** - Services must be thread-safe
7. **Logging** - Use SLF4J for all logging

### Adding New Services
1. Create interface in `service/` package
2. Implement business logic
3. Define DTOs in `model/` package
4. Write comprehensive unit tests
5. Document expected behavior

### Preparing for REST Migration
When adding new features, consider:
- What will become a REST endpoint?
- What data needs to be transferred?
- What business rules apply?
- How will it scale?

## Future Enhancements
1. Implement complete tournament lifecycle services
2. Add match scheduling algorithms
3. Implement scoring and ranking logic
4. Add data import/export services
5. Prepare REST API controllers for migration
6. Add validation framework (Bean Validation)
7. Implement caching strategies
8. Add metrics and observability hooks
