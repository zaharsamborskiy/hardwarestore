package com.samborskiy.hardwarestore.store.model;

import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity @Table(name = "showcase")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor @AllArgsConstructor
public class Showcase {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    UUID id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "address", nullable = false)
    String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    TypeShowcases type;
    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;
    @Column(name = "last_update_at", nullable = false)
    LocalDateTime lastUpdateAt;
}
