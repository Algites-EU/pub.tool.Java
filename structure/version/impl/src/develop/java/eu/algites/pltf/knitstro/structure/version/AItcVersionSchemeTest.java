package eu.algites.pltf.knitstro.structure.version;

import org.testng.annotations.Test;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Tests for {@link AIcVersionScheme}.
 */
public class AItcVersionSchemeTest {

    @Test
    public void testValidatePassesForValidScheme() {
        AIcVersionScheme locScheme = new AIcVersionScheme(
                AIcVersionTestFactory.createDummySchemeDataType(),
                "builtin:maven",
                List.of(AIcVersionTestFactory.createSimpleTwoNumberStructure("maven2", "canon"))
        );

        locScheme.validate();
    }

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateRejectsDuplicateStructureCodes() {
        AIiVersionStructure locA = AIcVersionTestFactory.createSimpleTwoNumberStructure("maven2", "canon");
        AIiVersionStructure locB = AIcVersionTestFactory.createSimpleTwoNumberStructure("maven2", "canon2");

        AIcVersionScheme locScheme = new AIcVersionScheme(
                AIcVersionTestFactory.createDummySchemeDataType(),
                "builtin:maven",
                List.of(locA, locB)
        );

        locScheme.validate();
    }

    @Test(expectedExceptions = AIxVersionException.class)
    public void testValidateWrapsStructureValidationFailures() {
        AIiVersionStructure locBadStructure = (AIiVersionStructure) Proxy.newProxyInstance(
                AIiVersionStructure.class.getClassLoader(),
                new Class<?>[]{AIiVersionStructure.class},
                (aProxy, aMethod, aArgs) -> {
                    if ("code".equals(aMethod.getName())) {
                        return "bad";
                    }
                    if ("validate".equals(aMethod.getName())) {
                        throw new IllegalStateException("boom");
                    }
                    if ("canonicalFormatCode".equals(aMethod.getName())) {
                        return "canon";
                    }
                    if ("components".equals(aMethod.getName()) || "outputOnlyFormats".equals(aMethod.getName()) || "sampleRawValues".equals(aMethod.getName())) {
                        return java.util.Collections.emptyList();
                    }
                    if ("canonicalFormat".equals(aMethod.getName())) {
                        throw new IllegalStateException("no canonical");
                    }
                    if ("findComponentByCode".equals(aMethod.getName()) || "findFormatByCode".equals(aMethod.getName())) {
                        return java.util.Optional.empty();
                    }
                    return null;
                }
        );

        AIcVersionScheme locScheme = new AIcVersionScheme(
                AIcVersionTestFactory.createDummySchemeDataType(),
                "builtin:maven",
                List.of(locBadStructure)
        );

        locScheme.validate();
    }
}
