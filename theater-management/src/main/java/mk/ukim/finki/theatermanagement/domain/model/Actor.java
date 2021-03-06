package mk.ukim.finki.theatermanagement.domain.model;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.name.FullName;

import javax.persistence.*;


@Entity
@Table(name = "actors")
@Getter
@Setter
public class Actor extends AbstractEntity<ActorId> {
    @EmbeddedId
    private ActorId id;

    @Version
    private Long version;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name",nullable = false))
    })
    private FullName fullName;
}
