DESCRIPTION = "TI DSP IQ Math Library"
HOMEPAGE = "http://www.ti.com/tool/sprc542"
SECTION = "devel"
LICENSE = "TI"

DEPENDS = "ti-dsplink"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-eula-unpack.inc

PR = "r0"

S = "${WORKDIR}/iqmathlib_${PV}"

SRC_URI = "\
    http://software-dl.ti.com/dsps/dsps_public_sw/c6000/web/c64p_iqmath/latest/exports//c64xplus-iqmath_${PV}_Linux-x86_Setup.bin;name=iqmathlibbin \
"

BINFILE="c64xplus-iqmath_${PV}_Linux-x86_Setup.bin"
TI_BIN_UNPK_CMDS="Y:workdir"
TI_BIN_UNPK_WDEXT="/iqmathlib_${PV}"

do_install() {
    install -d ${D}${installdir}/ti-iqmathlib
    cp -pPrf ${S}/* ${D}${installdir}/ti-iqmathlib
    install -d ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/DEBUG
    install -d ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/RELEASE
    cp -pPrf ${S}/lib/*.lib ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/DEBUG/
    cp -pPrf ${S}/lib/*.lib ${D}${LINK_INSTALL_DIR_RECIPE}/dsplink/dsp/BUILD/OMAP3530_0/EXPORT/RELEASE/
}

