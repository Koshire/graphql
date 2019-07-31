package com.lwo.akulov.graph.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public String aroundBy(String value, String charValue) {
        return charValue + value + charValue;
    }

    public boolean isNull(Object object) {
        return object == null;
    }
}
