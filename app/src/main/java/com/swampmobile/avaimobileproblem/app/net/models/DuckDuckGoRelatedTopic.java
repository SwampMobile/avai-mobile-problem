package com.swampmobile.avaimobileproblem.app.net.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * POJO representing a server response related topic from the Duck Duck Go API.
 */
public class DuckDuckGoRelatedTopic {
    @SerializedName("Result")
    private String result;

    @SerializedName("Icon")
    private DuckDuckGoIcon icon;

    @SerializedName("FirstURL")
    private String firstUrl;

    @SerializedName("Text")
    private String text;

    public String getResult() {
        return result;
    }

    public boolean hasIcon() {
        return !TextUtils.isEmpty(icon.getUrl());
    }

    public DuckDuckGoIcon getIcon() {
        return icon;
    }

    public String getFirstUrl() {
        return firstUrl;
    }

    public String getText() {
        return text;
    }

    public static class DuckDuckGoIcon {
        @SerializedName("URL")
        private String url;

        @SerializedName("Width")
        private String width;

        @SerializedName("Height")
        private String height;

        public String getUrl() {
            return url;
        }

        public Integer getWidth() {
            return TextUtils.isEmpty(width) ? null : Integer.parseInt(width);
        }

        public Integer getHeight() {
            return TextUtils.isEmpty(height) ? null : Integer.parseInt(height);
        }
    }
}
