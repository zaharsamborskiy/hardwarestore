package com.samborskiy.hardwarestore.store.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.samborskiy.hardwarestore.store.model.enums.PositionProduct;
import com.samborskiy.hardwarestore.store.model.enums.TypeProduct;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;


@Entity @Table(name = "product")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
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
    Double price;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at", nullable = false)
    LocalDate createAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "last_update_at", nullable = false)
    LocalDate lastUpdateAt;
}
