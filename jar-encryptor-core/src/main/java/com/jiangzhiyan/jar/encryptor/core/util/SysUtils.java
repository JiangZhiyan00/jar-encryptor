package com.jiangzhiyan.jar.encryptor.core.util;

import lombok.experimental.UtilityClass;
import oshi.SystemInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统工具类
 */
@UtilityClass
public class SysUtils {

    private static final SystemInfo SYSTEM_INFO = new SystemInfo();

    /**
     * 获取mac地址
     *
     * @return mac 列表
     */
    public static List<String> getMacList() {
        return SYSTEM_INFO.getHardware().getNetworkIFs().stream()
                .filter(x -> x != null && x.getMacaddr() != null && !x.getMacaddr().trim().isEmpty())
                .map(x -> x.getMacaddr().trim().toUpperCase())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * 获取cpu序列号
     *
     * @return 序列号
     */
    public static String getCPUSerialNumber() {
        String processorID = SYSTEM_INFO.getHardware().getProcessor().getProcessorIdentifier().getProcessorID();
        return processorID == null ? "" : processorID.trim().toUpperCase();
    }


    /**
     * 获取主板序列号
     *
     * @return 主板序列号
     */
    public static String getMotherBoardSerialNumber() {
        String serialNumber = SYSTEM_INFO.getHardware().getComputerSystem().getSerialNumber();
        return serialNumber == null ? "" : serialNumber.trim().toUpperCase();
    }

    /**
     * 生成机器码
     *
     * @return 机器码
     */
    public static char[] makeMachineCode() {
        char[] c1 = EncryptUtils.md5(getMacList().toString().toCharArray());
        char[] c2 = EncryptUtils.md5(getCPUSerialNumber().toCharArray());
        char[] c3 = EncryptUtils.md5(getMotherBoardSerialNumber().toCharArray());
        char[] chars = StrUtils.merger(c1, c2, c3);
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
        return chars;
    }

    public static void main(String[] args) {
        System.out.println(makeMachineCode());
    }
}
