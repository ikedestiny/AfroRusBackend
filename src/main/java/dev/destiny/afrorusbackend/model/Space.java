package dev.destiny.afrorusbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userId;
    private SpaceType type;
    private Double weightKg; // nullable for documents
    private Double pricePerKg; // in rubles
    private String currency; // "RUB" or "NGN"
    private String description;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private String departureCity;
    private String arrivalCity;
    private boolean isVerifiedUser;
    private LocalDateTime createdAt;


    public Space(String id, String userId, SpaceType type, Double weightKg, Double pricePerKg, String currency, String description, LocalDateTime availableFrom, LocalDateTime availableTo, String departureCity, String arrivalCity, boolean isVerifiedUser, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.weightKg = weightKg;
        this.pricePerKg = pricePerKg;
        this.currency = currency;
        this.description = description;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.isVerifiedUser = isVerifiedUser;
        this.createdAt = createdAt;
    }

    public Space() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SpaceType getType() {
        return type;
    }

    public void setType(SpaceType type) {
        this.type = type;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDateTime getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDateTime availableTo) {
        this.availableTo = availableTo;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public boolean isVerifiedUser() {
        return isVerifiedUser;
    }

    public void setVerifiedUser(boolean verifiedUser) {
        isVerifiedUser = verifiedUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", weightKg=" + weightKg +
                ", pricePerKg=" + pricePerKg +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", isVerifiedUser=" + isVerifiedUser +
                ", createdAt=" + createdAt +
                '}';
    }
}
