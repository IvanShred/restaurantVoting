package ru.project.restaurantvoting.to.response;

import ru.project.restaurantvoting.to.BaseTo;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class VoteResponseTo extends BaseTo {

    @NotNull
    private LocalDate dateVote;

    private int userId;

    private int restaurantId;

    public VoteResponseTo() {
    }

    public VoteResponseTo(Integer id, @NotNull LocalDate dateVote, int userId, int restaurantId) {
        super(id);
        this.dateVote = dateVote;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDateVote() {
        return dateVote;
    }

    public void setDateVote(LocalDate dateVote) {
        this.dateVote = dateVote;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "VoteResponseTo{" +
                "dateVote=" + dateVote +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
