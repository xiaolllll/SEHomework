package com.example.test.bean;

public class SkillType {
    public final static int TECHNOLOGY = 1; // 第一个状态  0000 0001
    public final static int FINANCE = 1 << 1; // 第二个状态 0000 0010
    public final static int LEGAL_AFFAIRS = 1 << 2;// 第三个状态   0000 0100
//    public final static Long FOURTH_STATE = 1L << 3;// 第四个状态  0000 1000
//    public final static Long FIFTH_STATE = 1L << 4;// 第五个状态   0001 0000
//    public final static Long SIXTH_STATE = 1L << 5;// 第六个状态   0010 0000
//    public final static Long SEVENTH_STATE = 1L << 6;// 第七个状态 0100 0000
//    public final static Long EIGHTH_STATE = 1L << 7;// 第八个状态  1000 0000
}//技术，财务，法务