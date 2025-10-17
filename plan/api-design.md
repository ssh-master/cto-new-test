# API Design

## Conventions
- **Base URL:** `https://api.efootball.example.com/v1`
- **Auth:** JSON Web Tokens obtained via OAuth2 password/client credentials flow.
- **Content Type:** `application/json; charset=utf-8`
- **Pagination:** Cursor-based with `next_cursor` and `limit` parameters.
- **Errors:** RFC 7807 problem+json payloads.

## Authentication & Identity
### POST `/auth/token`
Request:
```json
{
  "grant_type": "password",
  "username": "player@example.com",
  "password": "secret"
}
```
Response:
```json
{
  "access_token": "jwt-token",
  "expires_in": 3600,
  "refresh_token": "refresh-token"
}
```

### GET `/profile`
Returns authenticated user profile, linked to player or admin roles.

## Tournament Management
### GET `/tournaments`
- Filters: `status`, `season`.
- Returns list with phase summaries and registration counts.

### POST `/tournaments`
- Admin-only endpoint to create tournaments and configure eight-phase bracket.

### GET `/tournaments/{id}`
- Detailed configuration with current phase, standings, and schedule snapshot.

### PATCH `/tournaments/{id}`
- Allows updating metadata, phase progression, and configuration flags.

## Phase & Schedule Operations
### GET `/tournaments/{id}/phases`
- Lists phases ordered by phase number with start/end windows.

### POST `/tournaments/{id}/phases/{phaseNumber}/advance`
- Triggers progression, recalculating standings and generating matches for next phase.

### GET `/matches`
- Query by `phase_id`, `competitor_id`, `status`.
- Supports live scoreboard use cases.

### POST `/matches`
- Admin-only: schedules a match, accepts competitor IDs, times, and metadata.

### PATCH `/matches/{id}`
- Updates match status, scores, and highlights.

## Player & Team Management
### GET `/players`
- Search players by name or Telegram handle.

### POST `/players`
- Registers a new player, optionally linking to Telegram account.

### GET `/teams`
- Retrieves team rosters and statistics.

### POST `/teams/{teamId}/members`
- Adds member to a team with role (captain, player).

## Import & Export
### POST `/tournaments/{id}/import`
- Accepts CSV/JSON payloads for bulk registrations or results.

### GET `/tournaments/{id}/export`
- Generates aggregated statistics for Hall of Fame publishing.

## Bot Integration Webhooks
### POST `/bot/telegram/webhook`
- Receives updates from Telegram (Pengrad library adapter).
- Dispatches commands to bot service layer.

### POST `/bot/telegram/notify`
- Internal endpoint used by backend to push notifications to Telegram chat IDs.

## Observability
### GET `/health`
- Liveness and readiness details, including database connectivity.

### GET `/metrics`
- Exposes Prometheus metrics for infrastructure monitoring.
