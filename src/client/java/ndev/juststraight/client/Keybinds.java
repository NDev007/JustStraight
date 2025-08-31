package ndev.juststraight.client;

/**
 * @author NDev007
 * @copyright JustStraightMod
 * @quote На ошибках учатся. Я уже профессор.
 * @since 31.08.2025
 */
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class Keybinds {
    public static KeyBinding TOGGLE;


    public static void init() {
        TOGGLE = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.juststraight.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.juststraight"
        ));
    }
}
