package ru.matthew.MyShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "basket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private User owner;

    @Column(name = "payment")
    @NotNull(message = "Цена не может быть пустой")
    private Payment payment;

    @Column(name = "price")
    private Integer price;

    @Column(name = "address")
    @NotEmpty(message = "Адрес заказа не может быть пустым")
    private String address;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn()
    private Advertisment advertisment;
}
