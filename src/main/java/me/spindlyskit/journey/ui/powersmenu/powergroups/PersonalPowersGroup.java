package me.spindlyskit.journey.ui.powersmenu.powergroups;

import me.spindlyskit.journey.powers.PowerTest;
import me.spindlyskit.journey.powers.ToggleablePowerTest;
import me.spindlyskit.journey.ui.powersmenu.PowerGroup;

public class PersonalPowersGroup extends PowerGroup {
    public PersonalPowersGroup(int baseX, int baseY, int index) {
        super(baseX, baseY, index);
    }

    @Override
    protected void addButtons() {
        addButton(new ToggleablePowerTest());
        addButton(new PowerTest());
    }
}
