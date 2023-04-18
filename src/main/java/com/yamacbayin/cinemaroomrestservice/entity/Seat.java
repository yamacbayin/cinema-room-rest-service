package com.yamacbayin.cinemaroomrestservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Seat {

    public static boolean AVAILABLE = true;
    public static boolean PURCHASED = false;

    private int row, column;
    /**
     * Annotated with @JsonIgnore
     * Clients can only see a list of available seats
     */
    private boolean isAvailable;
    /**
     * Price is automatically set depending on the seat row
     * Rows [1, 4] cost 10$, [5, ∞) cost 8$
     */
    private int price;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.isAvailable = true;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return getRow() == seat.getRow() && getColumn() == seat.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }
}