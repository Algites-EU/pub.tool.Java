package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Project property settings.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcProjectPropertiesDefDTO {

    @JsonProperty("includedTemplateUids")
    private List<String> includedTemplateUids;

    @JsonProperty("propertyDefinitions")
    private List<AIcProjectPropertyItemDefDTO> propertyDefinitions;


    public List<String> getincludedTemplateUids() {
        return includedTemplateUids;
    }

    public void setIncludedTemplateUids(List<String> aIncludedTempalteIds) {
        includedTemplateUids = aIncludedTempalteIds;
    }

    public List<AIcProjectPropertyItemDefDTO> getPropertyDefinitions() {
        return propertyDefinitions;
    }

    public void setPropertyDefinitions(List<AIcProjectPropertyItemDefDTO> aDefs) {
        propertyDefinitions = aDefs;
    }

}
