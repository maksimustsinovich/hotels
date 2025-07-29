package by.ustsinovich.hotels.entity;

import by.ustsinovich.hotels.entity.embeddable.Address;
import by.ustsinovich.hotels.entity.embeddable.ArrivalTime;
import by.ustsinovich.hotels.entity.embeddable.ContactInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Embedded
    private Address address;

    @Embedded
    private ContactInfo contacts;

    @Embedded
    private ArrivalTime arrivalTime;

    @ElementCollection
    @CollectionTable(
            schema = "hotels",
            name = "hotel_amenities",
            joinColumns = {
                    @JoinColumn(name = "hotel_id")
            }
    )
    @Column(name = "amenity")
    private Set<String> amenities = new HashSet<>();

}
