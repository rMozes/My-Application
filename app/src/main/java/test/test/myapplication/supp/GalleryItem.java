package test.test.myapplication.supp;

/**
 * Created by RICHI on 2014.10.01..
 */
public class GalleryItem {

    private String mName;
    private String mText;
    private String mSmallUrl;
    private String mBigUrl;

    public GalleryItem() {}

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mAuthor) {
        this.mText = mAuthor;
    }

    public String getSmallUrl() {
        return mSmallUrl;
    }

    public void setSmallUrl(String mSmallUrl) {
        this.mSmallUrl = mSmallUrl;
    }

    public String geBigUrl() {
        return mBigUrl;
    }

    public void setBigUrl(String mBigUrl) {
        this.mBigUrl = mBigUrl;
    }
}
