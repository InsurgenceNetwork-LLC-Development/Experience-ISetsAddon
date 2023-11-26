package org.insurgencedev.experience;

import lombok.NonNull;
import org.bukkit.entity.Player;
import org.insurgencedev.insurgencesets.api.ISetsAPI;
import org.insurgencedev.insurgencesets.api.currency.Currency;
import org.insurgencedev.insurgencesets.models.currency.TransactionTypes;

public class ExperienceCurrency extends Currency {

    private static final String name = "Experience";

    public ExperienceCurrency() {
        super(name, "XP");
    }

    @Override
    public boolean canAfford(@NonNull Player player, @NonNull Object o) {
        return player.getExp() >= ((Number) o).floatValue();
    }

    @NonNull
    @Override
    public TransactionTypes handleDeposit(@NonNull Player player, @NonNull Object o, String s) {
        if (isInvalidSet(s)) {
            return TransactionTypes.FAIL;
        }

        player.setExp(player.getExp() + ((Number) o).floatValue());
        return TransactionTypes.SUCCESS;
    }

    @NonNull
    @Override
    public TransactionTypes handleTransaction(@NonNull Player player, @NonNull Object o, String s) {
        if (isInvalidSet(s)) {
            return TransactionTypes.FAIL;
        }

        float amount = ((Number) o).floatValue();
        if (player.getExp() < amount) {
            return TransactionTypes.FAIL_INSUFFICIENT_FUNDS;
        }

        player.setExp(player.getExp() - ((Number) o).floatValue());
        return TransactionTypes.SUCCESS;
    }

    private boolean isInvalidSet(String armorSet) {
        return armorSet == null || ISetsAPI.getArmorSetManager().findArmorSet(armorSet) == null;
    }
}
