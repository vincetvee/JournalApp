package com.example.ubfac.myjournalapp.model;

import org.parceler.Parcel;

@Parcel
public class Journal {
    private String Id;
    private String title;
    private String content;
    private long date;
    private long  time;

    public Journal(){}

    public Journal(String Id,String title,String content,long date,long time){
        this.Id =Id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return Id;
    }

    public void setId(String journalId) {
        this.Id = journalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "Id='" + Id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
