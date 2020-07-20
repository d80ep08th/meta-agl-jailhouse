SUMMARY = "Linux-based partitioning hypervisor"
DESCRIPTION = "Jailhouse is a partitioning Hypervisor based on Linux. It is able to run bare-metal applications or (adapted) \
operating systems besides Linux. For this purpose, it configures CPU and device virtualization features of the hardware \
platform in a way that none of these domains, called 'cells' here, can interfere with each other in an unacceptable way."
HOMEPAGE = "https://github.com/siemens/jailhouse"
SECTION = "jailhouse"
LICENSE = "GPL-2.0 & BSD-2-Clause"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9fa7f895f96bde2d47fd5b7d95b6ba4d \
"

SRCREV = "4ce7658dddfd5a1682a379d5ac46657e93fe1ff0"

PV = "0.12+git${SRCPV}"

SRC_URI = " git://github.com/siemens/jailhouse "

DEPENDS = " \
	virtual/kernel \
	dtc-native \
	python3-mako-native \
	python3-mako \
	make-native \"
	
RDEPENDS_${PN} += "\
	python3-curses\
	python3-datetime\
	python3-mmap\
"

require jailhouse-arch.inc
inherit module python3native bash-completion deploy setuptools3


S = "${WORKDIR}/git"
B = "${S}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
#COMPATIBLE_MACHINE = "(raspberrypi4)"

JH_DATADIR ?= "${datadir}/jailhouse"
JH_EXEC_DIR ?= "${libexecdir}/jailhouse"
CELL_DIR ?= "${JH_DATADIR}/cells"
CELLCONF_DIR ?= "${JH_DATADIR}/configs"
INMATES_DIR ?= "${JH_DATADIR}/inmates"

JH_CELL_FILES ?= "*.cell"

#JH_INMATE_DTB ?= ""
#JH_LINUX_DEMO_CELL ?= ""
#JH_SYSCONFIG_CELL ?= ""
#INITRAMFS_IMAGE ?= ""
#JH_RAMFS_IMAGE ?= "${INITRAMFS_IMAGE}"
#JH_CMDLINE ?= ""


EXTRA_OEMAKE = " \
	ARCH=${JH_ARCH} \
	CROSS_COMPILE=${TARGET_PREFIX} \
	CC='${CC}' \
	KDIR=${STAGING_KERNEL_BUILDDIR} \"

do_configure() {
	sed -i -e "s#env python#env python3#g" ${S}/scripts/*
	sed -i -e "s#env python#env python3#g" ${S}/tools/*
	if [ -d ${STAGING_DIR_HOST}/${CELLCONF_DIR} ];
	then
		cp ${STAGING_DIR_HOST}/${CELLCONF_DIR}/*.c ${S}/configs/
	fi
	
}


#do_configure[depends] += "virtual/kernel:do_shared_workdir"
#do_configure[depends] += "virtual/kernel:do_compile_kernelmodules"

#USER_SPACE_CFLAGS = '${CFLAGS} -DLIBEXECDIR=\\\"${libexecdir}\\\" \
#                    -DJAILHOUSE_VERSION=\\\"$JAILHOUSE_VERSION\\\" \
#                    -Wall -Wextra -Wmissing-declarations -Wmissing-prototypes -Werror \
#                    -I../driver'

#TOOLS_SRC_DIR = "${S}/tools"

do_compile() {
	oe_runmake V=1
}

do_install() {
	# Install pyjailhouse python modules needed by the tools
	distutils3_do_install

	# We want to install the python tools, but we do not want to use pip...
	# At least with v0.10, we can work around this with
	# 'PIP=":" PYTHON_PIP_USEABLE=yes'
	oe_runmake PIP=: PYTHON=python3 PYTHON_PIP_USEABLE=yes DESTDIR=${D} install

	install -d ${D}${CELL_DIR}
	install -m 0644 ${B}/configs/${JH_ARCH}/${JH_CELL_FILES} ${D}${CELL_DIR}/ || true

	install -d ${D}${INMATES_DIR}
	install -m 0644 ${B}/inmates/demos/${JH_ARCH}/*.bin ${D}${INMATES_DIR} || true

	install -d ${D}/boot
	if [ -n "${JH_RAMFS_IMAGE}" ]
	then
		if [ -f ${DEPLOY_DIR_IMAGE}/${JH_RAMFS_IMAGE}-${MACHINE}.cpio ]
		then
			install -m 0644 ${DEPLOY_DIR_IMAGE}/${JH_RAMFS_IMAGE}-${MACHINE}.cpio ${D}/boot
		else
			bberror "Could not find JH_RAMFS_IMAGE (${JH_RAMFS_IMAGE}-${MACHINE}.cpio)!"
			bberror "Please make sure that \"cpio\" is in IMAGE_FSTYPES."
		fi
	fi

	if [ -n "${JH_INMATE_DTB}" -a -n "${JH_LINUX_DEMO_CELL}" ]; then
		cd ${TOOLS_SRC_DIR}

		echo "#! /bin/sh" > ${D}${JH_DATADIR}/linux-demo.sh
		echo "jailhouse enable ${CELL_DIR}/${JH_SYSCONFIG_CELL}" >> ${D}${JH_DATADIR}/linux-demo.sh
		./jailhouse-cell-linux -w ${D}${JH_DATADIR}/${JH_INMATE_DTB} \
			-a ${JH_ARCH} -c "${JH_CMDLINE}" \
			-d ../configs/${JH_ARCH}/dts/${JH_INMATE_DTB} \
			-i ${D}/boot/${JH_RAMFS_IMAGE}-${MACHINE}.cpio \
			${D}${CELL_DIR}/${JH_LINUX_DEMO_CELL} \
			${DEPLOY_DIR_IMAGE}/Image \
			| tr -cd '\11\12\15\40-\176' \
			>> ${D}${JH_DATADIR}/linux-demo.sh

		sed -i -e 's,^Modified device tree written.*,,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e 's,\${D},,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e 's, linux-loader.bin, ${JH_EXEC_DIR}/linux-loader.bin,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e 's,\${DEPLOY_DIR_IMAGE},/boot,g' ${D}${JH_DATADIR}/linux-demo.sh
		sed -i -e '/^\s*$/d' ${D}${JH_DATADIR}/linux-demo.sh
		chmod +x ${D}${JH_DATADIR}/linux-demo.sh
	fi
}








PACKAGE_BEFORE_PN = "kernel-module-jailhouse pyjailhouse ${PN}-tools ${PN}-demos"

#removed /boot from FILES_${PN}

FILES_${PN} = "${base_libdir}/firmware ${libexecdir} ${sbindir} ${JH_DATADIR} "
FILES_pyjailhouse = "${PYTHON_SITEPACKAGES_DIR}"
FILES_${PN}-tools = "${libexecdir}/${BPN}/${BPN}-* ${JH_DATADIR}/*.tmpl"
FILES_${PN}-demos = "${JH_DATADIR}/ ${sbindir}/ivshmem-demo"



RDEPENDS_${PN}-tools = " \
	pyjailhouse \
	python3-mmap \
	python3-math \
	python3-datetime \
	python3-curses \
	python3-compression \
	python3-mako \
	"

RDEPENDS_pyjailhouse = " \
	python3-core \
	python3-ctypes \
	python3-fcntl \
	python3-shell \
	"

RRECOMMENDS_${PN} = "${PN}-tools"
INSANE_SKIP_${PN} = "ldflags"
KERNEL_MODULE_AUTOLOAD += "jailhouse"

# Any extra cells/inmates from external recipes/packages
# CELLS = ""

