package com.cwj.scriptlib.reactor;

import java.util.List;

/**
 * com.cwj.scriptlib.reactor
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-08-10 21:05
 */
public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);
    void processComplete();
}
