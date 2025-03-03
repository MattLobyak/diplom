package ru.matthew.MyShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.matthew.MyShop.models.*;

import java.util.List;
import java.util.Optional;

//Поиск и фильтрация данных об объявлениях
public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>  {
    //Поиск всех объявлений с учетом номера страницы и количества элементов
    Page<Advertisment> findAdvertismentByStatusEquals(Status status, Pageable pageable);

    //Поиск всех объявлений с содержащий в названии 'name'
    //с учетом номера страницы и количества элементов
    Page<Advertisment> findAdvertismentByNameContainsAndStateEqualsAndCategoryEqualsAndCityEqualsAndPriceIsGreaterThanEqualAndPriceLessThanEqualAndStatusEquals(String title, State state, Category category, City city, Integer pricemore, Integer priceless, Status status, Pageable pageable);
    Page<Advertisment> findAdvertismentByNameContainsAndPriceIsGreaterThanAndPriceLessThanAndStatusEquals(String title, Integer pricemore, Integer priceless, Status status, Pageable pageable);
    //Page<Advertisment> findAdvertismentByNameContainsAndStateEqualsAndCategoryEqualsAndCityEqualsAndPriceIsGreaterThanAndPriceLessThan(String title, State state, Category category, City city, Integer pricemore, Integer priceless, Pageable pageable);
    Page<Advertisment> findAdvertismentByNameContainsAndPriceIsGreaterThanEqual(String title, Integer pricemore, Pageable pageable);
    Page<Advertisment> findAdvertismentByPriceGreaterThanEqual(Integer pricemore, Pageable pageable);
    //Поиск объявленя по наименованию
    Optional<Advertisment> findAdvertismentByName(String name);
    List<Advertisment> findAdvertismentByOwnerEquals(User user);
}
