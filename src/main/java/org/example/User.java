package org.example;

import java.util.Date;

public class User {

    // Fields
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private Date date_joined;

    // Constructor
    public User(int id, String first_name, String last_name, String email, Date date_joined){
        this.id = id;
        this. first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.date_joined = date_joined;
    }

    // toString
    @Override
    public String toString(){
        return "User [id: " +id+", first_name: " +first_name+", last_name: " 
        +last_name+", email: " +email+", date_joined: " +date_joined+ "]";
    }

    //Getters
    public int getID(){
        return this.id;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public String getEmail(){
        return this.email;
    }

    public Date getDateJoined(){
        return this.date_joined;
    }

    


}
