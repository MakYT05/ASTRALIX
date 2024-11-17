package org.zeith.modid.init;

import org.zeith.hammeranims.api.animation.AnimationHolder;
import org.zeith.hammeranims.api.animation.IAnimationContainer;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface AnimationsMI {
    @RegistryName("rumaruka_animation")
    IAnimationContainer RUMARUKA_ANIMATION = IAnimationContainer.createNoSuffix();
    AnimationHolder RUMARUKA_ANIMATION_GO = RUMARUKA_ANIMATION.holder("rumaruka_go");
    AnimationHolder RUMARUKA_ANIMATION_HEAD = RUMARUKA_ANIMATION.holder("rumaruka_head");
}