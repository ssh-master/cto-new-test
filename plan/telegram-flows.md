# Telegram Interaction Flows

## Overview
The Telegram bot, implemented using the Pengrad Java library, enables real-time interaction between tournament administrators and participants. The bot supports registrations, match updates, and notifications across all eight phases of the tournament.

## Registration Flow
1. **User sends `/start` command.**
2. Bot responds with welcome message and registration prompt.
3. User provides email or player identifier via inline keyboard.
4. Bot calls `POST /players` API to create or fetch profile.
5. Confirmation message includes current phase context and onboarding checklist.

```
User → /start → Bot
Bot → Welcome + register button → User
User → Registration payload → Bot
Bot → Backend /players → Data store
Bot → Confirmation → User
```

## Match Result Reporting
1. Admin issues `/report` command within admin channel.
2. Bot prompts for match identifier.
3. Admin selects match via inline keyboard populated from `/matches?status=scheduled`.
4. Bot requests scoreline and optional notes.
5. Bot calls `PATCH /matches/{id}` with payload, then pushes summary to participants.

## Phase Advancement Broadcast
1. Scheduler or admin triggers `/advance_phase`.
2. Bot validates permissions via `/profile` endpoint.
3. Backend executes `POST /tournaments/{id}/phases/{phaseNumber}/advance`.
4. Bot notifies all subscribed participants with next phase start times.

## Notification Subscription
- Users opt-in with `/notify on` to receive personal alerts.
- Bot stores subscription status in `telegram_subscriptions` table (Room cache + backend).
- `/notify off` unsubscribes and removes webhook targets.

## Support & Escalation
- `/help` provides quick links to documentation and support contacts.
- `/contact_admin` triggers bot to notify admin chat with user context.

## Error Handling
- Network errors produce retry with exponential backoff and user-facing apology.
- Permission denials result in guidance to contact administrators.
- All interactions are logged in `audit_logs` for compliance and troubleshooting.
