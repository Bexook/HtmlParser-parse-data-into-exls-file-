package Scraper;

public  class Model {
  private String url;
  private String tagName;
  private String tagValue;
  private String nextPage;
  private int pages;
  private String tagNamePrice;
  private String tagValuePrice;
  private String tagUrlSecondName;
  private String getTagUrlSecondValue;
  private String getTagSerialNumberData;
  private String getTagSerialNumberValue;




    public Model(String url,
                 String tagName,
                 String tagValue,
                 String nextPage,
                 int pages,
                 String tagNamePrice,
                 String tagValuePrice,
                 String getTagSerialNumberData,
                 String getTagSerialNumberValue) {
        this.url = url;
        this.tagName = tagName;
        this.nextPage = nextPage;
        this.tagValue = tagValue;
        this.pages = pages;
        this.tagNamePrice = tagNamePrice;
        this.tagValuePrice = tagValuePrice;
        this.getTagSerialNumberData=getTagSerialNumberData;
        this.getTagSerialNumberValue=getTagSerialNumberValue;
    }



    public Model(String url, String tagName, String tagValue, String nextPage, int pages, String tagNamePrice, String tagValuePrice) {
        this.url = url;
        this.tagName = tagName;
        this.nextPage = nextPage;
        this.tagValue = tagValue;
        this.pages = pages;
        this.tagNamePrice = tagNamePrice;
        this.tagValuePrice = tagValuePrice;
    }

    public Model(String url, String tagName,  String tagValue,String nextPage, int pages) {
        this.url = url;
        this.tagName = tagName;
        this.nextPage = nextPage;
        this.tagValue = tagValue;
        this.pages = pages;
    }


    public Model(String url, String tagName,  String tagValue) {
        this.url = url;
        this.tagName = tagName;
        this.tagValue = tagValue;
    }

    public Model() {
    }

    public String getUrl() {
        return url;
    }

    public String getTagName() {
        return tagName;
    }

    public String getNextPage() {
        return nextPage;
    }

    public String getTagValue() {
        return tagValue;
    }

    public int getPages() {
        return pages;
    }

    public String getTagNamePrice() {
        return tagNamePrice;
    }

    public String getTagValuePrice() {
        return tagValuePrice;
    }




    public String getTagSerialNumberData() {
        return getTagSerialNumberData;
    }

    public String getTagSerialNumberValue() {
        return getTagSerialNumberValue;
    }
}

