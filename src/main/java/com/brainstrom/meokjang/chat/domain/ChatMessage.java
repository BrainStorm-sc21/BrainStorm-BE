package com.brainstrom.meokjang.chat.domain;

import com.brainstrom.meokjang.chat.dto.ChatMessageDto;
import com.brainstrom.meokjang.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = "CHAT_MESSAGE")
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    @Id @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "user_id", nullable = false)
    private User sender;

    @Column(nullable = false, length = 300)
    private String message;

    @Column
    private LocalDateTime time;

    public static ChatMessage toEntity(ChatMessageDto chatMessageDto, ChatRoom chatRoom, User sender){
        ChatMessage chatMessageEntity = new ChatMessage();
        chatMessageEntity.setChatRoom(chatRoom);
        chatMessageEntity.setSender(sender);
        chatMessageEntity.setMessage(chatMessageDto.getMessage());
        chatMessageEntity.setTime(chatMessageDto.getTime());
        return chatMessageEntity;
    }
}