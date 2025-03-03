package ru.matthew.MyShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "userorder")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment")
    @NotNull(message = "Выберите метод оплаты")
    private OrderPayment orderPayment;

    @Column(name = "name")
    @NotEmpty(message = "Имя получателя не может быть пустым")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "phoneNumber", nullable = false)
    @NotEmpty(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "(\\+375\\d{9})?", message = "Должен состоять из 12 цифр +375292222222")
    private String phoneNumber;

    @Column(name = "address")
    @NotEmpty(message = "Адрес заказа не может быть пустым")
    private String address;

    @Column(name = "status")
    private Status status;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardDate")
    private String cardDate;

    @Column(name = "holderInfo")
    private String holderInfo;

    @Column(name = "cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private User owner;

    @ManyToOne
    @JoinColumn(name = "advertisment_id", referencedColumnName = "id")
    @JsonIgnore
    private Advertisment item;

}
