package illia.kai.obm_plugin.events;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Events implements Listener {

    private static final Logger LOGGER = Logger.getLogger(Events.class.getName()); static {
        LOGGER.setLevel(Level.OFF);
        LOGGER.addHandler(new ConsoleHandler());
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Skeleton || event.getEntity() instanceof Zombie) {
            LOGGER.fine("EntitySpawnEvent");
            Monster monster = (Monster) event.getEntity();
            Player nearbyPlayer;
            try {
                nearbyPlayer = (Player) monster.getWorld().getNearbyEntities(
                        monster.getLocation(),
                        1000,
                        1000,
                        1000,
                        (entity -> entity instanceof Player)
                ).stream().findFirst().get();
            }
            catch (NoSuchElementException e) {
                return;
            }
            LOGGER.log(Level.FINE, "Player: {0}", nearbyPlayer.getDisplayName());
            LOGGER.log(Level.FINE, "Monster: {0}", monster.getName());

            // 1 : 200
            int chance = 200;
            int monsterKills = nearbyPlayer.getStatistic(Statistic.KILL_ENTITY, monster.getType());
            int monsterKilledBy = nearbyPlayer.getStatistic(Statistic.ENTITY_KILLED_BY, monster.getType());
            chance -= monsterKills;
            chance += monsterKilledBy;
            chance -= nearbyPlayer.getTotalExperience();

            if(chance <= 0) chance = 1;

            Random random = new Random();
            int randomInt = random.nextInt(chance) + 1;

            LOGGER.log(Level.WARNING, "Chance: {0}", chance);

            if(randomInt <= 1) {
                if (monster instanceof Skeleton)
                    this.changeSkeletonEquipment((Skeleton) monster, nearbyPlayer);
                else if (monster instanceof Zombie)
                    this.changeZombieEquipment((Zombie) monster, nearbyPlayer);
            }
        }
    }

    private void changeSkeletonEquipment(Skeleton monster, Player player) {
        // 1 : 2
        int chanceOfGettingAnArmor = 2;
        Random random = new Random();
        int playerMaterialChance = this.getPlayerMaterialChance(player);

        if (playerMaterialChance <= 0)
            playerMaterialChance = 1;

        LOGGER.log(Level.WARNING, "PlayerMaterialChance: {0}", playerMaterialChance);

        // helmet
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            LOGGER.log(Level.WARNING, "Armor");
            int materialChance = random.nextInt(playerMaterialChance)+1;
            LOGGER.log(Level.WARNING, "materialChance: {0}", materialChance);
            if(materialChance <= 1) {
                monster.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
                monster.getEquipment().setHelmetDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));

            else if (materialChance <= 15)
                monster.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));

            else if (materialChance <= 20)
                monster.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));

            else if (materialChance <= 25)
                monster.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));

            else
                monster.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        }

        // ChestPlate
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            LOGGER.log(Level.WARNING, "Armor");
            int materialChance = random.nextInt(playerMaterialChance)+1;
            LOGGER.log(Level.WARNING, "materialChance: {0}", materialChance);
            if(materialChance <= 1) {
                monster.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                monster.getEquipment().setChestplateDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));

            else if (materialChance <= 15)
                monster.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));

            else if (materialChance <= 20)
                monster.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));

            else if (materialChance <= 25)
                monster.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));

            else
                monster.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        }

        // LEGGINGS
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            LOGGER.log(Level.WARNING, "Armor");
            int materialChance = random.nextInt(playerMaterialChance)+1;
            LOGGER.log(Level.WARNING, "materialChance: {0}", materialChance);
            if(materialChance <= 1) {
                monster.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
                monster.getEquipment().setLeggingsDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));

            else if (materialChance <= 15)
                monster.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));

            else if (materialChance <= 20)
                monster.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));

            else if (materialChance <= 25)
                monster.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));

            else
                monster.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        }

        // boots
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            LOGGER.log(Level.WARNING, "Armor");
            int materialChance = random.nextInt(playerMaterialChance)+1;
            LOGGER.log(Level.WARNING, "materialChance: {0}", materialChance);
            if(materialChance <= 1) {
                monster.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                monster.getEquipment().setBootsDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

            else if (materialChance <= 15)
                monster.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));

            else if (materialChance <= 20)
                monster.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));

            else if (materialChance <= 25)
                monster.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));

            else
                monster.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        }
    }

    private void changeZombieEquipment(Zombie monster, Player player) {
        // 1 : 2
        int chanceOfGettingAnArmor = 2;
        Random random = new Random();
        int playerMaterialChance = this.getPlayerMaterialChance(player);

        if (playerMaterialChance <= 0)
            playerMaterialChance = 1;

        LOGGER.log(Level.WARNING, "PlayerMaterialChance: {0}", playerMaterialChance);

        // helmet
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            int materialChance = random.nextInt(playerMaterialChance)+1;
            if(materialChance <= 1) {
                monster.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
                monster.getEquipment().setHelmetDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));

            else if (materialChance <= 15)
                monster.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));

            else if (materialChance <= 20)
                monster.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));

            else if (materialChance <= 25)
                monster.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));

            else
                monster.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        }

        // ChestPlate
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            int materialChance = random.nextInt(playerMaterialChance)+1;
            if(materialChance <= 1) {
                monster.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                monster.getEquipment().setChestplateDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));

            else if (materialChance <= 15)
                monster.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));

            else if (materialChance <= 20)
                monster.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));

            else if (materialChance <= 25)
                monster.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));

            else
                monster.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        }

        // LEGGINGS
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            int materialChance = random.nextInt(playerMaterialChance)+1;
            if(materialChance <= 1) {
                monster.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
                monster.getEquipment().setLeggingsDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));

            else if (materialChance <= 15)
                monster.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));

            else if (materialChance <= 20)
                monster.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));

            else if (materialChance <= 25)
                monster.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));

            else
                monster.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        }

        // boots
        if(random.nextInt(chanceOfGettingAnArmor)+1 == 1){
            int materialChance = random.nextInt(playerMaterialChance)+1;
            if(materialChance <= 1) {
                monster.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                monster.getEquipment().setBootsDropChance(0);
            }

            else if (materialChance <= 10)
                monster.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

            else if (materialChance <= 15)
                monster.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));

            else if (materialChance <= 20)
                monster.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));

            else if (materialChance <= 25)
                monster.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));

            else
                monster.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        }
    }

    private int getPlayerMaterialChance(Player player) {
        int chance = 75;
        try {
            List<ItemStack> armorList = new ArrayList<>();
            armorList.add(player.getInventory().getHelmet());
            armorList.add(player.getInventory().getChestplate());
            armorList.add(player.getInventory().getLeggings());
            armorList.add(player.getInventory().getBoots());

            for (ItemStack armor : armorList) {
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

                    default:
                        break;
                }
            }

            List<Field> fields = List.of(Enchantment.class.getFields());
            for (ItemStack armor : armorList) {
                for(Field field : fields) {
                    chance -= armor.getEnchantmentLevel((Enchantment) field.get(Enchantment.class));
                }
            }
        } catch (NullPointerException ignored) {

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        return chance;
    }
}
