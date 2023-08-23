package com.samborskiy.hardwarestore.store.model;


import com.samborskiy.hardwarestore.store.model.enums.PositionProduct;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "product")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @ManyToOne
    @JoinColumn(name = "showcase_id", nullable = false)
    Showcase showcase;
    @Enumerated(EnumType.STRING)
    @Column(name = "position_product", nullable = false)
    PositionProduct positionProduct;
    @Column(name = "name", nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_product", nullable = false)
    TypeProduct typeProduct;
    @Column(name = "price", nullable = false)
    Integer price;
    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;
    @Column(name = "last_update_at", nullable = false)
    LocalDateTime lastUpdateAt;
}
