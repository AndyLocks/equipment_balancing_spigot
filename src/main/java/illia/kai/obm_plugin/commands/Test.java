package illia.kai.obm_plugin.commands;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Test implements CommandExecutor {

    private static final Logger LOGGER = Logger.getLogger(Test.class.getName()); static {
        LOGGER.setLevel(Level.FINE);
        LOGGER.addHandler(new ConsoleHandler());
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        int chance = 75;
        try {
            List<ItemStack> armorList = new ArrayList<>();
            armorList.add(player.getInventory().getHelmet());
            armorList.add(player.getInventory().getChestplate());
            armorList.add(player.getInventory().getLeggings());
            armorList.add(player.getInventory().getBoots());

            for (ItemStack armor : armorList) {
                if(armor == null) continue;

                switch (armor.getType()) {
                    case LEATHER_HELMET:
                    case LEATHER_CHESTPLATE:
                    case LEATHER_LEGGINGS:
                    case LEATHER_BOOTS:
                        chance -= 1;
                        break;

                    case CHAINMAIL_HELMET:
                    case CHAINMAIL_CHESTPLATE:
                    case CHAINMAIL_LEGGINGS:
                    case CHAINMAIL_BOOTS:
                        chance -= 2;
                        break;

                    case IRON_HELMET:
                    case IRON_CHESTPLATE:
                    case IRON_LEGGINGS:
                    case IRON_BOOTS:
                        chance -= 2;
                        break;

                    case GOLDEN_HELMET:
                    case GOLDEN_CHESTPLATE:
                    case GOLDEN_LEGGINGS:
                    case GOLDEN_BOOTS:
                        chance -= 2;
                        break;

                    case DIAMOND_HELMET:
                    case DIAMOND_CHESTPLATE:
                    case DIAMOND_LEGGINGS:
                    case DIAMOND_BOOTS:
                        chance -= 3;
                        break;

                    case NETHERITE_HELMET:
                    case NETHERITE_CHESTPLATE:
                    case NETHERITE_LEGGINGS:
                    case NETHERITE_BOOTS:
                        chance -= 4;
                        break;
                }
            }

            List<Field> fields = List.of(Enchantment.class.getFields());
            for (ItemStack armor : armorList) {
                if (armor == null) continue;

                for(Field field : fields) {
                    chance -= armor.getEnchantmentLevel((Enchantment) field.get(Enchantment.class));
                }
            }
        } catch (NullPointerException ignored) {

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        player.sendMessage("Chance: " + Integer.toString(chance));
        return true;
    }
}
