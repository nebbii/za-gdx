package com.nebbii.zagdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ArchipelagoLocationMap {
    public static final Map<String, Integer> CELL_TO_ID = createCellToIdMap();

    private ArchipelagoLocationMap() {}

    public static Integer getId(String cell) {
        if (!(CELL_TO_ID.containsKey(cell))) {
            return -1;
        }

        return CELL_TO_ID.get(cell);
    }

    private static Map<String, Integer> createCellToIdMap() {
        Map<String, Integer> map = new HashMap<>();

        // Overworld item / pickup locations
        map.put("d24_0", 1);    // Overworld D24 Boomerang
        map.put("e20_0", 2);    // Overworld E20 Full Pitcher
        map.put("f28_0", 3);    // Overworld F28 Ladder
        map.put("h23_0", 4);    // Overworld H23 Wand
        map.put("j22_1", 5);    // Overworld J22 Firestorm
        map.put("j22a_4", 6);   // Overworld J22a Dagger
        map.put("j24_1", 7);    // Overworld J24 Empty Pitcher
        map.put("j24_2", 8);    // Overworld J24 Vial of Wind

        // Shrine of Earth item / pickup locations
        map.put("s104_2", 9);   // Shrine of Earth S104 1st Underworld Map
        map.put("s105_3", 10);  // Shrine of Earth S105 Compass
        map.put("s108_1", 11);  // Shrine of Earth S108 Jade Ring
        map.put("s116_0", 12);  // Shrine of Earth S116 Red Boots
        map.put("s122_0", 13);  // Shrine of Earth S122 1st Celestial Sign

        // Overworld enemy locations
        map.put("d24_1", 1001);

        map.put("e21_0", 1002);
        map.put("e21_1", 1003);
        map.put("e22_0", 1004);
        map.put("e22_1", 1005);
        map.put("e22_2", 1006);
        map.put("e22_3", 1007);
        map.put("e23_0", 1008);
        map.put("e23_1", 1009);
        map.put("e23_2", 1010);
        map.put("e24_0", 1011);
        map.put("e24_1", 1012);

        map.put("f21_0", 1013);
        map.put("f21_1", 1014);
        map.put("f21_2", 1015);
        map.put("f21_3", 1016);
        map.put("f21_4", 1017);
        map.put("f25_0", 1018);
        map.put("f25_1", 1019);
        map.put("f25_2", 1020);
        map.put("f27_0", 1021);
        map.put("f27_1", 1022);
        map.put("f27_2", 1023);

        map.put("g21_0", 1024);
        map.put("g21_1", 1025);
        map.put("g21_2", 1026);
        map.put("g25_0", 1027);
        map.put("g25_1", 1028);
        map.put("g25_2", 1029);
        map.put("g26_0", 1030);
        map.put("g26_1", 1031);
        map.put("g26_2", 1032);
        map.put("g27_0", 1033);
        map.put("g27_1", 1034);
        map.put("g28_0", 1035);
        map.put("g28_1", 1036);
        map.put("g29_0", 1037);
        map.put("g29_1", 1038);
        map.put("g30_0", 1039);
        map.put("g30_1", 1040);
        map.put("g30_2", 1041);
        map.put("g31_0", 1042);
        map.put("g31_1", 1043);
        map.put("g31_2", 1044);
        map.put("g31_3", 1045);

        map.put("h21_0", 1046);
        map.put("h21_1", 1047);
        map.put("h21_2", 1048);
        map.put("h21_3", 1049);
        map.put("h21_4", 1050);
        map.put("h25_0", 1051);
        map.put("h25_1", 1052);
        map.put("h25_2", 1053);
        map.put("h26_0", 1054);
        map.put("h26_1", 1055);
        map.put("h26_2", 1056);
        map.put("h27_0", 1057);
        map.put("h27_1", 1058);
        map.put("h27_2", 1059);
        map.put("h28_0", 1060);
        map.put("h28_1", 1061);
        map.put("h28_2", 1062);

        map.put("i21_0", 1063);
        map.put("i22_0", 1064);
        map.put("i22_1", 1065);
        map.put("i22_2", 1066);
        map.put("i22_3", 1067);
        map.put("i22_4", 1068);
        map.put("i23_0", 1069);
        map.put("i23_1", 1070);
        map.put("i23_2", 1071);
        map.put("i24_0", 1072);
        map.put("i24_1", 1073);
        map.put("i24_2", 1074);
        map.put("i24_3", 1075);
        map.put("i24_4", 1076);
        map.put("i25_0", 1077);
        map.put("i25_1", 1078);
        map.put("i25_2", 1079);
        map.put("i25_3", 1080);
        map.put("i26_0", 1081);
        map.put("i26_1", 1082);
        map.put("i26_2", 1083);

        map.put("j23_0", 1084);
        map.put("j23_1", 1085);
        map.put("j23_2", 1086);
        map.put("j23_3", 1087);
        map.put("j25_0", 1088);
        map.put("j25_1", 1089);
        map.put("j26_0", 1090);
        map.put("j26_1", 1091);

        map.put("s101a_0", 1092);

        // Shrine of Earth enemy locations
        map.put("s103_0", 2001);
        map.put("s103_1", 2002);

        map.put("s104_0", 2003);
        map.put("s104_1", 2004);

        map.put("s105_0", 2005);
        map.put("s105_1", 2006);
        map.put("s105_2", 2007);

        map.put("s106_0", 2008);
        map.put("s106_1", 2009);

        map.put("s110_0", 2010);
        map.put("s110_1", 2011);
        map.put("s110_2", 2012);
        map.put("s110_3", 2013);

        map.put("s111_0", 2014);

        map.put("s115_0", 2015);
        map.put("s115_1", 2016);

        map.put("s116_1", 2017);
        map.put("s116_2", 2018);
        map.put("s116_3", 2019);

        map.put("s107_0", 2020);
        map.put("s112_0", 2021);
        map.put("s120_0", 2022);
        map.put("s121_0", 2023);

        return Collections.unmodifiableMap(map);
    }
}
