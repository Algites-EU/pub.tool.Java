package eu.algites.pltf.knitstro.structure.version;

import eu.algites.pltf.knitstro.structure.version.component.AInVersionComponentPrecedenceRole;
import eu.algites.pltf.knitstro.structure.version.component.AInVersionComponentValueType;
import eu.algites.pltf.knitstro.structure.version.component.AIcVersionComponent;
import eu.algites.pltf.knitstro.structure.version.format.AIcVersionFormat;
import eu.algites.pltf.knitstro.structure.version.format.AIcVersionParsePattern;
import eu.algites.pltf.knitstro.structure.version.format.AIcVersionRenderPattern;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests for {@link AIcVersionStructure}.
 */
public class AItcVersionStructureTest {

    @Test
    public void testValidatePassesForValidCanonicalStructure() {
        AIcVersionStructure locStructure = AIcVersionTestFactory.createSimpleTwoNumberStructure("maven2", "canon");
        locStructure.validate();
    }

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateRejectsDuplicateComponentCodes() {
        AIcVersionStructure locStructure = new AIcVersionStructure(
                "dupComp",
                List.of(
                        new AIcVersionComponent("x", AInVersionComponentValueType.TEXT, AInVersionComponentPrecedenceRole.PRECEDENCE),
                        new AIcVersionComponent("x", AInVersionComponentValueType.TEXT, AInVersionComponentPrecedenceRole.PRECEDENCE)
                ),
                List.of(
                        new AIcVersionFormat(
                                "canon",
                                List.of(new AIcVersionParsePattern("^(?<x>.+)$")),
                                new AIcVersionRenderPattern("${x}")
                        )
                ),
                "canon",
                Map.of("x", "a")
        );

        locStructure.validate();
    }

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateRejectsCanonicalFormatWithoutParsePatterns() {
        AIcVersionStructure locStructure = new AIcVersionStructure(
                "noParse",
                List.of(new AIcVersionComponent("x", AInVersionComponentValueType.TEXT, AInVersionComponentPrecedenceRole.PRECEDENCE)),
                List.of(
                        new AIcVersionFormat(
                                "canon",
                                List.of(),
                                new AIcVersionRenderPattern("${x}")
                        )
                ),
                "canon",
                Map.of("x", "a")
        );

        locStructure.validate();
    }

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateRejectsCanonicalRenderReferencingUnknownComponent() {
        AIcVersionStructure locStructure = new AIcVersionStructure(
                "unknownRef",
                List.of(new AIcVersionComponent("x", AInVersionComponentValueType.TEXT, AInVersionComponentPrecedenceRole.PRECEDENCE)),
                List.of(
                        new AIcVersionFormat(
                                "canon",
                                List.of(new AIcVersionParsePattern("^(?<x>.+)$")),
                                new AIcVersionRenderPattern("${x}-${y}")
                        )
                ),
                "canon",
                Map.of("x", "a")
        );

        locStructure.validate();
    }

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateRejectsSampleRoundTripWhenRenderedDoesNotMatchParsePatterns() {
        Map<String, String> locSample = new LinkedHashMap<>();
        locSample.put("x", "a");

        AIcVersionStructure locStructure = new AIcVersionStructure(
                "roundTrip",
                List.of(new AIcVersionComponent("x", AInVersionComponentValueType.TEXT, AInVersionComponentPrecedenceRole.PRECEDENCE)),
                List.of(
                        new AIcVersionFormat(
                                "canon",
                                List.of(new AIcVersionParsePattern("^THIS_WILL_NOT_MATCH$")),
                                new AIcVersionRenderPattern("${x}")
                        )
                ),
                "canon",
                locSample
        );

        locStructure.validate();
    }
}
