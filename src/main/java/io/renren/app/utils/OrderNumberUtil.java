package io.renren.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class OrderNumberUtil {
    static HashMap<String, AtomicLong> mapSequence = new HashMap<String, AtomicLong>();

    public static String priStrID() {
        AtomicLong mySeq = mapSequence.get("order");
        if (mySeq == null) {
            mySeq = new AtomicLong(100001);
            mapSequence.put("order", mySeq);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateFormat = sdf.format(new Date());
        String inc = mySeq.getAndIncrement() + "";
        int incLen = inc.length();
        inc = inc.substring(incLen - 3, incLen);
        String orderNo = dateFormat + inc;
        return orderNo;
    }
}
