package org.zeith.modid.init;

import org.zeith.hammeranims.api.geometry.IGeometryContainer;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ModelsMI {
    @RegistryName("zeith_mob")
    IGeometryContainer ZEITH_MOB_MODEL = IGeometryContainer.createNoSuffix();

    @RegistryName("moontlex_mob")
    IGeometryContainer MOONTLEX_MOB_MODEL = IGeometryContainer.createNoSuffix();

    @RegistryName("astral_zombie")
    IGeometryContainer ASTRAL_ZOMBIE_MODEL = IGeometryContainer.createNoSuffix();

    @RegistryName("rumaruka_mob")
    IGeometryContainer RUMARUKA_MOB_MODEL = IGeometryContainer.createNoSuffix();
}