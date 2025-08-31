package ndev.juststraight.client;

/**
 * @author NDev007
 * @copyright JustStraightMod
 * @quote На ошибках учатся. Я уже профессор.
 * @since 31.08.2025
 */
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class JuststraightMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(JuststraightConfig.class, GsonConfigSerializer::new);
        JuststraightClient.init();
    }
}