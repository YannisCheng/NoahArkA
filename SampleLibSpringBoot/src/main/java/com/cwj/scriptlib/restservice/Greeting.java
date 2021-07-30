package com.cwj.scriptlib.restservice;

/**
 * com.cwj.scriptlib.restservice
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-30 14:43
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
