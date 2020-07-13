# yocto-jailhouse
tree of the repo

.
├── conf
│   └── layer.conf
├── README.md
├── recipes-extended
│   └── jailhouse
│       ├── jailhouse
│       │   └── 0001-tools-update-shebang-in-helper-scripts-for-python3.patch
│       ├── jailhouse-arch.inc
│       └── jailhouse_git.bb
├── recipes-kernel
│   ├── hello-mod
│   │   ├── files
│   │   │   ├── COPYING
│   │   │   ├── hello.c
│   │   │   └── Makefile
│   │   └── hello-mod_0.1.bb
│   ├── linux
│   │   ├── 0001-x86-jailhouse-Improve-setup-data-version-comparison.patch
│   │   ├── 0002-x86-jailhouse-Only-enable-platform-UARTs-if-availabl.patch
│   │   ├── 0003-jailhouse-Add-simple-debug-console-via-the-hyperviso.patch
│   │   ├── 0004-arm-Export-__boot_cpu_mode-for-use-in-Jailhouse-driv.patch
│   │   ├── 0005-mm-Re-export-ioremap_page_range.patch
│   │   ├── 0006-arm-arm64-export-__hyp_stub_vectors.patch
│   │   ├── 0007-x86-Export-lapic_timer_period.patch
│   │   ├── 0008-arm64-dts-marvell-armada-37xx-Set-pci-domain.patch
│   │   ├── 0009-arm64-dts-marvell-armada-8030-mcbin-Set-pci-domain.patch
│   │   ├── 0010-uio-Enable-read-only-mappings.patch
│   │   ├── 0011-ivshmem-Add-header-file.patch
│   │   ├── 0012-uio-Add-driver-for-inter-VM-shared-memory-device.patch
│   │   ├── 0013-ivshmem-net-virtual-network-device-for-Jailhouse.patch
│   │   ├── 0014-ivshmem-net-Map-shmem-region-as-RAM.patch
│   │   ├── 0015-ivshmem-net-fix-race-in-state-machine.patch
│   │   ├── 0016-ivshmem-net-Remove-unused-variable.patch
│   │   ├── 0017-ivshmem-net-Enable-INTx.patch
│   │   ├── 0018-ivshmem-net-Improve-identification-of-resources.patch
│   │   ├── 0019-ivshmem-net-Switch-to-reset-state-on-each-net-stop-a.patch
│   │   ├── 0020-ivshmem-net-Add-ethtool-register-dump.patch
│   │   ├── 0021-ivshmem-net-Fix-stuck-state-machine-during-setup.patch
│   │   ├── 0022-ivshmem-net-Switch-to-relative-descriptor-addresses.patch
│   │   ├── 0023-ivshmem-net-Switch-to-pci_alloc_irq_vectors.patch
│   │   ├── 0024-ivshmem-net-fill-in-and-check-used-descriptor-chain-.patch
│   │   ├── 0025-ivshmem-net-slightly-improve-debug-output.patch
│   │   ├── 0026-ivshmem-net-set-and-check-descriptor-flags.patch
│   │   ├── 0027-ivshmem-net-add-MAC-changing-interface.patch
│   │   ├── 0028-ivshmem-net-Silence-compiler-warning.patch
│   │   ├── 0029-ivshmem-net-Fix-bogus-transition-to-RESET-state.patch
│   │   ├── 0030-ivshmem-net-Refactor-and-comment-ivshm_net_state_cha.patch
│   │   ├── 0031-ivshmem-net-Switch-to-netdev_xmit_more-helper.patch
│   │   ├── 0032-ivshmem-net-Adjust-to-reworked-version-of-ivshmem-in.patch
│   │   └── jailhouse.cfg
│   ├── linux-raspberrypi_5.4%.bbappend
│   └── linux-raspberrypi_%.bbappend
├── rpi4.conf
└── templates
    └── feature
        └── agl-jailhouse
            ├── 50_bblayers.conf.inc
            └── 50_local.conf.inc



-----------------------------------
Summary of why each file is needed 
-----------------------------------
.
├── README.md
├── recipes-bsp
│   ├── arm-trusted-firmware
│   │   ├── arm-trusted-firmware_2.2.inc
│   │   ├── arm-trusted-firmware-rpi4_2.2.bb
│   │   └── files
│   │       ├── 0001-rpi3-4-Add-support-for-offlining-CPUs.patch
│   │       └── rpi-rules
│   └── rpi-firmware
│       ├── files
│       │   ├── cmdline.txt
│       │   └── config.txt
│       └── rpi-firmware_1.20190925.bb
├── recipes-kernel
│   └── linux
│       ├── files
│       │   ├── 0001-arm64-dts-zcu100-revC-Give-wifi-some-time-after-powe.patch
│       │   ├── 0001-ARM-dts-orange-pi-zero-Adjust-wifi-settings.patch
│       │   ├── 0001-pwrseq_simple-Workaround-for-missing-device-tree-ent.patch
│       │   ├── arm64_defconfig_5.4
│       │   ├── preempt-rt.cfg
│       │   └── rpi4_defconfig_5.4
│       ├── linux-jailhouse_5.4.17.bb
│       ├── linux-jailhouse_5.4.inc
│       ├── linux-jailhouse-rpi_5.4.16.bb
│       └── linux-jailhouse-rt_5.4.17-rt9.bb
└── rpi4.conf
