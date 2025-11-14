package org.example;

import java.util.Date;

public class Measurements {

    //Fields
    private int measurement_id;
    private int user_id;
    private Date log_date;
    private double weight_lbs;
    private double chest_inches;
    private double arms_inches;
    private double waist_inches;
    private double bodyfat_percent;

    //Constructor
    public Measurements(int measurement_id, int user_id, Date log_date, double weight_lbs,
                        double chest_inches, double arms_inches, double waist_inches, double bodyfat_percent){
        this.measurement_id = measurement_id;
        this.user_id = user_id;
        this.log_date = log_date;
        this.weight_lbs = weight_lbs;
        this.chest_inches = chest_inches;
        this.arms_inches = arms_inches;
        this.waist_inches = waist_inches;
        this.bodyfat_percent = bodyfat_percent;
    }

    //toString
    @Override
    public String toString(){
        return "Measurements [measurement_id: "+measurement_id+", user_id: "+user_id+", log_date: "+log_date+
        ", weight_lbs: "+weight_lbs+", chest_inches: "+chest_inches+", arms_inches: "+arms_inches+
        ", waist_inches: "+waist_inches+", bodyfat_percent: "+bodyfat_percent+"]";
    }

    //Getters
    public int getMeasurementID(){
        return this.measurement_id;
    }

    public int getUserID(){
        return this.user_id;
    }

    public Date getLogDate(){
        return this.log_date;
    }

    public double getWeight(){
        return this.weight_lbs;
    }

    public double getChest(){
        return this.chest_inches;
    }

    public double getArms(){
        return this.arms_inches;
    }

    public double getWaist(){
        return this.waist_inches;
    }

    public double getBodyFat(){
        return this.bodyfat_percent;
    }



}
