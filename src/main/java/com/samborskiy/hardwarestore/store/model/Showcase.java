package com.samborskiy.hardwarestore.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samborskiy.hardwarestore.store.model.enums.TypeShowcases;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Table(name = "showcase")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at", nullable = false)
    LocalDate createAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "last_update_at", nullable = false)
    LocalDate lastUpdateAt;
}
