package com.brainstrom.meokjang.chat.domain;

import com.brainstrom.meokjang.chat.dto.ChatRoomDto;
import com.brainstrom.meokjang.deal.domain.Deal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "CHAT_ROOM")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoom_id")
    private Long id;

    @Column
    private String roomId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @Column
    private Long sender;

    @Column
    private Long receiver;

    @Column
    private String lastMessage;

    @Column
    private LocalDateTime lastTime;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    public static ChatRoom toEntity(ChatRoomDto chatRoomDto, Deal deal){
        ChatRoom chatRoomEntity = new ChatRoom();
        chatRoomEntity.setRoomId(chatRoomDto.getRoomId());
        chatRoomEntity.setDeal(deal);
        chatRoomEntity.setSender(chatRoomDto.getSender());
        chatRoomEntity.setReceiver(chatRoomDto.getReceiver());
        return chatRoomEntity;
    }
}
