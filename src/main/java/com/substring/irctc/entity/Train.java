package com.substring.irctc.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.substring.irctc.annotations.ValidCoach;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="trains")
public class Train {

    @NotEmpty(message = "train number is required !!")
    @Size(min = 3,max = 20,message = "Invalid length train no. !!")
    @Pattern(regexp = "^\\d+$",message = "Invalid No. train number contains only digits.")
    @Id
    private String trainNo;

    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$", message = "Invalid train name. letters, space & Hyphens are allowed")
    private String name;

   private String routeName;



    public Train(String trainNo,
                 String name,
                 String routeName) {
        this.trainNo = trainNo;
        this.name = name;
        this.routeName = routeName;
    }

    public Train()
    {

    }

    public Train(String trainNo) {
        this.trainNo = trainNo;

    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
