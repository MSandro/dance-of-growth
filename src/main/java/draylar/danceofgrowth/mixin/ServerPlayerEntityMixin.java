package draylar.danceofgrowth.mixin;

import com.mojang.authlib.GameProfile;
import draylar.danceofgrowth.DanceOfGrowth;
import draylar.danceofgrowth.impl.PlayerReader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements PlayerReader {

    private float lastJump = 1000;
    private float lastSprint = 1000;

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void tickStatus(CallbackInfo ci) {
        if(jumping) {
            lastJump = 0;
        }

        if(isSprinting()) {
            lastSprint = 0;
        }

        lastJump++;
        lastSprint++;
    }

    @Override
    public void setSneaking(boolean sneaking) {
        if(sneaking) {
            DanceOfGrowth.onShift(me());
        }

        super.setSneaking(sneaking);
    }

    @Unique
    private ServerPlayerEntity me() {
        return (ServerPlayerEntity) (Object) this;
    }

    @Override
    public boolean wasRecentlySprinting() {
        return lastJump < DanceOfGrowth.CONFIG.jumpTime;
    }

    @Override
    public boolean wasRecentlyJumping() {
        return lastSprint < DanceOfGrowth.CONFIG.sprintTime;
    }

    private ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }
}
