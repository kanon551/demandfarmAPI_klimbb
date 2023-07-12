package com.demandfarm.klimb.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommonResponse {

    private String house_names;
    private String characters;
    private boolean favorite;
    private String charactername;
    private Long id;
}
