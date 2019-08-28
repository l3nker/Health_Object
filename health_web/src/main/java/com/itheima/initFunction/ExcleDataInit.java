package com.itheima.initFunction;

import java.util.List;
import java.util.Map;

public class ExcleDataInit {
  private   Map<String, Object> result;

    @Override
    public String toString() {
        return "ExcleDataInit{" +
                "result=" + result +
                ", reportDate='" + reportDate + '\'' +
                ", todayNewMember=" + todayNewMember +
                ", totalMember=" + totalMember +
                ", thisWeekNewMember=" + thisWeekNewMember +
                ", thisMonthNewMember=" + thisMonthNewMember +
                ", todayOrderNumber=" + todayOrderNumber +
                ", thisWeekOrderNumber=" + thisWeekOrderNumber +
                ", thisMonthOrderNumber=" + thisMonthOrderNumber +
                ", todayVisitsNumber=" + todayVisitsNumber +
                ", thisWeekVisitsNumber=" + thisWeekVisitsNumber +
                ", thisMonthVisitsNumber=" + thisMonthVisitsNumber +
                ", hotSetmeal=" + hotSetmeal +
                '}';
    }

    private String reportDate;
    private Integer todayNewMember;
    private Integer totalMember;
    private Integer thisWeekNewMember;
    private Integer thisMonthNewMember;
    private Integer todayOrderNumber;
    private Integer thisWeekOrderNumber;
    private Integer thisMonthOrderNumber;
    private Integer todayVisitsNumber;
    private Integer thisWeekVisitsNumber;
    private Integer thisMonthVisitsNumber;
    private List<Map> hotSetmeal;


    public ExcleDataInit(Map<String, Object> result) {
        this.result = result;

        reportDate = (String) result.get("reportDate");
        todayNewMember = (Integer) result.get("todayNewMember");
        totalMember = (Integer) result.get("totalMember");
        thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
        thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
        todayOrderNumber = (Integer) result.get("todayOrderNumber");
        thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
        thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
        todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
        thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
        thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
        hotSetmeal = (List<Map>) result.get("hotSetmeal");
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getTodayNewMember() {
        return todayNewMember;
    }

    public void setTodayNewMember(Integer todayNewMember) {
        this.todayNewMember = todayNewMember;
    }

    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    public Integer getThisWeekNewMember() {
        return thisWeekNewMember;
    }

    public void setThisWeekNewMember(Integer thisWeekNewMember) {
        this.thisWeekNewMember = thisWeekNewMember;
    }

    public Integer getThisMonthNewMember() {
        return thisMonthNewMember;
    }

    public void setThisMonthNewMember(Integer thisMonthNewMember) {
        this.thisMonthNewMember = thisMonthNewMember;
    }

    public Integer getTodayOrderNumber() {
        return todayOrderNumber;
    }

    public void setTodayOrderNumber(Integer todayOrderNumber) {
        this.todayOrderNumber = todayOrderNumber;
    }

    public Integer getThisWeekOrderNumber() {
        return thisWeekOrderNumber;
    }

    public void setThisWeekOrderNumber(Integer thisWeekOrderNumber) {
        this.thisWeekOrderNumber = thisWeekOrderNumber;
    }

    public Integer getThisMonthOrderNumber() {
        return thisMonthOrderNumber;
    }

    public void setThisMonthOrderNumber(Integer thisMonthOrderNumber) {
        this.thisMonthOrderNumber = thisMonthOrderNumber;
    }

    public Integer getTodayVisitsNumber() {
        return todayVisitsNumber;
    }

    public void setTodayVisitsNumber(Integer todayVisitsNumber) {
        this.todayVisitsNumber = todayVisitsNumber;
    }

    public Integer getThisWeekVisitsNumber() {
        return thisWeekVisitsNumber;
    }

    public void setThisWeekVisitsNumber(Integer thisWeekVisitsNumber) {
        this.thisWeekVisitsNumber = thisWeekVisitsNumber;
    }

    public Integer getThisMonthVisitsNumber() {
        return thisMonthVisitsNumber;
    }

    public void setThisMonthVisitsNumber(Integer thisMonthVisitsNumber) {
        this.thisMonthVisitsNumber = thisMonthVisitsNumber;
    }

    public List<Map> getHotSetmeal() {
        return hotSetmeal;
    }

    public void setHotSetmeal(List<Map> hotSetmeal) {
        this.hotSetmeal = hotSetmeal;
    }
}
