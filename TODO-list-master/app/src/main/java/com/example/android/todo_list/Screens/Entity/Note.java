package com.example.android.todo_list.Screens.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    public String prio;
    public String repeat;
    @PrimaryKey(autoGenerate = true)
    int id;

    String title;

    String description;

    int priority;
    //boolean checkBox;

    public Note(String title, String description, int priority,String prio,String repeat) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.prio=prio;
        this.repeat=repeat;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
    public String getprio(){return prio;}
    public String getrepeat(){return repeat;}
}
