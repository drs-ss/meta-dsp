DESCRIPTION = "DSP source/sink blocks for GNU Radio"
SECTION = "apps"
PRIORITY = "optional"
LICENSE = "GPLv2+"
DEPENDS = "linux-libc-headers libgcc ti-dsplink"
RDEPENDS = "ti-dsplink"

require recipes-ti/includes/ti-paths.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PV = "0.1"

SRC_URI = "git://github.com/alfayez/easyCom-gpp.git;protocol=https \
    file://make-lib.patch \
"
SRCREV = "master"

S = "${WORKDIR}/git/easycom-gpp-dsplink"

export DSPLINK="${LINK_INSTALL_DIR}/dsplink"

do_configure_prepend() {
	find ${S} -name COMPONENT | xargs sed -i s:'COMP_PATH .*':'COMP_PATH       \:= ${S}':g
}

do_compile() {
    make \
      CROSS_COMPILE="${TARGET_PREFIX}" \
      CC="${TOOLCHAIN_PATH}/${TARGET_PREFIX}gcc" \
      AR="${TOOLCHAIN_PATH}/${TARGET_PREFIX}ar" \
      LD="${TOOLCHAIN_PATH}/${TARGET_PREFIX}ld" \
      COMPILER="${TOOLCHAIN_PATH}/${TARGET_PREFIX}gcc" \
      ARCHIVER="${TOOLCHAIN_PATH}/${TARGET_PREFIX}ar" \
      KERNEL_DIR="${STAGING_KERNEL_DIR}" \
      all

	${CC} ${CFLAGS} ${LDFLAGS} -shared -Wl,-soname,libloopgppAl2.so.1 -o libloopgppAl2.so.1.0 \
        ${DSPLINK}/gpp/BUILD/EXPORT/DEBUG/loopgppAl2.o
#        ${DSPLINK}/gpp/BUILD/EXPORT/RELEASE/loopgppAl2.o
}


do_install() {
	oe_libinstall -so -s libloopgppAl2 ${D}${libdir}
	install -d ${D}${includedir}
	install -m 0644 ${S}/gnuradio_beagleboard_dsp.h ${D}${includedir}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
