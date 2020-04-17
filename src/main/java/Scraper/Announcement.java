package Scraper;

public class Announcement {
    String title;
    String price;
    String serialNumber;
    String date;


    public Announcement(String title, String price, String serialNumber, String date) {
        this.title = title;
        this.price = price;
        this.serialNumber = serialNumber;
        this.date = date;
    }

    public Announcement(String title, String price, String serialNumber) {
        this.title = title;
        this.price = price;
        this.serialNumber = serialNumber;
    }


    public Announcement(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public Announcement(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

