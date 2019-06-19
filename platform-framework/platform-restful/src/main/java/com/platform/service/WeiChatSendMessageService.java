package com.platform.service;

import com.platform.entity.BReservationcardEntity;

public interface WeiChatSendMessageService {
    public void sendWeiChatMessage(BReservationcardEntity entity, String sendMessageFlag);
}
