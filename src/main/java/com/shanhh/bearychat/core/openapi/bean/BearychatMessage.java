package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 14:23
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatMessage extends BearychatRequest {

    private static final long serialVersionUID = -916938355000058040L;

    /* @fomartter: on
    {
      "attachments": [
        {
          "color": "#ffa500",
          "images": [
            {
              "height": 160,
              "url": "https://images.bearychat.com/ad21145330b6f95a94edc9456961db4e?orig=http%3A%2F%2Fimg7%2Edoubanio%2Ecom%2Ficon%2Ful15067564-30%2Ejpg%3Fheight%3D160%26width%3D160",
              "width": 160
            }
          ],
          "text": "attachment text",
          "title": "attachment title",
          "type": "rtm"
        }
      ],
      "created": "2017-05-01T18:13:25.000+0800",
      "created_ts": 1493633604746,
      "fallback": null,
      "is_channel": false,
      "key": "1493633604746.0032",
      "refer_key": null,
      "robot_id": null,
      "subtype": "normal",
      "team_id": "=bwAxP",
      "text": "中午吃啥",
      "uid": "=bwOKs",
      "updated": "2017-05-01T18:13:25.000+0800",
      "vchannel_id": "=b2y6W624k"
    }
     * @formatter: off
     */

    private String key;
    private Date updated;
    private Date created;

    private String text;
    private String uid;
    @JsonProperty("vchannel_id")
    private String vchannelId;
    private List<Attachment> attachments;

    @Data
    @NoArgsConstructor
    @ToString
    public static class Attachment implements Serializable {
        private String color;
        private String text;
        private String title;
        private String type;
        private List<Image> images;

        @Data
        @NoArgsConstructor
        @ToString
        public static class Image implements Serializable {
            private int height;
            private String url;
            private String width;
        }
    }
}
