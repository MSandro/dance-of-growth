package draylar.danceofgrowth.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

public class DanceOfGrowthConfig implements Config {

    @Comment(value = "The chance for a nearby plant to receive the bonemeal effect when a player hits the crouch button. 10 is 1/10, 20 is 1/20.")
    public int chance = 25;

    @Comment(value = "Time, in ticks, from the last player jump for it to have an effect on the sneak chance.")
    public int jumpTime = 40;

    public float jumpModifier = 1.5f;

    @Comment(value = "Time, in ticks, from the last player sprint action for it to have an effect on the sneak chance.")
    public int sprintTime = 40;

    public float sprintModifier = 1.5f;

    @Comment(value = "The minimum number of nearby plant that can be grown through crouches.")
    public int cropPerSneakMin = 1;

    @Comment(value = "The maximum number of nearby plant that can be grown through crouches.")
    public int cropPerSneakMax = 1;

    public int horizontalGrowthRadius = 5;
    public int verticalGrowthRadius = 2;
    public boolean allowCrops = true;
    public boolean growGrass = false;
    public float rainModifier = 1.1f;
    public boolean showParticles = true;

    @Override
    public String getName() {
        return "danceofgrowth";
    }
}
