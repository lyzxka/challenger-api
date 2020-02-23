package io.renren.common.utils;


import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhanglei
 * @Date: 2018/8/20 13:34
 * @Description:
 */
public class PrimaryGenerater {

    private static PrimaryGenerater primaryGenerater = null;

    private PrimaryGenerater() {
    }

    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static PrimaryGenerater getInstance() {
        if (primaryGenerater == null) {
            synchronized (PrimaryGenerater.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new PrimaryGenerater();
                }
            }
        }
        return primaryGenerater;
    }

    /**
     *  生成下一个编号
     * @param sno    传最大的编号，才能返回累加后的!
     * @param prefix 生成的编号前缀
     * @return
     */
    public synchronized String generaterNextNumber(String sno,String prefix) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        if (StringUtils.isEmpty(sno)) {
            sno = prefix+"2018000000";// 第一次初始化编号
        }
        DecimalFormat df = new DecimalFormat("000000");
        id = prefix+formatter.format(date)+ df.format(1 + Integer.parseInt(sno.substring(7, sno.length())));

        return id;
    }

    /**
     *  生成展示权重权限code
     * @param sno 最大code
     * @return
     */
    public synchronized Integer generaterDepartmentCode(Integer sno) {
        return 1+sno;
    }

}
