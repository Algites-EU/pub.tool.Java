package eu.algites.tool.devops.build.model.loader.loading;

import java.util.Collections;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>
 * Title: {@link AItcTemplateLateInitializationMapTest}
 * </p>
 * <p>
 * Description: <br/>
 * Tests for {@code AIcTemplateLateInitializationMap}.
 * <p>
 * The tests verify insertion-order preservation and late initialization semantics.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 8:52
 */
public final class AItcTemplateLateInitializationMapTest {

	@Test
	public void testInsertionOrderIsPreservedWhenValuesInitializedLater() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locMap = new AIcTemplateLateInitializationMap<>();

		locMap.declareKey("A");
		locMap.declareKey("B");
		locMap.declareKey("C");

		locMap.initialize(new AIcTestTemplate("B", Collections.emptyMap()));
		locMap.initialize(new AIcTestTemplate("A", Collections.emptyMap()));

		String[] locKeysInOrder = locMap.viewSlotsByKey().keySet().toArray(new String[0]);
		Assert.assertEquals(locKeysInOrder, new String[] { "A", "B", "C" },
				"Key insertion order must be preserved even if values are initialized later in different order.");
	}

	@Test
	public void testInitializeWithoutDeclareAddsKeyAtThatMoment() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locMap = new AIcTemplateLateInitializationMap<>();

		locMap.declareKey("A");
		locMap.declareKey("B");

		locMap.initialize(new AIcTestTemplate("X", Collections.emptyMap()));

		String[] locKeysInOrder = locMap.viewSlotsByKey().keySet().toArray(new String[0]);
		Assert.assertEquals(locKeysInOrder, new String[] { "A", "B", "X" },
				"If initialize() is called without declareKey(), the key must be inserted at that moment (end of order).");

		Assert.assertTrue(locMap.isInitialized("X"), "The implicitly declared key must be initialized.");
		Assert.assertEquals(locMap.getValueOrThrow("X").getTemplateUid(), "X", "The stored value must be retrievable.");
	}

	@Test
	public void testPutAllCopiesKeysAndInitializedValues() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locSourceMap = new AIcTemplateLateInitializationMap<>();
		locSourceMap.declareKey("A");
		locSourceMap.declareKey("B");
		locSourceMap.declareKey("C");
		locSourceMap.initialize(new AIcTestTemplate("B", Collections.emptyMap()));

		AIcTemplateLateInitializationMap<AIcTestTemplate> locTargetMap = new AIcTemplateLateInitializationMap<>();
		locTargetMap.declareKey("Z");
		locTargetMap.initialize(new AIcTestTemplate("Z", Collections.emptyMap()));

		locTargetMap.putAll(locSourceMap);

		String[] locKeysInOrder = locTargetMap.viewSlotsByKey().keySet().toArray(new String[0]);
		Assert.assertEquals(locKeysInOrder, new String[] { "Z", "A", "B", "C" },
				"putAll must preserve the order of existing target keys and append new keys in the source iteration order.");

		Assert.assertTrue(locTargetMap.isInitialized("B"), "Initialized values from the source map must be copied.");
		Assert.assertEquals(locTargetMap.getValueOrThrow("B").getTemplateUid(), "B", "Copied value must match the source value.");

		// Idempotence: repeating the same putAll must not throw.
		locTargetMap.putAll(locSourceMap);
	}

	@Test
	public void testFindUninitializedSlotsReturnsOnlyMissingValuesInOrder() {
		AIcTemplateLateInitializationMap<AIcTestTemplate> locMap = new AIcTemplateLateInitializationMap<>();
		locMap.declareKey("A");
		locMap.declareKey("B");
		locMap.declareKey("C");
		locMap.initialize(new AIcTestTemplate("B", Collections.emptyMap()));

		String[] locUninitializedKeys = locMap.findKeysWithUninitializedSlots().toArray(new String[0]);
		Assert.assertEquals(locUninitializedKeys, new String[] { "A", "C" },
				"findKeysWithUninitializedSlots must return only keys without values, preserving insertion order.");

		Map<String, AIcLateInitializationSlot<AIcTestTemplate>> locUninitializedSlots = locMap.findUninitializedSlotsByKey();
		String[] locUninitializedSlotKeys = locUninitializedSlots.keySet().toArray(new String[0]);
		Assert.assertEquals(locUninitializedSlotKeys, new String[] { "A", "C" },
				"findUninitializedSlotsByKey must return only uninitialized slots, preserving insertion order.");
		Assert.assertFalse(locUninitializedSlots.get("A").isInitialized(), "Returned slot must be uninitialized.");
		Assert.assertFalse(locUninitializedSlots.get("C").isInitialized(), "Returned slot must be uninitialized.");
	}

}
