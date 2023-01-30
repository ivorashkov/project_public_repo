package com.example.web;

import com.example.web.model.entity.OfficeEntity;
import com.example.web.model.entity.TourOfferEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.OfficeRepository;
import com.example.web.repository.ToursRepository;
import com.example.web.repository.UserRepository;
import com.example.web.util.ValidatorUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TestWebAppRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final ToursRepository toursRepository;
    private final ValidatorUtil validatorUtil;

    public TestWebAppRunner(UserRepository userRepository, OfficeRepository officeRepository, ToursRepository toursRepository, ValidatorUtil validatorUtil) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.toursRepository = toursRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void run(String... args) throws Exception {

//        isActiveValidationTest(validatorUtil, userRepository);
//        isAdminValidationTest(validatorUtil, userRepository);
//        officesFound(officeRepository, userRepository);
//        List<Optional<TourOfferEntity>> allByCreationDate = toursRepository.findAllNewTourOffersOrderByDateAsc();
//        printAllEntries(toursRepository.findAllNewTourOffersOrderByDateDESC());
//        printAllEntries(toursRepository.findAllTourOffersByCountryOrderByCountryDESC());
//        printAllEntries(toursRepository.findAllTourOffersByCountryOrderByCountryASC());

           printAllEntries(toursRepository.findAll_TourOffers_ByDate());
    }

    private void printAllEntries(List<Optional<TourOfferEntity>> allByCreationDate) {
        for (Optional<TourOfferEntity> tourOfferEntity : allByCreationDate) {
            System.out.println(
                    "date " + tourOfferEntity.get().getCreationDate() +
                            " price: " + tourOfferEntity.get().getPrice() +
                            " country: " + tourOfferEntity.get().getTargetCountry() +
                    " city " + tourOfferEntity.get().getTargetCity() +
                            " duration: " + tourOfferEntity.get().getDurationInDays() +
                            " discount: " + tourOfferEntity.get().getDiscount() + "%" +
                            " transport: " + tourOfferEntity.get().getTransportType() +
                            "stars: " + tourOfferEntity.get().getStarsOfHotel());
        }
    }

    public static void officesFound(OfficeRepository officeRepository, UserRepository userRepository) {
        UserEntity user = userRepository.findUserEntityByFirstName("ivo").orElse(null);
        List<Optional<OfficeEntity>> offices = officeRepository.findAllOfficesByUserIdAsc(user.getId());

        for (Optional<OfficeEntity> office : offices) {
            System.out.println(office.get().getCity());
        }
    }

    public static void isAdminValidationTest(ValidatorUtil validatorUtil, UserRepository userRepository) {
        boolean isAdmin = validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("ivo").orElse(null));
        boolean isAdmin1 = validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("kolo").orElse(null));
        boolean isAdmin2 = validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("deno").orElse(null));
        System.out.println("ivo -> " + isAdmin);
        System.out.println("kolo -> " + isAdmin1);
        System.out.println("deno -> " + isAdmin2);
    }

    public static void isActiveValidationTest(ValidatorUtil validatorUtil, UserRepository userRepository) {
        boolean isActive = validatorUtil.isActive(userRepository.findUserEntityByFirstName("ivo").orElse(null));
        boolean isActive1 = validatorUtil.isActive(userRepository.findUserEntityByFirstName("kolo").orElse(null));
        boolean isActive2 = validatorUtil.isActive(userRepository.findUserEntityByFirstName("deno").orElse(null));
        System.out.println("ivo -> " + isActive);
        System.out.println("kolo -> " + isActive1);
        System.out.println("deno -> " + isActive2);
    }
}
