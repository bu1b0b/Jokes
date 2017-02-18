
package ru.bu1b0b.jokes.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;


public class JokeModel {

    @SerializedName("desc")
    private String mDesc;
    @SerializedName("elementPureHtml")
    private String mElementPureHtml;
    @SerializedName("link")
    private String mLink;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getElementPureHtml() {
        return mElementPureHtml;
    }

    public void setElementPureHtml(String elementPureHtml) {
        mElementPureHtml = elementPureHtml;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    @Override
    public boolean equals(Object obj) {
        JokeModel joke = (JokeModel) obj;
        return mElementPureHtml.equals(joke.mElementPureHtml);

    }
}
