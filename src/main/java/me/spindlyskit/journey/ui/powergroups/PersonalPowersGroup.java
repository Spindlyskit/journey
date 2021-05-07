package me.spindlyskit.journey.ui.powergroups;

import me.spindlyskit.journey.powers.PowerTest;
import me.spindlyskit.journey.powers.ToggleablePowerTest;
import me.spindlyskit.journey.ui.PowerGroup;

public class PersonalPowersGroup extends PowerGroup {
    public PersonalPowersGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY, index, toggled);
    }

    protected void addButtons(int x, int y) {
        addButton(x, y, new ToggleablePowerTest());
        addButton(x, y, new PowerTest());
    }
}
