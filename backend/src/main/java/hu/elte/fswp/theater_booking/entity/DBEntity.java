package hu.elte.fswp.theater_booking.entity;

public interface DBEntity {
    int getId();
    boolean isSameAs(DBEntity other);
}
