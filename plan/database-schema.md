# Database Schema

## Overview
The platform maintains tournament data across eight competitive phases. Core entities capture players, teams, matches, bracket progress, and audit logs for compliance.

## Entity List
- **players** — Participant profile and contact information.
- **teams** — Optional grouping for squad-based tournaments.
- **tournaments** — High-level tournament definition with configuration metadata.
- **phases** — Represents each of the eight tournament phases, linked to tournaments.
- **matches** — Match scheduling, results, and tie-break data.
- **match_events** — Granular events per match (goals, cards, penalties).
- **standings** — Aggregated statistics per phase and player/team.
- **registrations** — Mapping of players/teams to tournaments and entry status.
- **audit_logs** — Operational logs for changes and administrative actions.

## Table Definitions

### `players`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | Generated server-side |
| `display_name` | VARCHAR(120) | NOT NULL | Visible alias |
| `country` | VARCHAR(64) | NULLABLE | ISO country code |
| `telegram_handle` | VARCHAR(64) | UNIQUE | Used by bot service |
| `created_at` | TIMESTAMP | NOT NULL | Default NOW |
| `updated_at` | TIMESTAMP | NOT NULL | Updated via trigger |

### `tournaments`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | |
| `name` | VARCHAR(120) | NOT NULL | |
| `season` | VARCHAR(16) | NOT NULL | e.g., `2024-S1` |
| `status` | VARCHAR(32) | NOT NULL | draft, active, archived |
| `max_players` | INT | NOT NULL | |
| `created_at` | TIMESTAMP | NOT NULL | |
| `updated_at` | TIMESTAMP | NOT NULL | |

### `phases`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | |
| `tournament_id` | UUID | FK → tournaments.id | |
| `phase_number` | SMALLINT | NOT NULL | 1..8 |
| `phase_name` | VARCHAR(60) | NOT NULL | e.g., Qualifiers |
| `starts_at` | TIMESTAMP | NULLABLE | |
| `ends_at` | TIMESTAMP | NULLABLE | |

### `matches`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | |
| `phase_id` | UUID | FK → phases.id | |
| `home_competitor_id` | UUID | NOT NULL | Player/team FK |
| `away_competitor_id` | UUID | NOT NULL | Player/team FK |
| `scheduled_at` | TIMESTAMP | NOT NULL | |
| `status` | VARCHAR(32) | NOT NULL | scheduled, live, finished |
| `home_score` | SMALLINT | DEFAULT 0 | |
| `away_score` | SMALLINT | DEFAULT 0 | |
| `metadata` | JSONB | NULLABLE | Arbitrary data |

### `match_events`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | |
| `match_id` | UUID | FK → matches.id | |
| `event_type` | VARCHAR(32) | NOT NULL | goal, card, penalty |
| `actor_id` | UUID | NULLABLE | Player involved |
| `timestamp` | TIMESTAMP | NOT NULL | Real-time occurrence |
| `payload` | JSONB | NULLABLE | Additional context |

### `standings`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | |
| `phase_id` | UUID | FK → phases.id | |
| `competitor_id` | UUID | NOT NULL | Player/team |
| `points` | SMALLINT | DEFAULT 0 | |
| `wins` | SMALLINT | DEFAULT 0 | |
| `losses` | SMALLINT | DEFAULT 0 | |
| `draws` | SMALLINT | DEFAULT 0 | |
| `goals_for` | SMALLINT | DEFAULT 0 | |
| `goals_against` | SMALLINT | DEFAULT 0 | |
| `updated_at` | TIMESTAMP | NOT NULL | |

### `audit_logs`
| Column | Type | Constraints | Notes |
| --- | --- | --- | --- |
| `id` | UUID | PK | |
| `entity_type` | VARCHAR(64) | NOT NULL | |
| `entity_id` | UUID | NOT NULL | |
| `action` | VARCHAR(64) | NOT NULL | create, update, delete |
| `performed_by` | UUID | NULLABLE | Admin account |
| `payload` | JSONB | NULLABLE | Diff metadata |
| `created_at` | TIMESTAMP | NOT NULL | |

## Relationships Overview
```
tournaments 1 ── * phases
phases     1 ── * matches
matches    1 ── * match_events
phases     1 ── * standings
players/teams ──< registrations >── tournaments
```

## Migration Strategy
- Use Flyway or Liquibase on the backend service for schema migrations.
- Version Room database with clear migration paths for mobile offline cache.
- Maintain SQL fixtures for integration tests and local seeding.
