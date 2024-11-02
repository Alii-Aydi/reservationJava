package org.reservations.GestionReservations.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.reservations.GestionReservations.Presistences.Reservation;
import org.reservations.GestionReservations.Presistences.Room;
import org.reservations.GestionReservations.Presistences.TimeSlot;
import org.reservations.GestionReservations.Presistences.User;
import org.reservations.GestionReservations.Repos.ReservRep;
import org.reservations.GestionReservations.Repos.RoomRep;
import org.reservations.GestionReservations.Repos.TimeSlotRep;
import org.reservations.GestionReservations.Repos.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ReservationControlleur {
    UserRep userServise;
    RoomRep roomService;
    ReservRep reservationService;
    TimeSlotRep timeService;

    @Autowired
    public ReservationControlleur(UserRep userServise, RoomRep roomService, ReservRep reservationService,
	    TimeSlotRep timeService) {
	this.userServise = userServise;
	this.reservationService = reservationService;
	this.roomService = roomService;
	this.timeService = timeService;
    }

    @GetMapping("/")
    public String accueil(Model model) {
	List<Reservation> res = reservationService.findAll();
	model.addAttribute("reservations", res);
	return "accueil";
    }

    // Auth
    @GetMapping("/register")
    public String registerPage(@ModelAttribute User u) {
	return "register";
    }

    @Transactional
    @PostMapping("/register")
    public String register(@ModelAttribute User user, @ModelAttribute Reservation reservation, Model model) {
	if (userServise.existsByAddress(user.getAddress())) {
	    model.addAttribute("usernameExists", true);
	    return "register";
	}
	userServise.save(user);
	model.addAttribute("ID", user.getId());
	List<Reservation> res = reservationService.findAll();
	model.addAttribute("reservations", res);
	return "reservationForm";
    }

    // Reserv
    @GetMapping("/reservation")
    public String reservation(@ModelAttribute Reservation reservation, @ModelAttribute Room room,
	    @ModelAttribute TimeSlot timeSlot, @ModelAttribute User user, Model model) {
	List<Reservation> res = reservationService.findAll();
	model.addAttribute("reservations", res);
	
	return "reservationForm";
    }

    @Transactional
    @PostMapping("/reserver")
    public String makeReservation(@ModelAttribute Reservation reservation, Model model, RedirectAttributes redirectAttributes) {
	Optional<Room> room = roomService.findById(reservation.getRoom().getRoomNumber());
	Optional<User> user = userServise.findById(reservation.getUser().getId());
	List<Reservation> res = reservationService.findAll();
	model.addAttribute("reservations", res);
	if (user.isEmpty()) {
	    model.addAttribute("error", "user not found");
	    return "reservationForm";
	}
	if (room.isEmpty()) {
	    model.addAttribute("error", "room not existing");
	    return "reservationForm";
	}
	List<TimeSlot> allTimeSlotsForDate = timeService.findAllByDate(reservation.getTimeSlot().getDate());
	if (!allTimeSlotsForDate.isEmpty()) {
	    for (TimeSlot t : allTimeSlotsForDate) {
		if (!t.isLibre(room.get())) {
		    model.addAttribute("error", "time not available");
		    return "reservationForm";
		}
	    }
	}
	reservation.setUser(user.get());
	reservation.setRoom(room.get());
	room.get().getReservations().add(reservation);
	reservation.getTimeSlot().reserverPlage(reservation);
	timeService.save(reservation.getTimeSlot());
	reservation.calculerPrix();
	reservationService.save(reservation);
	redirectAttributes.addFlashAttribute("success", "reservation validee"+reservation.getReservationNumber());
	return "redirect:/reservation";
    }

}
