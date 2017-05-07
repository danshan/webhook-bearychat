package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dan
 * @since 2017-05-03 11:59
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatTeam implements Serializable {

    /*
     * @formatter: on
    {
      "id": "=bw52O",
      "subdomain": "openapi",
      "name": "BearyChat OpenAPI",
      "email_domain": null,
      "logo_url": null,
      "description": "",
      "plan": "basic",
      "created": "2017-01-11T12:28:31.000+0000"
    }
     * @formatter: off
     */

    private String id;
    @JsonProperty("sub_domain")
    private String subDomain;
    private String name;
    @JsonProperty("email_domain")
    private String emailDomain;
    @JsonProperty("log_url")
    private String logUrl;
    private String description;
    private String plan;
    private Date created;

}
