package com.panel.LRapp.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "countL")
    private int like;

    @Column(name = "countDL")
    private int disLike;

    public Posts(String content, int like, int disLike) {
        this.content = content;
        this.like = like;
        this.disLike = disLike;
    }
}
