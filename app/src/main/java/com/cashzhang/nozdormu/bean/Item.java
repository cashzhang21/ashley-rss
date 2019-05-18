
package com.cashzhang.nozdormu.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("keywords")
    @Expose
    private List<String> keywords = null;
    @SerializedName("originId")
    @Expose
    private String originId;
    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("alternate")
    @Expose
    private List<Alternate_> alternate = null;
    @SerializedName("crawled")
    @Expose
    private Integer crawled;
    @SerializedName("published")
    @Expose
    private Integer published;
    @SerializedName("origin")
    @Expose
    private Origin origin;
    @SerializedName("visual")
    @Expose
    private Visual visual;
    @SerializedName("canonicalUrl")
    @Expose
    private String canonicalUrl;
    @SerializedName("unread")
    @Expose
    private Boolean unread;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("entities")
    @Expose
    private List<Entity> entities = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Alternate_> getAlternate() {
        return alternate;
    }

    public void setAlternate(List<Alternate_> alternate) {
        this.alternate = alternate;
    }

    public Integer getCrawled() {
        return crawled;
    }

    public void setCrawled(Integer crawled) {
        this.crawled = crawled;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Boolean getUnread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("keywords", keywords).append("originId", originId).append("fingerprint", fingerprint).append("title", title).append("summary", summary).append("alternate", alternate).append("crawled", crawled).append("published", published).append("origin", origin).append("visual", visual).append("canonicalUrl", canonicalUrl).append("unread", unread).append("categories", categories).append("entities", entities).toString();
    }

}