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
public class doctor {
     private final StringProperty Doctor_Id;
    private final StringProperty Name;
    private final StringProperty Department;
    private final StringProperty Email;
    private final StringProperty Contact_Number;
     private final StringProperty Number;
   
    public doctor(String Doctor_Id, String Name, String Department, String Email, String Contact_Number,String Number) {
        this.Number = new SimpleStringProperty(Number);
        this.Doctor_Id = new SimpleStringProperty(Doctor_Id);
        this.Name = new SimpleStringProperty(Name);
         this.Email = new SimpleStringProperty(Email);
        this.Department = new SimpleStringProperty(Department);
        this.Contact_Number = new SimpleStringProperty(Contact_Number);
       
       
    }

   
     public String getName() {
        return Name.get();
    }

    public String getEmail() {
        return Email.get();
    }

    public String getDepartment() {
        return Department.get();
  
    }
     public String getContact_Number() {
        return Contact_Number.get();
  
    }
      public String getDoctor_Id() {
        return Doctor_Id.get();
  
    }
     public String getNumberd() {
        return Number.get();
  
    }
       
       public void setName(String value){
        Name.set(value);
    }
    public void setemail(String value){
        Email.set(value);
    }
    public void setdepartment(String value){
        Department.set(value);
    }
    public void setContact_Number(String value){
        Contact_Number.set(value);
    }
    public void setDoctor_Id(String value){
        Doctor_Id.set(value);
    }
    public void setNumber(String value){
        Number.set(value);
    }
    public StringProperty Doctor_IdProperty() {
        return Doctor_Id;
    }

    public StringProperty NameProperty() {
        return Name;
    }

    public StringProperty DepartmentProperty() {
        return Department;
    }

    public StringProperty EmailProperty() {
        return Email;
    }

    public StringProperty Contact_NumberProperty() {
        return Contact_Number;
    }
     public StringProperty NumberProperty() {
        return Number;
    }
   
   
}