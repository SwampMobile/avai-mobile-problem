package com.swampmobile.avaimobileproblem.app.net.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * POJO representing a server response from the Duck Duck Go API.
 */
public class DuckDuckGoResponse {
    @SerializedName("Abstract")
    private String abstractValue;

    @SerializedName("AbstractText")
    private String abstractText;

    @SerializedName("AbstractSource")
    private String abstractSource;

    @SerializedName("AbstractURL")
    private String abstractUrl;

    @SerializedName("Image")
    private String image;

    @SerializedName("Heading")
    private String heading;

    @SerializedName("Answer")
    private String answer;

    @SerializedName("Redirect")
    private String redirect;

    @SerializedName("AnswerType")
    private String answerType;

    @SerializedName("Definition")
    private String definition;

    @SerializedName("DefinitionSource")
    private String definitionSource;

    @SerializedName("DefinitionURL")
    private String definitionUrl;

    @SerializedName("RelatedTopics")
    private List<DuckDuckGoRelatedTopic> relatedTopics;

    // We ignore results in this project because they are not required.
//    @SerializedName("Results")
//    private List<DuckDuckGoResult> results;

    @SerializedName("Type")
    private String type;

    public String getAbstractValue() {
        return abstractValue;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getAbstractSource() {
        return abstractSource;
    }

    public String getAbstractUrl() {
        return abstractUrl;
    }

    public String getImage() {
        return image;
    }

    public String getHeading() {
        return heading;
    }

    public String getAnswer() {
        return answer;
    }

    public String getRedirect() {
        return redirect;
    }

    public String getAnswerType() {
        return answerType;
    }

    public String getDefinition() {
        return definition;
    }

    public String getDefinitionSource() {
        return definitionSource;
    }

    public String getDefinitionUrl() {
        return definitionUrl;
    }

    public List<DuckDuckGoRelatedTopic> getRelatedTopics() {
        return relatedTopics;
    }

    public String getType() {
        return type;
    }
}