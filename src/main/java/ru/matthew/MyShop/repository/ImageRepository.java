package ru.matthew.MyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matthew.MyShop.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
