package com.example.pren.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class Home
{
    private String txtHome;
    private int imgHome;

    public Home(String txtHome, int imgHome) {
        this.txtHome = txtHome;
        this.imgHome = imgHome;
    }

    public String getTxtHome() {
        return txtHome;
    }

    public int getImgHome() {
        return imgHome;
    }

    public void setTxtHome(String txtHome) {
        this.txtHome = txtHome;
    }

    public void setImgHome(int imgHome) {
        this.imgHome = imgHome;
    }
}
