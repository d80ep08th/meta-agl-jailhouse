# yocto-jailhouse
tree of the repo

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
