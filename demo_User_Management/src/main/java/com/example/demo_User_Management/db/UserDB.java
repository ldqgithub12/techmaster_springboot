package com.example.demo_User_Management.db;

import com.example.demo_User_Management.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> list = new ArrayList<>(List.of(
            new User(1,"Nguyen Van A","ledangquang443@gmail.com","0123456789","Thành phố Hà Nội","avatar1","password1"),
            new User(2,"Nguyen Van B","nguyenvanb@gmail.com","0123456789","Tỉnh Điện Biên","avatar2","password2"),
            new User(3,"Nguyen Van C","nguyenvanc@gmail.com","0123456789","Tỉnh Bắc Ninh","avatar3","password3"),
            new User(4,"Nguyen Van D","nguyenvand@gmail.com","0123456789","Thành phố Hải Phòng","avatar4","password4")
    ));
}
