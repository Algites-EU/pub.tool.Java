package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Properties renderPattern (named set of imported templates and key/value definitions).
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcProjectPropertiesTemplateDefDTO extends AIcAbstractTemplateDefDTO {

	@JsonProperty("defs")
    private List<Object> defs;

	public List<Object> getDefs() {
        return defs;
    }

    public void setDefs(List<Object> aDefs) {
        defs = aDefs;
    }
}
