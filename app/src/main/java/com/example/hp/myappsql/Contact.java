package com.example.hp.myappsql;

/**
 * Created by hp on 28-06-2017.
 */

public class Contact {
    int id;
    String name;
    String phoneno;
    String age;



    public Contact(String name, String phoneno, String age) {
        this.name = name;
        this.phoneno = phoneno;
        this.age = age;
    }

    public Contact() {
    }

    public Contact(int id, String name, String phoneno) {
        this.name=name;
        this.id=id;
        this.phoneno=phoneno;
        
    }
    public Contact(String name, String _phone_number){
        this.name = name;
        this.phoneno = _phone_number;
    }

    public Contact(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
