package me.spindlyskit.journey.ui.powersmenu.powergroups;

import me.spindlyskit.journey.powers.GodModePower;
import me.spindlyskit.journey.powers.PowerTest;
import me.spindlyskit.journey.ui.powersmenu.PowerGroup;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;
import net.minecraft.client.MinecraftClient;

public class PersonalPowersGroup extends PowerGroup {
    public PersonalPowersGroup(int baseX, int baseY, int index) {
        super(baseX, baseY, index);
    }

    @Override
    protected void addButtons(PowersMenuOptions options) {
        addButton(new GodModePower(options));
        addButton(new PowerTest());
    }
}
