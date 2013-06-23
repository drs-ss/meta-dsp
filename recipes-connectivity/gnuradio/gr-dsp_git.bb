DESCRIPTION = "DSP source/sink blocks for GNU Radio"
SECTION = "apps"
PRIORITY = "optional"
LICENSE = "GPLv3"
DEPENDS = "gnuradio python swig-native easycom-gpp"

PV = "0.1"
PR = "r2"

inherit distutils-base cmake pkgconfig

export BUILD_SYS
export HOST_SYS="${MULTIMACH_TARGET_SYS}"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/drs-ss/gr-dsp \
    file://use-abs-path-for-loopAl2.patch \
"
SRCREV = "82e94723acb91972a364d069f4e03f2cabd3e4b4"
	    
S = "${WORKDIR}/git"

FILESPATHPKG_prepend = "${PN}-git:"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = ""
EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"

PACKAGES =+ "\
    ${PN}-grc \
    ${PN}-examples \
"

FILES_${PN}-grc = "${datadir}/gnuradio/grc"

FILES_${PN} += "\
    ${PYTHON_SITEPACKAGES_DIR}/gnuradio/* \
    ${datadir}/gnuradio/* \
"

FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/gnuradio/.debug \
                    ${PYTHON_SITEPACKAGES_DIR}/gnuradio/*/.debug \
		   "
FILES_${PN}-examples = "${datadir}/gnuradio/examples"

RDEPENDS_${PN} = "gnuradio easycom-gpp easycom-dsp"
