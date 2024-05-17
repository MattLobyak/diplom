package ru.matthew.MyShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "description", nullable = false)
    @NotEmpty(message = "Описание не может быть пустым")
    @Size(min = 10, max = 300, message = "Описание должно быть от 10 до 300 символов длиной")
    private String description;

    @Column(name = "dateOfPublishing")
    private LocalDate dateOfPublishing;

    @ManyToOne
    @JoinColumn()
    private Advertisment advertisment;

    @ManyToOne
    @JoinColumn()
    private User user;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", dateOfPublishing=" + dateOfPublishing +
                ", advertisment=" + advertisment +
                '}';
    }

    //    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private Advertisment advertisment;
}
