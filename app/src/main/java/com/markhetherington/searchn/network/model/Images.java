
package com.markhetherington.searchn.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Images {

    @Expose
    private Object hidpi;
    @Expose
    private String normal;
    @Expose
    private String teaser;

    /**
     * 
     * @return
     *     The hidpi
     */
    public Object getHidpi() {
        return hidpi;
    }

    /**
     * 
     * @param hidpi
     *     The hidpi
     */
    public void setHidpi(Object hidpi) {
        this.hidpi = hidpi;
    }

    /**
     * 
     * @return
     *     The normal
     */
    public String getNormal() {
        return normal;
    }

    /**
     * 
     * @param normal
     *     The normal
     */
    public void setNormal(String normal) {
        this.normal = normal;
    }

    /**
     * 
     * @return
     *     The teaser
     */
    public String getTeaser() {
        return teaser;
    }

    /**
     * 
     * @param teaser
     *     The teaser
     */
    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

}
