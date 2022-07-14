package com.kevinjolley.lil.learningspring.util;

import com.kevinjolley.lil.learningspring.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component // So Spring will pick this up
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    // This is waiting for Spring to finish building and ready to work before moving forwards.
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;


    public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Iterable<Guest> guests = this.guestRepository.findAll();
        Iterable<Reservation> reservations = this.reservationRepository.findAll();

        rooms.forEach(System.out::println);
        guests.forEach(System.out::println);
        reservations.forEach(System.out::println);
    }
}
