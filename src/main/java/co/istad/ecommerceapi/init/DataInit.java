package co.istad.ecommerceapi.init;

import co.istad.ecommerceapi.features.auth.RoleRepository;
import co.istad.ecommerceapi.features.card.CardRepository;
import co.istad.ecommerceapi.features.card.CardTypeRepository;
import co.istad.ecommerceapi.domain.*;
import co.istad.ecommerceapi.features.category.CategoryRepository;
import co.istad.ecommerceapi.features.producet.ProductRepository;
import co.istad.ecommerceapi.features.user.UserRepository;
import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CardTypeRepository cardTypeRepository;
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void initData() {
        initCardType();
        initCard();
        initRoles();
        initUsers();
        initProducts();
        initCategory();

    }

    private void initRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().name("USER").build());
        roles.add(Role.builder().name("ADMIN").build());
        roleRepository.saveAll(roles);
    }



    private void initCardType() {
        CardType visaCard = new CardType();
        visaCard.setCardTypeName("Visa");

        cardTypeRepository.saveAll(List.of(visaCard));
    }


    private void initCard() {
        Card card = new Card();
        card.setNumber("4222-3222-1222-4333");
        card.setCvv("455");
        card.setExpiryDate(LocalDate.of(2027, 1, 1));

        CardType visaCardType = cardTypeRepository.findByName("Visa")
                .orElseThrow();
        card.setCardType(visaCardType);

        cardRepository.save(card);
    }



    private void initUsers() {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setName("Admin");
        user.setDob(LocalDate.of(2004,2,23));
        user.setPhoneNumber("0962226229");
        user.setGender("M");
        user.setEmail("admin@gamil.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setProfileImageUrl("admin");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsVerified(true);
        user.setCityOrProvince("Battambang");
        user.setKhanOrDistrict("Bovel");
        user.setVillage("Bovel");
        user.setStreet("168");

        user.setIsDeleted(false);
        user.setIsAccountNonExpired(true);
        user.setIsCredentialsNonExpired(true);
        user.setIsAccountNonLocked(true);

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).orElseThrow());
        roles.add(roleRepository.findById(2).orElseThrow());
        user.setRoles(roles);


        userRepository.save(user);

    }


    private void initProducts() {

        Product iphone16PM = new Product();
        iphone16PM.setProductName("iphone16PM");
        iphone16PM.setSize("256");
        iphone16PM.setPrice(BigDecimal.valueOf(1200));
        iphone16PM.setAvailable(true);
        iphone16PM.setStock(5);
        iphone16PM.setNumberOfPeopleBought(44       );
        iphone16PM.setImageUrl("Iphone 16 PM");

        Product iphone15PM = new Product();
        iphone15PM.setProductName("iphone15PM");
        iphone15PM.setSize("128");
        iphone15PM.setPrice(BigDecimal.valueOf(900));
        iphone15PM.setAvailable(true);
        iphone15PM.setStock(4);
        iphone15PM.setNumberOfPeopleBought(444);
        iphone15PM.setImageUrl("Iphone 16 PM");

        Product iphone14PM = new Product();
        iphone14PM.setProductName("iphone14PM");
        iphone14PM.setSize("256");
        iphone14PM.setPrice(BigDecimal.valueOf(700));
        iphone14PM.setAvailable(true);
        iphone14PM.setStock(4);
        iphone14PM.setNumberOfPeopleBought(1);
        iphone14PM.setImageUrl("Iphone 16 PM");

        productRepository.saveAll(List.of(iphone16PM, iphone15PM, iphone14PM));
    }

    private void initCategory() {

        Category iPhone  = new Category();
        iPhone .setCategoryName("IPhone");
        Category samsung= new Category();
        samsung.setCategoryName("Samsung");
        Category sony = new Category();
        sony.setCategoryName("Sony");

        categoryRepository.saveAll(List.of(iPhone, samsung, sony));
    }
}
