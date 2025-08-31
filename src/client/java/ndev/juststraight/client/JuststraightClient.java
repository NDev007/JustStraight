package ndev.juststraight.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class JuststraightClient {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static boolean enabled = false;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> tick());
        Keybinds.init();
    }

    private static void tick() {
        if (client.player == null || client.world == null) return;
        JuststraightConfig config = AutoConfig.getConfigHolder(JuststraightConfig.class).getConfig();

        while (Keybinds.TOGGLE.wasPressed()) {
            enabled = !enabled;

            if (enabled) {
                Text word = Text.translatable("juststraight.word.enabled")
                        .setStyle(Style.EMPTY.withColor(Formatting.GREEN));
                client.player.sendMessage(
                        Text.translatable("message.juststraight.enabled", word),
                        true
                );
            } else {
                Text word = Text.translatable("juststraight.word.disabled")
                        .setStyle(Style.EMPTY.withColor(Formatting.RED));
                client.player.sendMessage(
                        Text.translatable("message.juststraight.disabled", word),
                        true
                );
                client.options.forwardKey.setPressed(false);
                client.options.sprintKey.setPressed(false);
                client.options.jumpKey.setPressed(false);
                client.options.useKey.setPressed(false);
            }
        }


        if (!enabled) return;

        if (config.autoEat && client.player.getHungerManager().getFoodLevel() < config.hungerThreshold) {
            performAutoEat(config);
            return;
        }

        client.options.forwardKey.setPressed(true);

        client.options.sprintKey.setPressed(config.sprint);

        if (config.bunnyhop) {
            if (client.player.isOnGround()) {
                client.options.jumpKey.setPressed(true);
            } else {
                client.options.jumpKey.setPressed(false);
            }
        } else {
            client.options.jumpKey.setPressed(false);
        }
    }

    private static void performAutoEat(JuststraightConfig config) {
        int foodSlot = findFoodInHotbar();
        if (foodSlot == -1) foodSlot = findFoodInInventory();
        if (foodSlot == -1) return;

        client.player.getInventory().setSelectedSlot(foodSlot);

        client.options.useKey.setPressed(true);

        if (client.player.getHungerManager().getFoodLevel() >= 20) {
            client.options.useKey.setPressed(false);
        }
    }

    private static int findFoodInHotbar() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = MinecraftClient.getInstance().player.getInventory().getStack(i);
            if (!stack.isEmpty() && stack.get(DataComponentTypes.FOOD) != null) {
                return i;
            }
        }
        return -1;
    }

    private static int findFoodInInventory() {
        for (int i = 9; i < 36; i++) {
            ItemStack stack = MinecraftClient.getInstance().player.getInventory().getStack(i);
            if (!stack.isEmpty() && stack.get(DataComponentTypes.FOOD) != null) {
                return i % 9;
            }
        }
        return -1;
    }
}
