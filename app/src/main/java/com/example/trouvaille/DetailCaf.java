package com.example.trouvaille;

public class DetailCaf{
    public String title,address,description,money,ranking,phoneno,time;

    public DetailCaf(){

    }
    public DetailCaf(String title,String address, String description,String money,String ranking,String phoneno ,String time){
        this.title=title;
        this.address=address;
        this.ranking=ranking;
        this.money=money;
        this.description=description;
        this.phoneno=phoneno;
        this.time=time;
    }

    public String getAddress(){
        return address;
    }
    public String getDescription(){
        return description;
    }
    public String getTitle(){
        return title;
    }
    public String getRanking(){
        return ranking;
    }
    public String getMoney(){
        return money;
    }
    public String getTime(){
        return time;
    }
    public String getPhoneno(){
        return phoneno;
    }
}
