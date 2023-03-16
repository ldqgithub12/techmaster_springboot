package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> users = new ArrayList<>(List.of(
            new User(1,"Nguyen Van A","ledangquang443@gmail.com","0123456789","avatar1"),
            new User(2,"Nguyen Van B","nguyenvanb@gmail.com","0123456789","avatar2"),
            new User(3,"Nguyen Van C","nguyenvanc@gmail.com","0123456789","avatar3"),
            new User(4,"Nguyen Van D","nguyenvand@gmail.com","0123456789","avatar4")
    ));
}
