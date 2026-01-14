package eu.algites.tool.build.model.loader;

import static eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledArtifact.ARTIFACT_CONFIG_FILE_NAME_WITHOUT_EXT;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactKind.AGGREGATOR;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactKind.DEPENDENCY_DEFINITION_POLICY;
import static eu.algites.tool.build.model.repository.intf.AIiSourceRepository.SOURCE_REPOSITORY_CONFIG_FILE_NAME_WITHOUT_EXT;
import static eu.algites.tool.build.model.utils.AIsArtifactModelUtils.filenameWithoutExtension;
import static eu.algites.tool.build.model.utils.AIsArtifactModelUtils.resolveArtificialArtifactId;

import eu.algites.tool.build.model.AIcArtifactModel;
import eu.algites.tool.build.model.artifact.dto.AIcArtifactCoordinateDefDTO;
import eu.algites.tool.build.model.artifact.dto.AIcArtifactCoordinateVersionDefDTO;
import eu.algites.tool.build.model.artifact.dto.AIcArtifactDefDTO;
import eu.algites.tool.build.model.artifact.dto.AIcDependencyDefDTO;
import eu.algites.tool.build.model.artifact.dto.AIcExclusionDefDTO;
import eu.algites.tool.build.model.artifact.dto.AIcScopeDefDTO;
import eu.algites.tool.build.model.artifact.impl.AIcAbstractUncontrolledArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcArtifactDependency;
import eu.algites.tool.build.model.artifact.impl.AIcArtifactDependencyScope;
import eu.algites.tool.build.model.artifact.impl.AIcArtifactOutput;
import eu.algites.tool.build.model.artifact.impl.AIcControlledAggregatorArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcControlledDependencyDefinitionPolicyArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcControlledProductCoreArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcControlledProductInterfaceBomArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcControlledProductVariantBomArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcDependencyScopeBehavior;
import eu.algites.tool.build.model.artifact.impl.AIcUncontrolledCoreArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcUncontrolledPomArtifact;
import eu.algites.tool.build.model.artifact.impl.AIcUndefinedArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledCoreArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledParentRwContainerArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiArtifactDependency;
import eu.algites.tool.build.model.common.dto.AIcArtifactContainerDefDTO;
import eu.algites.tool.build.model.common.dto.AIcCommonHolderDefDTO;
import eu.algites.tool.build.model.common.intf.AIcContainedArtifactLocalKey;
import eu.algites.tool.build.model.common.intf.AIiAbstractArtifactContainer;
import eu.algites.tool.build.model.repository.dto.AIcSourceRepositoryDefDTO;
import eu.algites.tool.build.model.repository.impl.AIcSourceRepository;
import eu.algites.tool.build.model.repository.intf.AIiSourceRepository;
import eu.algites.tool.build.model.utils.AInConfigurationFileKind;
import eu.algites.tool.build.model.utils.AIsArtifactModelUtils;
import eu.algites.tool.build.model.version.dto.AIcVersionContextDefDTO;
import eu.algites.tool.build.model.version.impl.AIcVersionContext;
import eu.algites.tool.build.model.version.impl.AIcVersionQualifier;
import eu.algites.tool.build.model.version.impl.AIcVersionReleaseLine;
import eu.algites.tool.build.model.version.impl.AIcVersionReleaseLineRevision;
import eu.algites.tool.build.model.version.intf.AIiVersionContext;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.MapperBuilder;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;
import tools.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Loads the artifact model from YAML into a resolved in-memory graph.
 *
 * <p>Implementation note: This loader intentionally keeps "model interfaces" clean of Jackson annotations.
 * YAML is deserialized into DTOs and then converted into the {@code impl} POJOs.</p>
 *
 * @author linhart1
 */
public final class AIcArtifactModelLoader {

	public static final Path VIRTUAL_NONEXISTENT_PATH = Path.of("");

	private static final ThreadLocal<Deque<AIiVersionContext>> versionContextDeque = ThreadLocal.withInitial(ArrayDeque::new);

	private AIcArtifactModelLoader() {
	}

	/**
	 * Performs the loading of the artifact model repository configuration. Only the contained artifacts will be loaded from the specified
	 * repository configuration file, other artifacts in the repository will be ignored
	 *
	 * @param aRepositoryConfigurationFile configuration file for the repository
	 * @return the loaded artifact model
	 * @throws IOException if an error occurs during loading
	 * @throws IllegalArgumentException if during the computation occurred some inconsistency or invalid input from file
	 * @throws IllegalStateException if during the computation occurred some inconsistency or invalid input from file
	 */
	public static AIcArtifactModel loadRepositoryArtifacts(
			Path aRepositoryConfigurationFile) throws IOException, IllegalStateException, IllegalArgumentException {
		if (aRepositoryConfigurationFile == null)
			throw new IllegalArgumentException("No repository configuration file specified.");
		return internalLoad(aRepositoryConfigurationFile, null);
	}

