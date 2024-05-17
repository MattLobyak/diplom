package ru.matthew.MyShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.matthew.MyShop.models.*;
import ru.matthew.MyShop.repository.AdvertismentRepository;
import ru.matthew.MyShop.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertismentService {

    private final AdvertismentRepository advertismentRepository;
    private final UserRepository userRepository;

    //Получение данных фильтра, номера страницы, количества элементов на ней
    public Page<Advertisment> getAllAdvertisments(String title) {
        if (title != null) return advertismentRepository.findAdvertismentByNameContains(title, PageRequest.of(0,2));
        return advertismentRepository.findAll(PageRequest.of(0, 2));
    }

    public Advertisment getAdvertisementById(long id) {
        return advertismentRepository.findById(id).orElse(null);
    }

    public Optional<Advertisment> getAdvertisementByName(String name) { return advertismentRepository.findAdvertismentByName(name); }

    public Advertisment registerAdvertisment(Principal principal, Advertisment advertisment) {
        advertisment.setDateOfPublishing(LocalDate.now());
        return advertismentRepository.save(advertisment);
    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findUserByEmail(principal.getName());
    }

    public void updateAdvertisment(Advertisment advertismentToUpdate, Long id) {
        advertismentToUpdate.setId(id);
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

    private Image fileToImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteAdvertisment(Long id) {
        advertismentRepository.delete(advertismentRepository.findById(id).orElse(null));
    }
}
