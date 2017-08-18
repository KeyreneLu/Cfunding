package com.jdyy.cfunding.utils;

import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30 0030.
 */

public class TextUtils {

    /**
     * 三位数中间加一个空格
     *
     * @param str money
     * @return
     */
    public static String MoneyDeal(String str, boolean flag) {
        int gap = 0;
        String money = "";
        String result = "";
        String[] datas;
        if (flag) {
            if (str.equals("0")) {
                result = str;
            } else {
                double number = Double.valueOf(str);
                DecimalFormat df = new DecimalFormat("#.00");
                money = df.format(number);
                datas = money.split("[.]");
                gap = datas[0].length();
                if (gap < 3) {
                    if (gap == 0){
                        result = str;
                    }else {
                        result = money;
                    }
                } else {
                    for (int i = gap; i > 0; i--) {
                        int diff = gap - i;
                        if ((diff + 1) % 3 == 0 && i != 1) {
                            result = "," + datas[0].substring(i - 1, gap) + result;
                            gap = i - 1;
                        } else if (i == 1 && diff >= 0) {
                            result = datas[0].substring(0, diff + 1) + result;
                        }
                    }
                    result = result + "." + datas[1];
                }
            }
        } else {
            if (str.equals("0")) {
                result = str;
            } else if (str.contains(".")) {
                datas = str.split("[.]");
                int length = datas[0].length();
                if (length < 3) {
                    result = datas[0];
                } else {
                    for (int i = length; i > 0; i--) {
                        int diff2 = length - i;
                        Log.e("MainActivity", "diff==" + diff2);
                        if ((diff2 + 1) % 3 == 0 && i != 1) {
                            result = "," + datas[0].substring(i - 1, length) + result;
                            length = i - 1;
                        } else if (i == 1 && diff2 >= 0) {
                            result = datas[0].substring(0, diff2 + 1) + result;
                        }
                    }
                }
                result = result + "." + datas[1];
            } else {
                int length = str.length();
                if (length < 3) {
                    result = str;
                } else {
                    for (int i = length; i > 0; i--) {
                        int diff3 = length - i;
                        if ((diff3 + 1) % 3 == 0 && i != 1) {
                            result = "," + str.substring(i - 1, length) + result;
                            length = i - 1;
                        } else if (i == 1 && diff3 >= 0) {
                            result = str.substring(0, diff3 + 1) + result;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 对手机号进行处理，用星号代替号码中的一些数字
     * @param startposition 开始位置
     * @param endposition   结束位置
     * @param str           手机号
     * @return
     */
    public static String PhoneDeal(int startposition, int endposition, String str) {
        int gap;
        String star = "";
        gap = endposition - startposition;
        for (int i = 0; i < gap; i++) {
            star = star + "*";
        }
        String maskNumber = str.substring(0, startposition) + star + str.substring(endposition, str.length());

        return maskNumber;
    }

    /**
     * 对银行卡号进行处理
     *
     * @param str 银行卡号
     * @return 处理后的银行卡号
     */
    public static String CardDeal(String str) {
        List<String> cards = new ArrayList<>();
        String BankCard = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == 3) {
                cards.add(str.substring(0, i));
            } else if (i == 7) {
                cards.add(str.substring(3, i));
            } else if (i == 11) {
                cards.add(str.substring(7, i));
            } else if (i == 15) {
                if (str.length() - 1 > i) {
                    cards.add(str.substring(11, i));
                    cards.add(str.substring(i, str.length() - 1));
                } else {
                    cards.add(str.substring(11, i));
                }
            }
        }
        for (int j = 0; j < cards.size(); j++) {
            if (j == cards.size() - 1) {
                BankCard = BankCard + cards.get(j) + "";
            } else {
                BankCard = BankCard + "****" + " ";
            }
        }
        return BankCard;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return  statusBarHeight1;
    }

    // 并用分割符把时间分成时间数组
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014/06/14"）
     * @param time
     * @return
     */
    public static String timesOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }


    // 并用分割符把时间分成时间数组
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014"）
     * @param time
     * @return
     */
    public static String timesyear(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    // 并用分割符把时间分成时间数组
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"06-14"）
     * @param time
     * @return
     */
    public static String timesday(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    //密码中必须包含数字、字母符号
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }
}
