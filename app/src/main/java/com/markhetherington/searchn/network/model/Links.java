
package com.markhetherington.searchn.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Links {

    @Expose
    private String web;
    @Expose
    private String twitter;

    /**
     * 
     * @return
     *     The web
     */
    public String getWeb() {
        return web;
    }

    /**
     * 
     * @param web
     *     The web
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * 
     * @return
     *     The twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * 
     * @param twitter
     *     The twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

}
