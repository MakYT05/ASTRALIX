package org.zeith.modid.init;

import org.zeith.hammeranims.api.geometry.IGeometryContainer;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModelsMI {
    @RegistryName("zeith_mob")
    IGeometryContainer ZEITH_MOB_MODEL = IGeometryContainer.createNoSuffix();
}