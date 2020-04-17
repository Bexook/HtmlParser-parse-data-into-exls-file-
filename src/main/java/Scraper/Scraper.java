package Scraper;

import org.Main.Controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Scraper {


    public static Model model;


    public Scraper(Model model) {
        this.model = model;
    }


    public ArrayList<String> getTitlePages() throws IOException {
        ArrayList<String> nameData = new ArrayList<String>();

        final Document docPage = Jsoup.connect(model.getUrl())
                .userAgent("Chrome")
                .get();

        final Elements list = docPage.getElementsByAttributeValue(model.getTagName(), model.getTagValue());
        list.forEach(listElement -> {
            if (listElement.childNodeSize() <= 0) {
                nameData.add(listElement.child(0).text());
            } else {
                nameData.add(listElement.text());
            }

        });
        for (int i = 2; i < model.getPages(); i++) {

            final StringBuilder str = new StringBuilder();
            str.append(model.getUrl());
            str.append(model.getNextPage());
            str.append(i);






          final  Document docPage2 = Jsoup.connect(str.toString()).get();
          final  Elements list2 = docPage2.getElementsByAttributeValue(model.getTagName(), model.getTagValue());
            list2.forEach(listElement -> {
                if (listElement.childNodeSize() <= 0) {
                    nameData.add(listElement.child(0).text());
                } else {
                    nameData.add(listElement.text());
                }
            });

        }

        return nameData;
    }

    public ArrayList<String> getPricePages() throws IOException {
        ArrayList<String> priceData = new ArrayList<String>();
        final Document docPage = Jsoup.connect(model.getUrl()).get();
        final Elements list = docPage.getElementsByAttributeValue(model.getTagNamePrice(), model.getTagValuePrice());
        list.forEach(listElement -> {
            if (listElement.childNodeSize() <= 0) {
                priceData.add(listElement.child(0).text());
            } else {
                priceData.add(listElement.text());
            }

        });
        for (int i = 2; i < model.getPages(); i++) {
            String url = "";
            // System.out.println(model.getUrl()+"\n"+model.getNextPage()+"\n"+i);
            final StringBuilder str = new StringBuilder();
            str.append(model.getUrl());
            str.append(model.getNextPage());
            str.append(i);
            final Document docPage2 = Jsoup.connect(str.toString()).get();
            //System.out.println(model.getUrl()+"\n"+model.getNextPage()+"\n"+i);
            final Elements list2 = docPage2.getElementsByAttributeValue(model.getTagNamePrice(), model.getTagValuePrice());
            list2.forEach(listElement -> {
                if (listElement.childNodeSize() <= 0) {
                    priceData.add(listElement.child(0).text());
                } else {
                    priceData.add(listElement.text());
                }
            });
        }

        return priceData;
    }

    public ArrayList<String> getDataURL() throws IOException {
        ArrayList<String> nameData = new ArrayList<String>();

        final Document docPage = Jsoup.connect(model.getUrl()).get();
        final Elements list = docPage.getElementsByAttributeValue(model.getTagName(), model.getTagValue());


        list.forEach(listElement -> {
            final Element aElement = listElement.child(0);
            String urlHREF = aElement.attr("href");
            nameData.add(urlHREF);
                /*String listHREF=listElement.child(0).attr("a["+model.getTagUrlSecondName()+"]");
                nameData.add(listHREF);
              /*  Element aElement = listElement.child(0);
                nameData.add(aElement.attr("a["+model.getTagUrlSecondName()+"]"));
                  /*  Element aElement = listElement.child(0);
                    String str=aElement.attr( model.getTagUrlSecondName());
                    nameData.add(str);*/

        });
        for (int i = 2; i < model.getPages(); i++) {
            final String url = model.getUrl() + "" + model.getNextPage() + i;
            //   System.out.println(model.getUrl()+"\n"+model.getNextPage()+"\n"+i);
            // System.out.println(model.getUrl()+"\n"+model.getNextPage()+"\n"+i);
            final StringBuilder str = new StringBuilder();
            str.append(model.getUrl());
            str.append(model.getNextPage());
            str.append(i);
            final Document docPage2 = Jsoup.connect(str.toString()).get();
            final Elements list2 = docPage2.getElementsByAttributeValue(model.getTagName(), model.getTagValue());


            list2.forEach(listElement -> {
                final Element aElement = listElement.child(0);
                String urlHREF = aElement.attr("href");
                nameData.add(urlHREF);
                   /* Element listHREF=listElement.select("a").first();
                   nameData.add(listHREF.attr("a["+model.getTagUrlSecondName()+"]"));
                    //Element aElement = listElement.child(0);
                   // nameData.add(aElement.attr("a["+model.getTagUrlSecondName()+"]"));
*/
            });
        }

        // System.out.println(nameData);
        return nameData;
    }

    public ArrayList<String> getSerialNumber() throws IOException {
        ArrayList<String> nameData = new ArrayList<String>();
        final Document docPage = Jsoup.connect(model.getUrl()).get();

        final Elements list = docPage.getElementsByAttributeValue(model.getTagSerialNumberData(), model.getTagSerialNumberValue());


        list.forEach(listElement -> {
            if (listElement.childNodeSize() <= 0) {
                nameData.add(listElement.child(0).text());
            } else {
                nameData.add(listElement.text());
            }


        });
        for (int i = 2; i < model.getPages(); i++) {
            final String url = model.getUrl() + "" + model.getNextPage() + i;
            // System.out.println(model.getUrl()+"\n"+model.getNextPage()+"\n"+i);
            // System.out.println(model.getUrl()+"\n"+model.getNextPage()+"\n"+i);
            final StringBuilder str = new StringBuilder();
            str.append(model.getUrl());
            str.append(model.getNextPage());
            str.append(i);
            final Document docPage2 = Jsoup.connect(str.toString()).get();
            final Elements list2 = docPage2.getElementsByAttributeValue(model.getTagSerialNumberData(), model.getTagSerialNumberValue());


            list2.forEach(listElement -> {
                if (listElement.childNodeSize() <= 0) {
                    nameData.add(listElement.child(0).text());
                } else {
                    nameData.add(listElement.text());
                }


            });

        }
        return nameData;
    }


    public ArrayList<Announcement> getAnnouncementTiTlePriceURLSerialNumber(ArrayList<String> nameData, ArrayList<String> priceData, ArrayList<String> urlHREF, ArrayList<String> serialNumber) {
        ArrayList<Announcement> resultDataAnnouncement = new ArrayList<Announcement>();
        Iterator nameDataIterator = nameData.iterator();
        Iterator priceDataIterator = priceData.iterator();
        Iterator urlHREFIterator = urlHREF.iterator();
        Iterator serialNumberIterator = serialNumber.iterator();
        while (nameDataIterator.hasNext() && priceDataIterator.hasNext() && urlHREFIterator.hasNext() && serialNumberIterator.hasNext()) {
            resultDataAnnouncement.add(new Announcement(nameDataIterator.next().toString(), priceDataIterator.next().toString(), urlHREFIterator.next().toString(), serialNumberIterator.next().toString()));

        }
        return resultDataAnnouncement;
    }

    public ArrayList<Announcement> getAnnouncementTiTlePriceURL(ArrayList<String> nameData, ArrayList<String> priceData, ArrayList<String> urlHREF) {
        ArrayList<Announcement> resultDataAnnouncement = new ArrayList<Announcement>();
        Iterator nameDataIterator = nameData.iterator();
        Iterator priceDataIterator = priceData.iterator();
        Iterator urlHREFIterator = urlHREF.iterator();
        while (nameDataIterator.hasNext() && priceDataIterator.hasNext() && urlHREFIterator.hasNext()) {
            resultDataAnnouncement.add(new Announcement(nameDataIterator.next().toString(), priceDataIterator.next().toString(), urlHREFIterator.next().toString()));

        }
        return resultDataAnnouncement;
    }

    public ArrayList<Announcement> getAnnouncementTitlePricePages(ArrayList<String> nameData, ArrayList<String> priceData) {
        ArrayList<Announcement> resultDataAnnouncement = new ArrayList<Announcement>();
        Iterator nameDataIterator = nameData.iterator();
        Iterator priceDataIterator = priceData.iterator();
        while (nameDataIterator.hasNext() && priceDataIterator.hasNext()) {
            resultDataAnnouncement.add(new Announcement(nameDataIterator.next().toString(), priceDataIterator.next().toString()));
        }
        return resultDataAnnouncement;
    }


    public ArrayList<Announcement> getAnnouncementTitlePages(ArrayList<String> nameData) {
        ArrayList<Announcement> resultDataAnnouncement = new ArrayList<Announcement>();
        Iterator nameDataIterator = nameData.iterator();
        while (nameDataIterator.hasNext()) {
            resultDataAnnouncement.add(new Announcement(nameDataIterator.next().toString()));
        }
        return resultDataAnnouncement;
    }


}
