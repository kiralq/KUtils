package com.kira.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("SimpleDateFormat")
public class StringUtils {

    /**
     * 大陆手机号码十一位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isMobileNum(String mobiles) {
        String regExp = "13\\d{9}|14[579]\\d{8}|15[0123456789]\\d{8}|17[01235678]\\d{8" +
                "}|18\\d{9}";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 设置密码的长度
     *
     * @param pwd
     * @return
     */
    public static boolean isPwdStrong(String pwd) {
        if (pwd == null || pwd.length() < 6) {
            return false;
        } else {
            return true;
        }
    }

    public static String formatSaleNum(int num) {
        String str = "已售";
        if (num > 10000) {
            return str + num / 10000 + "." + (num % 10000 + "").substring(0, 2) + "万";
        } else {
            return str + num;
        }
    }

    public static String formatPwd(String str) {
        if (11 == str.length()) {
            return str.substring(0, 3) + "****" + str.substring(7);
        }
        return str;
    }

    public static String formatMonthSaleNum(int num) {
        String str = "月售";
        if (num > 10000) {
            return str + num / 10000 + "." + (num % 10000 + "").substring(0, 2) + "万";
        } else {
            return str + num;
        }
    }

    public static String formatTime(int num) {
        if (("" + num).length() <= 1) {
            return "0" + num;
        } else
            return num + "";
    }

    public static String formatKileMiter(double miters) {
        if (miters > 1) {
            return miters + "km";
        } else {
            try {
                return Integer.valueOf(miters * 1000 + "") + "m";
            }catch (NumberFormatException e){
                return  "0.0m";
            }

        }
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime)
            throws ParseException {
        Date date = stringToDate(strTime, "yyyy-MM-dd HH:mm:ss"); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static SpannableStringBuilder changeTextForeColor(Context context,String colorText, String wholeText, int color) {
        if (!TextUtils.isEmpty(colorText) && !TextUtils.isEmpty(wholeText)) {
            int start = wholeText.indexOf(colorText);
            int end = start + colorText.length();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(wholeText);
            CharacterStyle characterStyle = new ForegroundColorSpan(context.getResources().getColor(color));
            spannableStringBuilder.setSpan(characterStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableStringBuilder;
        }
        return null;
    }
}
