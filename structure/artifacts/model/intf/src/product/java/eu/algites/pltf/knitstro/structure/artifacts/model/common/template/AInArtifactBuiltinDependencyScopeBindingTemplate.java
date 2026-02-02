package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.common.AInComponentOriginClass.BUILTIN;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_SCOPE_BINDING_TEMPLATE;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AIsComponentUtils.createBuiltinTemplateUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AIsComponentUtils.parseUid;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIcArtifactDependencyScopeBinding;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIcArtifactDependencyScopeRuleBehavior;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIcArtifactDependencyScopeRuleExportBehavior;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIcArtifactDependencyScopeRuleImportBehavior;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyScopeBinding;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AInBuiltinPurpose;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AInBuiltinSourceSetGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: {@link AInArtifactBuiltinDependencyScopeBindingTemplate}
 * </p>
 * <p>
 * Description: Defines the possible scopes of the dependencies.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 07.01.26 16:02
 */
public enum AInArtifactBuiltinDependencyScopeBindingTemplate
        implements
		AIiArtifactTemplateDataUidRecord, AIiArtifactDependencyScopeBindingTemplateData {
    /**
     * Maven: compile scope (transitive) - available on compile and runtime classpaths.
     */
    MAVEN_COMPILE(false, "mavenCompile", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            true,
            true,
            true,
            true,
            false
    )),

    /**
     * Maven: provided scope (non-transitive) - available only at compile time of the current module.
     */
    MAVEN_PROVIDED(false, "mavenProvided", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            true,
            false,
            false,
            false,
            false
    )),

    /**
     * Maven: runtime scope (transitive) - available only on runtime classpath.
     */
    MAVEN_RUNTIME(false, "mavenRuntime", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            false,
            true,
            false,
            true,
            false
    )),

    /**
     * Maven: test scope (non-transitive) - available only for tests.
     */
    MAVEN_TEST(false, "mavenTest", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.TEST_ONLY,
            true,
            true,
            false,
            false,
            false
    )),

    /**
     * Maven: import scope (managed dependencies only) - used only for dependency management.
     */
    MAVEN_IMPORT(true, "mavenImport", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_AND_TEST,
            false,
            false,
            false,
            false,
            true
    )),

    /**
     * Gradle: api (transitive, exposed) - available on compile and runtime classpaths and exported to consumers.
     */
    GRADLE_API(false, "gradleApi", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            true,
            true,
            true,
            true,
            false
    )),

    /**
     * Gradle: implementation (transitive, not exposed) - available on compile and runtime classpaths, not exported.
     */
    GRADLE_IMPLEMENTATION(false, "gradleImplementation", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            true,
            true,
            false,
            false,
            false
    )),

    /**
     * Gradle: compileOnly (not exposed) - available only on compile classpath.
     */
    GRADLE_COMPILE_ONLY(false, "gradleCompileOnly", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            true,
            false,
            false,
            false,
            false
    )),

    /**
     * Gradle: runtimeOnly (not exposed) - available only on runtime classpath.
     */
    GRADLE_RUNTIME_ONLY(false, "gradleRuntimeOnly", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            false,
            true,
            false,
            false,
            false
    )),

    /**
     * Gradle: testApi - available for test compile and runtime.
     */
    GRADLE_TEST_API(false, "gradleTestApi", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.TEST_ONLY,
            true,
            true,
            false,
            false,
            false
    )),

    /**
     * Gradle: testImplementation - available for test compile and runtime.
     */
    GRADLE_TEST_IMPLEMENTATION(false, "gradleTestImplementation", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.TEST_ONLY,
            true,
            true,
            false,
            false,
            false
    )),

    /**
     * Gradle: testCompileOnly - available only on test compile classpath.
     */
    GRADLE_TEST_COMPILE_ONLY(false, "gradleTestCompileOnly", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.TEST_ONLY,
            true,
            false,
            false,
            false,
            false
    )),

    /**
     * Gradle: testRuntimeOnly - available only on test runtime classpath.
     */
    GRADLE_TEST_RUNTIME_ONLY(false, "gradleTestRuntimeOnly", createSingleBinding(
            AInBuiltinPurpose.CLASSPATH_ITEM,
            AInBuiltinSourceSetGroup.TEST_ONLY,
            false,
            true,
            false,
            false,
            false
    )),

    /**
     * Gradle: annotationProcessor - available for main compilation only.
     */
    GRADLE_ANNOTATION_PROCESSOR(false, "gradleAnnotationProcessor", createSingleBinding(
            AInBuiltinPurpose.SOURCE_PROCESSOR,
            AInBuiltinSourceSetGroup.MAIN_ONLY,
            true,
            false,
            false,
            false,
            false
    )),

    /**
     * Gradle: testAnnotationProcessor - available for test compilation only.
     */
    GRADLE_TEST_ANNOTATION_PROCESSOR(false, "gradleTestAnnotationProcessor", createSingleBinding(
            AInBuiltinPurpose.SOURCE_PROCESSOR,
            AInBuiltinSourceSetGroup.TEST_ONLY,
            true,
            false,
            false,
            false,
            false
    )),


    ;

    private final boolean usedForManagedDependenciesOnly;
    private final String templateCode;
    private final List<AIiArtifactDependencyScopeBinding> scopeBindings;

    AInArtifactBuiltinDependencyScopeBindingTemplate(
            final boolean aUsedForManagedDependenciesOnly,
            final String aTemplateCode,
            final List<AIiArtifactDependencyScopeBinding> aScopeRules) {
        usedForManagedDependenciesOnly = aUsedForManagedDependenciesOnly;
        templateCode = Objects.requireNonNull(aTemplateCode, "aTemplateId must not be null");
        scopeBindings = Objects.requireNonNull(aScopeRules, "aScopeRules must not be null");
    }

    /**
     * Defines the scope level can be used only in the definition of the managed dependencies, not in direct dependencies.
     *
     * @return the managedOnly flag for the scope level.
     */
    public boolean isUsedForManagedDependenciesOnly() {
        return usedForManagedDependenciesOnly;
    }



    @Override
    public List<AIiArtifactDependencyScopeBinding> getBindings() {
        return scopeBindings;
    }

    /**
     * Gets the {@link AInArtifactBuiltinDependencyScopeBindingTemplate} by its properties.
     *
     * @param aOrigin the origin class
     * @param aNamespace the namespace
     * @param aTemplateCode the renderPattern Code (last UID component)
     * @return the found renderPattern or throws an exception if not found
     * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
     *                                  or {@link #getByUidOrThrow(String)} throws an exception
     */
    public static AInArtifactBuiltinDependencyScopeBindingTemplate getByPropsOrThrow(
            final AInUidEnumDataOrigin aOrigin,
            final String aNamespace,
            final String aTemplateCode) throws IllegalArgumentException {
        if (aOrigin != BUILTIN) {
            throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
                    + aNamespace + "', renderPattern-id='" + aTemplateCode + "'");
        }
        return getByUidOrThrow(createBuiltinUid(List.of(aTemplateCode), AIiArtifactTemplateDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
    }

	/**
     * Gets the {@link AInArtifactBuiltinDependencyScopeBindingTemplate} by its renderPattern UID.
     *
     * @param aUid renderPattern UID
     * @return the found renderPattern or throws an exception if not found
     * @throws IllegalArgumentException if the UID is invalid or not found among enum values
     */
    public static AInArtifactBuiltinDependencyScopeBindingTemplate getByUidOrThrow(final String aUid) throws IllegalArgumentException {
        return findByUid(aUid)
                .orElseThrow(() -> new IllegalArgumentException("Illegal templateUid: '" + aUid + "'"));
    }

    /**
     * Finds the given builtin by the builtin-UID.
     *
     * @param aUid UID for which the built-in item has to be searched for
     * @return the found built-in item or empty optional, if no built-in item was found
     */
    public static Optional<AInArtifactBuiltinDependencyScopeBindingTemplate> findByUid(final String aUid) {
        if (aUid == null || aUid.isBlank()) {
            return Optional.empty();
        }
        AIiArtifactTemplateDataUidRecord locParsedTemplate = (AIiArtifactTemplateDataUidRecord) parseUid(DEPENDENCY_SCOPE_BINDING_TEMPLATE, aUid);
        if (locParsedTemplate.origin() != BUILTIN) {
            return Optional.empty();
        }
        return Stream.of(values())
                .filter(locTemplate -> locTemplate.templateCode().equals(locParsedTemplate.templateCode()))
                .findAny();
    }

    /**
     * Returns the last component of the UID (the renderPattern id).
     *
     * @return renderPattern id
     */
    @Override
    public String templateCode() {
        return templateCode;
    }

	@Override
	public String code() {
		return AIiArtifactTemplateDataUidRecord.super.code();
	}

	@Override
	public String uid() {
		return "";
	}

	@Override
    public AInUidEnumDataOrigin origin() {
        return BUILTIN;
    }

    @Override
    public String namespace() {
        return "";
    }


    private static List<AIiArtifactDependencyScopeBinding> createSingleBinding(
            final AInBuiltinPurpose aPurpose,
            final AInBuiltinSourceSetGroup aSourceSet,
            final boolean aImportUseForCompile,
            final boolean aImportUseForRuntime,
            final boolean aExportUseForCompile,
            final boolean aExportUseForRuntime,
            final boolean aExportUseForManagement) {
        return Collections.unmodifiableList(Arrays.asList(createRule(
                aPurpose,
                aSourceSet,
                aImportUseForCompile,
                aImportUseForRuntime,
                aExportUseForCompile,
                aExportUseForRuntime,
                aExportUseForManagement
        )));
    }

    private static AIiArtifactDependencyScopeBinding createRule(
            final AInBuiltinPurpose aPurpose,
            final AInBuiltinSourceSetGroup aSourceSet,
            final boolean aImportUseForCompile,
            final boolean aImportUseForRuntime,
            final boolean aExportUseForCompile,
            final boolean aExportUseForRuntime,
            final boolean aExportUseForManagement) {

        AIcArtifactDependencyScopeRuleImportBehavior locImportBehavior = new AIcArtifactDependencyScopeRuleImportBehavior();
        locImportBehavior.setUseForCompile(aImportUseForCompile);
        locImportBehavior.setUseForRuntime(aImportUseForRuntime);

        AIcArtifactDependencyScopeRuleExportBehavior locExportBehavior = new AIcArtifactDependencyScopeRuleExportBehavior();
        locExportBehavior.setUseForCompile(aExportUseForCompile);
        locExportBehavior.setUseForRuntime(aExportUseForRuntime);
        locExportBehavior.setUseForManagement(aExportUseForManagement);

        AIcArtifactDependencyScopeRuleBehavior locBehavior = new AIcArtifactDependencyScopeRuleBehavior();
        locBehavior.setImportBehavior(locImportBehavior);
        locBehavior.setExportBehavior(locExportBehavior);

        AIcArtifactDependencyScopeBinding locRule = new AIcArtifactDependencyScopeBinding();
        locRule.setPurpose(aPurpose);
        locRule.setSourceSet(aSourceSet);
        locRule.setBehavior(locBehavior);
        locRule.setWeight(0);
        locRule.setLocked(Boolean.TRUE);

        return locRule;
    }

	@Override
	public AIiArtifactTemplateDataType getDataType() {
		return (AIiArtifactTemplateDataType) DEPENDENCY_SCOPE_BINDING_TEMPLATE.getDataType();
	}

	@Override
	public AIiUidEnumDataRecord getDataRecord() {
		return this;
	}

	@Override
	public Map templateItems() {
		return Map.of();
	}
}
