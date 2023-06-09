package com.brainstrom.meokjang.food.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "FOOD")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Food {
    @Id @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "stock")
    private Double stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expire_date")
    private LocalDate expireDate;
    @Column(name = "storage_way")
    private String storageWay;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Food(Long userId, String foodName, Double stock, String expireDate, String storageWay) {
        this.userId = userId;
        this.foodName = foodName;
        this.stock = stock;
        this.expireDate = LocalDate.parse(expireDate);
        this.storageWay = storageWay;
        this.createdAt = LocalDateTime.now();
    }
}
