package ndev.juststraight.client;

/**
 * @author NDev007
 * @copyright JustStraightMod
 * @quote На ошибках учатся. Я уже профессор.
 * @since 31.08.2025
 */
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "juststraight")
public class JuststraightConfig implements ConfigData {
  @ConfigEntry.Gui.RequiresRestart
  public boolean sprint = true;

  public boolean bunnyhop = false;

  public boolean autoEat = true;

  public int hungerThreshold = 6;
}
