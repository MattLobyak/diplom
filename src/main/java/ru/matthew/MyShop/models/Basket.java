package ru.matthew.MyShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "itemId")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private User owner;
}
