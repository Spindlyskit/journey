package me.spindlyskit.journey.ui.powersmenu.powergroups;

import me.spindlyskit.journey.powers.TimeSetPower;
import me.spindlyskit.journey.powers.WeatherSetPower;
import me.spindlyskit.journey.ui.powersmenu.PowerGroup;
import me.spindlyskit.journey.ui.powersmenu.PowersMenuOptions;

public class WeatherPowersGroup extends PowerGroup {
    public WeatherPowersGroup(int baseX, int baseY, int index) {
        super(baseX, baseY, index);
    }

    @Override
    protected void addButtons(PowersMenuOptions options) {
        addButton(new WeatherSetPower(WeatherSetPower.WEATHER_TYPE.Clear, "clear"));
        addButton(new WeatherSetPower(WeatherSetPower.WEATHER_TYPE.Rain, "rain"));
        addButton(new WeatherSetPower(WeatherSetPower.WEATHER_TYPE.Thunder, "thunder"));
    }
}
