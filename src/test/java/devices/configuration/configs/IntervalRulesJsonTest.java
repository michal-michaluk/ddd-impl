package devices.configuration.configs;


import com.fasterxml.jackson.core.JsonProcessingException;
import devices.configuration.JsonAssert;
import devices.configuration.JsonConfiguration;
import devices.configuration.remote.IntervalRules;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;

public class IntervalRulesJsonTest {

    @Test
    void checkBackwardCompatibilityWihtIntetvalRulesV1() throws JsonProcessingException {
        @Language("JSON") var json = "{" +
                "  \"byIds\": [" +
                "    {" +
                "      \"interval\": 600.000000000," +
                "      \"devices\": [" +
                "        \"EVB-P4562137\"," +
                "        \"ALF-9571445\"," +
                "        \"CS_7155_CGC100\"," +
                "        \"EVB-P9287312\"," +
                "        \"ALF-2844179\"" +
                "      ]" +
                "    }," +
                "    {" +
                "      \"interval\": 2700.000000000," +
                "      \"devices\": [" +
                "        \"t53_8264_019\"," +
                "        \"EVB-P15079256\"," +
                "        \"EVB-P0984003\"," +
                "        \"EVB-P1515640\"," +
                "        \"EVB-P1515526\"" +
                "      ]" +
                "    }" +
                "  ]," +
                "  \"byModel\": [" +
                "    {" +
                "      \"interval\": 60.000000000," +
                "      \"vendor\": \"Alfen BV\"," +
                "      \"model\": \"NG920-5250[6-9]\"" +
                "    }," +
                "    {" +
                "      \"interval\": 60.000000000," +
                "      \"vendor\": \"ChargeStorm AB\"," +
                "      \"model\": \"Chargestorm Connected\"" +
                "    }," +
                "    {" +
                "      \"interval\": 120.000000000," +
                "      \"vendor\": \"EV-BOX\"," +
                "      \"model\": \"G3-M5320E-F2.*\"" +
                "    }" +
                "  ]," +
                "  \"byProtocol\": [" +
                "    {" +
                "      \"interval\": 600.000000000," +
                "      \"protocol\": \"IoT20\"" +
                "    }" +
                "  ]," +
                "  \"def\": 1800.000000000" +
                "}";
        IntervalRules object = parse(json);

        JsonAssert.assertThat(json).isExactlyLike(JsonAssert.json(object));
    }

    private IntervalRules parse(@Language("JSON") String json) throws JsonProcessingException {
        return JsonConfiguration.OBJECT_MAPPER.readValue(json, IntervalRules.class);
    }
}
