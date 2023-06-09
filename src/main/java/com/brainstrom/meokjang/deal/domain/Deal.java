package com.brainstrom.meokjang.deal.domain;

import com.brainstrom.meokjang.chat.domain.ChatRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "DEAL")
@Getter
@NoArgsConstructor
public class Deal {

    @Id @Column(name = "deal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "deal_type", nullable = false)
    private Integer dealType;

    @Column(name = "deal_name", length = 30, nullable = false)
    private String dealName;

    @Column(name = "deal_content", length = 1000, nullable = false)
    private String dealContent;

    @Column(name = "location", length = 40, nullable = false)
    private String location;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "image1", length = 255)
    private String image1;

    @Column(name = "image2", length = 255)
    private String image2;

    @Column(name = "image3", length = 255)
    private String image3;

    @Column(name = "image4", length = 255)
    private String image4;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "deal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    @Builder
    public Deal(Long userId, Integer dealType, String dealName, String dealContent, String location, Double latitude,
                Double longitude, String image1, String image2, String image3, String image4, Boolean isClosed,
                Boolean isDeleted) {
        this.userId = userId;
        this.dealType = dealType;
        this.dealName = dealName;
        this.dealContent = dealContent;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.isClosed = isClosed;
        this.isDeleted = isDeleted;
    }

    public void update(Integer dealType, String dealName, String dealContent, String[] imageList) {
        this.dealType = dealType;
        this.dealName = dealName;
        this.dealContent = dealContent;
        this.image1 = imageList[0];
        this.image2 = imageList[1];
        this.image3 = imageList[2];
        this.image4 = imageList[3];
    }

    public void complete() {
        this.isClosed = true;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public String[] getImageList() {
        return new String[]{image1, image2, image3, image4};
    }
}
