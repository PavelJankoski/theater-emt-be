package mk.ukim.finki.reservation.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.reservation.domain.enums.ReservationStatus;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
public class Reservation extends AbstractEntity<ReservationId> {

    @EmbeddedId
    private ReservationId id;

    @Version
    private Long version;

    @Column(name = "reservated_on")
    private Instant reservatedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status",nullable = false)
    private ReservationStatus status;

    @Column(name = "seat_number", nullable = false)
    private int seatNo;

    @Column(name = "seat_row", nullable = false)
    private int seatRow;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="user_id"))
    private UserId userId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="show_id",nullable = false))
    private ShowId showId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="seat_id",nullable = false))
    private SeatId seatId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money price;
    
    
    public Reservation(UserId userId, ShowId showId, SeatId seatId,Money price) {
        this.id = DomainObjectId.randomId(ReservationId.class);
        this.userId = userId;
        this.showId = showId;
        this.seatId = seatId;
        this.reservatedOn = null;
        this.status = ReservationStatus.NOT_RESERVED;
        this.price=price;
    }
}