	/**
	 * Performs the loading of the artifact model from the passed files, where the files are intended to be loaded without any connection to
	 * the repository where they are located.
	 *
	 * @param aRootAggregatorOrSingleProductArtifactConfigurationFiles configuration files for the artifacts
	 * @return the loaded artifact model
	 * @throws IOException if an error occurs during loading
	 * @throws IllegalArgumentException if during the computation occurred some inconsistency or invalid input from file
	 * @throws IllegalStateException if during the computation occurred some inconsistency or invalid input from file
	 */
	public static AIcArtifactModel loadSpecificArtifactWithoutRepositoryKnown(
			Set<Path> aRootAggregatorOrSingleProductArtifactConfigurationFiles)
			throws IOException, IllegalStateException, IllegalArgumentException {
		if (aRootAggregatorOrSingleProductArtifactConfigurationFiles == null
				|| aRootAggregatorOrSingleProductArtifactConfigurationFiles.isEmpty())
			throw new IllegalArgumentException("No artifact configuration files specified.");
		return internalLoad(null, aRootAggregatorOrSingleProductArtifactConfigurationFiles);
	}

	/**
	 * Performs the loading of the artifact model from the passed files. The artifact can take the version context from the repository if it
	 * is specified there but ignores the definitions of the contained artifacts in the repository configuration
	 *
	 * @param aRepositoryConfigurationFile configuration file for the repository
	 * @param aRootAggregatorOrSingleProductArtifactConfigurationFiles configuration files for the artifacts to load
	 * @return the loaded artifact model
	 * @throws IOException if an error occurs during loading
	 * @throws IllegalArgumentException if during the computation occurred some inconsistency or invalid input from file
	 * @throws IllegalStateException if during the computation occurred some inconsistency or invalid input from file
	 */
	public static AIcArtifactModel loadSpecificArtifactWithRepositoryVersion(
			Path aRepositoryConfigurationFile,
			Set<Path> aRootAggregatorOrSingleProductArtifactConfigurationFiles)
			throws IOException, IllegalStateException, IllegalArgumentException {
		if (aRepositoryConfigurationFile == null)
			throw new IllegalArgumentException("No repository configuration file specified.");
		if (aRootAggregatorOrSingleProductArtifactConfigurationFiles == null
				|| aRootAggregatorOrSingleProductArtifactConfigurationFiles.isEmpty())
			throw new IllegalArgumentException("No artifact configuration files specified.");
		return internalLoad(aRepositoryConfigurationFile, aRootAggregatorOrSingleProductArtifactConfigurationFiles);
	}

