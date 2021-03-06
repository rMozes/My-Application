package test.test.myapplication.supp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RICHI on 2014.10.01..
 */
public class GalleryItem {

    @SerializedName("name")
    private String mName;
    @SerializedName("image")
    private String mUrl;

    public GalleryItem() {}

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mSmallUrl) {
        this.mUrl = mSmallUrl;
    }

}
