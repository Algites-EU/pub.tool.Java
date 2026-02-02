package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.AIiArtifactOutputTypeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyScopeBindingExportModeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyScopeBindingLocalUsageModeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyScopeBindingLockKindData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyScopeBindingSetDataInheritanceModeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencySourceSetData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencySourceSetGroupData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyPurposeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyPurposeGroupData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * One application rule for a dependency.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyBindingDefDTO {

    @JsonProperty("templates")
    private List<String> templateCodes;

    @JsonProperty("purposeGroup")
    private AIiArtifactDependencyPurposeGroupData purposeGroup;

	  @JsonProperty("purposes")
	  private List<AIiArtifactDependencyPurposeData> purposes;

	  @JsonProperty("purposesInheritance")
  	private AIiArtifactDependencyScopeBindingSetDataInheritanceModeData purposesInheritanceMode;

	  @JsonProperty("sourceSetGroup")
    private AIiArtifactDependencySourceSetGroupData sourceSetGroup;

	  @JsonProperty("sourceSets")
	  private List<AIiArtifactDependencySourceSetData> sourceSets;

		@JsonProperty("sourceSetsInheritance")
		private AIiArtifactDependencyScopeBindingSetDataInheritanceModeData sourceSetsInheritanceMode;

 	  @JsonProperty("outputType")
		private AIiArtifactOutputTypeData outputType;

	/* none | asDependency | asConstraint */
	@JsonProperty("export")
	private AIiArtifactDependencyScopeBindingExportModeData export;

	/* withTransitives | withoutTransitives | disabled */
	@JsonProperty("localUsage")
	private AIiArtifactDependencyScopeBindingLocalUsageModeData localUsageMode;

	@JsonProperty("exclusions")
		private List<AIcArtifactCoordinateDefDTO> exclusions;

	@JsonProperty("exclusionsInheritance")
	private AIiArtifactDependencyScopeBindingSetDataInheritanceModeData exclusionsInheritanceMode;

		@JsonProperty("lock")
    private AIiArtifactDependencyScopeBindingLockKindData lockKind;

    @JsonProperty("weight")
    private Integer weight;

    public List<String> getTemplateCodes() {
        return templateCodes;
    }

    public void setTemplateCodes(List<String> aTemplateCodes) {
        templateCodes = aTemplateCodes;
    }

    public AIiArtifactDependencyPurposeGroupData getPurposeGroup() {
        return purposeGroup;
    }

    public void setPurpose(AIiArtifactDependencyPurposeGroupData aPurpose) {
        purposeGroup = aPurpose;
    }

	public List<AIiArtifactDependencyPurposeData> getPurposes() {
		return purposes;
	}

	public void setPurpose(List<AIiArtifactDependencyPurposeData> aPurposes) {
		purposes = aPurposes;
	}

	public AIiArtifactDependencySourceSetGroupData getSourceSetGroup() {
        return sourceSetGroup;
    }

    public void setSourceSetGroup(AIiArtifactDependencySourceSetGroupData aSourceSetGroup) {
        sourceSetGroup = aSourceSetGroup;
    }

	/**
	 * @return the purposes
	 */
	public List<AIiArtifactDependencySourceSetData> getSourceSets() {
		return sourceSets;
	}

	/**
	 * @param aSourceSets the source sets
	 */
	public void setSourceSets(final List<AIiArtifactDependencySourceSetData> aSourceSets) {
		sourceSets = aSourceSets;
	}

	/**
	 * @return the outputType
	 */
	public AIiArtifactOutputTypeData getOutputType() {
		return outputType;
	}

	/**
	 * @param aOutputType the outputType
	 */
	public void setOutputType(final AIiArtifactOutputTypeData aOutputType) {
		outputType = aOutputType;
	}

	/**
	 * @return the exclusions
	 */
	public List<AIcArtifactCoordinateDefDTO> getExclusions() {
		return exclusions;
	}

	/**
	 * @param aExclusions the exclusions
	 */
	public void setExclusions(final List<AIcArtifactCoordinateDefDTO> aExclusions) {
		exclusions = aExclusions;
	}

    public AIiArtifactDependencyScopeBindingLockKindData getLockKind() {
        return lockKind;
    }

    public void setLockKind(AIiArtifactDependencyScopeBindingLockKindData aLockKind) {
        lockKind = aLockKind;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer aWeight) {
        weight = aWeight;
    }
}
