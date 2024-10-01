package com.esvar.lokibot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyTelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;

    public MyTelegramBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }



    @Override
    public void onUpdateReceived(Update update) {
        List<String> groups = new ArrayList<>();
        groups.add("-1002320914817");
        groups.add("-1002386014228");
        groups.add("-1002334572214");


        if (update.hasMessage()){
            System.out.println(update.getMessage().getChatId());
            sendMessagesToChannels(update.getMessage().getText(), groups);
            groups.clear();
        }
    }

    public void sendMessagesToChannels(String message, List<String> channelIds) {
        for (String channelId : channelIds) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(channelId);
            sendMessage.setText(message);
            System.out.println(sendMessage.getChatId());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
