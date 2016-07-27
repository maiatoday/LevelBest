package net.maiatoday.levelbest.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/**
 * POJO to define basic mood/energy tracking datapoint
 * Created by maia on 2016/07/27.
 */

public class Entry {

    public static final int MOOD = 0;
    public static final int ENERGY_LEVEL = 1;
    public static final int ENERGY_TYPE = 2;


    @IntDef({MOOD, ENERGY_LEVEL, ENERGY_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EntryType {
    }

    int id;
    long dateTime;
    @EntryType
    int entryType;
    float level;
    String comment;

    public Entry(int id, int entryType, float level) {
        this.id = id;
        this.entryType = entryType;
        this.level = level;
        this.comment = "";
        this.dateTime = new Date().getTime();

    }

    public Entry(int id, long dateTime, int entryType, float level) {
        this.id = id;
        this.dateTime = dateTime;
        this.entryType = entryType;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public
    @EntryType
    int getEntryType() {
        return entryType;
    }

    public void setEntryType(@EntryType int entryType) {
        this.entryType = entryType;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", dateTime='" + dateTime + '\'' +
                ", entryType=" + entryTypeToString(entryType) + "(" + entryType + ")" +
                ", level=" + level +
                ", comment='" + comment + '\'' +
                '}';
    }

    public static String entryTypeToString(@EntryType int et) {
        switch (et) {
            case MOOD:
                return "Mood";
            case ENERGY_LEVEL:
                return "Energy Level";
            case ENERGY_TYPE:
                return "Energy Type";
            default:
                return "Unknown";
        }
    }
}
