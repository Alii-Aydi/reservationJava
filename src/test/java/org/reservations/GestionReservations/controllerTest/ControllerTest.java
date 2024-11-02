package org.reservations.GestionReservations.controllerTest;

import org.junit.jupiter.api.Test;
import org.reservations.GestionReservations.GestionReservationsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GestionReservationsApplication.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void testMakeReservation() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.post("/reserver").contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.param("user.id", "1") 
		.param("room.roomNumber", "101") 
		.param("timeSlot.date", "2023-01-01") 
		.param("timeSlot.startTime", "09:00").param("timeSlot.endTime", "11:00"))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.redirectedUrl("/reservation"))
		.andExpect(MockMvcResultMatchers.flash().attributeExists("success"));
    }
    
    
//    @Test
//    @Transactional
//    void testMakeReservationWithNonAvailableTimeSlot() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/reserver")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("user.id", "1") 
//                .param("room.roomNumber", "103") 
//                .param("timeSlot.date", "2022-02-15") 
//                .param("timeSlot.startTime", "09:00")
//                .param("timeSlot.endTime", "14:00"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("reservationForm"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
//                .andExpect(MockMvcResultMatchers.model().attribute("error", "time not available"));
//    }
}
