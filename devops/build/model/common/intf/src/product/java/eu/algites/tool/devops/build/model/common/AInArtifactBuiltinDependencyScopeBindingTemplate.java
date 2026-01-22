package eu.algites.tool.devops.build.model.common;

import static eu.algites.tool.devops.build.model.common.AInComponentOriginClass.BUILTIN;
import static eu.algites.tool.devops.build.model.common.AInComponentType.DEPENDENCY_SCOPE_RULE_TEMPLATE;
import static eu.algites.tool.devops.build.model.common.AIsComponentUtils.createBuiltinTemplateUid;
import static eu.algites.tool.devops.build.model.common.AIsComponentUtils.parseUid;

import eu.algites.tool.devops.build.model.artifact.AIcArtifactDependencyScopeBinding;
import eu.algites.tool.devops.build.model.artifact.AIcArtifactDependencyScopeRuleBehavior;
import eu.algites.tool.devops.build.model.artifact.AIcArtifactDependencyScopeRuleExportBehavior;
import eu.algites.tool.devops.build.model.artifact.AIcArtifactDependencyScopeRuleImportBehavior;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeBinding;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeBindingsContainer;
import eu.algites.tool.devops.build.model.dependency.AInArtifactDependencyBuiltinSourceSet;
import eu.algites.tool.devops.build.model.dependency.AInArtifactDependencyBuiltinUsageChannel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        implements AIiArtifactDependencyScopeBindingsContainer, AIiArtifactTemplateUidPartsRecord {
    /**
     * Maven: compile scope (transitive) - available on compile and runtime classpaths.
     */
    MAVEN_COMPILE(false, "mavenCompile", createSingleBinding(
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.TEST_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_AND_TEST,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.TEST_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.TEST_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.TEST_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.CLASSPATH_ITEM,
            AInArtifactDependencyBuiltinSourceSet.TEST_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.SOURCE_PROCESSOR,
            AInArtifactDependencyBuiltinSourceSet.MAIN_ONLY,
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
            AInArtifactDependencyBuiltinUsageChannel.SOURCE_PROCESSOR,
            AInArtifactDependencyBuiltinSourceSet.TEST_ONLY,
            true,
            false,
            false,
            false,
            false
    )),


    ;

    private final boolean usedForManagedDependenciesOnly;
    private final String templateId;
    private final List<AIiArtifactDependencyScopeBinding> scopeRules;

    AInArtifactBuiltinDependencyScopeBindingTemplate(
            final boolean aUsedForManagedDependenciesOnly,
            final String aTemplateId,
            final List<AIiArtifactDependencyScopeBinding> aScopeRules) {
        usedForManagedDependenciesOnly = aUsedForManagedDependenciesOnly;
        templateId = Objects.requireNonNull(aTemplateId, "aTemplateId must not be null");
        scopeRules = Objects.requireNonNull(aScopeRules, "aScopeRules must not be null");
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
        return scopeRules;
    }

    /**
     * Gets the {@link AInArtifactBuiltinDependencyScopeBindingTemplate} by its properties.
     *
     * @param aOriginClass the origin class
     * @param aNamespace the namespace
     * @param aTemplateId the template id (last UID component)
     * @return the found template or throws an exception if not found
     * @throws IllegalArgumentException if origin class is not {@link AInComponentOriginClass#BUILTIN}
     *                                  or {@link #getByUidOrThrow(String)} throws an exception
     */
    public static AIiArtifactTemplateUidPartsRecord getByPropsOrThrow(
            final AInComponentOriginClass aOriginClass,
            final String aNamespace,
            final String aTemplateId) throws IllegalArgumentException {
        if (aOriginClass != BUILTIN) {
            throw new IllegalArgumentException("Unsupported originClass: '" + aOriginClass + "' for parameters namespace='"
                    + aNamespace + "', template-id='" + aTemplateId + "'");
        }
        return getByUidOrThrow(createBuiltinTemplateUid(aTemplateId));
    }

    /**
     * Gets the {@link AInArtifactBuiltinDependencyScopeBindingTemplate} by its template UID.
     *
     * @param aTemplateUid template UID
     * @return the found template or throws an exception if not found
     * @throws IllegalArgumentException if the UID is invalid or not found among enum values
     */
    public static AIiArtifactTemplateUidPartsRecord getByUidOrThrow(final String aTemplateUid) throws IllegalArgumentException {
        return findByUid(aTemplateUid)
                .orElseThrow(() -> new IllegalArgumentException("Illegal templateUid: '" + aTemplateUid + "'"));
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
        AIiArtifactTemplateUidPartsRecord locParsedTemplate = parseUid(DEPENDENCY_SCOPE_RULE_TEMPLATE, aUid);
        if (locParsedTemplate.originClass() != BUILTIN) {
            return Optional.empty();
        }
        return Stream.of(values())
                .filter(locTemplate -> locTemplate.templateId().equals(locParsedTemplate.templateId()))
                .findAny();
    }

    /**
     * Returns the last component of the UID (the template id).
     *
     * @return template id
     */
    @Override
    public String templateId() {
        return templateId;
    }

    @Override
    public AInComponentOriginClass originClass() {
        return BUILTIN;
    }

    @Override
    public String namespace() {
        return "";
    }

    private static List<AIiArtifactDependencyScopeBinding> createSingleBinding(
            final AInArtifactDependencyBuiltinUsageChannel aUsage,
            final AInArtifactDependencyBuiltinSourceSet aSourceSet,
            final boolean aImportUseForCompile,
            final boolean aImportUseForRuntime,
            final boolean aExportUseForCompile,
            final boolean aExportUseForRuntime,
            final boolean aExportUseForManagement) {
        return Collections.unmodifiableList(Arrays.asList(createRule(
                aUsage,
                aSourceSet,
                aImportUseForCompile,
                aImportUseForRuntime,
                aExportUseForCompile,
                aExportUseForRuntime,
                aExportUseForManagement
        )));
    }

    private static AIiArtifactDependencyScopeBinding createRule(
            final AInArtifactDependencyBuiltinUsageChannel aUsage,
            final AInArtifactDependencyBuiltinSourceSet aSourceSet,
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
        locRule.setUsage(aUsage);
        locRule.setSourceSet(aSourceSet);
        locRule.setBehavior(locBehavior);
        locRule.setWeight(0);
        locRule.setLocked(Boolean.TRUE);

        return locRule;
    }
}
