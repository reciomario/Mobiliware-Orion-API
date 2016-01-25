package entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class UpdateResponse {
    public final Entity[] contextResponses;

    @JsonCreator
    public UpdateResponse(@JsonProperty("contextResponses") Entity[] contextResponses){
        this.contextResponses = contextResponses;
    }

 
}