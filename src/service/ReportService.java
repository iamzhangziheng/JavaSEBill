package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class ReportService {

    /**
     * 获取某一天的消费金额
     * @param d
     * @param monthRawData
     * @return
     */
//    public int getDaySpend(Date d,List<Record> monthRawData){
////        int daySpend = 0;
////        for (Record record : monthRawData) {
////            if(record.date.equals(d))
////                daySpend+=record.spend;
////        }
////        return daySpend;
////    }
    public int getDaySpend(Date d, List<Record> monthRawDate){
        int daySpend = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(d);
        for(Record record : monthRawDate){
            String date = sdf.format(record.date);
            if(date.equals(nowDate))
                daySpend += record.spend;
        }
        System.out.println("此天收入为"+daySpend);
        return daySpend;
    }
//    public int getDaySpend(Date d, List<Record> monthRawDate){
//        int daySpend = 0;
//        for(Record record : monthRawDate){
//            System.out.println(record.date);
//            System.out.println(d);
//            if(DateUtil.util2sql(record.date).equals(DateUtil.util2sql(d)))
//                daySpend += record.spend;
//        }
//        System.out.println("此天收入为"+daySpend);
//        return daySpend;
//    }
/**
      * 获取固定日期那一天的消费总额
      *
      * @param d
      *            日期
      * @param monthRawData
      *            消费记录列表
      * @return int 消费总额
      */

    /**
     * 获取一个月的消费记录集合
     * @return
     */
    public List<Record> listThisMonthRecords() {
        RecordDAO dao= new RecordDAO();
        List<Record> monthRawData= dao.listThisMonth();
        List<Record> result= new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.spend=daySpend;
            result.add(r);
        }
        return result;

    }

}