package org.helper.weka;

import weka.classifiers.Classifier;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;

public class WekaCSGO implements Serializable {
    public Classifier classifier;
    public WekaCSGO(Classifier classifier)
    {
        this.classifier = classifier;
    }

    public String getPrediction(String lineForPredict) throws Exception {

        StringBuilder stringBuilder = new StringBuilder("@RELATION TEST\n" +
                "@ATTRIBUTE TEAMRANK_ft NUMERIC\n" +
                "@ATTRIBUTE TEAMRANK_st NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERHS_ft NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERKD_ft NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERROUNDS_ft NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERHLTV_ft NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERHS_ft NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERKD_ft NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERROUNDS_ft NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERHLTV_ft NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERHS_ft NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERKD_ft NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERROUNDS_ft NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERHLTV_ft NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERHS_ft NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERKD_ft NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERROUNDS_ft NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERHLTV_ft NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERHS_ft NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERKD_ft NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERROUNDS_ft NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERHLTV_ft NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERHS_st NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERKD_st NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERROUNDS_st NUMERIC\n" +
                "@ATTRIBUTE FIRSTPLAYERHLTV_st NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERHS_st NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERKD_st NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERROUNDS_st NUMERIC\n" +
                "@ATTRIBUTE SECONDPLAYERHLTV_st NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERHS_st NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERKD_st NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERROUNDS_st NUMERIC\n" +
                "@ATTRIBUTE THIRDPLAYERHLTV_st NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERHS_st NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERKD_st NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERROUNDS_st NUMERIC\n" +
                "@ATTRIBUTE FOURTHPLAYERHLTV_st NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERHS_st NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERKD_st NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERROUNDS_st NUMERIC\n" +
                "@ATTRIBUTE FIFTHPLAYERHLTV_st NUMERIC\n" +
                "@ATTRIBUTE WINRATE_ft NUMERIC\n" +
                "@ATTRIBUTE STANDIN_ft NUMERIC\n" +
                "@ATTRIBUTE WINRATE_st NUMERIC\n" +
                "@ATTRIBUTE STANDIN_st NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_mm NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_mw NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_mr NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_dm NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_dw NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_dr NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_im NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_iw NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_ir NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_om NUMERIC \n" +
                "@ATTRIBUTE FT_MAPS_ow NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_or NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_nm NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_nw NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_nr NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_vm NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_vw NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_vr NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_am NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_aw NUMERIC\n" +
                "@ATTRIBUTE FT_MAPS_ar NUMERIC\n" +
                "@ATTRIBUTE _SecondM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _SecondM_OPPTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _SecondM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _SecondM_OPPTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SCORE_FM_S NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SCORE_SM_S NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SCORE_TM_S NUMERIC\n" +
                "@ATTRIBUTE _SecondM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _SecondM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _SecondM_DECIDER NUMERIC\n" +
                "@ATTRIBUTE _SecondM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _SecondM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _SecondM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _SecondM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _SecondM_HLTV_FIVEP NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_OPPTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_OPPTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SCORE_FM_S NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SCORE_SM_S NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SCORE_TM_S NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_DECIDER NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _ThirdM_HLTV_FIVEP NUMERIC\n" +
                "@ATTRIBUTE _FouthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _FouthM_OPPTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _FouthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _FouthM_OPPTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SCORE_FM_S NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SCORE_SM_S NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SCORE_TM_S NUMERIC\n" +
                "@ATTRIBUTE _FouthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _FouthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _FouthM_DECIDER NUMERIC\n" +
                "@ATTRIBUTE _FouthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _FouthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _FouthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _FouthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _FouthM_HLTV_FIVEP NUMERIC\n" +
                "@ATTRIBUTE _FifthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _FifthM_OPPTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _FifthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _FifthM_OPPTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SCORE_FM_S NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SCORE_SM_S NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _FifthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _FifthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _FifthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _FifthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _FifthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _FifthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _FifthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _FifthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _SixthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _SixthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _SixthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _SixthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _SixthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _SixthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _SixthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SixthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _SixthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _SixthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _SixthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _SixthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _SeventhM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _SeventhM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _EighthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _EighthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _EighthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _EighthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _EighthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _EighthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _EighthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _EighthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _EighthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _EighthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _EighthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _EighthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _NinethM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _NinethM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _NinethM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _NinethM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _NinethM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _NinethM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _NinethM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _NinethM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _NinethM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _NinethM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _NinethM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _NinethM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE _TenthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE _TenthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE _TenthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE _TenthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE _TenthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE _TenthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE _TenthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE _TenthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE _TenthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE _TenthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE _TenthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE _TenthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ST_MAPS_mm NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_mw NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_mr NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_dm NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_dw NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_dr NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_im NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_iw NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_ir NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_om NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_ow NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_or NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_nm NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_nw NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_nr NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_vm NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_vw NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_vr NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_am NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_aw NUMERIC\n" +
                "@ATTRIBUTE ST_MAPS_ar NUMERIC\n" +

                "@ATTRIBUTE SecondM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE SecondM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SecondM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE SecondM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SecondM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE SecondM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SecondM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE SecondM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SecondM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE SecondM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SecondM_FPICK NUMERIC\n" +
                "@ATTRIBUTE SecondM_SPICK NUMERIC\n" +
                "@ATTRIBUTE SecondM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SecondM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE SecondM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE SecondM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE SecondM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE SecondM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE ThirdM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE ThirdM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE ThirdM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE ThirdM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE ThirdM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_FPICK NUMERIC\n" +
                "@ATTRIBUTE ThirdM_SPICK NUMERIC\n" +
                "@ATTRIBUTE ThirdM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE ThirdM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE ThirdM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE ThirdM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE ThirdM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE ThirdM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE FouthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE FouthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE FouthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE FouthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE FouthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE FouthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE FouthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FouthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE FouthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE FouthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE FouthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE FouthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE FifthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE FifthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE FifthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE FifthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE FifthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE FifthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE FifthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE FifthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE FifthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE FifthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE FifthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE FifthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE SixthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE SixthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE SixthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE SixthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE SixthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE SixthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE SixthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SixthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE SixthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE SixthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE SixthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE SixthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE SeventhM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE SeventhM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE SeventhM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE SeventhM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE SeventhM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_FPICK NUMERIC\n" +
                "@ATTRIBUTE SeventhM_SPICK NUMERIC\n" +
                "@ATTRIBUTE SeventhM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE SeventhM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE SeventhM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE SeventhM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE SeventhM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE SeventhM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE EighthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE EighthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE EighthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE EighthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE EighthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE EighthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE EighthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE EighthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE EighthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE EighthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE EighthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE EighthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE NinethM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE NinethM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE NinethM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE NinethM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE NinethM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_FPICK NUMERIC\n" +
                "@ATTRIBUTE NinethM_SPICK NUMERIC\n" +
                "@ATTRIBUTE NinethM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE NinethM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE NinethM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE NinethM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE NinethM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE NinethM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_USTEAMSC NUMERIC\n" +
                "@ATTRIBUTE TenthM_OPPTEAMSC NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_USTEAMRANK NUMERIC\n" +
                "@ATTRIBUTE TenthM_OPPTEAMRANK NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_SCORE_FM_F NUMERIC\n" +
                "@ATTRIBUTE TenthM_SCORE_FM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_SCORE_SM_F NUMERIC\n" +
                "@ATTRIBUTE TenthM_SCORE_SM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_SCORE_TM_F NUMERIC\n" +
                "@ATTRIBUTE TenthM_SCORE_TM_S NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_FPICK NUMERIC\n" +
                "@ATTRIBUTE TenthM_SPICK NUMERIC\n" +
                "@ATTRIBUTE TenthM_DECIDER NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE TenthM_HLTV_FP NUMERIC\n" +
                "@ATTRIBUTE TenthM_HLTV_SP NUMERIC\n" +
                "@ATTRIBUTE TenthM_HLTV_TP NUMERIC\n" +
                "@ATTRIBUTE TenthM_HLTV_FOURP NUMERIC\n" +
                "@ATTRIBUTE TenthM_HLTV_FIVEP NUMERIC\n" +
                "\n" +
                "@ATTRIBUTE class {0,1,2,3}\n" +
                "\n" +
                "\n" +
                "@DATA\n");
        stringBuilder.append(lineForPredict);
        Reader inputString = new StringReader(stringBuilder.toString());
        BufferedReader reader = new BufferedReader(inputString);

        Instances instance = new Instances(reader);
        instance.setClassIndex(instance.numAttributes() - 1);

        double prediction = classifier.classifyInstance(instance.get(0));

        return prediction + "";
    }
}
