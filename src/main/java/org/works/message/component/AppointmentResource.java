package org.works.message.component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.works.message.bean.MakeAppointment;

@Path("/appointment")
public class AppointmentResource {

	@GET
	@Path("/make")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public MakeAppointment makeAppointment() {
		MakeAppointment appointment = new MakeAppointment();
		appointment.setPatientIen("100");

		return appointment;
	}

}
