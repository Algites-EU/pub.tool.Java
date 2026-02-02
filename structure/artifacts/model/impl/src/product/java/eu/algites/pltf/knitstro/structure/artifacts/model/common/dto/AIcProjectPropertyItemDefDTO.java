package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * Title: {@link AIcProjectPropertyItemDefDTO}
 * </p>
 * <p>
 * Description: <br/>
 * A single project property definition list item.
 * <p>
 * Expected YAML form:
 * <pre>
 * - someKey: someValue
 * </pre>
 * Exactly one key-value pair is allowed per list item.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 12:44
 */
public class AIcProjectPropertyItemDefDTO {

	@JsonIgnore
	private String key;

	@JsonIgnore
	private Object value;

	@JsonAnySetter
	public void put(final String aKey, final Object aValue) {
		Objects.requireNonNull(aKey, "aKey must not be null");

		if (key != null && !Objects.equals(key, aKey)) {
			throw new IllegalArgumentException(
					"Each propertyDefinitions item must contain exactly one key, but multiple keys were provided. "
							+ "Existing key: " + key + ", new key: " + aKey
			);
		}

		key = aKey;
		value = aValue;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	@JsonAnyGetter
	public Map<String, Object> toYamlMap() {
		if (key == null) {
			return Collections.emptyMap();
		}
		Map<String, Object> locMap = new LinkedHashMap<>();
		locMap.put(key, value);
		return locMap;
	}
}