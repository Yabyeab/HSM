package Home_page;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
   
    private StringProperty Patient_Id;
    private StringProperty Doctor_Id;
   
    private StringProperty Status;
   

    public Appointment(String Patient_Id, String Doctor_Id, String Status) {
       this.Patient_Id = new SimpleStringProperty(Patient_Id);
        this.Doctor_Id = new SimpleStringProperty(Doctor_Id);
        this.Status = new SimpleStringProperty(Status);
    }

   

    public String getPatientId() {
        return Patient_Id.get();
    }

     public void setPatient_Id(String value){
        Patient_Id.set(value);
    }

    public String getDoctorId() {
        return Doctor_Id.get();
    }

     public void setDoctorId(String value){
        Doctor_Id.set(value);
    }
   
    public String getStatus() {
       return Status.get();
    }

    public void setStatus(String value){
        Status.set(value);
    }
    public StringProperty Patient_IdProperty() {
        return Patient_Id;
    }
    public StringProperty Doctor_IdProperty() {
        return Doctor_Id;
    }
    public StringProperty StatusProperty() {
        return Status;
    }
   
}