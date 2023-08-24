/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Home_page;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author dell
 */

public class Patient {

    private final StringProperty Patient_Id;
    private final StringProperty Name;
    private final StringProperty Birthyear;
    private final StringProperty Date;
    private final StringProperty Contact_Number;
    private final StringProperty id;
    private final StringProperty Gender;
    private final StringProperty Assigneddid;
    private final StringProperty Status;
    private final StringProperty email;

    public Patient(String id,String Patient_Id, String Name, String Gender,String Birthyear, String Contact_Number, String email, String Date, String Status,String Assigneddid) {
        this.id = new SimpleStringProperty(id);
        this.Patient_Id = new SimpleStringProperty(Patient_Id);
        this.Name = new SimpleStringProperty(Name);
        this.Gender = new SimpleStringProperty(Gender);
        this.Birthyear = new SimpleStringProperty(Birthyear);
        this.Contact_Number = new SimpleStringProperty(Contact_Number);
        this.email = new SimpleStringProperty(email);
        this.Date = new SimpleStringProperty(Date);
        this.Status = new SimpleStringProperty(Status);

        this.Assigneddid = new SimpleStringProperty(Assigneddid);

    }

    public String getName() {
        return Name.get();
    }

    public String getBirthyear() {
        return Birthyear.get();
    }

    public String getDate() {
        return Date.get();

    }

    public String getContact_Number() {
        return Contact_Number.get();

    }

    public String getPatient_Id() {
        return Patient_Id.get();

    }

    public String getid() {
        return id.get();
    }

    public String getGender() {
        return Gender.get();
    }

    public String getAssigneddid() {
        return Assigneddid.get();

    }

    public String getStatus() {
        return Status.get();

    }

    public String getemail() {
        return email.get();

    }

    public void setName(String value) {
        Name.set(value);
    }

    public void setBirthyear(String value) {
        Birthyear.set(value);
    }

    public void setDate(String value) {
        Date.set(value);
    }

    public void setContact_Number(String value) {
        Contact_Number.set(value);
    }

    public void setPatient_Id(String value) {
        Patient_Id.set(value);
    }

    public void setid(String value) {
        id.set(value);
    }

    public void setGender(String value) {
        Gender.set(value);
    }

    public void setAssigneddid(String value) {
        Assigneddid.set(value);
    }

    public void setStatus(String value) {
        Status.set(value);
    }

    public void setemail(String value) {
        email.set(value);
    }

    public StringProperty Patient_IdProperty() {
        return Patient_Id;
    }

    public StringProperty NameProperty() {
        return Name;
    }

    public StringProperty DateProperty() {
        return Date;
    }

    public StringProperty BirthyearProperty() {
        return Birthyear;
    }

    public StringProperty Contact_NumberProperty() {
        return Contact_Number;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty GenderProperty() {
        return Name;
    }

    public StringProperty AssigneddidProperty() {
        return Assigneddid;
    }

    public StringProperty StatusProperty() {
        return Status;
    }

    public StringProperty emailProperty() {
        return email;
    }

}
