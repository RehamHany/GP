package com.panel.LRapp.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@Entity
@Table(name = "userChallengePublic")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserChallengePublic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "icon")
    private String icon;

    @Column(name = "name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "days")
    private List<String> days;


    public UserChallengePublic(String icon, String name, String description, List<String> days) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.days = days;
    }
}
