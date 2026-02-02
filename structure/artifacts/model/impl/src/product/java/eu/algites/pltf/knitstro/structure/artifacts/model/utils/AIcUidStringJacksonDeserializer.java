package eu.algites.pltf.knitstro.structure.artifacts.model.utils;

/**
 * <p>
 * Title: {@link AIcUidStringJacksonDeserializer}
 * </p>
 * <p>
 * Description: TODO: Add description
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 25.01.26 11:14
 */


import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.util.function.Function;

/**
 * <p>
 * Generic Jackson 3 deserializer for values represented by a single UID string in JSON/YAML.
 * </p>
 *
 * @param <T> target type
 * @author linhart1
 */
public final class AIcUidStringJacksonDeserializer<T> extends StdDeserializer<T> {

	private final Function<String, T> uidToValueFactory;

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 *
	 * @param aTargetType target type class
	 * @param aUidToValueFactory factory converting UID string to target value
	 */
	public AIcUidStringJacksonDeserializer(Class<T> aTargetType, Function<String, T> aUidToValueFactory) {
		super(aTargetType);
		uidToValueFactory = aUidToValueFactory;
	}

	@Override
	public T deserialize(JsonParser aJsonParser, DeserializationContext aContext) {
		JsonToken locToken = aJsonParser.currentToken();
		if (locToken == null) {
			locToken = aJsonParser.nextToken();
		}

		String locUid = extractUidOrThrow(aJsonParser, locToken);

		try {
			return uidToValueFactory.apply(locUid);
		} catch (RuntimeException locException) {
			throw new IllegalStateException("Unable to deserialize UID value: " + locUid, locException);
		}
	}

	private static String extractUidOrThrow(JsonParser aJsonParser, JsonToken aToken) throws IllegalStateException {
		if (aToken == null) {
			throw new IllegalStateException("UID value is missing (no token).");
		}

		if (aToken == JsonToken.VALUE_STRING) {
			String locUid = aJsonParser.getString();
			if (locUid == null || locUid.isBlank()) {
				throw new IllegalStateException("UID value is empty.");
			}
			return locUid;
		}

		if (aToken == JsonToken.VALUE_NULL) {
			throw new IllegalStateException("UID value is null.");
		}

		if (aToken == JsonToken.START_OBJECT) {
			return extractUidFromObjectOrThrow(aJsonParser);
		}

		throw new IllegalStateException("Unsupported UID representation. Expected a string (or an object with 'uid' property). Token: " + aToken);
	}

	private static String extractUidFromObjectOrThrow(JsonParser aJsonParser) throws IllegalStateException {
		String locUid = null;

		while (aJsonParser.nextToken() != JsonToken.END_OBJECT) {
			JsonToken locToken = aJsonParser.currentToken();
			if (locToken == JsonToken.PROPERTY_NAME) {
				String locFieldName = aJsonParser.currentName();
				JsonToken locValueToken = aJsonParser.nextToken();

				if ("uid".equals(locFieldName)) {
					if (locValueToken != JsonToken.VALUE_STRING) {
						throw new IllegalStateException("Property 'uid' must be a string.");
					}
					locUid = aJsonParser.getString();
				} else {
					aJsonParser.skipChildren();
				}
			}
		}

		if (locUid == null || locUid.isBlank()) {
			throw new IllegalStateException("UID value is missing/empty in object representation.");
		}

		return locUid;
	}
}
