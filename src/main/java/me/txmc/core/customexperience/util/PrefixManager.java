package me.txmc.core.customexperience.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 * Manages and retrieves the appropriate prefix for players based on their permissions,
 * including the ability to animate prefixes.
 *
 * <p>This class is part of the 8b8tCore plugin, which provides custom functionalities
 * including permission-based prefixes for players, with animated tags.</p>
 *
 * @author Minelord9000 (agarciacorte)
 * @since 2024/08/11 03:42 PM
 */
public class PrefixManager {

    private static final Map<String, String> PREFIXES = new HashMap<>();
    private static double globalAnimationIndex = 0.0;

    private static final List<String> PREFIX_HIERARCHY = Arrays.asList(
            "8b8tcore.prefix.dev",
            "*",
            "8b8tcore.prefix.bot"
    );

    static {
        PREFIXES.put("8b8tcore.prefix.dev", "<gradient:#5555FF:#9492F5:#5555FF:%s>[DEV<green>✔</green>]</gradient>");
        PREFIXES.put("*", "<gradient:#BA3FFC:#D9ADFD:#BA3FFC:%s>[OWNER<green>✔</green>]</gradient>");
        PREFIXES.put("8b8tcore.prefix.bot", "<gradient:#00AA00:#6ef385:#00AA00:%s>[BOT]</gradient>");
    }

    public String getPrefix(Player player) {

        String highestPermission = "";

        for (String permission : PREFIX_HIERARCHY) {
            if (player.hasPermission(permission)) {
                highestPermission = permission;
                break;
            }
        }

        if (highestPermission.isEmpty()) {
            return "";
        }

        String basePrefix = PREFIXES.get(highestPermission);
        globalAnimationIndex = (globalAnimationIndex + 0.1) % 1.0;
        String animatedPrefix = basePrefix.replace("%s", String.format("%.1f", globalAnimationIndex));

        return animatedPrefix + " ";
    }
}