package com.efootball.tournament.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Service class for Telegram Bot integration.
 * 
 * Handles communication with Telegram users for:
 * - Tournament notifications
 * - Match reminders
 * - Registration confirmations
 * - Administrative commands
 * 
 * Uses the Pengrad Telegram Bot API library.
 */
@Singleton
public class TelegramBotService {
    
    private TelegramBot bot;
    
    @Inject
    public TelegramBotService() {
    }
    
    public void initialize(String botToken) {
        this.bot = new TelegramBot(botToken);
    }
    
    public void sendMessage(long chatId, String message) {
        if (bot == null) {
            throw new IllegalStateException("Bot not initialized. Call initialize() first.");
        }
        bot.execute(new SendMessage(chatId, message));
    }
    
    public boolean isInitialized() {
        return bot != null;
    }
}
