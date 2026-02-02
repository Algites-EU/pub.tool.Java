package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Version context for controlled artifacts in a container.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcVersionContextDefDTO {

    @JsonProperty("versionContextId")
    private String versionContextId;

    @JsonProperty("releaseLine")
    private String releaseLine;

  	@JsonProperty("revision")
	  private Integer revision;

	  @JsonProperty("revisionStringSize")
    private Integer revisionStringSize;

    @JsonProperty("qualifierKind")
    private String qualifierKind;

    @JsonProperty("qualifierLabel")
    private String qualifierLabel;

	/**
	 * @return the versionContextId
	 */
	public String getVersionContextId() {
		return versionContextId;
	}

	/**
	 * @param aVersionContextId the versionContextId
	 */
	public void setVersionContextId(final String aVersionContextId) {
		versionContextId = aVersionContextId;
	}

	public String getReleaseLine() {
        return releaseLine;
    }

    public void setReleaseLine(String aReleaseLine) {
        releaseLine = aReleaseLine;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer aRevision) {
        revision = aRevision;
    }

	/**
	 * @return the revisionStringSize
	 */
	public Integer getRevisionStringSize() {
		return revisionStringSize;
	}

	/**
	 * @param aRevisionStringSize the revisionStringSize
	 */
	public void setRevisionStringSize(final Integer aRevisionStringSize) {
		revisionStringSize = aRevisionStringSize;
	}

	public String getQualifierKind() {
        return qualifierKind;
    }

    public void setQualifierKind(String aQualifierKind) {
        qualifierKind = aQualifierKind;
    }

    public String getQualifierLabel() {
        return qualifierLabel;
    }

    public void setQualifierLabel(String aQualifierLabel) {
        qualifierLabel = aQualifierLabel;
    }
}
