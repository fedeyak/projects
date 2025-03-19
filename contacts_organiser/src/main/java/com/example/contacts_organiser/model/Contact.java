package com.example.contacts_organiser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    String name;
    String phone;
    String email;

    @Override
    public String toString() {
        return name + " | " + phone + "| " + email;
    }
}
