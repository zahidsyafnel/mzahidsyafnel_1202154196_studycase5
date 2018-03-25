package com.example.zahid.mzahidsyafnel_1202154196_studycase5;

public class AddData {
    //variable yang telah dideklarasi dan akan digunakan
    String todo, describe, priority;

    //Method untuk konstruktor
    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.describe=desc;
        this.priority=prior;
    }

    //method untuk setter dan getter untuk to do
    public String getTodo() {
        return todo;
    }
    public void setTodo(String todo) {
        this.todo = todo;
    }

    //method untuk setter dan getter untuk describe
    public String getDesc() {
        return describe;
    }
    public void setDesc(String desc) {
        this.describe = desc;
    }

    //method untuk setter dan getter untuk priority
    public String getPrior() {
        return priority;
    }
    public void setPrior(String prior) {
        this.priority = prior;
    }
}

