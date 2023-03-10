package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime changeDate = LocalDateTime.now();

    @ManyToOne
    private User changeUser;

    public AbstractEntity() {
    }

    public AbstractEntity(User changeUser) {
        this.changeUser = changeUser;
    }

    public Long getId() {
        return id;
    }

    public User getChangeUser() {
        return changeUser;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }
}
