package org.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import FileWriterReader.WriteRead.txt.FileReader;
import FileWriterReader.WriteRead.txt.FileWriter;
import FileWriterReader.WriteXLS.WriteFile;
import Filter.Filter;
import Scraper.Model;
import Scraper.Scraper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller  {

    @FXML
    private Button addToDB;
    @FXML
    private Button start;

    @FXML
    private TextField url;
    @FXML
    private TextField pages;
    @FXML
    private TextField nameOfFile;
    @FXML
    private TextField nextPage;

    @FXML
    private TextField tagNameData;

    @FXML
    private TextField tagValueData;

    @FXML
    private TextField tagNamePrice;
    @FXML
    private TextField tagValuePrice;


    @FXML
    private TextField tagNameData3;

    @FXML
    private TextField tagValueData3;

    @FXML
    private TextArea database;

    @FXML
    private Label UrlMainERROR;

    @FXML
    private Label NextPageUrlERROR;

    @FXML
    private Label PagesERROR;

    @FXML
    private Label tagDataNameERROR;

    @FXML
    private Label tagPriceNameERROR;

    @FXML
    private Label tagDataValueERROR;

    @FXML
    private Label tagPriceValueERROR;


    @FXML
    private Label tagNameData2ERROR;

    @FXML
    private Label tagNameData3ERROR;

    @FXML
    private Label tagValueData2ERROR;

    @FXML
    private Label tagValueData3ERROR;

    @FXML
    public  Label status;

    @FXML
    private RadioButton filtByName;

    @FXML
    private RadioButton filtByPrice;

    @FXML
    private RadioButton getUrl;


    @FXML
    private TextArea filterKeysTA;


    @FXML
    private String getTextUrl() {
        if (url.getText().length() <= 0) {
            UrlMainERROR.setTextFill(Color.RED);
            UrlMainERROR.setText("Please input url");
            return null;
        } else {
            return url.getText();
        }
    }

    @FXML
    private int getTextPages() {
        if (pages.getText().length() <= 0) {
            PagesERROR.setTextFill(Color.RED);
            PagesERROR.setText("Please input number of pages.Min = 0");
            return -1;
        } else {
            return Integer.parseInt(pages.getText());
        }
    }

    @FXML
    private String getTextNextPage() {
        if (nextPage.getText().length() <= 0 && getTextPages() <= 0) {
            return null;
        } else {
            return nextPage.getText();
        }
    }

    @FXML
    private String getTextNameOfFile() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String result = nameOfFile.getText() + dtf.format(now);
        nameOfFile.setText(result);
        return result;
    }

    @FXML
    private String getTagNameData() {
        if (tagNameData.getText().length() <= 0) {
            tagDataNameERROR.setTextFill(Color.RED);
            tagDataNameERROR.setText("Please input tag name");
            return null;
        } else {
            return tagNameData.getText();
        }
    }

    @FXML
    private String getTagValueData() {
        if (tagValueData.getText().length() <= 0) {
            tagDataValueERROR.setTextFill(Color.RED);
            tagDataValueERROR.setText("Please input tag value");
        }
        return tagValueData.getText();
    }

    @FXML
    private String getTagNamePrice() {
        if (tagNamePrice.getText().length() <= 0) {
            tagPriceNameERROR.setTextFill(Color.RED);
            tagPriceNameERROR.setText("Program won`t use this!");
            return null;
        } else {
            return tagNamePrice.getText();
        }
    }

    @FXML
    private String getTagValuePrice() {
        if (tagValuePrice.getText().length() <= 0) {
            tagPriceValueERROR.setTextFill(Color.RED);
            tagPriceValueERROR.setText("Program won`t use this!");
        }
        return tagValuePrice.getText();
    }




    @FXML
    private String getTagNameDataThird() {
        if (tagNameData3.getText().length() <= 0) {
            tagNameData3ERROR.setTextFill(Color.RED);
            tagNameData3ERROR.setText("Program won`t use this!");
        }
        return tagNameData3.getText();
    }



    @FXML
    private String getTagValueDataThird() {
        if (tagValueData3.getText().length() <= 0) {
            tagValueData3ERROR.setTextFill(Color.RED);
            tagValueData3ERROR.setText("Program won`t use this!");
        }
        return tagValueData3.getText();
    }


    public Model getModel() {
        if(Objects.requireNonNull(getTextUrl()).length()<=0){
            return new Model();
        }
        if (StatusGetUrlOfAnnouncement() && getTagNameDataThird().length() > 0 && getTagValueDataThird().length() > 0) {
            return new Model(getTextUrl(), getTagNameData(), getTagValueData(), getTextNextPage(), getTextPages(), getTagNamePrice(), getTagValuePrice(), getTagNameDataThird(), getTagValueDataThird());
        }else if(getTagValuePrice().length() <= 0 && Objects.requireNonNull(getTagNamePrice()).length() <= 0){
            return new Model(getTextUrl(), getTagNameData(), getTagValueData(), getTextNextPage(), getTextPages());
        }else if(!StatusGetUrlOfAnnouncement() && getTagNameDataThird().length() > 0 && getTagValueDataThird().length() > 0){
            return new Model(getTextUrl(), getTagNameData(), getTagValueData(), getTextNextPage(), getTextPages(), getTagNamePrice(), getTagValuePrice(), getTagNameDataThird(), getTagValueDataThird());
        }else if (getTagValuePrice().length() > 0 && Objects.requireNonNull(getTagNamePrice()).length() > 0) {
            return new Model(getTextUrl(), getTagNameData(), getTagValueData(), getTextNextPage(), getTextPages(), getTagNamePrice(), getTagValuePrice());
        }
        return null;
    }

    @FXML
    public void showDB(ArrayList<String> urls, TextArea textAr) {
        String urlsFromDb = " ";
        Iterator<String> iterator = urls.listIterator();
        while (iterator.hasNext()) {
            urlsFromDb = urlsFromDb + "\n" + iterator.next();
        }
        textAr.setText(urlsFromDb);
    }

    @FXML
    public void show() throws IOException {
        //TODO don't forget about it
        Model mod = getModel();
        if (mod != null) {
            FileWriter fileDBWrite = new FileWriter(mod);
            fileDBWrite.WriteDB();
            FileReader fileDBRead = new FileReader();
            showDB(fileDBRead.ReadFile(), database);
        }

    }


    @FXML
    public void showApp() throws FileNotFoundException {
        //TODO don't forget about it
        FileReader fileDBRead = new FileReader();
        showDB(fileDBRead.ReadFile(), database);
    }


    private boolean StatusFiltByName() {
        return filtByName.isSelected();
    }

    private boolean StatusFiltByPrice() {
        return filtByPrice.isSelected();
    }

    private boolean StatusGetUrlOfAnnouncement(){
        return  getUrl.isSelected();
    }

    private ArrayList<String> getFilterKeys() {
        ArrayList<String> strKeys = new ArrayList<>();
        String[] filter_keys = filterKeysTA.getText().split("\n");
        for (String str:
             filter_keys) {
            strKeys.add(str);
        }

        return strKeys;
    }

    public void oprate() throws IOException {
        Model mod = getModel();
        Scraper horoz = new Scraper(mod);
        status.setText("is");

        if ((Objects.requireNonNull(getTextUrl())).length() > 0 &&
                (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 &&
                (Objects.requireNonNull(getTagNamePrice())).length() > 0 &&
                getTagValuePrice().length() > 0
                && StatusGetUrlOfAnnouncement()&&
                getTagNameDataThird().length() > 0 &&
                getTagValueDataThird().length() > 0
                && !StatusFiltByName()) {
                status.setText("1");
            if (WriteFile.WriteToFile(getTextNameOfFile(),horoz.getAnnouncementTiTlePriceURLSerialNumber(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL(), horoz.getSerialNumber())) == 0) {
                status.setTextFill(Color.RED);
                //  System.out.println("9 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();

            }
        }
        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 && (Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                && StatusGetUrlOfAnnouncement() && getTagNameDataThird().length() > 0 && getTagValueDataThird().length() > 0 && StatusFiltByName()) {
            status.setText("2");
            if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTiTlePriceURLSerialNumber(horoz.getTitlePages(), horoz.getPricePages(),horoz.getDataURL(), horoz.getSerialNumber())) == 0) {
                status.setTextFill(Color.RED);
                //  System.out.println("8 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }
        }
        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 && (Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                && getTagNameDataThird().length()>0 &&!StatusGetUrlOfAnnouncement()&& !StatusFiltByName()) {
            status.setText("3");
            if (WriteFile.WriteToFile(getTextNameOfFile(), ( horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getSerialNumber()))) == 0) {
                status.setTextFill(Color.RED);
                //System.out.println("7 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();

            }
        }
        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 &&(Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                && getTagNameDataThird().length()>0 &&!StatusGetUrlOfAnnouncement()&& StatusFiltByName()) {
            status.setText("4");
            if (WriteFile.WriteToFile(getTextNameOfFile(),Filter.FilterByName (getFilterKeys(), horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getSerialNumber()))) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("7 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();

            }
        }

        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 && (Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                && getTagNameDataThird().length()>0 &&StatusGetUrlOfAnnouncement()&&!StatusFiltByPrice()) {
            status.setText("5");
            if (WriteFile.WriteToFile(getTextNameOfFile(),( horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getSerialNumber()))) == 0) {
                status.setTextFill(Color.RED);
                //  System.out.println("7 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }
        }
        if ((getTextUrl()).length() > 0 &&(Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 && (Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                &&!StatusGetUrlOfAnnouncement()&& !StatusFiltByPrice()) {
            status.setText("6");
            if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByPrice(getFilterKeys(), horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL()))) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("7 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }
        }

        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 && (Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                &&StatusGetUrlOfAnnouncement() && !StatusFiltByName()) {
            status.setText("7");
            if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByName(getFilterKeys(), horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL()))) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("6 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }

        }


        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 && (Objects.requireNonNull(getTagNamePrice())).length() > 0 && getTagValuePrice().length() > 0
                &&StatusGetUrlOfAnnouncement() && !StatusFiltByName()) {
            status.setText("8");
            if (WriteFile.WriteToFile(getTextNameOfFile(), ( horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL()))) == 0) {
                status.setTextFill(Color.RED);
                //System.out.println("6 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }

        }

        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 && getTagValueData().length() > 0 &&!StatusGetUrlOfAnnouncement()&& StatusFiltByPrice()) {
            status.setText("9");
            if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByPrice(getFilterKeys(), horoz.getAnnouncementTitlePricePages(horoz.getTitlePages(), horoz.getPricePages()))) == 0) {
                status.setTextFill(Color.RED);
                //  System.out.println("5 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }
        }
        if ((getTextUrl()).length() > 0 &&(getTagNameData()).length() > 0 && getTagValueData().length() > 0&&!StatusGetUrlOfAnnouncement() && StatusFiltByName()) {
            status.setText("10");
            if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTitlePages(horoz.getTitlePages())) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("4 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }
        }

        if ((getTextUrl()).length() > 0 && (Objects.requireNonNull(getTagNameData())).length() > 0 && getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0 &&StatusGetUrlOfAnnouncement()&& StatusFiltByName()) {
            status.setText("11");
            if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByName(getFilterKeys(), horoz.getAnnouncementTitlePricePages(horoz.getTitlePages(), horoz.getPricePages()))) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("3 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            }

        }
        if ((getTextUrl()).length() > 0 &&
                (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 &&
                (Objects.requireNonNull(getTagNamePrice())).length() > 0 &&
                getTagValuePrice().length() > 0 &&StatusGetUrlOfAnnouncement()&&
                !StatusFiltByName()) {
            status.setText("12");
            if (WriteFile.WriteToFile(getTextNameOfFile(),horoz.getAnnouncementTitlePricePages(horoz.getTitlePages(), horoz.getPricePages())) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("1 try");
                status.setText("SUCCESSFULLY WROTE!");
                show();

            }

        }

        if ((getTextUrl()).length() > 0 &&
                (Objects.requireNonNull(getTagNameData())).length() > 0 &&
                getTagValueData().length() > 0 &&
                !StatusFiltByName()) {
            status.setText("13");
            if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTitlePages(horoz.getTitlePages())) == 0) {
                status.setTextFill(Color.RED);
                // System.out.println("2 tyr");
                status.setText("SUCCESSFULLY WROTE!");
                show();
            } else {
                status.setTextFill(Color.RED);
                status.setText("SOMETHING GONE WRONG!");

            }
        }
        status.setText("are");
    }


    @FXML
    private void startOperation(ActionEvent e) throws IOException{

        status.setTextFill(Color.RED);
        status.setText("Operation started!");
        String strTry =getTextUrl();
        status.setText(strTry);
        Platform.runLater(()-> {
            try {
                status.setText("try");
                oprate();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        status.setText("end");
    }
/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showApp();
            }
        });

        start.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                   /* status.setTextFill(Color.RED);
                    status.setText("Operation started!");*/
                            /* Model mod = getModel();
                           Scraper horoz = new Scraper(mod);

                               if ((getTextUrl()).length() > 0 &&
                                       (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 &&
                                       (getTagNamePrice()).length() > 0 &&
                                       getTagValuePrice().length() > 0
                                       && StatusGetUrlOfAnnouncement() &&
                                       getTagNameDataThird().length() > 0 &&
                                       getTagValueDataThird().length() > 0
                                       && !StatusFiltByName()) {

                                   if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTiTlePriceURLSerialNumber(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL(), horoz.getSerialNumber())) == 0) {
                                       status.setTextFill(Color.RED);
                                       //  System.out.println("9 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }
                               }
                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && StatusGetUrlOfAnnouncement() && getTagNameDataThird().length() > 0 && getTagValueDataThird().length() > 0 && StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTiTlePriceURLSerialNumber(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL(), horoz.getSerialNumber())) == 0) {
                                       status.setTextFill(Color.RED);
                                       //  System.out.println("8 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }
                               }
                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && getTagNameDataThird().length() > 0 && !StatusGetUrlOfAnnouncement() && !StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), (horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getSerialNumber()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       //System.out.println("7 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }
                               }
                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && getTagNameDataThird().length() > 0 && !StatusGetUrlOfAnnouncement() && StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByName(getFilterKeys(), horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getSerialNumber()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("7 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }
                               }

                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && getTagNameDataThird().length() > 0 && !StatusGetUrlOfAnnouncement() && !StatusFiltByPrice()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), (horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getSerialNumber()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       //  System.out.println("7 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }
                               }
                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && StatusGetUrlOfAnnouncement() && StatusFiltByPrice()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByPrice(getFilterKeys(), horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("7 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();

                                   }
                               }

                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && StatusGetUrlOfAnnouncement() && StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByName(getFilterKeys(), horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("6 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();

                                   }

                               }


                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 && (getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0
                                       && StatusGetUrlOfAnnouncement() && !StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), (horoz.getAnnouncementTiTlePriceURL(horoz.getTitlePages(), horoz.getPricePages(), horoz.getDataURL()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       //System.out.println("6 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();

                                   }

                               }

                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 && getTagValueData().length() > 0 && !StatusGetUrlOfAnnouncement() && StatusFiltByPrice()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByPrice(getFilterKeys(), horoz.getAnnouncementTitlePricePages(horoz.getTitlePages(), horoz.getPricePages()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       //  System.out.println("5 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }
                               }
                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 && getTagValueData().length() > 0 && !StatusGetUrlOfAnnouncement() && StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTitlePages(horoz.getTitlePages())) == 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("4 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();

                                   }
                               }

                               if ((getTextUrl()).length() > 0 && (getTagNameData()).length() > 0 && getTagValueData().length() > 0 && Objects.requireNonNull(getTagNamePrice()).length() > 0 && getTagValuePrice().length() > 0 && StatusGetUrlOfAnnouncement() && StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), Filter.FilterByName(getFilterKeys(), horoz.getAnnouncementTitlePricePages(horoz.getTitlePages(), horoz.getPricePages()))) == 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("3 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }

                               }
                               if ((getTextUrl()).length() > 0 &&
                                     (getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 &&
                                      (getTagNamePrice()).length() > 0 &&
                                       getTagValuePrice().length() > 0 && StatusGetUrlOfAnnouncement() &&
                                       !StatusFiltByName()) {
                                    int index=WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTitlePricePages(horoz.getTitlePages(), horoz.getPricePages()));
                                   if ( index== 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("1 try");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();
                                   }

                               }

                               if ((getTextUrl()).length() > 0 &&
                                       Objects.requireNonNull(getTagNameData()).length() > 0 &&
                                       getTagValueData().length() > 0 &&
                                       !StatusFiltByName()) {
                                   if (WriteFile.WriteToFile(getTextNameOfFile(), horoz.getAnnouncementTitlePages(horoz.getTitlePages())) == 0) {
                                       status.setTextFill(Color.RED);
                                       // System.out.println("2 tyr");
                                       status.setText("SUCCESSFULLY WROTE!");
                                       show();

                                   } else {
                                       status.setTextFill(Color.RED);
                                       status.setText("SOMETHING GONE WRONG!");

                                   }
                               }
                               status.setText("SOMETHING GONE WRONG!");
                       }
        });


    }*/
}
