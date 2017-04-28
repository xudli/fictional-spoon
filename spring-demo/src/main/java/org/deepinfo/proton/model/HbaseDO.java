package org.deepinfo.proton.model;

/**
 * Created by lixudong on 2017/4/27.
 */
public class HbaseDO {

    public String key;

    public String familyName ;

    public String qualifier;

    public String value;

    public HbaseDO() {
    }

    public HbaseDO(String key, String familyName, String qualifier, String value) {
        this.key = key;
        this.familyName = familyName;
        this.qualifier = qualifier;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
