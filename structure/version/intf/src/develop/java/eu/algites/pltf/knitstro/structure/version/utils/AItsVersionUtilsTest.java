package eu.algites.pltf.knitstro.structure.version.utils;

import eu.algites.pltf.knitstro.structure.version.AIiResolvedVersion;
import eu.algites.pltf.knitstro.structure.version.AIiVersionScheme;
import eu.algites.pltf.knitstro.structure.version.AIiVersionStructure;
import eu.algites.pltf.knitstro.structure.version.AIxVersionException;
import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;
import eu.algites.pltf.knitstro.structure.version.component.AInVersionComponentPrecedenceRole;
import eu.algites.pltf.knitstro.structure.version.component.AInVersionComponentValueType;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionFormat;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionParsePattern;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionRenderPattern;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link AIsVersionUtils}.
 *
 * These tests use dynamic proxies for Knitstro interfaces to avoid coupling to concrete implementations.
 */
public class AItsVersionUtilsTest {

	@Test
	public void testParseCanonicalUniqueSuccess() throws Exception {
		AIiVersionStructure locStructure = createSimpleStructure("simple");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure));

		AIiResolvedVersion locResolved = AIsVersionUtils.parseCanonicalUnique(locScheme, "2026.02+123");

		Assert.assertEquals(locResolved.canonicalText(), "2026.02+123");
		Assert.assertEquals(locResolved.structure().code(), "simple");

		Map<String, String> locValues = locResolved.rawValuesByComponentCode();
		Assert.assertEquals(locValues.get("year"), "2026");
		Assert.assertEquals(locValues.get("month"), "02");
		Assert.assertEquals(locValues.get("build"), "123");
	}

	@Test(expectedExceptions = AIxVersionNotRecognizedException.class)
	public void testParseCanonicalUniqueNotRecognized() throws Exception {
		AIiVersionStructure locStructure = createSimpleStructure("simple");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure));

		AIsVersionUtils.parseCanonicalUnique(locScheme, "nope");
	}

	@Test(expectedExceptions = AIxVersionAmbiguousException.class)
	public void testParseCanonicalUniqueAmbiguous() throws Exception {
		AIiVersionStructure locStructure1 = createSimpleStructure("s1");
		AIiVersionStructure locStructure2 = createSimpleStructure("s2");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure1, locStructure2));

		AIsVersionUtils.parseCanonicalUnique(locScheme, "2026.02+123");
	}

	@Test
	public void testNormalizeToCanonicalSuccess() throws Exception {
		AIiVersionStructure locStructure = createSimpleStructure("simple");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure));

		Map<String, String> locRaw = new LinkedHashMap<>();
		locRaw.put("year", "2026");
		locRaw.put("month", "02");
		locRaw.put("build", "123");

		String locCanonical = AIsVersionUtils.normalizeToCanonical(locScheme, "simple", locRaw);

		Assert.assertEquals(locCanonical, "2026.02+123");
	}

	@Test(expectedExceptions = AIxVersionException.class)
	public void testNormalizeToCanonicalUnknownStructure() throws Exception {
		AIiVersionStructure locStructure = createSimpleStructure("simple");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure));

		AIsVersionUtils.normalizeToCanonical(locScheme, "unknown", Map.of("year", "2026"));
	}

	@Test(expectedExceptions = AIxVersionException.class)
	public void testNormalizeToCanonicalMatchesDifferentStructure() throws Exception {
		/*
		 * Structure A renders "X-1", but cannot parse it.
		 * Structure B can parse "X-1".
		 * normalizeToCanonical(A) must fail due to structure mismatch.
		 */
		AIiVersionComponent locCompX = createComponent("x");
		AIiVersionComponent locCompN = createComponent("n");

		AIiVersionFormat locFormatA = createFormat(
				"A",
				createRenderPattern("${x}-${n}"),
				List.of(createParsePattern("(?<x>NOPE)-(?<n>\\d+)"))
		);

		AIiVersionStructure locStructureA = createStructure(
				"A",
				List.of(locCompX, locCompN),
				locFormatA,
				List.of(locFormatA)
		);

		AIiVersionFormat locFormatB = createFormat(
				"B",
				createRenderPattern("${x}-${n}"),
				List.of(createParsePattern("(?<x>[A-Za-z]+)-(?<n>\\d+)"))
		);

		AIiVersionStructure locStructureB = createStructure(
				"B",
				List.of(locCompX, locCompN),
				locFormatB,
				List.of(locFormatB)
		);

		AIiVersionScheme locScheme = createScheme(List.of(locStructureA, locStructureB));

		Map<String, String> locRaw = new LinkedHashMap<>();
		locRaw.put("x", "X");
		locRaw.put("n", "1");

		AIsVersionUtils.normalizeToCanonical(locScheme, "A", locRaw);
	}

	@Test
	public void testRenderOutputSuccess() throws Exception {
		AIiVersionComponent locYear = createComponent("year");
		AIiVersionComponent locMonth = createComponent("month");
		AIiVersionComponent locBuild = createComponent("build");

		AIiVersionFormat locCanonical = createFormat(
				"canonical",
				createRenderPattern("${year}.${month}+${build}"),
				List.of(createParsePattern("(?<year>\\d{4})\\.(?<month>\\d{2})\\+(?<build>\\d+)"))
		);

		AIiVersionFormat locShort = createFormat(
				"short",
				createRenderPattern("${year}.${month}"),
				Collections.emptyList()
		);

		AIiVersionStructure locStructure = createStructure(
				"simple",
				List.of(locYear, locMonth, locBuild),
				locCanonical,
				List.of(locCanonical, locShort)
		);

		AIiVersionScheme locScheme = createScheme(List.of(locStructure));
		AIiResolvedVersion locResolved = AIsVersionUtils.parseCanonicalUnique(locScheme, "2026.02+123");

		String locRendered = AIsVersionUtils.renderOutput(locResolved, "short");
		Assert.assertEquals(locRendered, "2026.02");
	}

	@Test(expectedExceptions = AIxVersionException.class)
	public void testRenderOutputUnknownFormatCode() throws Exception {
		AIiVersionStructure locStructure = createSimpleStructure("simple");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure));
		AIiResolvedVersion locResolved = AIsVersionUtils.parseCanonicalUnique(locScheme, "2026.02+123");

		AIsVersionUtils.renderOutput(locResolved, "missing");
	}

	@Test(expectedExceptions = AIxVersionException.class)
	public void testRenderOutputMissingComponentValue() throws Exception {
		AIiVersionStructure locStructure = createSimpleStructureWithExtraOutputFormat("simple", "full", "${year}.${month}+${build}");
		AIiVersionScheme locScheme = createScheme(List.of(locStructure));

		Map<String, String> locRaw = new LinkedHashMap<>();
		locRaw.put("year", "2026");
		locRaw.put("month", "02");
		// build missing

		AIiResolvedVersion locResolved = createResolved(locScheme, "x", "x", locStructure, locRaw);

		AIsVersionUtils.renderOutput(locResolved, "full");
	}

	@Test(expectedExceptions = AIxVersionException.class)
	public void testRenderOutputReferencesUnknownComponent() throws Exception {
		AIiVersionComponent locYear = createComponent("year");

		AIiVersionFormat locCanonical = createFormat(
				"canonical",
				createRenderPattern("${year}"),
				List.of(createParsePattern("(?<year>\\d{4})"))
		);

		AIiVersionFormat locBad = createFormat(
				"bad",
				createRenderPattern("${unknown}"),
				Collections.emptyList()
		);

		AIiVersionStructure locStructure = createStructure(
				"simple",
				List.of(locYear),
				locCanonical,
				List.of(locCanonical, locBad)
		);

		AIiResolvedVersion locResolved = createResolved(createScheme(List.of(locStructure)), "x", "x", locStructure, Map.of("year", "2026"));
		AIsVersionUtils.renderOutput(locResolved, "bad");
	}

	private static AIiVersionStructure createSimpleStructure(final String aStructureCode) {
		AIiVersionComponent locYear = createComponent("year");
		AIiVersionComponent locMonth = createComponent("month");
		AIiVersionComponent locBuild = createComponent("build");

		AIiVersionFormat locCanonical = createFormat(
				"canonical",
				createRenderPattern("${year}.${month}+${build}"),
				List.of(createParsePattern("(?<year>\\d{4})\\.(?<month>\\d{2})\\+(?<build>\\d+)"))
		);

		return createStructure(
				aStructureCode,
				List.of(locYear, locMonth, locBuild),
				locCanonical,
				List.of(locCanonical)
		);
	}

	private static AIiVersionStructure createSimpleStructureWithExtraOutputFormat(
			final String aStructureCode,
			final String aFormatCode,
			final String aRenderPatternSource) {

		AIiVersionComponent locYear = createComponent("year");
		AIiVersionComponent locMonth = createComponent("month");
		AIiVersionComponent locBuild = createComponent("build");

		AIiVersionFormat locCanonical = createFormat(
				"canonical",
				createRenderPattern("${year}.${month}+${build}"),
				List.of(createParsePattern("(?<year>\\d{4})\\.(?<month>\\d{2})\\+(?<build>\\d+)"))
		);

		AIiVersionFormat locOutput = createFormat(
				aFormatCode,
				createRenderPattern(aRenderPatternSource),
				Collections.emptyList()
		);

		return createStructure(
				aStructureCode,
				List.of(locYear, locMonth, locBuild),
				locCanonical,
				List.of(locCanonical, locOutput)
		);
	}

	private static AIiVersionScheme createScheme(final List<AIiVersionStructure> aStructures) {
		return (AIiVersionScheme) Proxy.newProxyInstance(
				AIiVersionScheme.class.getClassLoader(),
				new Class<?>[]{AIiVersionScheme.class},
				(aProxy, aMethod, aArgs) -> {
					String locName = aMethod.getName();
					if ("structures".equals(locName)) {
						return aStructures;
					}
					if ("findStructureByCode".equals(locName)) {
						String locCode = (String) aArgs[0];
						for (AIiVersionStructure locS : aStructures) {
							if (locS != null && locCode != null && locCode.equals(locS.code())) {
								return Optional.of(locS);
							}
						}
						return Optional.empty();
					}
					if ("transitions".equals(locName)) {
						return Collections.emptyList();
					}
					if ("findTransitionByCode".equals(locName)) {
						return Optional.empty();
					}
					if ("validate".equals(locName)) {
						return null;
					}
					// uiddata / enumdata methods not needed for tests
					if (aMethod.getReturnType().isPrimitive()) {
						if (boolean.class.equals(aMethod.getReturnType())) {
							return false;
						}
						if (int.class.equals(aMethod.getReturnType())) {
							return 0;
						}
						if (long.class.equals(aMethod.getReturnType())) {
							return 0L;
						}
					}
					return null;
				}
		);
	}

	private static AIiVersionStructure createStructure(
			final String aStructureCode,
			final List<AIiVersionComponent> aComponents,
			final AIiVersionFormat aCanonicalFormat,
			final List<AIiVersionFormat> aAllFormats) {

		Map<String, AIiVersionFormat> locFormatByCode = new LinkedHashMap<>();
		for (AIiVersionFormat locF : aAllFormats) {
			locFormatByCode.put(locF.code(), locF);
		}

		return (AIiVersionStructure) Proxy.newProxyInstance(
				AIiVersionStructure.class.getClassLoader(),
				new Class<?>[]{AIiVersionStructure.class},
				(aProxy, aMethod, aArgs) -> {
					String locName = aMethod.getName();
					if ("code".equals(locName)) {
						return aStructureCode;
					}
					if ("components".equals(locName)) {
						return aComponents;
					}
					if ("findComponentByCode".equals(locName)) {
						String locCode = (String) aArgs[0];
						for (AIiVersionComponent locC : aComponents) {
							if (locC != null && locCode != null && locCode.equals(locC.code())) {
								return Optional.of(locC);
							}
						}
						return Optional.empty();
					}
					if ("outputOnlyFormats".equals(locName)) {
						return aAllFormats;
					}
					if ("findFormatByCode".equals(locName)) {
						String locCode = (String) aArgs[0];
						return Optional.ofNullable(locFormatByCode.get(locCode));
					}
					if ("canonicalFormatCode".equals(locName)) {
						return aCanonicalFormat.code();
					}
					if ("canonicalFormat".equals(locName)) {
						return aCanonicalFormat;
					}
					if ("validate".equals(locName)) {
						return null;
					}
					if ("sampleRawValues".equals(locName)) {
						return Collections.emptyMap();
					}

					if (aMethod.getReturnType().isPrimitive()) {
						if (boolean.class.equals(aMethod.getReturnType())) {
							return false;
						}
						if (int.class.equals(aMethod.getReturnType())) {
							return 0;
						}
						if (long.class.equals(aMethod.getReturnType())) {
							return 0L;
						}
					}
					return null;
				}
		);
	}

	private static AIiVersionFormat createFormat(
			final String aCode,
			final AIiVersionRenderPattern aRenderPattern,
			final List<AIiVersionParsePattern> aParsePatterns) {

		return (AIiVersionFormat) Proxy.newProxyInstance(
				AIiVersionFormat.class.getClassLoader(),
				new Class<?>[]{AIiVersionFormat.class},
				(aProxy, aMethod, aArgs) -> {
					String locName = aMethod.getName();
					if ("code".equals(locName)) {
						return aCode;
					}
					if ("parsePatterns".equals(locName)) {
						return aParsePatterns;
					}
					if ("renderPattern".equals(locName)) {
						return aRenderPattern;
					}
					if ("validate".equals(locName)) {
						return null;
					}
					if (aMethod.getReturnType().isPrimitive()) {
						if (boolean.class.equals(aMethod.getReturnType())) {
							return false;
						}
						if (int.class.equals(aMethod.getReturnType())) {
							return 0;
						}
						if (long.class.equals(aMethod.getReturnType())) {
							return 0L;
						}
					}
					return null;
				}
		);
	}

	private static AIiVersionParsePattern createParsePattern(final String aRegexSource) {
		Pattern locCompiled = Pattern.compile(aRegexSource);
		return new AIiVersionParsePattern() {
			@Override
			public String patternSource() {
				return aRegexSource;
			}

			@Override
			public Pattern compiledPattern() {
				return locCompiled;
			}
		};
	}

	private static AIiVersionRenderPattern createRenderPattern(final String aPatternSource) {
		return new AIiVersionRenderPattern() {
			@Override
			public String patternSource() {
				return aPatternSource;
			}
		};
	}

	private static AIiVersionComponent createComponent(final String aCode) {
		return (AIiVersionComponent) Proxy.newProxyInstance(
				AIiVersionComponent.class.getClassLoader(),
				new Class<?>[]{AIiVersionComponent.class},
				(aProxy, aMethod, aArgs) -> {
					String locName = aMethod.getName();
					if ("code".equals(locName)) {
						return aCode;
					}
					if ("valueType".equals(locName)) {
						return AInVersionComponentValueType.TEXT;
					}
					if ("precedenceRole".equals(locName)) {
						return AInVersionComponentPrecedenceRole.PRECEDENCE;
					}
					if (aMethod.getReturnType().isPrimitive()) {
						if (boolean.class.equals(aMethod.getReturnType())) {
							return false;
						}
						if (int.class.equals(aMethod.getReturnType())) {
							return 0;
						}
						if (long.class.equals(aMethod.getReturnType())) {
							return 0L;
						}
					}
					return null;
				}
		);
	}

	private static AIiResolvedVersion createResolved(
			final AIiVersionScheme aScheme,
			final String aOriginalText,
			final String aCanonicalText,
			final AIiVersionStructure aStructure,
			final Map<String, String> aRawValues) {

		return (AIiResolvedVersion) Proxy.newProxyInstance(
				AIiResolvedVersion.class.getClassLoader(),
				new Class<?>[]{AIiResolvedVersion.class},
				(aProxy, aMethod, aArgs) -> {
					String locName = aMethod.getName();
					if ("scheme".equals(locName)) {
						return aScheme;
					}
					if ("originalText".equals(locName)) {
						return aOriginalText;
					}
					if ("canonicalText".equals(locName)) {
						return aCanonicalText;
					}
					if ("structure".equals(locName)) {
						return aStructure;
					}
					if ("rawValuesByComponentCode".equals(locName)) {
						return aRawValues;
					}
					return null;
				}
		);
	}
}
