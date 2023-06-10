package com.brainstrom.meokjang.notice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long targetUserId;
    private String title;
    private String body;
    private Map<String, String> data;

    @Builder
    public FCMNotificationRequestDto(Long targetUserId, String title, String body, Map<String, String> data) {
        this.targetUserId = targetUserId;
        this.title = title;
        this.body = body;
        this.data = data;
    }
}
