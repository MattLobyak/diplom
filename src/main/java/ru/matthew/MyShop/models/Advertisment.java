package ru.matthew.MyShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advertisement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Advertisment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Заголовок объявления не может быть пустым")
    @Size(max = 100, message = "Заголовок объявления должен содержать не более 100 символов")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Описание объявления не может быть пустым")
    @Size(min = 10, message = "Описание объявления должно быть не короче 50 символов")
    private String description;

    @Column(name = "payment")
    @NotNull(message = "Цена не может быть пустой")
    private Payment payment;

    @Column(name = "category")
    @NotNull(message = "Категория не может быть пустой")
    private Category category;

    @Column(name = "price")
    private Integer price;

    @Column(name = "dateOfPublishing")
    private LocalDate dateOfPublishing;

    @Column(name = "city")
    @NotNull(message = "Цена не может быть пустой")
    private City city;

    @Column(name = "state")
    private State state;

    @Column(name = "status")
    private Status status;

    //Связь Объявление с автором
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "advertisment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews;


    public Integer getRating() {
        int rating = 0;
        if (reviews.size() != 0) {
            for (Review review : reviews) {
                rating += review.getRating();
            }
            //Расчет средней оценки объявления, приведение типа
            rating = (int) Math.ceil(rating / reviews.size());
            return rating;
        }
        else {
            return 0;
        }
    }
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "advertisment")
//    private List<Image> images = new ArrayList<>();

//    public void addImageToAdvertisment(Image image) {
//        image.setAdvertisment(this);
//        images.add(image);
//    }


}
