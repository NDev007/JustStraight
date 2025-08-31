package ndev.juststraight.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

/**
 * @author NDev007
 * @copyright JustStraightMod
 * @quote На ошибках учатся. Я уже профессор.
 * @since 31.08.2025
 */
public class JuststraightModMenuIntegration implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return parent -> AutoConfig.getConfigScreen(JuststraightConfig.class, parent).get();
  }
}

