package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.ArrayList;
import java.util.List;

/**
 * Version rule for uncontrolled dependencies.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyVersionRuleDefDTO {

    @JsonProperty("preferred")
    private String preferred;

    @JsonProperty("range")
    private String range;

    @JsonProperty("ranges")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<String> ranges = new ArrayList<>();

    public String getPreferred() {
        return preferred;
    }

    public void setPreferred(String aPreferred) {
        preferred = aPreferred;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String aRange) {
        range = aRange;
    }

    public List<String> getRanges() {
        return ranges;
    }

    public void setRanges(List<String> aRanges) {
        ranges = aRanges;
    }
}
