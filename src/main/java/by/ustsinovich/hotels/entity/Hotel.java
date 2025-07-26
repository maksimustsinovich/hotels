package by.ustsinovich.hotels.entity;

import by.ustsinovich.hotels.entity.embeddable.Address;
import by.ustsinovich.hotels.entity.embeddable.ArrivalTime;
import by.ustsinovich.hotels.entity.embeddable.ContactInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "hotels", name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Embedded
    private Address address;

    @Embedded
    private ContactInfo contacts;

    @Embedded
    private ArrivalTime arrivalTime;

}
