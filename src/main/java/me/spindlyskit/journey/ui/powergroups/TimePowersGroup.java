package me.spindlyskit.journey.ui.powergroups;

import me.spindlyskit.journey.powers.PowerTest;
import me.spindlyskit.journey.powers.TimeSetPower;
import me.spindlyskit.journey.powers.ToggleablePowerTest;
import me.spindlyskit.journey.ui.PowerGroup;

public class TimePowersGroup extends PowerGroup {
    public TimePowersGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY, index, toggled);
    }

    @Override
    protected void addButtons() {
        // Day
        addButton(new TimeSetPower(1000));
        // Noon
        addButton(new TimeSetPower(6000));
        // Night
        addButton(new TimeSetPower(13000));
        // Midnight
        addButton(new TimeSetPower(18000));
    }
}
