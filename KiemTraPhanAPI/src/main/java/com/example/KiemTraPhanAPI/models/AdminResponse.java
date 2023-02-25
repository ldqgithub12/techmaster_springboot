package com.example.KiemTraPhanAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminResponse {
    private int curPage;
    private int pageSize;
    private int totalPage;
    private int totalItem;
    private Course[] data;
}
