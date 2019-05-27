//package com.xiamuyao.sample;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//public class CalcUtil {
//
//    ///  <summary>
//    ///  評価損益計算、想定損益計算.
//    ///  </summary>
//    ///  <param  name="rate">レート(想定損益の場合は注文の価格).</param>
//    ///  <param  name="tanka">約定価格</param>
//    ///  <param  name="kbn">売りor買い</param>
//    ///  <param  name="amount">数量</param>
//    ///  <param  name="jpy">対円かどうか</param>
//    ///  <param  name="bidJpy"></param>
//    ///  <param  name="askJpy"></param>
//    ///  <returns>Pl  Value</returns>
//    public static double CalcPL(double rate, double tanka, String kbn, Long amount, boolean jpy, double bidJpy, double askJpy) {
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
//        if (CodeDef.BS_BUY == kbn) {
//            //買建玉.
//            if (jpy) {
//                //対円.
//                //レートや単価を10000倍で整数に変換、最後に10のn乗で割る.
//                pl = ((rate * Math.pow(10, 4) - tanka * Math.pow(10, 4)) *
//                        amount /
//                        Math.pow(10, 4));
//            } else {
//                //対ドル.
//                //レートや単価を1000000倍で整数に変換、円転仲値掛ける、最後に10のn乗で割る.
//                //円転仲値は小数5桁の有効桁数、4桁だと数量が1000万の場合などで誤差が大きい.
//                pl = (rate *
//                        Math.pow(10, 6) -
//                        tanka * Math.pow(10, 6)) *
//                        amount *
//                        CommonUtil.RoundDown((double) ((bidJpy *
//                                Math.pow(10, 4) +
//                                askJpy *
//                                        Math.pow(10, 4)) /
//                                2 / Math.pow(10, 4)), 5)
//                        / Math.pow(10, 6);
//            }
//        } else {
//            //売建玉.
//            if (jpy) {
//                //対円.
//                //レートや単価を10000倍で整数に変換、最後に10のn乗で割る.
//                pl = ((rate * Math.pow(10, 4) - tanka * Math.pow(10, 4)) * amount / Math.pow(10, 4) * (-1));
//            } else {
//                //対ドル.
//                //レートや単価を1000000倍で整数に変換、円転仲値掛ける、最後に10のn乗で割る.
//                //円転仲値は小数5桁の有効桁数、4桁だと数量が1000万の場合などで誤差が大きい.
//                pl = ((rate * Math.pow(10, 6) - tanka * Math.pow(10, 6)) * amount * CommonUtil.RoundDown((double) ((bidJpy * Math.pow(10, 4) + askJpy * Math.pow(10, 4)) / 2 / Math.pow(10, 4)), 5) / Math.pow(10, 6) * (-1));
//            }
//        }
//
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pl  =  "  +  pl);
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pl.ToString()  =  "  +  pl.ToString()  );
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pl.ToString(F4)  =  "  +  pl.ToString("F4")  );
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "CommonUtil.RoundDown  ((double)pl,  4)  =  "  +  CommonUtil.RoundDown  ((double)pl,  4)  );
//        //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "Convert.ToDouble(pl)  =  "  +  Convert.ToDouble(pl));
//
//        //return  CommonUtil.RoundDown  ((double)pl,  ConfigDef.PL_PRECISION);
//        return Convert.ToDouble(pl);
//    }
//
//    ///  <summary>
//    ///  損益PIPS計算
//    ///  </summary>
//    ///  <param  name="rate"></param>
//    ///  <param  name="tanka"></param>
//    ///  <param  name="kbn">売買区分</param>
//    ///  <param  name="jpy">対円:true    対円以外:false</param>
//    ///  <returns>Pips  Value</returns>
//    public static double CalcPips(double rate, double tanka, String kbn, boolean jpy) {
//        //PIPS計算.
//        //  対円.
//        //  買建損益PIPS：(買いの総建玉数量を元にしたラダーBid  -  買建平均単価)  *  100  .
//        //  売建損益PIPS：(売りの総建玉数量を元にしたラダーAsk  -  売建平均単価)  *  100  *  (-1)  .
//        //  対ドル
//        //  買建損益PIPS：(買いの総建玉数量を元にしたラダーBid  -  買建平均単価)  *  10000  .
//        //  売建損益PIPS：(売りの総建玉数量を元にしたラダーAsk  -  売建平均単価)  *  10000  *  (-1)  .
//        //小数２桁有効３桁目切捨て.
//
//        //double  pips  =  0;
//        BigDecimal pips = 0.0;  //decimalはMをつけること.
//
//        unchecked {
//            if (kbn == CodeDef.BS_BUY) {
//                //買建玉.
//                if (jpy) {
//                    //対円.
//                    //レートや単価を10000倍で整数に変換、最後に10のn乗で割る.
//                    //pips  =   ((rate  *  Math.pow  (10,  4)  -  tanka  *  Math.pow  (10,  4))  *  100  /  Math.pow  (10,  4));
//                    pips = ((rate * Math.pow(10, 4) -
//                            tanka * Math.pow(10, 4)) /
//                            Math.pow(10, 2));
//                } else {
//                    //対ドル.
//                    //レートや単価を1000000倍で整数に変換、最後に10のn乗で割る.
//                    //pips  =   ((rate  *  Math.pow  (10,  6)  -  tanka*  Math.pow  (10,  6))  *  10000  /  Math.pow  (10,  6));
//                    pips = ((rate * Math.pow(10, 6) - tanka * Math.pow(10, 6)) / Math.pow(10, 2));
//                }
//            } else {
//                //売建玉.
//                if (jpy) {
//                    //対円.
//                    //レートや単価を10000倍で整数に変換、最後に10のn乗で割る.
//                    //pips  =   ((rate  *  Math.pow  (10,  4)  -  tanka  *  Math.pow  (10,  4))  *  100  /  Math.pow  (10,  4))  *  (-1);
//                    pips = ((rate * Math.pow(10, 4) - tanka * Math.pow(10, 4)) / Math.pow(10, 2) * (-1));
//                } else {
//                    //対ドル.
//                    //レートや単価を1000000倍で整数に変換、最後に10のn乗で割る.
//                    //pips  =   ((rate  *  Math.pow  (10,  6)  -  tanka*  Math.pow  (10,  6))  *  10000  /  Math.pow  (10,  6))  *  (-1);
//                    pips = ((rate * Math.pow(10, 6) - tanka * Math.pow(10, 6)) / Math.pow(10, 2) * (-1));
//                }
//            }
//
//            //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "pips  =  "  +  pips);
//            //LogUtil.Debug  (ConstDef.LOG_DEBUG_TAG,  "Convert.ToDouble(pips)  =  "  +  Convert.ToDouble(pips));
//
//        }
//
//        return Convert.ToDouble(pips);
//    }
//
//    ///  <summary>
//    ///  建玉一覧の合計や平均の情報の生成
//    ///  </summary>
//    ///  <returns>建玉一覧計算値用クラス</returns>
//    ///  <param  name="list">建玉一覧表示用リスト</param>
//    public static PostionSumInfoModel CreatePositionSumInfo(List<PositionListModel> list) {
//        PostionSumInfoModel info = new PostionSumInfoModel();
//        //  合計や平均の生成
//        var bsBuy = list.Where(bs = > bs.Bs == CodeDef.BS_BUY);                //  売買区分が買の結果
//        var bsSel = list.Where(bs = > bs.Bs == CodeDef.BS_SELL);        //  売買区分が売の結果
//
//        info.TotalBuyPosAmt = (uint) bsBuy.Sum(d = > d.Amount);                //  建玉数量合計
//        info.TotalSelPosAmt = (uint) bsSel.Sum(d = > d.Amount);
//        info.TotalPosAmt = info.TotalBuyPosAmt + info.TotalSelPosAmt;
//        info.TotalSw = list.Sum(d = > d.SumSW);                                                //  スワップポイント合計
//        info.TotalBuyPL = bsBuy.Sum(d = > d.AppraisalPL);                        //  評価損益合計
//        info.TotalSelPL = bsSel.Sum(d = > d.AppraisalPL);
//        info.TotalPL = info.TotalBuyPL + info.TotalSelPL;
//
//        //  平均を出すのは通貨ペアが1種類だけの場合
//        //  対円の場合は小数第4位、対円以外は小数第6位まで
//        if (list.GroupBy(s = > s.CurId).Count() == 1){
//            //int  aveRoundNum  =  list.First  ().CurId.EndsWith  ("JPY")  ?  4  :  6;
//            InitManager initManager = InitManager.GetInstance();
//            int aveRoundNum = initManager.CurPairDigit(list.First().CurId);  //初期化電文の各通貨ペアの桁数に変更.
//            info.AvgBuyPrice = bsBuy.Count() > 0 ?
//                    ( double?)
//            CommonUtil.RoundDown(bsBuy.Sum(d = > d.Price * d.Amount) / info.TotalBuyPosAmt, aveRoundNum)  :null;
//            info.AvgSelPrice = bsSel.Count() > 0 ?
//                    ( double?)
//            CommonUtil.RoundDown(bsSel.Sum(d = > d.Price * d.Amount) / info.TotalSelPosAmt, aveRoundNum)  :null;
//        }
//        return info;
//    }
//
//    ///  <summary>
//    ///  建玉一覧のモデルから売買区分毎の数量の算出する.
//    ///  </summary>
//    ///  <returns>買、売の順の配列</returns>
//    ///  <param  name="modelR">建玉一覧のモデル</param>
//    public static Long[] CalcPositionBsAmtList(RefPositionListModelR modelR) {
//        Long[] bsAmtList = new Long[2];  //変数の型は変わるはず.
//
//        if (modelR != null && modelR.PositionList != null && modelR.PositionList.Count > 0) {
//            for (int i = 0; i < modelR.PositionList.Count; i++) {
//                FXArrayList array = modelR.PositionList;
//                FXHashTable hash = (FXHashTable) array[i];
//
//                String bs = (String) hash["urikai"];  //売りか買いのフラグ用.
//                String amount = (String) hash["amount"];  //数量.
//
//                if (bs == CodeDef.BS_BUY) {
//                    //買建玉合計を計算.
//                    bsAmtList[0] += CommonUtil.atoul(amount);
//                } else {
//                    //売建玉合計を計算.
//                    bsAmtList[1] += CommonUtil.atoul(amount);
//                }
//            }
//        }
//
//        //戻値の型も変わるはず.
//        return bsAmtList;
//    }
//
//    ///  <summary>
//    ///  建玉一覧のモデルから売買建玉の合計、売買PLを算出する.
//    ///  </summary>
//    ///  <returns>売買建玉平均と売買PL.</returns>
//    ///  <param  name="modelR">Model  r.</param>
//    ///  <param  name="jpyF">If  set  to  <c>true</c>  jpy  f.</param>
//    ///  <param  name="bid2">Bid2.</param>
//    ///  <param  name="ask2">Ask2.</param>
//    ///  <param  name="bidJpy">Bid  jpy.</param>
//    ///  <param  name="askJpy">Ask  jpy.</param>
//    public static double[] CalcPositionAvePricePl(RefPositionListModelR modelR, boolean jpyF, double bid2, double ask2, double bidJpy, double askJpy) {
//        double[] retArray = new double[4];  //変数の型は変わるはず.
//        //1番目:買建玉の合計.
//        //2番目:買PL
//        //3番目:売建玉の合計.
//        //4番目:売PL.
//
//        if (modelR != null && modelR.PositionList != null && modelR.PositionList.Count > 0) {
//            for (int i = 0; i < modelR.PositionList.Count; i++) {
//                FXArrayList array = modelR.PositionList;
//                FXHashTable hash = (FXHashTable) array[i];
//
//                String bs = (String) hash["urikai"];  //売りか買いのフラグ用.
//                Long amount = CommonUtil.atoul((String) hash["amount"]);  //数量.
//                double price = CommonUtil.atod((String) hash["firstprice"]);  //建単価.
//
//                if (bs == CodeDef.BS_BUY) {
//                    //買.
//                    retArray[0] += unchecked((double) (price * amount));
//                    //retArray  [1]  +=  CalcPL  (bid2,  price,  bs,  amount,  jpyF,  bidJpy,  askJpy);
//                    //評価損益の表示桁数に丸め.
//                    retArray[1] += CommonUtil.RoundDown(CalcPL(bid2, price, bs, amount, jpyF, bidJpy, askJpy), ConfigDef.PL_PRECISION);
//                } else {
//                    //売.
//                    retArray[2] += unchecked((double) (price * amount));
//                    //retArray  [3]  +=  CalcPL  (ask2,  price,  bs,  amount,  jpyF,  bidJpy,  askJpy);
//                    //評価損益の表示桁数に丸め.
//                    retArray[3] += CommonUtil.RoundDown(CalcPL(ask2, price, bs, amount, jpyF, bidJpy, askJpy), ConfigDef.PL_PRECISION);
//                }
//            }
//
//            //評価損益の表示桁数に丸め.
//            retArray[1] = CommonUtil.RoundDown2(retArray[1], ConfigDef.PL_PRECISION);
//            retArray[3] = CommonUtil.RoundDown2(retArray[3], ConfigDef.PL_PRECISION);
//        }
//
//        //戻値の型も変わるはず.
//        return retArray;
//    }
//
//    ///  <summary>
//    ///  損益PIPS(指値、逆指値)の値から想定損益を計算.
//    ///  2WAYOCOで使用.
//    ///  </summary>
//    ///  <returns>The  pips  to  gl.</returns>
//    ///  <param  name="amtTxt">数量.</param>
//    ///  <param  name="pipsTxt">PIPSのテキスト.</param>
//    ///  <param  name="curPair">通貨ペア.</param>
//    ///  <param  name="baseBid">円転用bid.</param>
//    ///  <param  name="baseAsk">円転用ask.</param>
//    public static String CalcPipsToGl(String amtTxt, String pipsTxt, String curPair, String baseBid, String baseAsk) {
//        double gainLoss = 0.0d;
//
//        String amout = CommonUtil.StringReplace(amtTxt, Convert.ToChar(CommonUtil.atoi(ConstDef.COMMA)).ToString(), "");  //カンマは空に置換.
//        boolean isBaseCurPair = curPair.EndsWith(ConfigDef.BASE_CURPAIR);  //対基準通貨かどうか.
//
//        double baseBidD = CommonUtil.atod(baseBid);
//        double baseAskD = CommonUtil.atod(baseAsk);
//        double middleRate = CommonUtil.RoundDown((baseBidD + baseAskD) / 2, ConfigDef.PL_PRECISION);  //円転用の仲値.
//
//        double pips = Math.Abs(CommonUtil.atod(pipsTxt));  //絶対値.
//
//        if (isBaseCurPair) {
//            //対基準通貨(現状対円)の場合.
//            //入力値の1  =  1銭  =  0.01円.
//            //想定損益  =  数量  *  損益PIPS  /  100.
//            gainLoss = CommonUtil.atod(amout) * pips / 100;
//        } else {
//            //対基準通貨(現状対ドル)の場合.
//            //入力値の1  =  0.01セント  =  0.0001ドル.
//            //想定損益  =  数量  *  損益PIPS  *  USDJPYの仲値  /  10000.
//            gainLoss = CommonUtil.atod(amout) * pips * middleRate / 10000;
//        }
//
//        return "" + gainLoss;
//    }
//
//    ///  <summary>
//    ///  想定損益の値からPIPSの値を計算.
//    ///  2WAYOCOで使用.
//    ///  </summary>
//    ///  <returns>The  gl  to  pips.</returns>
//    ///  <param  name="amtTxt">数量.</param>
//    ///  <param  name="glTxt">想定損益のテキスト.</param>
//    ///  <param  name="curPair">通貨ペア.</param>
//    ///  <param  name="baseBid">円転用bid.</param>
//    ///  <param  name="baseAsk">円転用ask.</param>
//    public static String CalcGlToPips(String amtTxt, String glTxt, String curPair, String baseBid, String baseAsk) {
//        double pips = 0.0d;
//
//        String amout = CommonUtil.StringReplace(amtTxt, Convert.ToChar(CommonUtil.atoi(ConstDef.COMMA)).ToString(), "");  //カンマは空に置換.
//        boolean isBaseCurPair = curPair.EndsWith(ConfigDef.BASE_CURPAIR);  //対基準通貨かどうか.
//
//        double baseBidD = CommonUtil.atod(baseBid);
//        double baseAskD = CommonUtil.atod(baseAsk);
//        double middleRate = CommonUtil.RoundDown((baseBidD + baseAskD) / 2, ConfigDef.PL_PRECISION);  //円転用の仲値.
//
//        double gl = Math.Abs(CommonUtil.atod(glTxt));  //絶対値.
//
//        if (isBaseCurPair) {
//            //対基準通貨(現状対円)の場合.
//            //損益PIPS  =  想定損益  *  100  /  数量.
//            pips = gl * 100 / CommonUtil.atod(amout);
//        } else {
//            //対円以外(現状対ドル).
//            //損益PIPS  =  想定損益  *  10000  /  数量  /  USDJPYの仲値.
//            pips = gl * 10000 / CommonUtil.atod(amout) / middleRate;
//        }
//
//        return "" + pips;
//    }
//}