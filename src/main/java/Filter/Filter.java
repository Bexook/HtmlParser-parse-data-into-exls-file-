package Filter;

import Scraper.Announcement;
import Scraper.Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Filter {

    public static ArrayList<Announcement> FilterByName(ArrayList<String> keys,ArrayList<Announcement> unFilteredName){
        ArrayList<Announcement> filteredAnnouncement = new ArrayList<>();
           Iterator iterateByKeys=keys.iterator();

           while(iterateByKeys.hasNext()){
               String KeyString = iterateByKeys.next().toString();
               Iterator iterateByNameFilter = unFilteredName.iterator();
               while(iterateByNameFilter.hasNext()){
                   Announcement announcement = (Announcement) iterateByNameFilter.next();
                   String NameString = announcement.getTitle();
                   if(NameString.startsWith(KeyString)){
                       if(announcement.getSerialNumber()!=null){
                           filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber()));
                       }else if(announcement.getDate()!=null){
                           filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber(),announcement.getDate()));
                       }else{
                           filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice()));
                       }

                   }else if(NameString.equals(KeyString)){
                       if(announcement.getSerialNumber()!=null){
                           filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber()));
                       }else if(announcement.getDate()!=null){
                           filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber(),announcement.getDate()));
                       }else{
                           filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice()));
                       }

                   }
               }
           }

        return filteredAnnouncement;
    }



    public static ArrayList<Announcement> FilterByPrice(ArrayList<String> keys,ArrayList<Announcement> unFiltered ){
        ArrayList<Announcement> filteredAnnouncement = new ArrayList<>();
        Iterator iterateByPriceFilter = unFiltered.iterator();
        while (iterateByPriceFilter.hasNext()){
            Iterator iterateByKeys=keys.iterator();
            while(iterateByKeys.hasNext()){
                String KeyString = iterateByKeys.next().toString();
                Announcement announcement = (Announcement) iterateByPriceFilter.next();
                String PriceString = announcement.getPrice();
                int twinOrNot=0;
                char[] key = KeyString.toCharArray();
                char[] name=PriceString.toCharArray();
                for(int i=0;i<(Math.min(KeyString.length(), PriceString.length()));i++){
                    for(int j=0;j<(Math.min(KeyString.length(), PriceString.length()));j++){
                        if(key[i]==name[j]){
                            twinOrNot++;
                        }
                    }
                }
               if(PriceString.startsWith(KeyString)){
                    if(announcement.getSerialNumber()!=null){
                        filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber()));
                    }else if(announcement.getDate()!=null){
                        filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber(),announcement.getDate()));
                    }else{
                        filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice()));
                    }

                }else if(KeyString.equals(PriceString)){
                   if(announcement.getSerialNumber()!=null){
                       filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber()));
                   }else if(announcement.getDate()!=null){
                       filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice(),announcement.getSerialNumber(),announcement.getDate()));
                   }else{
                       filteredAnnouncement.add(new Announcement(announcement.getTitle(),announcement.getPrice()));
                   }
               }
            }

        }
        return filteredAnnouncement;
    }

}
