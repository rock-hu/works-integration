package org.works.message.bean;

import java.io.Serializable;

public class MakeAppointment implements Serializable {
	private String patientIen;
	private String appointmentDateTime;

	public String getPatientIen() {
		return patientIen;
	}

	public void setPatientIen(String patientIen) {
		this.patientIen = patientIen;
	}

	public String getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(String appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
}
