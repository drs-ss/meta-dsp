DESCRIPTION = "TI DSPLib Library"
HOMEPAGE = "http://www.ti.com/tool/sprc265"
SECTION = "devel"
LICENSE = "TI"

DEPENDS = "ti-dsplink"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-eula-unpack.inc

PR = "r0"

S = "${WORKDIR}/dsplib_${PV}"

SRC_URI = "\
    http://software-dl.ti.com/sdoemb/sdoemb_public_sw/dsplib/latest/exports/dsplib_c64Px_${PV}_Linux.bin;name=dsplibbin \
"

BINFILE="dsplib_c64Px_${PV}_Linux.bin"
TI_BIN_UNPK_CMDS="Y:workdir"
TI_BIN_UNPK_WDEXT="/dsplib_${PV}"

do_install() {
    install -d ${D}${DSPLIB_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${DSPLIB_INSTALL_DIR_RECIPE}
    install -d ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/DEBUG
    install -d ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/RELEASE
    cp -pPrf ${S}/lib/*.a* ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/DEBUG/
    cp -pPrf ${S}/lib/*.a* ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/RELEASE/
}

