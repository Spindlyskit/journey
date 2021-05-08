package me.spindlyskit.journey.ui.powergroups;

import me.spindlyskit.journey.powers.PowerTest;
import me.spindlyskit.journey.powers.ToggleablePowerTest;
import me.spindlyskit.journey.ui.PowerGroup;

public class PersonalPowersGroup extends PowerGroup {
    public PersonalPowersGroup(int baseX, int baseY, int index, boolean toggled) {
        super(baseX, baseY, index, toggled);
    }

    @Override
    protected void addButtons() {
        addButton(new ToggleablePowerTest());
        addButton(new PowerTest());
    }
}
