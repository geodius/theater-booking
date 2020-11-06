package hu.elte.fswp.theater_booking.security;

public class ResponseView {
    public static class PublicView {}
    public static class OwnerView extends PublicView {}
    public static class AdminView extends OwnerView {}
    public static class InternalView extends AdminView {}
}
