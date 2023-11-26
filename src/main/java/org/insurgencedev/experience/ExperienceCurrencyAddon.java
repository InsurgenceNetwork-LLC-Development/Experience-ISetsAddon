package org.insurgencedev.experience;

import org.insurgencedev.insurgencesets.api.ISetsAPI;
import org.insurgencedev.insurgencesets.api.addon.ISetsAddon;
import org.insurgencedev.insurgencesets.api.addon.InsurgenceSetsAddon;

@ISetsAddon(name = "Experience", version = "1.0.0", author = "Insurgence Dev Team", description = "Use player exp as a currency")
public class ExperienceCurrencyAddon extends InsurgenceSetsAddon {

    @Override
    public void onAddonStart() {
        ISetsAPI.getCurrencyManager().registerCurrency(new ExperienceCurrency());
    }
}
