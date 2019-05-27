//package com.xiamuyao.sample;
//
//
//public class DemoUtli {
//    public static void main(String[] args) {
//        double rate = 109.481;           //bid2
//
//        double tanka = 110.299;         //平均数量
//
//        double bid = 109.479;
//
//        double ask = 109.482;
//
//        long amount = 100;
//
//        System.out.println(CalcPL(rate, tanka, amount, true, bid, ask));
//    }
//
//
//    public static double CalcPL(double rate, double tanka, Long amount, boolean jpy, double bidJpy, double askJpy) {
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "CalcUtil#CalcPL");
//
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "rate  =  "  +  rate);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "tanka  =  "  +  tanka);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "kbn  =  "  +  kbn);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "amount  =  "  +  amount);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "jpy  =  "  +  jpy);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "bidJpy  =  "  +  bidJpy);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "askJpy  =  "  +  askJpy);
//
//        //評価損益計算式.
//        //<対円>.
//        //買建：（現在の入力した数量を加味したラダーBid  -  約定価格）X数量  .
//        //売建：（現在の入力した数量を加味したラダーAsk  -  約定価格）X数量×(-1)  .
//        //<その他(現状だと対ドル3種)>.
//        //買建：（現在の入力した数量を加味したラダーBid  -約定価格）X数量X  .
//        //		JPYの現在値の基準レートの仲値（Rounddown（(bid+ask)/2,小数5桁))			※	切捨てでお願いします.
//        //売建：（現在の入力した数量を加味したラダーAsk  -  約定価格）X数量X					1.23456  →  1.2345.
//        //		JPYの現在値の基準レートの仲値（Rounddown（(bid+ask)/2,小数5桁))×(-1)		-1.23456  →  -1.2345.
//
//        //評価損益計算.
//        //double  pl  =  0.0f;
//        double pl = 0.0;  //decimalはMをつけること.
//
////        if (CodeDef.BS_BUY == kbn) {
//        //買建玉.
//        if (jpy) {
//            //対円.
//            //レートや単価を10000倍で整数に変換、最後に10のn乗で割る.
//            pl = (rate * Math.pow(10, 4) - tanka * Math.pow(10, 4)) * amount / Math.pow(10, 4);
//        } else {
//            //対ドル.
//            //レートや単価を1000000倍で整数に変換、円転仲値掛ける、最後に10のn乗で割る.
//            //円転仲値は小数5桁の有効桁数、4桁だと数量が1000万の場合などで誤差が大きい.
//            pl = (rate * Math.pow(10, 6) - tanka * Math.pow(10, 6)) *
//                    amount *
//                    RoundDown(((((bidJpy * Math.pow(10, 4) + askJpy * Math.pow(10, 4)) / 2 / Math.pow(10, 4)))), 5)
//                    / Math.pow(10, 6);
//        }
////        }
////        else {
////            //売建玉.
////            if (jpy) {
////                //対円.
////                //レートや単価を10000倍で整数に変換、最後に10のn乗で割る.
////                pl = (BigDecimal) (((BigDecimal) rate * (BigDecimal) Math.pow(10, 4) - (BigDecimal) tanka * (BigDecimal) Math.pow(10, 4)) * (BigDecimal) amount / (BigDecimal) Math.pow(10, 4) * (BigDecimal) (-1));
////            } else {
////                //対ドル.
////                //レートや単価を1000000倍で整数に変換、円転仲値掛ける、最後に10のn乗で割る.
////                //円転仲値は小数5桁の有効桁数、4桁だと数量が1000万の場合などで誤差が大きい.
////                pl = (BigDecimal) (((BigDecimal) rate * (BigDecimal) Math.pow(10, 6) - (BigDecimal) tanka * (BigDecimal) Math.pow(10, 6)) * (BigDecimal) amount * (BigDecimal) CommonUtil.RoundDown((double) (((BigDecimal) bidJpy * (BigDecimal) Math.pow(10, 4) + (BigDecimal) askJpy * (BigDecimal) Math.pow(10, 4)) / (BigDecimal) 2 / (BigDecimal) Math.pow(10, 4)), 5) / (BigDecimal) Math.pow(10, 6) * (BigDecimal) (-1));
////            }
////        }
//
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pl  =  "  +  pl);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pl.ToString()  =  "  +  pl.ToString()  );
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pl.ToString(F4)  =  "  +  pl.ToString("F4")  );
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "CommonUtil.RoundDown  ((double)pl,  4)  =  "  +  CommonUtil.RoundDown  ((double)pl,  4)  );
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "Convert.ToDouble(pl)  =  "  +  Convert.ToDouble(pl));
//
//        //return  CommonUtil.RoundDown  ((double)pl,  ConfigDef.PL_PRECISION);
//        return pl;
//    }
//
//    public static double RoundDown(double num, int pow) {
//        double ret = 0.0d;
//        try {
//            int powNum = (int) Math.pow(10.0d, pow); //10のn乗.
//            ret = num * powNum;
//
//            //1.23456を小数点以下4桁表示なら1.2345 .
//            //-1.23456を小数点以下4桁表示なら-1.2345 .
//            if (num > 0.0d) {
//                //正の数の場合は切り捨て.
//                ret = Math.floor(ret);
//            } else {
//                //負の数の場合は切り上げ.
//                ret = Math.ceil(ret);
//            }
//
//            ret = ret / powNum;
//        } catch (Exception e) {
//        }
//        return ret;
//    }
//}
//
//
