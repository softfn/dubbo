package com.alibaba.dubbo.rpc;

import java.io.Serializable;

/**
 * <p/>
 * Status
 * <p/>
 *
 * @author softfn
 */
public interface Status extends Serializable {
    /**
     * 编码
     *
     * @return
     */
    int code();

    /**
     * 备注
     *
     * @return
     */
    String msg();
}
