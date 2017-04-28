package org.deepinfo.proton.params;

/**
 * Created by lixudong on 2017/4/27.
 */
public class HbaseParam {

    private String database;

    private String tableName;

    private String rowName;

    private String familyName;

    private String quelier;

    private String startRow;

    private String stopRow;

    private String startTime;

    private String orderKey;


    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    private String stopTime;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getQuelier() {
        return quelier;
    }

    public void setQuelier(String quelier) {
        this.quelier = quelier;
    }

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getStopRow() {
        return stopRow;
    }

    public void setStopRow(String stopRow) {
        this.stopRow = stopRow;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

}
