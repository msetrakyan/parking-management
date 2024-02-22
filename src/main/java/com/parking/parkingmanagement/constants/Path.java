package com.parking.parkingmanagement.constants;

public class Path {

    public static final String PARKING = "/parking";
    public static final String PARKING_DELETE = PARKING + "/{id}";

    public static final String BOOKING = "/booking";

    public static final String BOOKING_COMMUNITY = BOOKING + "/community";

    public static final String AUTH = "/auth";

    public static final String REGISTER = AUTH + "/register";

    public static final String LOGIN = AUTH + "/login";

    public static final String USER = "/user/**";


}
