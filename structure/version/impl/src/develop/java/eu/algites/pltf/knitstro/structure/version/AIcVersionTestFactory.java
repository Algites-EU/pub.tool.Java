package eu.algites.pltf.knitstro.structure.version;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.version.component.AInVersionComponentPrecedenceRole;
import eu.algites.pltf.knitstro.structure.version.component.AInVersionComponentValueType;
import eu.algites.pltf.knitstro.structure.version.component.AIcVersionComponent;
import eu.algites.pltf.knitstro.structure.version.format.AIcVersionFormat;
import eu.algites.pltf.knitstro.structure.version.format.AIcVersionParsePattern;
import eu.algites.pltf.knitstro.structure.version.format.AIcVersionRenderPattern;
import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionFormat;

import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Test helper factory for version impl tests.
 */
final class AIcVersionTestFactory {

    private AIcVersionTestFactory() {
        /* no-op */
    }

    static AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin> createDummySchemeDataType() {
        return (AIiUidEnumDataType<? extends AIiUidEnumDataRecord, AInUidEnumDataOrigin>) Proxy.newProxyInstance(
                AIiUidEnumDataType.class.getClassLoader(),
                new Class<?>[]{AIiUidEnumDataType.class},
                (aProxy, aMethod, aArgs) -> null
        );
    }

    static AIcVersionStructure createSimpleTwoNumberStructure(
            final String aStructureCode,
            final String aCanonicalFormatCode) {

        AIiVersionComponent locMajor = new AIcVersionComponent(
                "major",
                AInVersionComponentValueType.NON_NEGATIVE_INTEGER,
                AInVersionComponentPrecedenceRole.PRECEDENCE
        );

        AIiVersionComponent locMinor = new AIcVersionComponent(
                "minor",
                AInVersionComponentValueType.NON_NEGATIVE_INTEGER,
                AInVersionComponentPrecedenceRole.PRECEDENCE
        );

        AIiVersionFormat locCanonical = new AIcVersionFormat(
                aCanonicalFormatCode,
                List.of(new AIcVersionParsePattern("^(?<major>\\d+)\\.(?<minor>\\d+)$")),
                new AIcVersionRenderPattern("${major}.${minor}")
        );

        Map<String, String> locSample = new LinkedHashMap<>();
        locSample.put("major", "1");
        locSample.put("minor", "0");

        return new AIcVersionStructure(
                aStructureCode,
                List.of(locMajor, locMinor),
                List.of(locCanonical),
                aCanonicalFormatCode,
                locSample
        );
    }
}
