package com.smarttravel.flightservice.entity;
import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromLocation;
    private String toLocation;
    private String date;      // simple String or LocalDate
    private Double price;
    private Integer availableSeats;
}
