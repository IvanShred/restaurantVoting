package ru.project.restaurantvoting.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_vote"},
        name = "vote_unique_userid_datevote_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "date_vote")
    private LocalDate dateVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate date, User user, Restaurant restaurant) {
        this(null, date, user, restaurant);
    }

    public Vote(Integer id, LocalDate dateVote, User user, Restaurant restaurant) {
        super(id);
        this.dateVote = dateVote;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDateVote() {
        return dateVote;
    }

    public void setDateVote(LocalDate dateVote) {
        this.dateVote = dateVote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "dateVote=" + dateVote +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