	/**
	 * Performs the loading of the artifact model from the passed files. The artifacts referenced in the repository configuration are
	 * ignored, only version context can be taken from the repository if specified
	 *
	 * @param aRepositoryConfigurationFile configuration file for the repository
	 * @param aRootAggregatorOrSingleProductArtifactConfigurationFileSet configuration files for the artifacts to load
	 * @return the loaded artifact model
	 * @throws IOException if an error occurs during loading
	 */
	private static AIcArtifactModel internalLoad(
			Path aRepositoryConfigurationFile,
			Set<Path> aRootAggregatorOrSingleProductArtifactConfigurationFileSet) throws IOException {
		LinkedHashMap<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> locArtifactsByIdMap = new LinkedHashMap<>();
		LinkedHashMap<AIcContainedArtifactLocalKey, AIiAbstractControlledArtifact> locArtifactsToFinalize = new LinkedHashMap<>();

		boolean locArtifactDefinitionsMandatory = aRepositoryConfigurationFile == null;

		AIiSourceRepository locSourceRepository = null;

		if (aRepositoryConfigurationFile != null) {
			if (!SOURCE_REPOSITORY_CONFIG_FILE_NAME_WITHOUT_EXT.equals(filenameWithoutExtension(aRepositoryConfigurationFile)))
				throw new IllegalArgumentException("Repository configuration file must have name '" + SOURCE_REPOSITORY_CONFIG_FILE_NAME_WITHOUT_EXT
						+ "', the file final name is invalid in path '" + aRepositoryConfigurationFile + "'.");
			final Path locRepositoryConfigurationPath = AIsArtifactModelUtils.resolveConfigFilePath(
					SOURCE_REPOSITORY_CONFIG_FILE_NAME_WITHOUT_EXT,
					AInConfigurationFileKind.getDefaultKind().getDefaultExtension(),
					aRepositoryConfigurationFile.getParent(),
					aPath -> {
					});
			if (locRepositoryConfigurationPath != null) {
				AIcCommonHolderDefDTO locRepositoryDefDTO = loadDef(locRepositoryConfigurationPath);
				locSourceRepository = initSourceRepository(
						locArtifactsByIdMap,
						locRepositoryDefDTO,
						locRepositoryConfigurationPath,
						aRootAggregatorOrSingleProductArtifactConfigurationFileSet == null);
				/* Remember the artifacts for final dependency resolution: */
				locArtifactsToFinalize.putAll(locSourceRepository.getContainedArtifacts());
			}
			else {
				throw new IllegalArgumentException(
						"Repository configuration file '" + aRepositoryConfigurationFile + "' could not be used for loading of repository.");
			}
		}
		if (locSourceRepository != null && locSourceRepository.getVersionContext() != null) {
			/* Set the repository version as a context for the whole evaluation, which in the next phases happens: */
			versionContextDeque.get().push(locSourceRepository.getVersionContext());
		}
		try {
			/* Load the artifacts: */
			for (Path locArtifactPath : aRootAggregatorOrSingleProductArtifactConfigurationFileSet) {
				AIcCommonHolderDefDTO locRootArtifactDefDTO = loadDef(locArtifactPath);
				initArtifactDef(
						locArtifactsByIdMap, locRootArtifactDefDTO, locArtifactPath,
						true, null, true);
				AIiAbstractControlledArtifact locRootArtifact
						= (AIiAbstractControlledArtifact) locArtifactsByIdMap.get(
						locRootArtifactDefDTO.getArtifactDefDTO().getCoordinateId()).getUnspecifiedVersionContainer().getArtifact();
				/* cover single core artifacts: */
				if (locRootArtifact.getArtifactKind().isCoreFunctionalityArtifact()
						&& ((AIiAbstractControlledCoreArtifact) locRootArtifact).isSingleModeCoreArtifact()) {
					locRootArtifact
							= (AIiControlledAggregatorArtifact) locArtifactsByIdMap.get(
							resolveArtificialArtifactId(
									locRootArtifact.getCoordinateId(),
									AGGREGATOR)).getUnspecifiedVersionContainer().getArtifact();
				}

				if (locRootArtifact instanceof AIiControlledAggregatorArtifact) {
					/* Remember the artifact for final dependency resolution: */
					AIiAbstractControlledArtifact locAlreadyExistingArtifact
							= locArtifactsToFinalize.get(locArtifactPath);
					if (locAlreadyExistingArtifact == null)
						locArtifactsToFinalize.put(new AIcContainedArtifactLocalKey(locArtifactPath, locRootArtifact.getArtifactId()), locRootArtifact);
					else if (!locAlreadyExistingArtifact.equals(locRootArtifact)) {
						throw new IllegalStateException("\\\\ Development error - Artifact resolved twice from the same file '" + locArtifactPath
								+ "', but with different result!"
								+ "\nAlready loaded artifact: " + locAlreadyExistingArtifact
								+ "\nNewly loaded artifact: " + locRootArtifact);
					}
				}
				else
					throw new IllegalStateException(
							"\\\\ Development error: Artifact '" + locRootArtifactDefDTO.getArtifactDefDTO().getCoordinateId() + "' from file '"
									+ aRootAggregatorOrSingleProductArtifactConfigurationFileSet
									+ "' is not an aggregator artifact as well as not single core artifact.");

			}

			final Collection<AIiAbstractControlledArtifact> locFinalArtifactList = locArtifactsToFinalize.values();
			resolveArtifactReferences(locArtifactsByIdMap, locFinalArtifactList);
			final AIcArtifactModel locResult
					= new AIcArtifactModel(
					locSourceRepository,
					locArtifactsByIdMap.entrySet().stream().map(
									(Function<Map.Entry<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>>, Map.Entry<String, AIiAbstractArtifact>>)
											aLoaderEntry -> new AbstractMap.SimpleEntry<>(
													aLoaderEntry.getKey(), aLoaderEntry.getValue().getUnspecifiedVersionContainer().getArtifact()))
							.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
			return locResult;
		}
		finally {
			if (locSourceRepository != null && locSourceRepository.getVersionContext() != null)
				versionContextDeque.get().pop();
		}
	}

	private static AIiSourceRepository initSourceRepository(
			final LinkedHashMap<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> aArtifactsByIdMap, final AIcCommonHolderDefDTO aRepositoryDefDTO,
			final Path aRepositoryConfigurationFile, final boolean aLoadRepositoryArtifacts) throws IOException {
		AIcSourceRepository locResult = new AIcSourceRepository();
		locResult.setRepositoryConfigurationFile(aRepositoryConfigurationFile);
		AIcSourceRepositoryDefDTO locSourceRepositoryDTO = aRepositoryDefDTO.getSourceRepositoryDefDTO();
		locResult.setId(locSourceRepositoryDTO.getRepositoryId());
		locResult.setName(locSourceRepositoryDTO.getDisplayName());
		locResult.setDescription(locSourceRepositoryDTO.getDescription());
		locResult.setVersionContext(resolveVersionContext(
				aRepositoryDefDTO.getVersionContextDefDTO(),
				"repoisitory " + locSourceRepositoryDTO.getRepositoryId()));
		if (locResult.getVersionContext() != null) {
			/* Set the repository version as a context for the whole evaluation, which in the next phases happens: */
			versionContextDeque.get().push(locResult.getVersionContext());
		}
		try {
			if (aLoadRepositoryArtifacts) {
				/* Load all aggregator artifacts and create shells for them: */
				AIcArtifactContainerDefDTO locArtifactContainerDefDTO = aRepositoryDefDTO.getSourceRepositoryDefDTO();
				loadContainedArtifacts(
						aArtifactsByIdMap,
						locResult, aRepositoryDefDTO.getSourceRepositoryDefDTO(), aRepositoryDefDTO,
						SOURCE_REPOSITORY_CONFIG_FILE_NAME_WITHOUT_EXT, aRepositoryConfigurationFile,
						aKey -> {
							if (aKey == null || locResult.getContainedArtifacts().containsKey(aKey))
								throw new IllegalArgumentException("Repository '" + locResult.getId() + "' in file '"
										+ aRepositoryConfigurationFile + "' contains " + (aKey == null ? "invalid/non-existent" : "duplicate")
										+ " artifact path '"
										+ aKey + "'");
						}
				);

			}
			return locResult;
		}
		finally {
			if (locResult.getVersionContext() != null)
				versionContextDeque.get().pop();
		}
	}

	private static Map<DeserializationFeature, Boolean> getDefaultDeserializationFeatures() {

		Map<DeserializationFeature, Boolean> locResult = new HashMap<>();
		locResult.put(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		return locResult;
  }

	private static AIcCommonHolderDefDTO loadDef(
			Path aDefConfigurationFile) throws IOException {
		try (InputStream locInputStream = Files.newInputStream(aDefConfigurationFile)) {
			/*Jackson 3: mappers are immutable; use the builder to configure features */
			MapperBuilder locMapperBuilder;
			switch (AInConfigurationFileKind.fromPath(aDefConfigurationFile)) {
			case YAML:
				locMapperBuilder = YAMLMapper.builder(new YAMLFactory());
				break;
			case JSON:
				locMapperBuilder = JsonMapper.builder(new JsonFactory());
				break;
			default:
				throw new IllegalArgumentException("Unrecognized artifact source: " + aDefConfigurationFile);
			}
			/* Configure the mapper */
			Map<DeserializationFeature, Boolean> locDeserializationFeatures = getDefaultDeserializationFeatures();
			for (DeserializationFeature locFeature : locDeserializationFeatures.keySet()) {
				if (locDeserializationFeatures.get(locFeature))
					locMapperBuilder.enable(locFeature);
				else
					locMapperBuilder.disable(locFeature);
			}
			ObjectMapper locMapper = locMapperBuilder.build();
			AIcCommonHolderDefDTO locLoadedDefinition = locMapper.readValue(locInputStream, AIcCommonHolderDefDTO.class);
			return locLoadedDefinition;
		}
	}

	private static void initArtifactDef(
			LinkedHashMap<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> aArtifactsByIdMap,
			AIcCommonHolderDefDTO aDefHolder,
			Path aConfigurationFile,
			final boolean aFirstRunAllowingSingleCoreArtifactInitialization,
			final AInArtifactKind aInitializingArtificialArtifactOfKind,
			final boolean aArtifactPrimaryRegistration)
			throws IOException {
		if (aDefHolder == null || aDefHolder.getArtifactDefDTO() == null) {
			return;
		}

		if (aFirstRunAllowingSingleCoreArtifactInitialization
				&& aInitializingArtificialArtifactOfKind == null
				&& (!aDefHolder.getArtifactDefDTO().getKind().isCoreFunctionalityArtifact()
				    || aDefHolder.getArtifactDefDTO().getKind() != AGGREGATOR)) {
			throw new IllegalStateException("Cannot loader artifact '" + aDefHolder.getArtifactDefDTO().getCoordinateId()
					+ "' in file '" + aConfigurationFile + "' if it is not an aggregator or core artifact in single artifact mode.");
		}

		/* 0) Initialize version context */
		boolean locArtifactWithNewVersionContextPushed = false;
		if (aDefHolder.getVersionContextDefDTO() == null) {
			if (versionContextDeque.get().peek() == null)
				throw new IllegalArgumentException(
						"Cannot load artifact '" + aDefHolder.getArtifactDefDTO().getCoordinateId() + "' in file '" + aConfigurationFile
								+ "' if no version context is defined. The version context can be defined:\n"
								+ "1. on the repository level\n"
								+ "2. in the aggregator artifact\n"
								+ "3. In the single core artifact\n"
								+ "but never inside of the non-aggregator artifact contained within some existing aggregator artifact.");
		} else {
			if (aFirstRunAllowingSingleCoreArtifactInitialization
					&& aDefHolder.getArtifactDefDTO().getKind().isCoreFunctionalityArtifact()
					&& aInitializingArtificialArtifactOfKind == null
					|| aDefHolder.getArtifactDefDTO().getKind() == AGGREGATOR) {
				versionContextDeque.get().push(
						resolveVersionContext(aDefHolder.getVersionContextDefDTO(), "artifact " + aDefHolder.getArtifactDefDTO().getCoordinateId()));
				locArtifactWithNewVersionContextPushed = true;
			}
		}
		try {
			/* 1) Instantiate artifact (without links) */
			initializeControlledArtifactShell(aDefHolder.getArtifactDefDTO(), aArtifactsByIdMap, aInitializingArtificialArtifactOfKind,
					aArtifactPrimaryRegistration, () -> "Loading of the artifact definition for coordinateId '"
							+ aDefHolder.getArtifactDefDTO().getCoordinateId() + "' from the file '" + aConfigurationFile + "'");
			AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact> locControlledArtifactContainer =
					aInitializingArtificialArtifactOfKind == null
							? aArtifactsByIdMap.get(aDefHolder.getArtifactDefDTO().getCoordinateId())
					    : aArtifactsByIdMap.get(resolveArtificialArtifactId(aDefHolder.getArtifactDefDTO().getCoordinateId(),
									aInitializingArtificialArtifactOfKind));
			if (aDefHolder.getArtifactDefDTO().getKind() == AGGREGATOR) {
				/* Perform Aggregator actions, which mainly represent the loading of the contained artifacts to its list.
				 * 1. In the case of the non-artificial artifact include also:
				 *    - the loading of all included artifacts from the configuration files all possible contained artifacts within the contained aggregators
				 * 2. In the case of artificial aggregator, the aggregator:
				 *    - finds the artificial policy and the original core artifact and include them into the lontained artifacts  */
				AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>
						locAggregatorArtifactContainer = aArtifactsByIdMap.get(aDefHolder.getArtifactDefDTO().getCoordinateId());
				AIiAbstractArtifactContainer locArtificialAggregatorArtifact
						= (AIiControlledAggregatorArtifact) locAggregatorArtifactContainer.getUnspecifiedVersionContainer().getArtifact();
				if (aInitializingArtificialArtifactOfKind != null) {
					if (aInitializingArtificialArtifactOfKind != AGGREGATOR)
						throw new IllegalStateException("\\\\ Development error - artificial aggregator has invalid kind '" + aInitializingArtificialArtifactOfKind
								+ " for single artifact '" + aDefHolder.getArtifactDefDTO().getCoordinateId());
					AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact> locContainerWithArtifactToContain
							= aArtifactsByIdMap.get(resolveArtificialArtifactId(aDefHolder.getArtifactDefDTO().getCoordinateId(),
							DEPENDENCY_DEFINITION_POLICY));
					AIiAbstractArtifact locArtificialPolicyArtifact
							= locContainerWithArtifactToContain.getUnspecifiedVersionContainer().getArtifact();
					/* First, find and include the artificial policy: */
					if (locArtificialPolicyArtifact instanceof AIiAbstractControlledArtifact locContainedArtificialPolicyArtifact) {
						locArtificialAggregatorArtifact.getContainedArtifacts().put(
								new AIcContainedArtifactLocalKey(locContainedArtificialPolicyArtifact.getArtifactConfigurationFile(),
										locContainedArtificialPolicyArtifact.getArtifactId()),
								locContainedArtificialPolicyArtifact);
					} else {
						throw new IllegalStateException("\\\\Development error: Artificial policy artifact '" + resolveArtificialArtifactId(aDefHolder.getArtifactDefDTO().getCoordinateId(),
								DEPENDENCY_DEFINITION_POLICY) + "' not found for core artifact '"
								+ aDefHolder.getArtifactDefDTO().getCoordinateId() + "' not found for artificial aggregator '" + locAggregatorArtifactContainer.getCoordinateId() + "'.");
					}
					/* And then find and include the original core artifact only: */
					locContainerWithArtifactToContain
							= aArtifactsByIdMap.get(aDefHolder.getArtifactDefDTO().getCoordinateId());
					if (locContainerWithArtifactToContain instanceof AIiAbstractControlledArtifact locContainedOriginalSingleCoreArtifact) {
						locAggregatorArtifactContainer.getContainedArtifacts().put(locContainedOriginalSingleCoreArtifact.getArtifactConfigurationFile(), locContainedOriginalSingleCoreArtifact);
					} else {
						throw new IllegalStateException("\\\\Development error: Original single core artifact '"
								+ aDefHolder.getArtifactDefDTO().getCoordinateId() + "' not found for artificial aggregator '" + locAggregatorArtifactContainer.getCoordinateId() + "'.");
					}
				} else {
					/* Load all aggregator artifacts and create shells for them: */
					loadContainedArtifacts(
							aArtifactsByIdMap,
							locAggregatorArtifactContainer, aDefHolder.getArtifactDefDTO(), aDefHolder,
							ARTIFACT_CONFIG_FILE_NAME_WITHOUT_EXT, aConfigurationFile,
							aPath -> {
								if (aPath == null || locAggregatorArtifactContainer.getContainedArtifacts().containsKey(aPath))
									throw new IllegalArgumentException("Aggregator '" + aDefHolder.getArtifactDefDTO().getCoordinateId() + "' in file '"
											+ aConfigurationFile + "' contains " + (aPath == null ? "invalid/non-existent" : "duplicate") + " artifact path '"
											+ aPath + "'");
							}
					);
				}
			}
			/* finalize the basic artifact initialization: */
			((AIiAbstractControlledArtifact)locControlledArtifactContainer.getUnspecifiedVersionContainer().getArtifact()).setArtifactConfigurationFile(aConfigurationFile);
			locControlledArtifactContainer.setVersionContext(versionContextDeque.get().peek());
			if (aFirstRunAllowingSingleCoreArtifactInitialization
					&& aDefHolder.getArtifactDefDTO().getKind() != null
					&& aDefHolder.getArtifactDefDTO().getKind().isCoreFunctionalityArtifact()) {
				/* Create the artificial policy and aggregator artifact for a new core artifact in single-artifact-mode.
				 * Use the same data loaded from the file, so the aggregator and policy can take all the settings
				 * from the single core artifact, only set which artificial artifact kind is being created: */
				initArtifactDef(aArtifactsByIdMap, aDefHolder, aConfigurationFile, false, AGGREGATOR, true);
				initArtifactDef(aArtifactsByIdMap, aDefHolder, aConfigurationFile, false, DEPENDENCY_DEFINITION_POLICY, true);
			}

		} finally {
			if (locArtifactWithNewVersionContextPushed)
				versionContextDeque.get().pop();
		}
	}

	private static void loadContainedArtifacts(
			final LinkedHashMap<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> aArtifactsByIdMap,
			final AIiAbstractArtifactContainer aArtifactContainer, final AIcArtifactContainerDefDTO aArtifactContainerDefDTO, final AIcCommonHolderDefDTO aDefHolder,
			final String aConfigFileNameWithoutExtension, final Path aConfigurationFile,
			final Consumer<AIcContainedArtifactLocalKey> aPathChecker) throws IOException {
		for (String locContainedArtifactPathString : aArtifactContainerDefDTO.getContainedArtifactRelativePaths()) {
			Path locContainedArtifactFolder = aConfigurationFile.getParent().resolve(locContainedArtifactPathString);

			AIcCommonHolderDefDTO locArtifactDefDTO = loadDef(
					aConfigurationFile);
			initArtifactDef(
					aArtifactsByIdMap, locArtifactDefDTO, aConfigurationFile,
					false, null, true);
			final AIiAbstractArtifact locCreatedArtifact = aArtifactsByIdMap.get(locArtifactDefDTO.getArtifactDefDTO().getCoordinateId())
					.getUnspecifiedVersionContainer().getArtifact();
			final AIcContainedArtifactLocalKey locContainedArtifactKey = new AIcContainedArtifactLocalKey(AIsArtifactModelUtils.resolveConfigFilePath(
					aConfigFileNameWithoutExtension,
					AInConfigurationFileKind.getDefaultKind().getDefaultExtension(),
					locContainedArtifactFolder,
					aPathChecker), locCreatedArtifact.getArtifactId());
			if (locCreatedArtifact instanceof AIiAbstractControlledArtifact locContainedArtifact) {
				if (aArtifactContainer.getContainedArtifacts().containsKey(locContainedArtifactKey))
					throw new IllegalStateException(
							"Aggregator '" + aDefHolder.getArtifactDefDTO().getCoordinateId() + "' in file '" + locContainedArtifactKey + "'.");
				aArtifactContainer.getContainedArtifacts().put(locContainedArtifactKey, locContainedArtifact);
			}
		}
	}

	private static void initializeControlledArtifactShell(
			final AIcArtifactCoordinateDefDTO aArtifactCoordinateDefDTO,
			final Map<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> byId,
			final AInArtifactKind aInitializingArtificialArtifactOfKind,
			final boolean aArtifactPrimaryRegistration,
			final Supplier<String> aHumanReadableOperationContextInfoSupplier) {

		final String locId =
				aInitializingArtificialArtifactOfKind == null
						? aArtifactCoordinateDefDTO.getCoordinateId()
						: resolveArtificialArtifactId(aArtifactCoordinateDefDTO.getCoordinateId(), aInitializingArtificialArtifactOfKind);

		if (aArtifactPrimaryRegistration) {
			if (aArtifactCoordinateDefDTO instanceof AIcArtifactCoordinateVersionDefDTO)
				throw new IllegalStateException("The primary controlled artifact "
						+ "may not have specified version, this field is for uncontrolled artifacts only. Artifact id: "
						+ locId  + "\nContext Info message: " + aHumanReadableOperationContextInfoSupplier.get());
		}

		AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact> locExistingLoadingContainer = byId.get(locId);

		boolean locPerformRegistration;
		if (locExistingLoadingContainer != null) {
			if(locExistingLoadingContainer.isPrimaryArtifactRegistration()) {
				if (aArtifactPrimaryRegistration)
					throw new IllegalStateException("Duplicate artifact id: " + locId
							+ " (duplicate primary artifact registration)"
							+ "\nContext Info message: " + aHumanReadableOperationContextInfoSupplier.get());
				locPerformRegistration = false;
			} else {
				if (!aArtifactPrimaryRegistration
						&& locExistingLoadingContainer.getArtifactDefDTO().getVersion() != null
						&& aArtifactCoordinateDefDTO.getVersion() != null
						&& !Objects.equals(locExistingLoadingContainer.getArtifactDefDTO().getVersion(),
						                   aArtifactCoordinateDefDTO.getVersion())) {
					throw new IllegalStateException("Version mismatch for artifact id: " + locId
							+ ", only the same version is allowed per multiple artifact reference declaration, but found "
					    + "at least two different: " + locExistingLoadingContainer.getArtifactDefDTO().getVersion()
							+ " and " +	aArtifactCoordinateDefDTO.getVersion()
							+ "\nContext Info message: " + aHumanReadableOperationContextInfoSupplier.get());
				}
				if (!aArtifactPrimaryRegistration
				    && locExistingLoadingContainer.getArtifactDefDTO().getVersion() == null
						&& aArtifactCoordinateDefDTO.getVersion() != null) {
					/* Overwrite the "coordinate-only" artifact without a version currently in map
					 * by the new created with a version specified: */
					locPerformRegistration = true;
				} else
					locPerformRegistration = false;
			}
		} else
			locPerformRegistration = true;

		AIiAbstractArtifact locNewCreatedArtifact = createArtifactShell(aArtifactCoordinateDefDTO, aInitializingArtificialArtifactOfKind);
		if (locPerformRegistration) {
		  byId.put(locId, new AIcArtifactModelLoadingContainer<>(locNewCreatedArtifact, aArtifactCoordinateDefDTO, aArtifactPrimaryRegistration));
		} else {
			validatePrimaryArtifactWithItsReference(locExistingLoadingContainer.getArtifact(), locNewCreatedArtifact);
		}
	}

	private static void resolveArtifactReferences(LinkedHashMap<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> aArtifactsByIdMap,
			Collection<AIiAbstractControlledArtifact> aArtifactsToProcess) {

		/* 2) Resolve cross-references and dependencies */
		for (AIiAbstractArtifact owner : aArtifactsToProcess) {
			AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact> locArtifactContainer = aArtifactsByIdMap.get(owner.getCoordinateId());
			AIcArtifactDefDTO locArtifactDefDTO = locArtifactContainer.getArtifactDefDTO();

			/* parent */
			if (locArtifactDefDTO.getParent() != null) {
				AIiAbstractArtifact parent = requireRef(
						aArtifactsByIdMap,
						locArtifactDefDTO.getParent().getCoordinateId(),
						"parent of " + locArtifactDefDTO.getCoordinateId());
				setParent((AIiAbstractControlledParentRwContainerArtifact) owner, parent);
			}

			if (owner instanceof AIcControlledAggregatorArtifact) {
				/* Already resolved in 1 */
			}

			// dependencies
			setDeps(owner, locArtifactDefDTO, aArtifactsByIdMap);
		}
	}

	private static void setDeps(AIiAbstractArtifact owner, AIcArtifactDefDTO a, Map<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> byId) {
		if (owner instanceof AIcControlledDependencyDefinitionPolicyArtifact) {
			((AIcControlledDependencyDefinitionPolicyArtifact) owner).setDirectDependencies(resolveDeps(a.getDirectDependencies(), byId));
			((AIcControlledDependencyDefinitionPolicyArtifact) owner).setManagedPolicyBackgroundDependencies(resolveDeps(
					a.getManagedPolicyBackgroundDependencies(),
					byId));
		}

		if (owner instanceof AIcControlledProductCoreArtifact) {
			((AIcControlledProductCoreArtifact) owner).setDirectDependencies(resolveDeps(a.getDirectDependencies(), byId));
		}

		if (owner instanceof AIcControlledPolicyBackgroundBomArtifact) {
			((AIcControlledPolicyBackgroundBomArtifact) owner).setManagedPolicyBackgroundDependencies(resolveDeps(
					a.getManagedPolicyBackgroundDependencies(),
					byId));
		}

		if (owner instanceof AIcControlledProductInterfaceBomArtifact) {
			((AIcControlledProductInterfaceBomArtifact) owner).setManagedInterfaceDependencies(resolveDeps(
					a.getManagedInterfaceDependencies(),
					byId));
		}

		if (owner instanceof AIcControlledProductVariantBomArtifact) {
			((AIcControlledProductVariantBomArtifact) owner).setManagedProductVariantDependencies(resolveDeps(
					a.getManagedProductVariantDependencies(),
					byId));
		}
	}

	@SuppressWarnings("unchecked")
	private static <T extends AIiAbstractArtifact> List<AIiArtifactDependency<? extends T>> resolveDeps(
			List<AIcDependencyDefDTO> deps, Map<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> byId) {

		if (deps == null)
			return null;

		List<AIiArtifactDependency<? extends T>> out = new ArrayList<>();
		for (AIcDependencyDefDTO d : deps) {
			AIiAbstractArtifact target = requireRef(byId, d.getReferencedArtifact(), "dependency.target");
			AIcArtifactDependency<T> dep = new AIcArtifactDependency<>();
			dep.setLinkedArtifact((T) target);
			dep.setDependencyScope(resolveScope(d.getScope()));

			if (d.getExclusions() != null) {
				for (AIcExclusionDefDTO ex : d.getExclusions()) {
					AIiAbstractArtifact exTarget = requireRef(byId, ex.getTargetId(), "dependency.exclusions.target");
					AIcArtifactOutput<AIiAbstractArtifact> proj = new AIcArtifactOutput<>();
					proj.setLinkedArtifact(exTarget);
					proj.setOutputClassifier(ex.getOutputClassifier());
					proj.setOutputPackagingId(ex.getOutputTypeId());
					dep.addDependencyExclusion(proj);
				}
			}

			out.add(dep);
		}
		return out;
	}

	private static AIcArtifactDependencyScope resolveScope(AIcScopeDefDTO scope) {
		if (scope == null)
			return null;

		AIcDependencyScopeBehavior behavior = new AIcDependencyScopeBehavior(
				Boolean.TRUE.equals(scope.getLocked()),
				Boolean.TRUE.equals(scope.getTransitive())
		);

		return new AIcArtifactDependencyScope(scope.getLevel(), behavior);
	}

	private static AIiAbstractArtifact createArtifactShell(
			AIcArtifactCoordinateVersionDefDTO aArtifactCoordinateDefDTO,
			final AInArtifactKind aInitializingArtificialArtifactOfKind) {

		final String locArtifactId =
				aInitializingArtificialArtifactOfKind == null
						? aArtifactCoordinateDefDTO.getArtifactId()
						  : resolveArtificialArtifactId(aArtifactCoordinateDefDTO.getArtifactId(), aInitializingArtificialArtifactOfKind);
		final String locCoordinateId =
				aInitializingArtificialArtifactOfKind == null
						? aArtifactCoordinateDefDTO.getArtifactId()
						: resolveArtificialArtifactId(aArtifactCoordinateDefDTO.getArtifactId(), aInitializingArtificialArtifactOfKind);

		if (!(aArtifactCoordinateDefDTO instanceof AIcArtifactDefDTO locArtifactDefDTO)) {
			AIcUndefinedArtifact u = new AIcUndefinedArtifact(locCoordinateId, aArtifactCoordinateDefDTO.getGroupId(), locArtifactId);
			u.setArtifactVersion(aArtifactCoordinateDefDTO.getVersion());
			return u;
		}

		AInArtifactKind locArtifactKind =
				aInitializingArtificialArtifactOfKind == null
						? locArtifactDefDTO.getKind()
						: aInitializingArtificialArtifactOfKind;

		final AIiVersionContext locVersionContext = versionContextDeque.get().peek();
		switch (locArtifactKind) {
		case DEPENDENCY_DEFINITION_POLICY: {
			AIcControlledDependencyDefinitionPolicyArtifact p = new AIcControlledDependencyDefinitionPolicyArtifact(locCoordinateId, locArtifactDefDTO.getGroupId(), locArtifactId);
			p.setVersionContext(locVersionContext);

			return p;
		}
		case PRODUCT_CORE: {
			AIcControlledProductCoreArtifact pc
					= new AIcControlledProductCoreArtifact(locCoordinateId, locArtifactDefDTO.getGroupId(), locArtifactId);
			pc.setVersionContext(locVersionContext);
			return pc;
		}
		case PRODUCT_INTERFACE_BOM: {
			AIcControlledProductInterfaceBomArtifact pi
					= new AIcControlledProductInterfaceBomArtifact(locCoordinateId, locArtifactDefDTO.getGroupId(), locArtifactId);
			pi.setVersionContext(locVersionContext);
			return pi;
		}
		case PRODUCT_VARIANT_BOM: {
			AIcControlledProductVariantBomArtifact pv
					= new AIcControlledProductVariantBomArtifact(locCoordinateId, locArtifactDefDTO.getGroupId(), locArtifactId);
			pv.setVersionContext(locVersionContext);
			return pv;
		}
		case AGGREGATOR: {
			AIcControlledAggregatorArtifact ag
					= new AIcControlledAggregatorArtifact(locCoordinateId, locArtifactDefDTO.getGroupId(), locArtifactId);
			ag.setVersionContext(locVersionContext);
			return ag;
		}
		default:
			throw new IllegalArgumentException("Unknown artifact kind: " + locArtifactKind + " for artifact with Id " + locArtifactDefDTO.getCoordinateId());
		}
	}

	private static AIiAbstractArtifact createUncontrolledArtifactShell(AIcArtifactDefDTO a, final AInArtifactKind aInitializingArtificialArtifactOfKind) {
		AInArtifactKind locArtifactKind =
				aInitializingArtificialArtifactOfKind == null
						? a.getKind()
						: aInitializingArtificialArtifactOfKind;
		final String locArtifactId =
				aInitializingArtificialArtifactOfKind == null
						? a.getArtifactId()
						: resolveArtificialArtifactId(a.getArtifactId(), aInitializingArtificialArtifactOfKind);

		// intf

		if (locArtifactKind.isControlled())
			throw new IllegalArgumentException("\\\\Development error: Controlled artifact kind: " + locArtifactKind + " for artifact with Id "
					+ a.getCoordinateId() + "cannot be used for creation of uncontrolled Artifact shell");
		if (locArtifactKind == AInArtifactKind.UNCONTROLLED_CORE || locArtifactKind == AInArtifactKind.UNCONTROLLED_POM) {
			require(a.getVersion(), "artifact.version (uncontrolled)");
			AIcAbstractUncontrolledArtifact u = locArtifactKind == AInArtifactKind.UNCONTROLLED_CORE
					?	new AIcUncontrolledCoreArtifact(a.getCoordinateId(), a.getGroupId(), locArtifactId)
					: new AIcUncontrolledPomArtifact(a.getCoordinateId(), a.getGroupId(), locArtifactId);
			u.setArtifactVersion(a.getVersion());
			return u;
		}
		throw new IllegalArgumentException("Unknown artifact kind: " + locArtifactKind + " for artifact with Id " + a.getCoordinateId());

	}

	private static AIcVersionContext resolveVersionContext(AIcVersionContextDefDTO vc, String artifactId) {
		if (vc == null) {
			// some kinds may not require a version context in early stages
			return null;
		}
		Objects.requireNonNull(vc.getReleaseLine(), "versionContext.releaseLine for " + artifactId);
		Objects.requireNonNull(vc.getQualifierKind(), "versionContext.qualifierKind for " + artifactId);
		Objects.requireNonNull(vc.getQualifierLabel(), "versionContext.qualifierLabel for " + artifactId);

		return new AIcVersionContext(
				new AIcVersionReleaseLine(vc.getReleaseLine()),
				new AIcVersionReleaseLineRevision(vc.getRevision()),
				new AIcVersionQualifier(vc.getQualifierKind(), vc.getQualifierLabel())
		);
	}

	@SuppressWarnings("unchecked")
	private static void setParent(AIiAbstractControlledParentRwContainerArtifact aOwner, AIiAbstractArtifact aParent) {
		aOwner.setParent(aParent);
	}

	private static AIiAbstractArtifact requireRef(Map<String, AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact>> byId, String id, String what) {
		require(id, what);
		AIcArtifactModelLoadingContainer<? extends AIiAbstractArtifact> locAlc = byId.get(id);
		if (locAlc == null) {
			throw new IllegalArgumentException("Unknown artifact reference '" + id + "' (" + what + ")");
		}
		return locAlc.getArtifact();
	}

	private static void require(Object value, String what) {
		if (value == null)
			throw new IllegalArgumentException("Missing required value: " + what);
		if (value instanceof String && ((String) value).trim().isEmpty()) {
			throw new IllegalArgumentException("Missing required value: " + what);
		}
	}

}
