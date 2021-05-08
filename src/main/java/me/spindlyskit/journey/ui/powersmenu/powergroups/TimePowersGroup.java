package me.spindlyskit.journey.ui.powersmenu.powergroups;

import me.spindlyskit.journey.powers.TimeSetPower;
import me.spindlyskit.journey.ui.powersmenu.PowerGroup;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;

public class TimePowersGroup extends PowerGroup {
    public TimePowersGroup(int baseX, int baseY, int index) {
        super(baseX, baseY, index);
    }

    @Override
    protected void addButtons(PowersMenuOptions options) {
        // Day
        addButton(new TimeSetPower(1000, "day"));
        // Noon
        addButton(new TimeSetPower(6000, "noon"));
        // Night
        addButton(new TimeSetPower(13000, "night"));
        // Midnight
        addButton(new TimeSetPower(18000, "midnight"));
    }
}
