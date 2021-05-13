package com.poiasd.restphonebooks.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The base class that provides UID functionality to objects that extend this class, during runtime.
 * <p>
 * Objects can be compared by their UIDs to distinguish between objects with the same fields' values.
 * <p>
 * The UID comparison is faster than {@code equals} or {@code hashCode}, and these two methods may be then redefined<br/>
 * (for example, {@code equals} may be configured to return {@code true}
 * if two objects have the same fields' values, even though the UIDs are different).
 * <p>
 * Note! UID is ignored by JSON de- and serialization ({@link JsonIgnore}).
 */
public class ModelUidBase {

    @JsonIgnore
    private final String uid = UidUtil.uid();

    public String getUid() {
        return uid;
    }
}
