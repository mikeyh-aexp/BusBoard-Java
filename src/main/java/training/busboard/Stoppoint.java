package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stoppoint {
    String naptanId;
    String commonName;
    List<additionalProperties> additionalProperties;

    public List<additionalProperties> getAdditionalProperties() {
        return additionalProperties;
    }

    public String getNaptanId() {
        return naptanId;
    }

    public String getCommonName() {
        return commonName;
    }

}
