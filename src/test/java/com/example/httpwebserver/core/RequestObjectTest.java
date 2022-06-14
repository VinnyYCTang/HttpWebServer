package com.example.httpwebserver.core;

import org.junit.jupiter.api.Test;

public class RequestObjectTest {

    @Test
    public void testGetParameterValue(){

        RequestObject request = new RequestObject("/oa/user/save?");
        System.out.println(request.getParameterValue("username"));

        RequestObject request1 = new RequestObject("/oa/user/save?username=");
        System.out.println(request1.getParameterValue("username"));

        RequestObject request2 = new RequestObject("/oa/user/save?username=vinny");
        System.out.println(request2.getParameterValue("username"));

        RequestObject request3 = new RequestObject("/oa/user/save?username=vinny&gender=1");
        System.out.println(request3.getParameterValue("gender"));

        RequestObject requestObject3 = new RequestObject("/benq/user/save?username=vinny&gender=1&interest=food&onterest=travel");
        System.out.println(requestObject3.getParameterValue("interest"));

    }

    @Test
    public void testGetParameterValues(){

        RequestObject requestObject1 = new RequestObject("/benq/user/save?username=vinny&gender=1");
        System.out.println(requestObject1.getParameterValues("gender")[0]);

        RequestObject requestObject2 = new RequestObject("/benq/user/save?username=vinny&gender=1&interest=food");
        String[] interests = requestObject2.getParameterValues("interest");
        for(String interest : interests) {
            System.out.println(interest);
        }

        RequestObject requestObject3 = new RequestObject("/benq/user/save?username=vinny&gender=1&interest=food&interest=travel");
        interests = requestObject3.getParameterValues("interest");
        for(String interest : interests) {
            System.out.println(interest);
        }

    }

}
