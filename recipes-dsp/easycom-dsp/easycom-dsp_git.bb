DESCRIPTION = "DSP side abstraction of TI's DSPLink package"
SECTION = "apps"
PRIORITY = "optional"

LICENSE = "GPLv2+"
DEPENDS = "ti-dsplink ti-dsplib ti-iqmathlib"
RDEPENDS = "ti-dsplink"

require recipes-ti/includes/ti-paths.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PV = "0.1"
PR = "r0"

SRC_URI = "\
    git://github.com/alfayez/easyCom-dsp.git;protocol=https \
    file://fix-paths.patch \
"
SRCREV = "master"

S = "${WORKDIR}/git/easycom-dsp-dsplink"

# SOC_FAMILY configuration

DSPLINKPLATFORM_dm6446    = "DAVINCI"
DSPLINKPLATFORM_dm6467    = "DAVINCIHD"
DSPLINKPLATFORM_omapl137  = "OMAPL1XX"
DSPLINKPLATFORM_omapl138  = "OMAPL138"
DSPLINKPLATFORM_omap3     = "OMAP3530"
DSPLINKPLATFORM          ?= "<UNDEFINED_DSPLINKPLATFORM>"

DSPLINKDSP_dm6446         = "DM6446GEM_0"
DSPLINKDSP_dm6467         = "DM6467GEM_0"
DSPLINKDSP_omapl137       = "OMAPL1XXGEM_0"
DSPLINKDSP_omapl138       = "OMAPL138GEM_0"
DSPLINKDSP_omap3          = "OMAP3530_0"
DSPLINKDSP               ?= "<UNDEFINED_DSPLINKDSP>"

export DSPLINK="${LINK_INSTALL_DIR}/dsplink"

do_configure_prepend() {
	find ${S} -name COMPONENT | xargs sed -i s:'COMP_PATH .*':'COMP_PATH       \:= ${S}':g
}

do_compile_prepend() {
    oe_runmake clean
}

do_install() {
    install -d ${D}/${datadir}/easycom-dsp
    cp ${DSPLINK}/dsp/export/BIN/DspBios/${DSPLINKPLATFORM}/${DSPLINKDSP}/RELEASE/loopAl2.out ${D}/${datadir}/easycom-dsp
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
