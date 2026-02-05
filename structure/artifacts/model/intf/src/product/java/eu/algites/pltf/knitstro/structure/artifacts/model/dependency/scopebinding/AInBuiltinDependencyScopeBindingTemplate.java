package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.common.AInComponentOriginClass.BUILTIN;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.DEPENDENCY_SCOPE_BINDING_TEMPLATE;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AIsComponentUtils.createBuiltinTemplateUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AIsComponentUtils.parseUid;

import eu.algites.lib.common.enums.uiddata.AIcUidEnumDataRegistry;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIcScopeBinding;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIiTemplateDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIiTemplateDataUidRecord;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AInBuiltinPurpose;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AInBuiltinSourceSetGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: {@link AInBuiltinDependencyScopeBindingTemplate}
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
public enum AInBuiltinDependencyScopeBindingTemplate
        implements
		AIiTemplateDataUidRecord, AIiDependencyScopeBindingTemplateData {
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
    private final List<AIiScopeBinding> scopeBindings;

    AInBuiltinDependencyScopeBindingTemplate(
            final boolean aUsedForManagedDependenciesOnly,
            final String aTemplateCode,
            final List<AIiScopeBinding> aScopeRules) {
        usedForManagedDependenciesOnly = aUsedForManagedDependenciesOnly;
        templateCode = Objects.requireNonNull(aTemplateCode, "aTemplateId must not be null");
        scopeBindings = Objects.requireNonNull(aScopeRules, "aScopeRules must not be null");
			AIcUidEnumDataRegistry.getInstance().registerData(true, true, true, this);
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
    public List<AIiScopeBinding> getBindings() {
        return scopeBindings;
    }

    /**
     * Gets the {@link AInBuiltinDependencyScopeBindingTemplate} by its properties.
     *
     * @param aOrigin the origin class
     * @param aNamespace the namespace
     * @param aTemplateCode the template Code (last UID component)
     * @return the found template or throws an exception if not found
     * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN}
     *                                  or {@link #getByUidOrThrow(String)} throws an exception
     */
    public static AInBuiltinDependencyScopeBindingTemplate getByPropsOrThrow(
            final AInUidEnumDataOrigin aOrigin,
            final String aNamespace,
            final String aTemplateCode) throws IllegalArgumentException {
        if (aOrigin != BUILTIN) {
            throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
                    + aNamespace + "', template-id='" + aTemplateCode + "'");
        }
        return getByUidOrThrow(createBuiltinUid(List.of(aTemplateCode), AIiTemplateDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
    }

	/**
     * Gets the {@link AInBuiltinDependencyScopeBindingTemplate} by its template UID.
     *
     * @param aUid template UID
     * @return the found template or throws an exception if not found
     * @throws IllegalArgumentException if the UID is invalid or not found among enum values
     */
    public static AInBuiltinDependencyScopeBindingTemplate getByUidOrThrow(final String aUid) throws IllegalArgumentException {
        return findByUid(aUid)
                .orElseThrow(() -> new IllegalArgumentException("Illegal templateUid: '" + aUid + "'"));
    }

    /**
     * Finds the given builtin by the builtin-UID.
     *
     * @param aUid UID for which the built-in item has to be searched for
     * @return the found built-in item or empty optional, if no built-in item was found
     */
    public static Optional<AInBuiltinDependencyScopeBindingTemplate> findByUid(final String aUid) {
        if (aUid == null || aUid.isBlank()) {
            return Optional.empty();
        }
        AIiTemplateDataUidRecord locParsedTemplate = (AIiTemplateDataUidRecord) parseUid(DEPENDENCY_SCOPE_BINDING_TEMPLATE, aUid);
        if (locParsedTemplate.origin() != BUILTIN) {
            return Optional.empty();
        }
        return Stream.of(values())
                .filter(locTemplate -> locTemplate.templateCode().equals(locParsedTemplate.templateCode()))
                .findAny();
    }

    /**
     * Returns the last component of the UID (the template id).
     *
     * @return template id
     */
    @Override
    public String templateCode() {
        return templateCode;
    }

	@Override
	public String code() {
		return AIiTemplateDataUidRecord.super.code();
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


    private static List<AIiScopeBinding> createSingleBinding(
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

    private static AIiScopeBinding createRule(
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

        AIcScopeBinding locRule = new AIcScopeBinding();
        locRule.setPurpose(aPurpose);
        locRule.setSourceSet(aSourceSet);
        locRule.setBehavior(locBehavior);
        locRule.setWeight(0);
        locRule.setLocked(Boolean.TRUE);

        return locRule;
    }

	@Override
	public AIiTemplateDataType getDataType() {
		return (AIiTemplateDataType) DEPENDENCY_SCOPE_BINDING_TEMPLATE.getDataType();
	}

	@Override
	public AIiTemplateDataUidRecord getDataRecord() {
		return this;
	}

}
