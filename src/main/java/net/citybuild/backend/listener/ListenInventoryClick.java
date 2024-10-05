package net.citybuild.backend.listener;

import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.plot.PlotArea;
import com.plotsquared.core.plot.PlotId;
import net.citybuild.CityBuild;
import net.citybuild.backend.begleiter.Begleiter;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.cases.animation.CaseAnimation;
import net.citybuild.backend.events.PerkToggleEvent;
import net.citybuild.backend.perk.Perk;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.user.User;
import net.citybuild.backend.user.creatorcode.CreatorCode;
import net.citybuild.backend.utility.Pair;
import net.citybuild.backend.utility.Serialization;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.frontend.inventory.begleiter.InventoryBegleiter;
import net.citybuild.frontend.inventory.booster.InventoryBooster;
import net.citybuild.frontend.inventory.booster.InventoryBuyBooster;
import net.citybuild.frontend.inventory.cases.InventoryBuyCases;
import net.citybuild.frontend.inventory.cases.InventoryCase;
import net.citybuild.frontend.inventory.cases.InventoryMyCases;
import net.citybuild.frontend.inventory.cases.InventoryShowcase;
import net.citybuild.frontend.inventory.perk.InventoryPerk;
import net.citybuild.frontend.inventory.plotmenu.InventoryPlotMenu;
import net.citybuild.frontend.inventory.voucher.InventoryVoucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public class ListenInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {

        final Player player = (Player) event.getWhoClicked();
        final User user = CityBuild.getUserManager().handle(player.getUniqueId());

        if (event.getView().getTitle().equals("§8» §6§lVote-Menü")) {
            event.setCancelled(true);
            return;
        }

        if (event.getView().getTitle().contains("§8» §a§lShowcase §7von §f")) {

            final String caseName = event.getView().getTitle().replaceAll("§8» §a§lShowcase §7von §f", "").trim();


            if (event.getRawSlot() == 45) {
                player.openInventory(InventoryCase.getCaseInventory(player));
                return;
            }


            final InventoryShowcase inventoryShowcase = ConstStorage.getCaseShowcase().get(player);
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);

            if (event.getRawSlot() == 48) {
                if (inventoryShowcase.page == 0) {
                    Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                    return;
                }
                if (inventoryShowcase.pagifier.getPages().size() >= (inventoryShowcase.page - 1) && (inventoryShowcase.page - 1) >= 0) {
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    inventoryShowcase.update((inventoryShowcase.page - 1), player, caseName);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                return;

            } else if (event.getRawSlot() == 50) {
                if ((inventoryShowcase.page + 1) >= inventoryShowcase.pagifier.getPages().size()) {
                    Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                    return;
                }

                if ((inventoryShowcase.page + 1) < inventoryShowcase.pagifier.getPages().size()) {
                    inventoryShowcase.update((inventoryShowcase.page + 1), player, caseName);
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                return;
            }
            return;
        }

        if (event.getView().getTitle().contains("§8» §a§lBearbeitung §7von §f") && player.isOp()) {

            final String caseName = event.getView().getTitle().replaceAll("§8» §a§lBearbeitung §7von §f", "").trim();


            if (event.getRawSlot() == 45) {
                player.openInventory(InventoryCase.getCaseInventory(player));
                return;
            }


            final InventoryShowcase inventoryShowcase = ConstStorage.getCaseShowcase().get(player);
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);

            if (event.getRawSlot() == 48) {
                if (inventoryShowcase.page == 0) {
                    Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                    return;
                }
                if (inventoryShowcase.pagifier.getPages().size() >= (inventoryShowcase.page - 1) && (inventoryShowcase.page - 1) >= 0) {
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    inventoryShowcase.update((inventoryShowcase.page - 1), player, caseName);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                return;

            } else if (event.getRawSlot() == 50) {
                if ((inventoryShowcase.page + 1) >= inventoryShowcase.pagifier.getPages().size()) {
                    Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                    return;
                }

                if ((inventoryShowcase.page + 1) < inventoryShowcase.pagifier.getPages().size()) {
                    inventoryShowcase.update((inventoryShowcase.page + 1), player, caseName);
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                return;
            }

            if (event.getRawSlot() > 0 && event.getRawSlot() < 45 && event.getCurrentItem().getType() != Material.BLACK_STAINED_GLASS_PANE) {
                ClickType click = event.getClick();

                if (click == ClickType.MIDDLE) {

                    final ItemStack itemStack = inventoryShowcase.pagifier.getPage(inventoryShowcase.page).get(event.getRawSlot());
                    final String base64 = Serialization.itemStackToBase64(itemStack);
                    final float chance = CityBuild.getCaseManager().getCase(caseName).getCaseItems().get(base64);

                    player.closeInventory();
                    CityBuild.getCaseManager().getChanceEdit().put(new Pair<>(player, caseName), new Pair<>(itemStack, chance));
                    Message.sendMessage(player, "§7Bitte gebe eine Chance für das gewünschte Item an§8.");
                    Message.sendMessage(player, "§7Schreibe §8'§ccancel§8' §7um den Vorgang abzubrechen§8.");
                    player.sendTitle("§c§lACHTUNG", "§cGebe die Chance im Chat an.", 10, 10, 10);
                    Utility.playChange(player);

                } else if (click == ClickType.DROP) {

                    final ItemStack itemStack = inventoryShowcase.pagifier.getPage(inventoryShowcase.page).get(event.getRawSlot());
                    player.getInventory().addItem(itemStack);


                    return;
                }
            }
            return;
        }

        if (event.getView().getTitle().equals("§8» §a§lKisten-Menü")) {
            event.setCancelled(true);

            final ClickType click = event.getClick();

            if (event.getRawSlot() == 50) {
                player.openInventory(InventoryBuyCases.getCaseInventory(player));
                return;
            }

            if (event.getRawSlot() == 20) {

                final Case clickedCase = CityBuild.getCaseManager().getCase("minivotekiste");


                if (!clickedCase.isEnabled()) {
                    Message.sendMessage(player, "§cDiese Case ist aktuell nicht verfügbar!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (clickedCase.getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (click == ClickType.LEFT) {
                    if (!CityBuild.getUserManager().hasCase(player.getUniqueId(), clickedCase)) {
                        Message.sendMessage(player, "§cDu besitzt keine §a§l" + clickedCase.getDisplayName() + "§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                        return;
                    }
                    CityBuild.getUserManager().removeCase(player.getUniqueId(), clickedCase);
                    new CaseAnimation(player).startAnimation(clickedCase, false);
                    return;
                }

                if (click == ClickType.RIGHT) {
                    final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(clickedCase.getName()), false);
                    ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                    inventoryShowcase.update(0, player, clickedCase.getName());
                    return;
                }

                if (click == ClickType.MIDDLE) {
                    new CaseAnimation(player).startAnimation(clickedCase, true);
                    return;
                }
                return;


            }

            if (event.getRawSlot() == 29) {

                final Case clickedCase = CityBuild.getCaseManager().getCase("votekiste");


                if (!clickedCase.isEnabled()) {
                    Message.sendMessage(player, "§cDiese Case ist aktuell nicht verfügbar!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (clickedCase.getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (click == ClickType.LEFT) {
                    if (!CityBuild.getUserManager().hasCase(player.getUniqueId(), clickedCase)) {
                        Message.sendMessage(player, "§cDu besitzt keine §a§l" + clickedCase.getDisplayName() + "§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                        return;
                    }
                    CityBuild.getUserManager().removeCase(player.getUniqueId(), clickedCase);
                    new CaseAnimation(player).startAnimation(clickedCase, false);
                    return;
                }

                if (click == ClickType.RIGHT) {
                    final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(clickedCase.getName()), false);
                    ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                    inventoryShowcase.update(0, player, clickedCase.getName());
                    return;
                }

                if (click == ClickType.MIDDLE) {
                    new CaseAnimation(player).startAnimation(clickedCase, true);
                    return;
                }
                return;
            }

            if (event.getRawSlot() == 24) {

                final Case clickedCase = CityBuild.getCaseManager().getCase("epischekiste");


                if (!clickedCase.isEnabled()) {
                    Message.sendMessage(player, "§cDiese Case ist aktuell nicht verfügbar!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (clickedCase.getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (click == ClickType.LEFT) {
                    if (!CityBuild.getUserManager().hasCase(player.getUniqueId(), clickedCase)) {
                        Message.sendMessage(player, "§cDu besitzt keine §a§l" + clickedCase.getDisplayName() + "§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                        return;
                    }
                    CityBuild.getUserManager().removeCase(player.getUniqueId(), clickedCase);
                    new CaseAnimation(player).startAnimation(clickedCase, false);
                    return;
                }

                if (click == ClickType.RIGHT) {
                    final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(clickedCase.getName()), false);
                    ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                    inventoryShowcase.update(0, player, clickedCase.getName());
                    return;
                }

                if (click == ClickType.MIDDLE) {
                    new CaseAnimation(player).startAnimation(clickedCase, true);
                    return;
                }
                return;
            }

            if (event.getRawSlot() == 33) {

                final Case clickedCase = CityBuild.getCaseManager().getCase("antikekiste");


                if (!clickedCase.isEnabled()) {
                    Message.sendMessage(player, "§cDiese Case ist aktuell nicht verfügbar!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (clickedCase.getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (click == ClickType.LEFT) {
                    if (!CityBuild.getUserManager().hasCase(player.getUniqueId(), clickedCase)) {
                        Message.sendMessage(player, "§cDu besitzt keine §a§l" + clickedCase.getDisplayName() + "§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                        return;
                    }
                    CityBuild.getUserManager().removeCase(player.getUniqueId(), clickedCase);
                    new CaseAnimation(player).startAnimation(clickedCase, false);
                    return;
                }

                if (click == ClickType.RIGHT) {
                    final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(clickedCase.getName()), false);
                    ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                    inventoryShowcase.update(0, player, clickedCase.getName());
                    return;
                }

                if (click == ClickType.MIDDLE) {
                    new CaseAnimation(player).startAnimation(clickedCase, true);
                    return;
                }
                return;
            }

            if (event.getRawSlot() == 13) {

                final Case clickedCase = CityBuild.getCaseManager().getCase("eiskiste");


                if (!clickedCase.isEnabled()) {
                    Message.sendMessage(player, "§cDiese Case ist aktuell nicht verfügbar!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (clickedCase.getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                    return;
                }

                if (click == ClickType.LEFT) {
                    if (!CityBuild.getUserManager().hasCase(player.getUniqueId(), clickedCase)) {
                        Message.sendMessage(player, "§cDu besitzt keine §a§l" + clickedCase.getDisplayName() + "§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                        return;
                    }
                    CityBuild.getUserManager().removeCase(player.getUniqueId(), clickedCase);
                    new CaseAnimation(player).startAnimation(clickedCase, false);
                    return;
                }

                if (click == ClickType.RIGHT) {
                    final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(clickedCase.getName()), false);
                    ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                    inventoryShowcase.update(0, player, clickedCase.getName());
                    return;
                }

                if (click == ClickType.MIDDLE) {
                    new CaseAnimation(player).startAnimation(clickedCase, true);
                    return;
                }
                return;


            }

            if (event.getRawSlot() == 48) {
                final InventoryMyCases inventoryMyCases = new InventoryMyCases();
                ConstStorage.getMyCases().put(player, inventoryMyCases);
                player.openInventory(inventoryMyCases.getMyCasesInventory(player));
                return;
            }
            return;
        }

        if (event.getView().getTitle().equals("§8» §a§lMeine Kisten")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;


            if (event.getRawSlot() == 36) {
                player.openInventory(InventoryCase.getCaseInventory(player));
                return;
            }


            if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {

                final ClickType click = event.getClick();
                final String rawCaseName = ConstStorage.getMyCases().get(player).saver.get(event.getRawSlot());
                final Case clickedCase = CityBuild.getCaseManager().getCase(rawCaseName);


                if (!clickedCase.isEnabled()) {
                    Message.sendMessage(player, "§cDiese Case ist aktuell nicht verfügbar!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);
                    return;
                }

                if (clickedCase.getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);
                    return;
                }

                if (click == ClickType.LEFT) {
                    if (!CityBuild.getUserManager().hasCase(player.getUniqueId(), clickedCase)) {
                        Message.sendMessage(player, "§cDu besitzt keine §a§l" + clickedCase.getDisplayName() + "§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);
                        return;
                    }
                    CityBuild.getUserManager().removeCase(player.getUniqueId(), clickedCase);
                    new CaseAnimation(player).startAnimation(clickedCase, false);
                    return;
                }

                if (click == ClickType.RIGHT) {
                    final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(clickedCase.getName()), false);
                    ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                    inventoryShowcase.update(0, player, clickedCase.getName());
                    return;
                }

                if (click == ClickType.MIDDLE) {
                    new CaseAnimation(player).startAnimation(clickedCase, true);
                    return;
                }
                return;
            }
            return;

        }

        if (event.getView().getTitle().equals("§8» §a§lKaufe hier deine Kisten!")) {
            event.setCancelled(true);


            if (event.getRawSlot() == 36) {
                player.openInventory(InventoryCase.getCaseInventory(player));
                return;
            }


            if (event.getRawSlot() == 11) {

                final Case caseA = CityBuild.getCaseManager().getCase("epischekiste");

                if (user.getBits() >= 300) {
                    user.setBits((user.getBits()) - 300);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 1);
                    Message.sendMessage(player, "§7Du hast §eeine " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 12) {

                final Case caseA = CityBuild.getCaseManager().getCase("epischekiste");

                if (user.getBits() >= 1200) {
                    user.setBits((user.getBits()) - 1200);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 5);
                    Message.sendMessage(player, "§7Du hast §e5§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 13) {

                final Case caseA = CityBuild.getCaseManager().getCase("epischekiste");

                if (user.getBits() >= 2400) {
                    user.setBits((user.getBits()) - 2400);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 10);
                    Message.sendMessage(player, "§7Du hast §e10§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);

                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 14) {

                final Case caseA = CityBuild.getCaseManager().getCase("epischekiste");

                if (user.getBits() >= 3400) {
                    user.setBits((user.getBits()) - 3400);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 15);
                    Message.sendMessage(player, "§7Du hast §e15§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 15) {

                final Case caseA = CityBuild.getCaseManager().getCase("epischekiste");

                if (user.getBits() >= 4500) {
                    user.setBits((user.getBits()) - 4500);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 20);
                    Message.sendMessage(player, "§7Du hast §e20§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);
                }

                return;
            }

            //

            if (event.getRawSlot() == 20) {

                final Case caseA = CityBuild.getCaseManager().getCase("antikekiste");

                if (user.getBits() >= 600) {
                    user.setBits((user.getBits()) - 600);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 1);
                    Message.sendMessage(player, "§7Du hast §eeine " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 21) {

                final Case caseA = CityBuild.getCaseManager().getCase("antikekiste");

                if (user.getBits() >= 600) {
                    user.setBits((user.getBits()) - 600);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 5);
                    Message.sendMessage(player, "§7Du hast §e5§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 22) {

                final Case caseA = CityBuild.getCaseManager().getCase("antikekiste");

                if (user.getBits() >= 2400) {
                    user.setBits((user.getBits()) - 2400);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 10);
                    Message.sendMessage(player, "§7Du hast §e10§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 23) {

                final Case caseA = CityBuild.getCaseManager().getCase("antikekiste");

                if (user.getBits() >= 4800) {
                    user.setBits((user.getBits()) - 4800);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 15);
                    Message.sendMessage(player, "§7Du hast §e15§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 24) {

                final Case caseA = CityBuild.getCaseManager().getCase("antikekiste");

                if (user.getBits() >= 7200) {
                    user.setBits((user.getBits()) - 7200);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 20);
                    Message.sendMessage(player, "§7Du hast §e20§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            //

            if (event.getRawSlot() == 29) {

                final Case caseA = CityBuild.getCaseManager().getCase("eiskiste");

                if (user.getBits() >= 1000) {
                    user.setBits((user.getBits()) - 1000);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 1);
                    Message.sendMessage(player, "§7Du hast §eeine " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 30) {

                final Case caseA = CityBuild.getCaseManager().getCase("eiskiste");

                if (user.getBits() >= 4500) {
                    user.setBits((user.getBits()) - 4500);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 5);
                    Message.sendMessage(player, "§7Du hast §e5§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 31) {

                final Case caseA = CityBuild.getCaseManager().getCase("eiskiste");

                if (user.getBits() >= 9000) {
                    user.setBits((user.getBits()) - 9000);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 10);
                    Message.sendMessage(player, "§7Du hast §e10§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            if (event.getRawSlot() == 32) {

                final Case caseA = CityBuild.getCaseManager().getCase("eiskiste");

                if (user.getBits() >= 13500) {
                    user.setBits((user.getBits()) - 13500);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 15);
                    Message.sendMessage(player, "§7Du hast §e15§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }


            if (event.getRawSlot() == 33) {

                final Case caseA = CityBuild.getCaseManager().getCase("eiskiste");

                if (user.getBits() >= 18000) {
                    user.setBits((user.getBits()) - 18000);
                    CityBuild.getUserManager().addCase(player.getUniqueId(), caseA, 20);
                    Message.sendMessage(player, "§7Du hast §e20§8x " + caseA.getDisplayName() + " §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }

                return;
            }

            return;
        }


        if (event.getView().getTitle().equals("§8» §d§lCreator-Code")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;

            if (event.getRawSlot() == 28 && CityBuild.getCcManager().hasCC(player.getUniqueId()) && event.getClick() == ClickType.LEFT) {
                final CreatorCode creatorCode = CityBuild.getCcManager().getCreatorCodeByUUID(player.getUniqueId());

                if (creatorCode.getIncomingBits() > 0) {
                    user.setBits((user.getBits()) + creatorCode.getIncomingBits());
                    creatorCode.setIncomingBits(0);
                    Message.sendMessage(player, "§aDu hast deine verdienten Bits eingesammelt§8.");
                    player.closeInventory();
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                }
                return;
            }

            if (event.getRawSlot() == 44 && event.getCurrentItem().getType() == Material.BARRIER) {

                if (CityBuild.getUserManager().hasCooldown(player.getUniqueId(), "creatorcode")) {
                    Message.sendMessage(player, "§cBitte habe noch etwas Geduld§8!");
                    Message.sendMessage(player, "§7Bereit in§8: §c" + Utility.timeToString(CityBuild.getUserManager().getTime(player.getUniqueId(), "creatorcode"), true));
                    Utility.playError(player);
                    return;
                }

                CityBuild.getUserManager().setCooldown(player.getUniqueId(), "creatorcode", TimeUnit.DAYS.toMillis(14));
                Message.sendMessage(player, "§cDu hast deinen Creator-Code entfernt§8.");
                player.playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_YES, 1, 1);
                user.setCreatorCode(null);
                player.closeInventory();
                return;
            }

            if (event.getCurrentItem().getType() == Material.PLAYER_HEAD && !CityBuild.getCcManager().hasCC(player.getUniqueId()) && !event.getCurrentItem().getItemMeta().getDisplayName().equals("§d§lDu?")) {

                if (CityBuild.getUserManager().hasCooldown(player.getUniqueId(), "creatorcode")) {
                    Message.sendMessage(player, "§cDu hast aktuell eine Creator-Code Sperre§8!");
                    Message.sendMessage(player, "§7Bereit in§8: §c" + Utility.timeToString(CityBuild.getUserManager().getTime(player.getUniqueId(), "creatorcode"), true));
                    Utility.playError(player);
                    return;
                }

                if (user.getCreatorCode() != null) {
                    Message.sendMessage(player, "§cDu unterstützt bereits einen Creator§8.");
                    return;
                }

                final String creatorCode = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§7Creator-Code§8: §5", "").trim();

                if (!CityBuild.getCcManager().existCC(creatorCode)) {
                    return;
                }

                user.setCreatorCode(creatorCode);
                Message.sendMessage(player, "§7Du unterstützt nun den Creator§8: §d" + Bukkit.getOfflinePlayer(CityBuild.getCcManager().getCreatorCode(creatorCode).getCreator()).getName());
                player.playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_YES, 1, 1);


                if (!CityBuild.getCcManager().getCreatorCode(creatorCode).getUses().contains(player.getUniqueId())) {
                    CityBuild.getCcManager().getCreatorCode(creatorCode).getUses().add(player.getUniqueId());
                }

                CityBuild.getCcManager().getCreatorCode(creatorCode).setIncomingBits((CityBuild.getCcManager().getCreatorCode(creatorCode).getIncomingBits() + 200));
                CityBuild.getCcManager().getCreatorCode(creatorCode).setIncomingBitsAlltime((CityBuild.getCcManager().getCreatorCode(creatorCode).getIncomingBitsAlltime() + 200));

                CityBuild.getUserManager().addCase(player.getUniqueId(), CityBuild.getCaseManager().getCase("antikekiste"), 2);
                user.setMoney((user.getMoney() + 2500));
                user.setBoosters((user.getBoosters()) + 1);

                Message.sendMessage(player, "§7Folgende Belohnungen wurden dir gutgeschrieben§8:");
                Message.sendMessage(player, " §e2.500§6€");
                Message.sendMessage(player, " §e1 Booster");
                Message.sendMessage(player, " §62§8x " + CityBuild.getCaseManager().getCase("antikekiste").getDisplayName());

                player.closeInventory();
            }

            return;
        }

        if (event.getView().getTitle().equals("§8» §f§lBooster-Menü")) {

            event.setCancelled(true);

            if (event.getRawSlot() == 41) {
                player.openInventory(InventoryBuyBooster.getBoosterBuyInventory(player));
                return;
            }
            return;
        }

        if (event.getView().getTitle().equals("§8» §f§lKaufe hier deine Booster!")) {

            event.setCancelled(true);

            if (event.getRawSlot() == 18) {
                player.openInventory(InventoryBooster.getBoosterInventory(player));
                return;
            }


            if (event.getRawSlot() == 11) {
                if (user.getBits() >= 200) {
                    user.setBits((user.getBits()) - 200);
                    user.setBoosters((user.getBoosters()) + 1);
                    Message.sendMessage(player, "§7Du hast §e1§8x §f§lBooster §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }
                return;
            }

            if (event.getRawSlot() == 12) {
                if (user.getBits() >= 1000) {
                    user.setBits((user.getBits()) - 1000);
                    user.setBoosters((user.getBoosters()) + 5);
                    Message.sendMessage(player, "§7Du hast §e5§8x §f§lBooster §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }
                return;
            }

            if (event.getRawSlot() == 13) {
                if (user.getBits() >= 2000) {
                    user.setBits((user.getBits()) - 2000);
                    user.setBoosters((user.getBoosters()) + 10);
                    Message.sendMessage(player, "§7Du hast §e10§8x §f§lBooster §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }
                return;
            }

            if (event.getRawSlot() == 14) {
                if (user.getBits() >= 3000) {
                    user.setBits((user.getBits()) - 3000);
                    user.setBoosters((user.getBoosters()) + 15);
                    Message.sendMessage(player, "§7Du hast §e15§8x §f§lBooster §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }
                return;
            }

            if (event.getRawSlot() == 15) {
                if (user.getBits() >= 4000) {
                    user.setBits((user.getBits()) - 4000);
                    user.setBoosters((user.getBoosters()) + 20);
                    Message.sendMessage(player, "§7Du hast §e20§8x §f§lBooster §7erworben§8.");
                    Utility.playSuccess(player);
                } else {
                    Message.sendMessage(player, "§cDafür hast du nicht ausreichend Bits§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);

                }
                return;
            }
            return;
        }

        if (event.getView().getTitle().equals("§8» " + Color.translateColorCodes("&#FF00B0&lPerks"))) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;

            final InventoryPerk inventoryPerk = ConstStorage.getPerks().get(player);


            if (event.getRawSlot() == 30) {
                if (inventoryPerk.page == 0) {
                    Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                    return;
                }
                if (inventoryPerk.pagifier.getPages().size() >= (inventoryPerk.page - 1) && (inventoryPerk.page - 1) >= 0) {
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    inventoryPerk.update((inventoryPerk.page - 1), player);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                return;

            } else if (event.getRawSlot() == 32) {
                if ((inventoryPerk.page + 1) >= inventoryPerk.pagifier.getPages().size()) {
                    Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                    return;
                }

                if ((inventoryPerk.page + 1) < inventoryPerk.pagifier.getPages().size()) {
                    inventoryPerk.update((inventoryPerk.page + 1), player);
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                return;
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Color.translateColorCodes("&#FF00B0&lPerk§8: §7"))) {

                final String rawPerkName = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll(Color.translateColorCodes("&#FF00B0&lPerk§8: §7"), "").trim();
                final Perk perk = Perk.getPerkByDisplayName(rawPerkName);

                if (perk == null) return;

                if (perk.getPermission() != null && player.hasPermission(perk.getPermission())) {
                    Message.sendMessage(player, "§cDie Befugnis hast du bereits in deinem Besitz§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);
                    return;
                }


                if (!CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk)) {
                    final long price = perk.getPrice();

                    if (price < 0) {
                        Message.sendMessage(player, "§cDieses Perk ist nur im Case-Opening erhältlich§8.");
                        return;
                    }
                    if (user.getMoney() < price) {
                        Message.sendMessage(player, "§cDafür ist dein Kontostand zu niedrig§8.");
                        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 8);
                        return;
                    }

                    user.setMoney((user.getMoney()) - price);
                    CityBuild.getUserManager().givePerk(player.getUniqueId(), perk);
                    Message.sendMessage(player, "§7Du hast erfolgreich das §a§l" + perk.getDisplayName() + " §7Perk erworben§8.");
                    inventoryPerk.update(inventoryPerk.page, player);
                    return;
                }

                if (CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk)) {

                    if (ConstStorage.getPerkToggle().getIfPresent(player) != null) {
                        Message.sendMessage(player, "§cBitte versuche es in Kürze erneut§8.");
                        return;
                    }

                    CityBuild.getUserManager().setPerkToggled(player.getUniqueId(), perk, !CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), perk));
                    Utility.playChange(player);
                    inventoryPerk.update(inventoryPerk.page, player);
                    ConstStorage.getPerkToggle().put(player, System.currentTimeMillis());
                    final PerkToggleEvent perkToggleEvent = new PerkToggleEvent(player, perk, CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), perk));
                    Bukkit.getPluginManager().callEvent(perkToggleEvent);
                    return;
                }


            }
            return;
        }

        //*

        if (event.getView().getTitle().equals("§8» " + Color.translateColorCodes("&#213c15&lBegleiter"))) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;

            final InventoryBegleiter inventoryBegleiter = ConstStorage.getBegleiter().get(player);


            if (event.getRawSlot() == 30) {
                if (inventoryBegleiter.page == 0) {
                    Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                    return;
                }
                if (inventoryBegleiter.pagifier.getPages().size() >= (inventoryBegleiter.page - 1) && (inventoryBegleiter.page - 1) >= 0) {
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    inventoryBegleiter.update((inventoryBegleiter.page - 1), player);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                return;

            } else if (event.getRawSlot() == 32) {
                if ((inventoryBegleiter.page + 1) >= inventoryBegleiter.pagifier.getPages().size()) {
                    Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                    return;
                }

                if ((inventoryBegleiter.page + 1) < inventoryBegleiter.pagifier.getPages().size()) {
                    inventoryBegleiter.update((inventoryBegleiter.page + 1), player);
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                return;
            }


            if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {

                final String rawPerkName = event.getCurrentItem().getItemMeta().getDisplayName();
                final Begleiter begleiter = Begleiter.getBegleiterByDisplayName(ChatColor.stripColor(rawPerkName).replaceAll("Begleiter:", "").trim());

                if (begleiter == null) return;


                if (!CityBuild.getUserManager().hasBegleiter(player.getUniqueId(), begleiter)) {
                    Message.sendMessage(player, "§cDu besitzt diesen Begleiter nicht§8.");
                    Utility.playError(player);
                    return;
                }

                if (CityBuild.getUserManager().hasBegleiter(player.getUniqueId(), begleiter)) {

                    if (ConstStorage.getBegleiterToggle().getIfPresent(player) != null) {
                        Message.sendMessage(player, "§cBitte versuche es in Kürze erneut§8.");
                        return;
                    }

                    CityBuild.getUserManager().setBegleiterToggled(player.getUniqueId(), begleiter, !CityBuild.getUserManager().hasBegleiterToggled(player.getUniqueId(), begleiter));
                    Utility.playChange(player);
                    inventoryBegleiter.update(inventoryBegleiter.page, player);
                    ConstStorage.getBegleiterToggle().put(player, System.currentTimeMillis());
                    CityBuild.getINSTANCE().getBegleiterController().sync(player);
                    return;
                }


            }
            return;
        }
        if (event.getView().getTitle().equals(Color.translateColorCodes("§8» &#72A300&lGrundstücksmenü"))) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;

            final InventoryPlotMenu inventoryPlotMenu = ConstStorage.getPlotMenu().get(player);


            if (event.getRawSlot() == 39) {
                if (inventoryPlotMenu.page == 0) {
                    Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                    return;
                }
                if (inventoryPlotMenu.pagifier.getPages().size() >= (inventoryPlotMenu.page - 1) && (inventoryPlotMenu.page - 1) >= 0) {
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    inventoryPlotMenu.update((inventoryPlotMenu.page - 1), player);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                return;

            } else if (event.getRawSlot() == 41) {
                if ((inventoryPlotMenu.page + 1) >= inventoryPlotMenu.pagifier.getPages().size()) {
                    Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                    return;
                }

                if ((inventoryPlotMenu.page + 1) < inventoryPlotMenu.pagifier.getPages().size()) {
                    inventoryPlotMenu.update((inventoryPlotMenu.page + 1), player);
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                return;
            }

            if (event.getCurrentItem().getType() == Material.RED_BED) {

                final String rawDisplayName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                final PlotId plotId = PlotId.fromString(rawDisplayName
                        .replaceAll("&#72A300&lGrundstück - ", "")
                        .replaceAll(" §8\\(§", "")
                        .replaceAll("§8\\)", "")
                        .replaceAll("[0-9]", "")
                        .trim());
                final PlotArea plotArea = PlotSquared.get().getPlotAreaManager().getPlotArea(ConstStorage.getPlotWelt(), plotId.toCommaSeparatedString());


                if (event.getClick() == ClickType.LEFT) {
                    // FIXME: 23.08.2023 Teleport usw..
                    System.out.println("clicked! - " + plotId);
                    System.out.println("Plot Owner - " + plotArea.getPlot(plotId).getOwner());
                    return;
                }


                return;
            }
            return;
        }

        if (event.getView().getTitle().equals(Color.translateColorCodes("§8» &#ae9d6f&lGutscheine"))) {
            event.setCancelled(true);


            final InventoryVoucher inventoryVoucher = ConstStorage.getVoucher().get(player);
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);

            if (event.getRawSlot() == 48) {
                if (inventoryVoucher.page == 0) {
                    Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                    return;
                }
                if (inventoryVoucher.pagifier.getPages().size() >= (inventoryVoucher.page - 1) && (inventoryVoucher.page - 1) >= 0) {
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    inventoryVoucher.update((inventoryVoucher.page - 1), player);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der ersten Seite§8.");
                return;

            } else if (event.getRawSlot() == 50) {
                if ((inventoryVoucher.page + 1) >= inventoryVoucher.pagifier.getPages().size()) {
                    Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                    return;
                }

                if ((inventoryVoucher.page + 1) < inventoryVoucher.pagifier.getPages().size()) {
                    inventoryVoucher.update((inventoryVoucher.page + 1), player);
                    player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                    return;
                }
                Message.sendMessage(player, "§cDu bist bereits auf der letzten Seite§8.");
                return;
            }

            if (event.getRawSlot() != 48 || event.getRawSlot() != 50) {
                if (event.getCurrentItem().getType() != Material.BLACK_STAINED_GLASS_PANE) {
                    player.getInventory().addItem(event.getCurrentItem());
                }
            }
        }

        if (event.getView().getTitle().equals("§8» §b§lAffiliate Shop")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;

            if(event.getRawSlot() == 12) {
                if(user.getPoints() >= 3) {
                    user.setPoints(user.getPoints()-3);
                    user.setBoosters(user.getBoosters()+1);
                    Message.sendMessage(player, "§aDu hast erfolgreich §b§l1§8x §f§lBooster §aerworben für §e§l3 §aPunkte.");
                    Utility.playSuccess(player);
                    return;
                }
                Message.sendMessage(player, "§cDu hast nicht genügend Punkte.");
            }
        }
    }
}

