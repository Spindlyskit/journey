package me.spindlyskit.journey.ui.powersmenu.powergroups;

import me.spindlyskit.journey.powers.TimeSetPower;
import me.spindlyskit.journey.ui.powersmenu.PowerGroup;

public class TimePowersGroup extends PowerGroup {
    public TimePowersGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY, index, toggled);
    }

    @Override
    protected void addButtons() {
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
