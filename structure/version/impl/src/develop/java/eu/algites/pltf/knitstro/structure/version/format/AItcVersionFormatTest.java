package eu.algites.pltf.knitstro.structure.version.format;

import eu.algites.pltf.knitstro.structure.version.AIxVersionException;

import org.testng.annotations.Test;

import java.util.List;

/**
 * Tests for {@link AIcVersionFormat}.
 */
public class AItcVersionFormatTest {

    @Test
    public void testValidatePassesForMinimalValidFormat() {
        AIcVersionFormat locFormat = new AIcVersionFormat(
                "canon",
                List.of(new AIcVersionParsePattern("^(?<major>\\d+)\\.(?<minor>\\d+)$")),
                new AIcVersionRenderPattern("${major}.${minor}")
        );

        locFormat.validate();
    }

		@Test(expectedExceptions = AIxVersionException.class)
		public void testValidateRejectsNullParsePatternItem() {
			AIcVersionFormat locFormat = new AIcVersionFormat(
					"canon",
					java.util.Arrays.asList((AIcVersionParsePattern) null),
					new AIcVersionRenderPattern("${major}.${minor}")
			);

			locFormat.validate();
		}

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateRejectsBlankRenderPatternSource() {
        AIiVersionRenderPattern locBadRenderPattern = new AIiVersionRenderPattern() {
            @Override
            public String patternSource() {
                return "   ";
            }

            @Override
            public java.util.Set<String> referencedComponentCodes() {
                return java.util.Collections.emptySet();
            }
        };

        AIcVersionFormat locFormat = new AIcVersionFormat(
                "canon",
                List.of(new AIcVersionParsePattern("^(?<major>\\d+)$")),
                locBadRenderPattern
        );

        locFormat.validate();
    }
}
