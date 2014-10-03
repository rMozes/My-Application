package test.test.myapplication.supp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RICHI on 2014.10.03..
 */
public class GalleryDetail {

    @SerializedName("name")
    private String mName;
    @SerializedName("text")
    private String mText;
    @SerializedName("image")
    private String mUrl;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
