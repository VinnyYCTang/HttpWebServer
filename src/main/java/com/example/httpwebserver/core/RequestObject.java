package com.example.httpwebserver.core;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * encapsulate a request object.
 * @author Vinny.YC.Tang
 * @version  1.0
 * @since    1.0
 *
 * */
public class RequestObject implements ServletRequest {

    private final Map<String, List<String>> parameterMap = new HashMap<>();

    public RequestObject(String requestURI){

        List<String> values = null;
        if(requestURI.contains("?")){
            String[] uriAndData = requestURI.split("[?]");
            if(uriAndData.length > 1) {
                String data = uriAndData[1];
                if (data.contains("&")) {
                    // multiple parameters
                    String[] nameAndValues = data.split("&");
                    for(String nameAndValue : nameAndValues){
                        values = new ArrayList<>();
                        String[] nameAndValueAttr = nameAndValue.split("=");
                        if(parameterMap.containsKey(nameAndValueAttr[0])){
                            if(nameAndValueAttr.length > 1)
                                parameterMap.get(nameAndValueAttr[0]).add(nameAndValueAttr[1]);
                        }else{
                            if(nameAndValueAttr.length > 1)
                               values.add(nameAndValueAttr[1]);
                            parameterMap.put(nameAndValueAttr[0], values);
                        }
                    }
                } else {
                    // single parameter
                    String[] nameAndValue = data.split("=");
                    values = new ArrayList<>();
                    if(nameAndValue.length > 1)
                        values.add(nameAndValue[1]);
                    parameterMap.put(nameAndValue[0], values);
                }
            }
        }

    }

    public String getParameterValue(String key){
        ArrayList<String> list = (ArrayList<String>) parameterMap.get(key);
        return (list != null && list.size() != 0) ? list.get(0) : null;
    }

    public String[] getParameterValues(String key) {
        String[] value = new String[]{};
        return parameterMap.get(key).toArray(value);
    }

}
