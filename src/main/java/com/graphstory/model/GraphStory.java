package com.graphstory.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GraphStory {


    public static final String convertLongtoStrDate(Long ld) {
        Date d = new Date(Long.valueOf(ld));
        SimpleDateFormat dformatter = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat tformatter = new SimpleDateFormat("h:mm a");
        return dformatter.format(d) + " at " + tformatter.format(d);
    }

    public static final String convertLongtoStrJustDate(Long ld) {
        Date d = new Date(Long.valueOf(ld));
        SimpleDateFormat dformatter = new SimpleDateFormat("MM/dd/yyyy");
        return dformatter.format(d);
    }
}
