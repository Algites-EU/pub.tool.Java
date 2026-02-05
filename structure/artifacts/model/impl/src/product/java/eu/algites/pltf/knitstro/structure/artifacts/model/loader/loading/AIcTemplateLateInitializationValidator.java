package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import eu.algites.tool.devops.build.model.common.template.AIiAbstractTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Title: {@link AIcTemplateLateInitializationValidator}
 * </p>
 * <p>
 * Description: the validation of the addition of the templates,
 *    controlling no cyclic relations can be contained between the templates
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 8:48
 */
class AIcTemplateLateInitializationValidator<T extends AIiAbstractTemplate<T>>
		implements AIiLateInitializationMapInsertValidator<String, T> {

	@Override
	public boolean validateInsert(
			final AIcLateInitializationMap<String, T> aMap,
			final String aKey,
			final AIcLateInitializationSlot<T> aSlot,
			final T aNewValue) {

		AIcLateInitializationSlot<T> locSlot = aMap.getOrDeclareSlot(aKey);
		if (locSlot.isInitialized()) {
			if (!Objects.equals(locSlot.getValueOrThrow(""), aNewValue)) {
				throw new AIxLateInitializationMapException(
						"Template already initialized for key: " + aKey
								+ " with value: " + locSlot.getValueOrThrow("")
								+ " cannot overwrite with new value: " + aNewValue
				);
			}
			return false;
		}

		if (aNewValue == null) {
			return false;
		}

		/*
		 * Validate the template referenced by the currently inserted template does not
		 * reference the currently inserted template back (directly or transitively),
		 * otherwise it would be a cyclic reference.
		 */
		Map<String, ?> locIncludedTemplates = aNewValue.getIncludedTemplates();
		if (locIncludedTemplates == null || locIncludedTemplates.isEmpty()) {
			return true;
		}

		for (String locTemplateUid : locIncludedTemplates.keySet()) {
			if (locTemplateUid == null) {
				continue;
			}

			if (Objects.equals(locTemplateUid, aKey)) {
				throw new AIxLateInitializationMapException(
						"Cyclic template reference detected: template '" + aKey
								+ "' includes itself directly ('" + locTemplateUid + "')."
				);
			}

			/*
			 * We only validate against templates already initialized in the map.
			 * If the included template is not initialized yet, we cannot prove or disprove a cycle here.
			 */
			if (!aMap.isInitialized(locTemplateUid)) {
				continue;
			}

			java.util.ArrayDeque<String> locPath = new java.util.ArrayDeque<>();
			java.util.HashSet<String> locVisited = new java.util.HashSet<>();
			java.util.HashSet<String> locVisiting = new java.util.HashSet<>();

			boolean locCycleFound = findPathToTargetTemplateUid(
					aMap,
					locTemplateUid,
					aKey,
					locVisited,
					locVisiting,
					locPath
			);

			if (locCycleFound) {
				String locRenderedPath = renderCyclePath(aKey, locPath);
				throw new AIxLateInitializationMapException(
						"Cyclic template reference detected while inserting template '" + aKey
								+ "'. Cycle: " + locRenderedPath
				);
			}
		}
		return true;
	}

	/**
	 * Performs a DFS from {@code aCurrentTemplateUid} over already initialized templates in {@code aMap} and checks whether
	 * {@code aTargetTemplateUid} is reachable.
	 * <p>
	 * If reachable, {@code aPath} will contain the path from {@code aCurrentTemplateUid} to {@code aTargetTemplateUid} (inclusive).
	 */
	private boolean findPathToTargetTemplateUid(
			final AIcLateInitializationMap<String, T> aMap,
			final String aCurrentTemplateUid,
			final String aTargetTemplateUid,
			final java.util.Set<String> aVisited,
			final java.util.Set<String> aVisiting,
			final java.util.ArrayDeque<String> aPath) {

		if (Objects.equals(aCurrentTemplateUid, aTargetTemplateUid)) {
			aPath.addLast(aCurrentTemplateUid);
			return true;
		}

		if (aVisited.contains(aCurrentTemplateUid)) {
			return false;
		}

		/*
		 * Guard against existing cycles inside already-defined templates.
		 * If they exist, we do not want infinite recursion here.
		 */
		if (aVisiting.contains(aCurrentTemplateUid)) {
			return false;
		}

		aVisiting.add(aCurrentTemplateUid);
		aPath.addLast(aCurrentTemplateUid);

		Optional<T> locTemplateOptional = aMap.findValue(aCurrentTemplateUid);
		if (locTemplateOptional.isEmpty()) {
			aPath.removeLast();
			aVisiting.remove(aCurrentTemplateUid);
			aVisited.add(aCurrentTemplateUid);
			return false;
		}

		T locTemplate = locTemplateOptional.get();
		Map<String, ?> locIncludedTemplates = locTemplate.getIncludedTemplates();
		if (locIncludedTemplates != null && !locIncludedTemplates.isEmpty()) {
			for (String locNextTemplateUid : locIncludedTemplates.keySet()) {
				if (locNextTemplateUid == null) {
					continue;
				}

				if (!aMap.isInitialized(locNextTemplateUid)) {
					continue;
				}

				boolean locFound = findPathToTargetTemplateUid(
						aMap,
						locNextTemplateUid,
						aTargetTemplateUid,
						aVisited,
						aVisiting,
						aPath
				);

				if (locFound) {
					return true;
				}
			}
		}

		aPath.removeLast();
		aVisiting.remove(aCurrentTemplateUid);
		aVisited.add(aCurrentTemplateUid);
		return false;
	}

	/**
	 * Renders the cycle path in a human readable form: aKey -> ... -> aKey. The {@code aPath} is expected to contain [firstIncluded, ...,
	 * aKey].
	 */
	private String renderCyclePath(
			final String aKey,
			final java.util.ArrayDeque<String> aPath) {

		StringBuilder locBuilder = new StringBuilder();
		locBuilder.append(aKey);

		for (String locItem : aPath) {
			locBuilder.append(" -> ").append(locItem);
		}

		if (aPath.isEmpty() || !Objects.equals(aPath.peekLast(), aKey)) {
			locBuilder.append(" -> ").append(aKey);
		}

		return locBuilder.toString();
	}
}
