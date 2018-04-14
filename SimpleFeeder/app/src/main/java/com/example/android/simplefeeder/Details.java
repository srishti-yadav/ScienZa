package com.example.android.simplefeeder;

/**
 * Created by lenovo on 4/8/2018.
 */

public class Details {
   private String title;
   private String description;
    private String image;
    public Details(String title,String description,String image)
    {
       this.title=title;
        this.description=description;
        this.image=image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
     public String getImage(){return image;}

}
