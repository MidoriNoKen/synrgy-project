package com.taufik.challenge6.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taufik.challenge6.Models.Entities.Auth.User;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant")
@SQLDelete(sql = "update merchant set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String location;

    private boolean open;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Product> products;

    private boolean deleted;
}
