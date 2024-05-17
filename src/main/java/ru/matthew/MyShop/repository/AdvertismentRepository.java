package ru.matthew.MyShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.matthew.MyShop.models.Advertisment;

import java.util.List;
import java.util.Optional;

//Поиск и фильтрация данных об объявлениях
public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>  {
    //Поиск всех объявлений с учетом номера страницы и количества элементов
    Page<Advertisment> findAll(Pageable pageable);
    //Поиск всех объявлений с содержащий в названии 'name'
    //с учетом номера страницы и количества элементов
    Page<Advertisment> findAdvertismentByNameContains(String name, Pageable pageable);
    //Поиск объявленя по наименованию
    Optional<Advertisment> findAdvertismentByName(String name);
}
