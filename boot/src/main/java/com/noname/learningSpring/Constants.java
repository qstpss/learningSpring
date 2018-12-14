package com.noname.learningSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public final String apiEntryPoint;
    public final boolean csrf;

    @Autowired
    public Constants(@Value("${app.server.apiEntryPoint}") String apiEntryPoint,
                     @Value("${app.server.csrf}") boolean csrf) {
        this.apiEntryPoint = apiEntryPoint;
        this.csrf = csrf;
    }
}
