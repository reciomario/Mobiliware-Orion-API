package entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Entity {
    public final ContextElement contextElement;
    public final StatusCode statusCode;

    @JsonCreator
    public Entity(@JsonProperty("contextElement") ContextElement contextElement, @JsonProperty("statusCode") StatusCode statusCode){
        this.contextElement = contextElement;
        this.statusCode = statusCode;
    }

    public static final class ContextElement {
        public final String type;
        public final String isPattern;
        public final String id;
        public final Attribute attributes[];

        @JsonCreator
        public ContextElement(@JsonProperty("type") String type, @JsonProperty("isPattern") String isPattern, @JsonProperty("id") String id, @JsonProperty("attributes") Attribute[] attributes){
            this.type = type;
            this.isPattern = isPattern;
            this.id = id;
            this.attributes = attributes;
        }

        public static final class Attribute {
            public final String name;
            public final String type;
            public final String value;
            public final Attribute metadatas[];
    
            @JsonCreator
            public Attribute(@JsonProperty("name") String name, @JsonProperty("type") String type, @JsonProperty("value") String value,
            		@JsonProperty("metadatas") Attribute[] metadatas){
                this.name = name;
                this.type = type;
                this.value = value;
                this.metadatas = metadatas;
            }
        }
    }

    public static final class StatusCode {
        public final String code;
        public final String reasonPhrase;
        public final String details;

        @JsonCreator
        public StatusCode(@JsonProperty("code") String code, @JsonProperty("reasonPhrase") String reasonPhrase,
        		@JsonProperty("details") String details){
            this.code = code;
            this.reasonPhrase = reasonPhrase;
            this.details = details;
        }
    }
}