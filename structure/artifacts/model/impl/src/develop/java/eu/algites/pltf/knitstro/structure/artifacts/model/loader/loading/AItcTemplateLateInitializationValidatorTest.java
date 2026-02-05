package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import eu.algites.tool.devops.build.model.common.template.AIcTemplateLateInitializationValidator;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>
 * Title: {@link AItcTemplateLateInitializationValidatorTest}
 * </p>
 * <p>
 * Description: <br/>
 * Tests for {@code AIcTemplateLateInitializationValidator}.
 * <p>
 * The tests verify that cyclic template references are rejected, including transitive cycles.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 9:09
 */
public final class AItcTemplateLateInitializationValidatorTest {

	@Test
	public void testRejectsDirectSelfInclude() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locMap = new AIcTemplateLateInitializationMap<>();
		AIcTemplateLateInitializationValidator<AIcTestTemplate> locValidator = new AIcTemplateLateInitializationValidator<>();

		Map<String, AIcTestTemplate> locIncludes = new LinkedHashMap<>();
		locIncludes.put("A", new AIcTestTemplate("A", locIncludes));
		AIcTestTemplate locTemplateA = new AIcTestTemplate("A", locIncludes);

		Assert.expectThrows(AIxLateInitializationMapException.class, () -> locValidator.validateInsert(
				locMap,
				"A",
				locMap.getOrDeclareSlot("A"),
				locTemplateA
		));
	}

	@Test
	public void testRejectsTransitiveCycleMultipleElements() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locMap = new AIcTemplateLateInitializationMap<>();
		AIcTemplateLateInitializationValidator<AIcTestTemplate> locValidator = new AIcTemplateLateInitializationValidator<>();

		/*
		 * Already defined templates:
		 *   B -> C
		 *   C -> A
		 * Now inserting:
		 *   A -> B
		 * Cycle: A -> B -> C -> A
		 */
		AIcTestTemplate locTemplateB = new AIcTestTemplate("B", (Map)mapOf("C"));
		AIcTestTemplate locTemplateC = new AIcTestTemplate("C", (Map)mapOf("A"));

		locMap.initialize(locTemplateB);
		locMap.initialize(locTemplateC);

		AIcTestTemplate locTemplateA = new AIcTestTemplate("A", (Map)mapOf("B"));

		Assert.expectThrows(AIxLateInitializationMapException.class, () -> locValidator.validateInsert(
				locMap,
				"A",
				locMap.getOrDeclareSlot("A"),
				locTemplateA
		));
	}

	@Test
	public void testAcceptsNonCyclicReferences() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locMap = new AIcTemplateLateInitializationMap<>();
		AIcTemplateLateInitializationValidator<AIcTestTemplate> locValidator = new AIcTemplateLateInitializationValidator<>();

		/*
		 * Already defined:
		 *   B -> C
		 *   C -> D
		 * Insert:
		 *   A -> B
		 * No path back to A exists.
		 */
		locMap.initialize(new AIcTestTemplate("B", (Map)mapOf("C")));
		locMap.initialize(new AIcTestTemplate("C", (Map)mapOf("D")));
		locMap.initialize(new AIcTestTemplate("D", Collections.emptyMap()));

		AIcTestTemplate locTemplateA = new AIcTestTemplate("A", (Map)mapOf("B"));

		locValidator.validateInsert(
				locMap,
				"A",
				locMap.getOrDeclareSlot("A"),
				locTemplateA
		);
	}

	private static Map<String, Object> mapOf(final String aTemplateUid) {
		Map<String, Object> locMap = new LinkedHashMap<>();
		locMap.put(aTemplateUid, new Object());
		return locMap;
	}

}
