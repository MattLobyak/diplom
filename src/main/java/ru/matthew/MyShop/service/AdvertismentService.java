package ru.matthew.MyShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.matthew.MyShop.models.*;
import ru.matthew.MyShop.repository.AdvertismentRepository;
import ru.matthew.MyShop.repository.BasketRepository;
import ru.matthew.MyShop.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertismentService {

    private final AdvertismentRepository advertismentRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    public Page<Advertisment> getAllAdvertisments(){
        return advertismentRepository.findAdvertismentByStatusEquals(Status.ACTIVE, PageRequest.of(0, 10));
    }

    public Page<Advertisment> getAllAdvertismentsToModerate(){
        return advertismentRepository.findAdvertismentByStatusEquals(Status.MODERATION, PageRequest.of(0, 10));
    }

    //Получение данных фильтра, номера страницы, количества элементов на ней
    public Page<Advertisment> getFilterAdvertisments(String title, Category category, City city, State state, Integer fromprice, Integer toprice) {
        if (fromprice == null) fromprice = 0;
        if (toprice == null) toprice = 1000000000;

        System.out.println("Имя "  + title + "   Категория " + category + "   Город " + city + "   Качество " + state + "   Старт цена " + fromprice + "   Финал цена " + toprice);
        if (category==null && city == null && state == null) {
            System.out.println("По цене");
            return advertismentRepository.findAdvertismentByNameContainsAndPriceIsGreaterThanAndPriceLessThanAndStatusEquals(title, fromprice, toprice, Status.ACTIVE, PageRequest.of(0,10));
            } else{
            return advertismentRepository.findAdvertismentByNameContainsAndStateEqualsAndCategoryEqualsAndCityEqualsAndPriceIsGreaterThanEqualAndPriceLessThanEqualAndStatusEquals(title, state,
                    category, city, fromprice, toprice, Status.ACTIVE, PageRequest.of(0,10));
        }
    }

    public Advertisment getAdvertisementById(long id) {
        return advertismentRepository.findById(id).orElse(null);
    }

    public Optional<Advertisment> getAdvertisementByName(String name) { return advertismentRepository.findAdvertismentByName(name); }

    public Advertisment registerAdvertisment(Principal principal, Advertisment advertisment) {
        advertisment.setDateOfPublishing(LocalDate.now());
        advertisment.setOwner(getUserByPrincipal(principal));
        advertisment.setStatus(Status.MODERATION);
        if (advertisment.getPayment() == Payment.FREE) advertisment.setPrice(0);
        return advertismentRepository.save(advertisment);
    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findUserByEmail(principal.getName());
    }

    public void updateAdvertisment(Advertisment advertismentToUpdate, Long id) {
        Advertisment currentAdvertisment = advertismentRepository.getById(id);

        advertismentToUpdate.setId(id);
        advertismentToUpdate.setStatus(Status.MODERATION);
        advertismentToUpdate.setDateOfPublishing(currentAdvertisment.getDateOfPublishing());
        advertismentToUpdate.setOwner(currentAdvertisment.getOwner());
        advertismentRepository.save(advertismentToUpdate);
    }

    public Integer getAverageRating(Advertisment advertisment){
        int rating = 0;
        for (Review review : advertisment.getReviews()){
            rating+=review.getRating();
        }
        //Расчет средней оценки объявления, приведение типа
        rating = (int) Math.ceil(rating / advertisment.getReviews().size());
        return rating;
    }

//    public Advertisment registerAdvertisment(Advertisment advertisment, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
//        Image image1;
//        Image image2;
//        Image image3;
//        if (file1.getSize() != 0) {
//            image1 = fileToImage(file1);
//            image1.setPreviewImage(true);
//            advertisment.addImageToAdvertisment(image1);
//        }
//        if (file2.getSize() != 0) {
//            image2 = fileToImage(file2);
//            advertisment.addImageToAdvertisment(image2);
//        }
//        if (file3.getSize() != 0) {
//            image3 = fileToImage(file3);
//            advertisment.addImageToAdvertisment(image3);
//        }
//        advertisment.setDateOfPublishing(LocalDate.now());
//        return advertismentRepository.save(advertisment);
//    }

//    private Image fileToImage(MultipartFile file) throws IOException {
//        Image image = new Image();
//        image.setName(file.getName());
//        image.setOriginalFileName(file.getOriginalFilename());
//        image.setContentType(file.getContentType());
//        image.setSize(file.getSize());
//        image.setBytes(file.getBytes());
//        return image;
//    }

    public void deleteAdvertisment(Long id) {
        Advertisment advertismentToDelete = advertismentRepository.findById(id).orElse(null);
//        User owner = userRepository.findUserByAdvertismentsContains(advertismentToDelete);
//        owner.getAdvertisments().remove(advertismentToDelete);
//        userRepository.save(owner);
        advertismentRepository.deleteById(id);
        //advertismentRepository.deleteAll();
    }

    public void addAdvertismentToBusket(long id, Principal principal) {
        Basket basket = new Basket(null, id, userRepository.findUserByEmail(principal.getName()));
        basketRepository.save(basket);
    }

    public void deleteAdvertismentFromBusket(long id, Principal principal) {
        User owner = userRepository.findUserByEmail(principal.getName());
        Basket basket = basketRepository.findBasketByOwnerEqualsAndItemIdEquals(owner, id);
        owner.getBasketItems().remove(basket);
        basketRepository.delete(basket);
        userRepository.save(owner);
    }

    public void approve(long id) {
        Advertisment advertisment = advertismentRepository.findById(id).orElse(null);
        User owner = advertisment.getOwner();
        advertisment.setStatus(Status.ACTIVE);
        for (Advertisment eachAdvertisment : owner.getAdvertisments()) {
            if (eachAdvertisment == advertisment){
                advertisment.setStatus(Status.ACTIVE);
            }
        }
        userRepository.save(owner);
    }

    public void deny(long id) {
        Advertisment advertisment = advertismentRepository.findById(id).orElse(null);
        User owner = advertisment.getOwner();
        advertisment.setStatus(Status.BLOCKED);
        for (Advertisment eachAdvertisment : owner.getAdvertisments()) {
            if (eachAdvertisment == advertisment){
                advertisment.setStatus(Status.BLOCKED);
            }
        }
        userRepository.save(owner);
    }

    public List<Advertisment> findBasketAddvetisments(Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName());
        List<Advertisment> basket = new ArrayList<>();
        //List<Basket> basketItems = basketRepository.findBasketByOwner(userRepository.findUserByEmail(principal.getName()).getId());
        for (Basket basketItem : user.getBasketItems() ){
            basket.add(advertismentRepository.findById(basketItem.getItemId()).orElse(null));
        }
        return basket;
    }

}
