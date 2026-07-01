package com.nebbii.zagdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ArchipelagoLocationMap {
    public static final Map<String, Long> CELL_TO_ID = createCellToIdMap();
    public static final Map<Long, String> ID_TO_CELL = createIdToCellMap();

    private ArchipelagoLocationMap() {}

    public static long getId(String cell) {
        if (!(CELL_TO_ID.containsKey(cell))) {
            return -1L;
        }

        return CELL_TO_ID.get(cell);
    }

    public static String getCell(long id) {
        if (!ID_TO_CELL.containsKey(id)) {
            return null;
        }

        return ID_TO_CELL.get(id);
    }

    private static Map<String, Long> createCellToIdMap() {
        Map<String, Long> map = new HashMap<>();

        // Overworld item / pickup locations
        map.put("d24_0", 1L);    // Overworld D24 Boomerang
        map.put("e20_0", 2L);    // Overworld E20 Full Pitcher
        map.put("f28_0", 3L);    // Overworld F28 Ladder
        map.put("h23_0", 4L);    // Overworld H23 Wand
        map.put("j22_1", 5L);    // Overworld J22 Firestorm
        map.put("j22a_4", 6L);   // Overworld J22a Dagger
        map.put("j24_1", 7L);    // Overworld J24 Empty Pitcher
        map.put("j24_2", 8L);    // Overworld J24 Vial of Wind

        // Shrine of Earth item / pickup locations
        map.put("s104_2", 9L);   // Shrine of Earth S104 1st Underworld Map
        map.put("s105_3", 10L);  // Shrine of Earth S105 Compass
        map.put("s108_1", 11L);  // Shrine of Earth S108 Jade Ring
        map.put("s116_0", 12L);  // Shrine of Earth S116 Red Boots
        map.put("s122_0", 13L);  // Shrine of Earth S122 1st Celestial Sign

        // Overworld enemy locations
        map.put("d24_1", 1001L);

        map.put("e21_0", 1002L);
        map.put("e21_1", 1003L);
        map.put("e22_0", 1004L);
        map.put("e22_1", 1005L);
        map.put("e22_2", 1006L);
        map.put("e22_3", 1007L);
        map.put("e23_0", 1008L);
        map.put("e23_1", 1009L);
        map.put("e23_2", 1010L);
        map.put("e24_0", 1011L);
        map.put("e24_1", 1012L);

        map.put("f21_0", 1013L);
        map.put("f21_1", 1014L);
        map.put("f21_2", 1015L);
        map.put("f21_3", 1016L);
        map.put("f21_4", 1017L);
        map.put("f25_0", 1018L);
        map.put("f25_1", 1019L);
        map.put("f25_2", 1020L);
        map.put("f27_0", 1021L);
        map.put("f27_1", 1022L);
        map.put("f27_2", 1023L);

        map.put("g21_0", 1024L);
        map.put("g21_1", 1025L);
        map.put("g21_2", 1026L);
        map.put("g25_0", 1027L);
        map.put("g25_1", 1028L);
        map.put("g25_2", 1029L);
        map.put("g26_0", 1030L);
        map.put("g26_1", 1031L);
        map.put("g26_2", 1032L);
        map.put("g27_0", 1033L);
        map.put("g27_1", 1034L);
        map.put("g28_0", 1035L);
        map.put("g28_1", 1036L);
        map.put("g29_0", 1037L);
        map.put("g29_1", 1038L);
        map.put("g30_0", 1039L);
        map.put("g30_1", 1040L);
        map.put("g30_2", 1041L);
        map.put("g31_0", 1042L);
        map.put("g31_1", 1043L);
        map.put("g31_2", 1044L);
        map.put("g31_3", 1045L);

        map.put("h21_0", 1046L);
        map.put("h21_1", 1047L);
        map.put("h21_2", 1048L);
        map.put("h21_3", 1049L);
        map.put("h21_4", 1050L);
        map.put("h25_0", 1051L);
        map.put("h25_1", 1052L);
        map.put("h25_2", 1053L);
        map.put("h26_0", 1054L);
        map.put("h26_1", 1055L);
        map.put("h26_2", 1056L);
        map.put("h27_0", 1057L);
        map.put("h27_1", 1058L);
        map.put("h27_2", 1059L);
        map.put("h28_0", 1060L);
        map.put("h28_1", 1061L);
        map.put("h28_2", 1062L);

        map.put("i21_0", 1063L);
        map.put("i22_0", 1064L);
        map.put("i22_1", 1065L);
        map.put("i22_2", 1066L);
        map.put("i22_3", 1067L);
        map.put("i22_4", 1068L);
        map.put("i23_0", 1069L);
        map.put("i23_1", 1070L);
        map.put("i23_2", 1071L);
        map.put("i24_0", 1072L);
        map.put("i24_1", 1073L);
        map.put("i24_2", 1074L);
        map.put("i24_3", 1075L);
        map.put("i24_4", 1076L);
        map.put("i25_0", 1077L);
        map.put("i25_1", 1078L);
        map.put("i25_2", 1079L);
        map.put("i25_3", 1080L);
        map.put("i26_0", 1081L);
        map.put("i26_1", 1082L);
        map.put("i26_2", 1083L);

        map.put("j23_0", 1084L);
        map.put("j23_1", 1085L);
        map.put("j23_2", 1086L);
        map.put("j23_3", 1087L);
        map.put("j25_0", 1088L);
        map.put("j25_1", 1089L);
        map.put("j26_0", 1090L);
        map.put("j26_1", 1091L);

        map.put("s101a_0", 1092L);

        // Shrine of Earth enemy locations
        map.put("s103_0", 2001L);
        map.put("s103_1", 2002L);

        map.put("s104_0", 2003L);
        map.put("s104_1", 2004L);

        map.put("s105_0", 2005L);
        map.put("s105_1", 2006L);
        map.put("s105_2", 2007L);

        map.put("s106_0", 2008L);
        map.put("s106_1", 2009L);

        map.put("s110_0", 2010L);
        map.put("s110_1", 2011L);
        map.put("s110_2", 2012L);
        map.put("s110_3", 2013L);

        map.put("s111_0", 2014L);

        map.put("s115_0", 2015L);
        map.put("s115_1", 2016L);

        map.put("s116_1", 2017L);
        map.put("s116_2", 2018L);
        map.put("s116_3", 2019L);

        map.put("s107_0", 2020L);
        map.put("s112_0", 2021L);
        map.put("s120_0", 2022L);
        map.put("s121_0", 2023L);

        return Collections.unmodifiableMap(map);
    }

    private static Map<Long, String> createIdToCellMap() {
        Map<Long, String> map = new HashMap<>();

        for (Map.Entry<String, Long> entry : CELL_TO_ID.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        return Collections.unmodifiableMap(map);
    }
}
