package com.example.slotgamegacor;

public class Helper {

    public static int getIcon(int id) {
        switch (id) {
            case 1:
                return R.drawable.slot_1;
            case 2:
                return R.drawable.slot_2;
            case 3:
                return R.drawable.slot_3;
            case 4:
                return R.drawable.slot_4;
            case 5:
                return R.drawable.slot_5;
            case 6:
                return R.drawable.slot_6;
            case 7:
                return R.drawable.slot_7;
            case 8:
                return R.drawable.slot_8;
            default:
                return R.drawable.slot_9;
        }
    }
}