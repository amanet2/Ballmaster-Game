package com.app.game;

import com.app.engine.utils.gDict;

public class gameUtilsTest {
    public static void test() {
        StringBuilder cvarDictString = new StringBuilder("{");
        for(String k : gameCVars.get().keySet()) {
            cvarDictString.append(k).append("=").append(gameCVars.get().getCVarValue(k)).append(",");
        }
        cvarDictString.deleteCharAt(cvarDictString.lastIndexOf(","));
        cvarDictString.append("}");

        gDict cVarDict = new gDict(cvarDictString.toString());
        gDict fooDict = new gDict("{foo=bar, bar=}");

        System.out.println(cVarDict.toString());
        System.out.println(cVarDict.get("showfps"));
        System.out.println(fooDict.toString());
        System.out.println(fooDict.get("foo"));
    }
}
