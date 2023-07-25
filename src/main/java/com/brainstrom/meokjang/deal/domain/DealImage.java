package com.brainstrom.meokjang.deal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "DealImage")
@Getter
@NoArgsConstructor
public class DealImage {

    @Id @Column(name = "di_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diId;

    @ManyToOne
    @JoinColumn(name = "deal_id", referencedColumnName = "deal_id", nullable = false)
    private Deal deal;

    @Column(name = "image") @Lob
    private byte[] image;
}
